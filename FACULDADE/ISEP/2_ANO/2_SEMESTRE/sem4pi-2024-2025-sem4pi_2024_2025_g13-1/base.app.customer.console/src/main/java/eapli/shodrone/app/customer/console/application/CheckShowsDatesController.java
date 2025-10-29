package eapli.shodrone.app.customer.console.application;

import eapli.shodrone.showmanagement.dto.ShowDTO;

import java.util.List;

public class CheckShowsDatesController {

    private final CheckShowDatesService checkShowDatesService = new CheckShowDatesService();

    public List<ShowDTO> getAllShows() {
        return checkShowDatesService.listMyScheduledShows();
    }
}

