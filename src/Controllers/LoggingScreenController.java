package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Net.LogClient;

import java.io.IOException;
/**
 * @author Jacek Polak
 * @since 10.10.16r
 * @version 2.0
 * Klasa kontrolera ekranu rejestracji klienta na serwerze RMI
 */
public class LoggingScreenController {

    //region variables
    @FXML private TextField ip_textfield;
    @FXML private TextField port_textfield;
    @FXML private Label error_label;
    @FXML private CheckBox localhost_checkbox;
    @FXML private Button connect_button;
    @FXML private Button exit_button;
    //endregion

    /**
     * Metoda obsługująca przycisk Connect, rejestruje klienta na serwerze na określonym porcie
     * @param event
     * @throws IOException
     */
    @FXML
    private void connectButtonAction(ActionEvent event) throws IOException {
        Parent clientScreen = FXMLLoader.load(getClass().getResource("../Model/clientScreen.fxml"));
        Scene clientScene = new Scene(clientScreen);
        Stage clientStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //ZGODNIE Z KONWENCJĄ
        if (port_textfield.getText().equals("1099") || port_textfield.getText().equals("1100")){
            try{
                LogClient client = new LogClient(ip_textfield.getText(), port_textfield.getText());
                clientStage.setTitle("Client");
                clientStage.setScene(clientScene);
                clientStage.show();

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        else{
            ip_textfield.clear();
            port_textfield.clear();
            error_label.setText("Invalid IP or port! Try again.");
        }
    }

    /**
     * Metoda obsługująca CheckBox
     * @param event
     */
    @FXML
    private void handleCheckBox(ActionEvent event){
        if(localhost_checkbox.isSelected()){
            ip_textfield.setText("127.0.0.1");
            port_textfield.setText("1099");
            ip_textfield.setEditable(false);
            port_textfield.setEditable(false);
        }
        else{
            ip_textfield.clear();
            port_textfield.clear();
            ip_textfield.setEditable(true);
            port_textfield.setEditable(true);
        }
    }

    /**
     * Metoda obsługująca przycisk zakończenia pracy klienta
     * @param event
     */
    @FXML
    private void exitButtonAction(ActionEvent event){
        System.out.println("Zamykam program...");
        System.exit(0);
    }

}
