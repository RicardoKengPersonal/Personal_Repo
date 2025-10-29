package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.app.customer.console.application.CheckShowsDatesController;
import eapli.shodrone.showmanagement.dto.ShowDTO;

import java.util.List;

public class CheckShowsDatesUI extends AbstractUI {

    private final CheckShowsDatesController controller = new CheckShowsDatesController();

    @Override
    protected boolean doShow() {
        List<ShowDTO> scheduledShows = controller.getAllShows();

        if (scheduledShows.isEmpty()) {
            System.out.println("No scheduled shows available.");
        } else {
            displayShows(scheduledShows, "Scheduled Shows:");
        }

        return false;
    }

    private void displayShows(List<ShowDTO> shows, String header) {
        System.out.println("\n" + header);
        for (ShowDTO dto : shows) {
            System.out.println("- " + dto);
        }
    }

    @Override
    public String headline() {
        return "Check Scheduled Shows";
    }
}
