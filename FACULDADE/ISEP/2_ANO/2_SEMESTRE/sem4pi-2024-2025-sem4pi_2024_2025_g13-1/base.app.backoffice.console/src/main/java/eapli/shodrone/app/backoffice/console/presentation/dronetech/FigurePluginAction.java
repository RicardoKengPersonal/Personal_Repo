package eapli.shodrone.app.backoffice.console.presentation.dronetech;
import eapli.framework.actions.Action;
public class FigurePluginAction implements Action{
    public boolean execute() { return new FigurePluginUI().show(); }
}
