package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import Net.LogClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Controllers.clientScreenController.*;
/**
 * Klasa kontrolera ekranu dodawania nowego typu logu do bazy danych
 * @author Jacek Polak
 * @since 10.10.16r
 * @version 2.0
 */
public class newTypeController implements Initializable{

    //region fields
    @FXML AnchorPane pane;
    @FXML Button create_button;
    @FXML TextField field1;
    @FXML TextField field2;
    @FXML TextField field3;
    @FXML TextField field4;
    @FXML TextField field5;
    @FXML TextField field6;
    @FXML TextField field7;
    @FXML TextField field8;
    @FXML TextField field9;
    @FXML TextField field10;
    @FXML TextField field11;
    @FXML TextField field12;
    @FXML TextField field13;
    @FXML TextField field14;
    @FXML TextField name_textfield;
    //endregion
    private ArrayList<String> nameCols = new ArrayList<>();

    /**
     * Metoda dodająca nowy typ logu do bazy danych
     * @param event
     */
    public void createButtonAction(ActionEvent event){
        for( Node node: pane.getChildren()) {
            if(node instanceof TextField) {
                if(!((TextField) node).getText().equals("")){
                    nameCols.add(((TextField) node).getText());
                }
            }
        }
        String tempName = nameCols.get(0);
        nameCols.remove(0);
        String[] strings = nameCols.stream().toArray(String[]::new);
        for(String s : fields.keySet()){
            if (s.toLowerCase().equals(tempName.toLowerCase())){
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("Adding error");
                dialog.setContentText("There is a log type with that name!");
                dialog.setResizable(true);
                dialog.getDialogPane().setPrefSize(300, 150);
                dialog.showAndWait();
                nameCols.clear();
                return;
            }
        }
        fields.put(tempName,strings);
        try{
            LogClient.logInt.createNewTypeDB(tempName,strings);
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Adding succeeded");
            dialog.setContentText("New type has been added succesfully!");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(300, 150);
            dialog.showAndWait();
            nameCols.clear();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * Metoda dodająca nowe pole tekstowe
     * @param event
     */
    public void newColAction(ActionEvent event){
        for( Node node: pane.getChildren()) {
            if(node instanceof TextField) {
                if(!(node).isVisible()){
                    node.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metoda inicjalizująca działanie
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
