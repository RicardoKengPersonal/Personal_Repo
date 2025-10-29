package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the main menu GUI.
 */
public class MainMenuGUI {

    /**
     * Handles the action when the login button is clicked.
     *
     * @param event the ActionEvent triggered by the login button
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            if (stage != null) {
                try {
                    Parent loginRoot = FXMLLoader.load(getClass().getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/Login.fxml"));
                    stage.setScene(new Scene(loginRoot, 400, 300));
                    stage.setTitle("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Stage is null.");
            }
        } else {
            System.out.println("Scene is null.");
        }
    }

    public void handleDevTeamButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Development Team");
        alert.setHeaderText(null);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("Nuno Teixeira - 1231375@isep.ipp.pt\n" +
                "Mariana Sousa - 1230792@isep.ipp.pt\n" +
                "Marta Domingues - 1231183@isep.ipp.pt\n" +
                "Ricardo Keng - 1231500@isep.ipp.pt");

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}
