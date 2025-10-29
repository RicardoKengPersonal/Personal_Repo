package eapli.shodrone.app.customerappserver.tcp;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.shodrone.usermanagement.domain.ShodronePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.app.customer.console.application.ProposalDocumentGeneratorService;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showmanagement.domain.Show;
import eapli.shodrone.showmanagement.domain.ShowID;
import eapli.shodrone.showmanagement.repository.ShowRepository;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalStatus;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.domain.ShowRequest;
import eapli.shodrone.showrequestmanagement.domain.Time;

import java.io.*;
import java.net.Socket;
import java.util.List;

import static eapli.shodrone.showmanagement.domain.Show.createShowFromProposal;

public class CustomerCommandHandler implements Runnable {

    private final Socket socket;
    private final ShowProposalRepository proposalRepo;
    private SystemUser authenticatedUser;

    public CustomerCommandHandler(Socket socket, ShowProposalRepository proposalRepo) {
        this.socket = socket;
        this.proposalRepo = proposalRepo;
        this.authenticatedUser = null;
    }

    @Override
    public void run() {
        try (
                InputStream rawIn = socket.getInputStream();
                DataInputStream in = new DataInputStream(new BufferedInputStream(rawIn));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // O primeiro passo DEVE ser a autenticação
            if (!handleAuthentication(in, out)) {
                out.println("ERROR: Authentication failed. Closing connection.");
                return; // Fecha a ligação se a autenticação falhar
            }

            String request;
            while ((request = readLine(in)) != null && !request.equalsIgnoreCase("EXIT")) {
                String[] parts = request.trim().split(" ", 2); // Dividir em comando e argumentos
                String command = parts[0].toUpperCase();

                switch (command) {
                    case "GET_PROPOSAL":
                        handleGetProposal(parts.length > 1 ? parts[1] : "", out);
                        break;
                    case "LIST_SHOWS":
                        handleListShows(out);
                        break;
                    case "REJECT_PROPOSAL":
                        handleRejectProposal(parts.length > 1 ? parts[1] : "", out);
                        break;
                    case "ACCEPT_PROPOSAL":
                        handleAcceptProposal(parts.length > 1 ? parts[1] : "", out);
                        break;
                    case "GET_SHOW_INFO":
                        handleGetShowInfo(parts.length > 1 ? parts[1] : "", out);
                        break;

                    default:
                        out.println("Invalid or malformed command.");
                }
            }
        } catch (IOException e) {
            System.err.println("Client disconnected or error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {

            }
        }
    }

    private boolean handleAuthentication(DataInputStream in, PrintWriter out) throws IOException {
        String authRequest = readLine(in);
        if (authRequest == null || authRequest.trim().isEmpty()) {
            return false;
        }

        String[] parts = authRequest.trim().split(" ", 3);

        if (parts.length != 3 || !parts[0].equalsIgnoreCase("AUTH")) {
            out.println("ERROR: Malformed AUTH command.");
            return false;
        }

        String username = parts[1];
        String password = parts[2];

        // 1. Configure the Authorization Registry
        AuthzRegistry.configure(
                PersistenceContext.repositories().users(),
                new ShodronePasswordPolicy(),
                new PlainTextEncoder()
        );

        // 2. Get the required service from the registry
        AuthorizationService authorizationService = AuthzRegistry.authorizationService();

        // 3. Create the AuthenticationService with the correct constructor signature
        AuthenticationService authz = new AuthenticationService(
                PersistenceContext.repositories().users(),
                authorizationService,
                new ShodronePasswordPolicy(),
                new PlainTextEncoder()
        );

        var userSession = authz.authenticate(username, password);

        if (userSession.isPresent()) {
            this.authenticatedUser = userSession.get().authenticatedUser();
            out.println("AUTH_SUCCESS");
            return true;
        } else {
            out.println("AUTH_FAIL");
            return false;
        }
    }

    private void handleAcceptProposal(String accessCode, PrintWriter out) {

        final ShowProposalRepository proposalRepo = PersistenceContext.repositories().showProposalRepository();
        final ShowRepository showRepo = PersistenceContext.repositories().showRepository();

        try {

            ShowProposal proposal = proposalRepo.findByAccessCode(accessCode)
                    .orElseThrow(() -> new IllegalArgumentException("Proposal not found for the given access code."));


            if (proposal.status() != ShowProposalStatus.SENT) {
                throw new IllegalStateException("This proposal cannot be accepted. Current status: " + proposal.status());
            }

            ShowRequest request = proposal.showRequest();
            Date date = request.eventDate();
            Time time = request.eventTime();

            proposal.updateStatus(ShowProposalStatus.ACCEPTED_CUSTOMER);
            proposalRepo.save(proposal);
            System.out.println("Proposal marked as ACCEPTED.");

            Show show = createShowFromProposal(proposal, request, date, time);
            showRepo.save(show);

            // Enviar a confirmação para o cliente
            System.out.println("Show created successfully.");
            out.printf("ID: %s\n", show.identity());
            out.printf("Date: %s\n", show.scheduledDate());
            out.printf("Time: %s\n", show.scheduledTime());
            out.printf("Address: %s\n", show.request().address().toString().replaceAll("[\\r\\n]", " "));
            out.printf("Status: %s\n", show.status().name());

            out.println("---END---");

        } catch (Exception e) {
            // Enviar uma mensagem de erro clara para o cliente
            out.println("ERROR: " + e.getMessage());
            System.err.println("Error accepting proposal: " + e.getMessage()); // Log no servidor
        }
    }

    private void handleRejectProposal(String accessCode, PrintWriter out) {
        try {
            ShowProposal proposal = proposalRepo.findByAccessCode(accessCode)
                    .orElseThrow(() -> new IllegalArgumentException("Proposal not found or access code is invalid."));

            if (proposal.status() != ShowProposalStatus.SENT) {
                throw new IllegalStateException("This proposal cannot be rejected. Current status: " + proposal.status());
            }

            proposal.updateStatus(ShowProposalStatus.DECLINED);
            proposalRepo.save(proposal);

            out.println("SUCCESS: Proposal has been marked as DECLINED.");

        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
        }
    }

    private void handleGetProposal(String accessCode, PrintWriter out) {
        try {
            ShowProposal proposal = proposalRepo.findByAccessCode(accessCode)
                    .orElseThrow(() -> new IllegalArgumentException("Proposal not found or access code is invalid."));

            ProposalDocumentGeneratorService generator = new ProposalDocumentGeneratorService();
            String documentContent = generator.generateDocumentAsString(proposal, proposal.templateIdentifier());

            // Envia o conteúdo do documento para o cliente
            out.println(documentContent);

            // Envia o sinal de fim de mensagem
            out.println("---END---");
            out.flush(); // Garante que tudo é enviado imediatamente

        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
            // Envia o sinal de fim de mensagem mesmo em caso de erro
            out.println("---END---");
            out.flush();
        }
    }

    private void handleListShows(PrintWriter out) {
        if (this.authenticatedUser == null) {
            out.println("ERROR: Not authenticated.");
            return;
        }

        String userEmail = this.authenticatedUser.email().toString();

        try {
            ShowRepository showRepo = PersistenceContext.repositories().showRepository();
            List<Show> shows = showRepo.findByCustomerRepresentativeEmail(userEmail);

            for (Show show : shows) {
                out.printf("%s;%s;%s;%s;%s%n",
                        show.identity(),
                        show.scheduledDate(),
                        show.scheduledTime(),
                        show.request().address().toString().replaceAll("[\\r\\n]", " "),
                        show.status().name());
            }
            out.println("---END---");

        } catch (Exception e) {
            out.println("Error listing shows: " + e.getMessage());
        }
    }
    private void handleGetShowInfo(String showIdArg, PrintWriter out) {
        if (this.authenticatedUser == null) {
            out.println("ERROR: Not authenticated.");
            return;
        }

        String userEmail = this.authenticatedUser.email().toString();

        try {
            if (showIdArg == null || showIdArg.trim().isEmpty()) {
                out.println("ERROR: Show ID is missing.");
                return;
            }

            int showId = Integer.parseInt(showIdArg.trim());

            ShowRepository showRepo = PersistenceContext.repositories().showRepository();
            Show show = showRepo.ofIdentity(ShowID.valueOf(showId)).orElse(null);

            if (show == null || !show.request().representative().email().toString().equalsIgnoreCase(userEmail)) {
                out.println("NOT_FOUND");
                return;
            }

            String id = show.identity().toString();
            String date = show.scheduledDate().toString();
            String time = show.scheduledTime().toString();
            String address = show.request().address().toString().replaceAll("[\\r\\n]", " ");
            String status = show.status().name();
            String duration = show.request().duration().toString();
            String description = show.request().description().toString().replaceAll("[\\r\\n]", " ");

            ShowProposal proposal = show.proposal();

            String figures = proposal.figureEntries().stream()
                    .map(f -> f.figure().toString())
                    .reduce((a, b) -> a + "," + b)
                    .orElse("N/A");

            String drones = proposal.getDroneFleet().entrySet().stream()
                    .map(e -> e.getKey().name() + " x" + e.getValue())
                    .reduce((a, b) -> a + "," + b)
                    .orElse("N/A");

            out.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s%n",
                    id, date, time, address, status, duration, description, figures, drones);

        } catch (Exception e) {
            out.println("NOT_FOUND");
        }
    }




    private String readLine(DataInputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = in.read()) != -1) {
            if (c == '\n') break;
            sb.append((char) c);
        }
        return sb.toString();
    }
}
