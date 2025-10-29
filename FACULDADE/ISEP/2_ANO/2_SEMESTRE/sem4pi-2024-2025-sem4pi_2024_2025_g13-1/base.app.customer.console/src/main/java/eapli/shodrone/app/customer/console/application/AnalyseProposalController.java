package eapli.shodrone.app.customer.console.application;

public class AnalyseProposalController {

    private final AnalyseProposalService service = new AnalyseProposalService();

    /**
     * Solicita a análise de uma proposta através do serviço.
     *
     * @param accessCode O código de acesso da proposta.
     * @return O conteúdo do documento da proposta.
     */
    public String analyseProposal(String accessCode) {
        return service.getProposalByAccessCode(accessCode);
    }
}