package eapli.shodrone.app.customer.console.application;
import eapli.shodrone.showmanagement.dto.ShowExtendedDTO;


public class GetShowInfoController {
    private final GetShowInfoService service=new GetShowInfoService();

    public ShowExtendedDTO getShowById(String showId) {
        return service.getShowInfoById(showId);
    }
}
