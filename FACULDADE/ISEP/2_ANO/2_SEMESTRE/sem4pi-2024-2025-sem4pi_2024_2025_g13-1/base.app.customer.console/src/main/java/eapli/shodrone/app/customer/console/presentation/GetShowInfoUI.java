package eapli.shodrone.app.customer.console.presentation;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.shodrone.app.customer.console.application.GetShowInfoController;
import eapli.shodrone.showmanagement.dto.ShowExtendedDTO;


public class GetShowInfoUI extends AbstractUI {

    private final GetShowInfoController controller = new GetShowInfoController();

    @Override
    protected boolean doShow() {

        final String showId = Console.readLine("Enter the Show ID:");

        ShowExtendedDTO show = controller.getShowById(showId);

        if (show == null) {
            System.out.println("Show not found or you do not have access to it.");
        } else {
            System.out.printf(
                    "ID: %s%nDate: %s%nTime: %s%nAddress: %s%nStatus: %s%nDuration: %s%nDescription: %s%nFigures: %s%nDrones: %s%n",
                    show.getId(), show.getDate(), show.getTime(), show.getAddress(),
                    show.getStatus(), show.getDuration(), show.getDescription(),
                    show.getFigures(), show.getDrones()
            );
        }

        return false;
    }

    @Override
    public String headline() {
        return "View Detailed Information of a Show";
    }
}

