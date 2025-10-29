package eapli.shodrone.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
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


public class ShowBootstrapper implements Action {

    @Override
    public boolean execute() {
        final ShowProposalRepository proposalRepo = PersistenceContext.repositories().showProposalRepository();
        final ShowRepository showRepo = PersistenceContext.repositories().showRepository();

        try {
            // Buscar proposta com ID 2
            ShowProposalID id = new ShowProposalID(2);
            ShowProposal proposal = proposalRepo.ofIdentity(id)
                    .orElseThrow(() -> new IllegalArgumentException("Proposta com ID 2 não encontrada."));

            // Forçar o carregamento do ShowRequest dentro da sessão activa
            ShowRequest request = proposal.showRequest();
            Date date = request.eventDate();
            Time time = request.eventTime();


            // Marcar como aceite se necessário
            if (proposal.status() != ShowProposalStatus.ACCEPTED_CUSTOMER) {
                proposal.updateStatus(ShowProposalStatus.ACCEPTED_CUSTOMER);
                proposalRepo.save(proposal);
                System.out.println("✅ Proposta marcada como ACCEPTED.");
            }

            // Criar Show directamente
            Show show = createShowFromProposal(proposal, request, date, time);
            showRepo.save(show);

            System.out.println("✅ Show criado com sucesso: " + show);
            return true;

        } catch (Exception e) {
            System.out.println("⚠ Erro ao criar Show: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Show createShowFromProposal(ShowProposal proposal, ShowRequest request, Date date, Time time) {
        if (proposal.status() != ShowProposalStatus.ACCEPTED_CUSTOMER) {
            throw new IllegalArgumentException("A proposta ainda não está aceite.");
        }

        return new Show(ShowID.valueOf(1), proposal, request, date, time);
    }
}
