package Controllers;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Net.LogClient;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Klasa kontrolera panelu klienta
 * @author Jacek Polak
 * @since 10.10.16r
 * @version 2.0
 */
public class clientScreenController implements Initializable{

    //region COMPONENTS
    @FXML AnchorPane up_pane;
    @FXML AnchorPane down_pane;
    @FXML MenuButton type_menu;
    @FXML Button insert_button;
    @FXML Button refresh_button;
    @FXML Button plus_button;
    @FXML TextField textfield1;
    @FXML TextField textfield2;
    @FXML TextField textfield3;
    @FXML TextField textfield4;
    @FXML TextField textfield5;
    @FXML TextField textfield6;
    @FXML TextField textfield7;
    @FXML TextField textfield8;
    @FXML TextField textfield9;
    @FXML TextField textfield10;
    @FXML TextField textfield11;
    @FXML TextField textfield12;
    @FXML TextField textfield13;
    @FXML TextField textfield14;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML Label label6;
    @FXML Label label7;
    @FXML Label label8;
    @FXML Label label9;
    @FXML Label label10;
    @FXML Label label11;
    @FXML Label label12;
    @FXML Label label13;
    @FXML Label label14;
    //endregion
    //region VARIABLES
    public int lol = 0;
    public static HashMap<String,String[]> fields = new HashMap<>();
    //endregion

    /**
     * Metoda dodająca do listy wyboru typów nowododany do bazy danych typ
     * @param logName Nazwa typu logu
     * @param params Nazwy jego kolumn
     */
    public void addMenuItem(String logName, String[] params){
        MenuItem item = new MenuItem(logName);
        item.setOnAction(e -> {
            for (Node node : down_pane.getChildren()){
                if (node instanceof Label){
                    node.setVisible(false);
                }
                else if(node instanceof TextField){
                    node.setVisible(false);
                    ((TextField)node).clear();
                }
            }
                for (Node node : down_pane.getChildren()) {
                    if (node instanceof Label) {
                        if (!(node).isVisible()) {
                            node.setVisible(true);
                            ((Label) node).setText(params[lol]);
                            lol++;
                            if(lol == params.length) break;
                        }
                    }
                }
            lol = 0;
            for (Node node : down_pane.getChildren()) {
                if (node instanceof TextField) {
                    if (!(node).isVisible()) {
                        node.setVisible(true);
                        lol++;
                        if(lol == params.length) break;
                    }
                }
            }
            lol = 0;
            type_menu.setText(logName);
        });
        type_menu.getItems().add(item);
        }

    /**
     * Metoda odpowiedzialna za obsługę przycisku odświeżania
     * @param event
     */
    public void refreshButtonAction(ActionEvent event){
        for (String key : fields.keySet()) {
            boolean finished = false;
                for(MenuItem item: type_menu.getItems()){
                    if(key.equals(item.getText())){
                        finished = true;
                    }
                }
                if(!finished) addMenuItem(key,fields.get(key));
            }
    }

    /**
     * Metoda uruchamiająca dodatkowe pole tekstowe
     * @param event
     * @throws IOException
     */
    public void plusButtonAction(ActionEvent event) throws IOException{
        Parent newTypeScreen = FXMLLoader.load(getClass().getResource("../Model/newType.fxml"));
        Scene newTypeScene = new Scene(newTypeScreen,251,303);
        Stage newTypeStage = new Stage();
        newTypeStage.setTitle("Add new type");
        newTypeStage.setScene(newTypeScene);
        newTypeStage.show();

    }

    /**
     * Metoda inicjalizująca panel klienta
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] simple_entry = {"comment"};
        fields.put("simpleentry",simple_entry);
        addMenuItem("simpleentry",simple_entry);
    }

    /**
     * Metoda obsługująca przycisk Insert, dodaje do bazy danych nowy wpis
     * @param event
     */
    public void insertButtonAction(ActionEvent event){
        ArrayList<String> list = new ArrayList<>();
        for(Node node: down_pane.getChildren()) {
            if(node instanceof TextField) {
                if(!((TextField) node).getText().equals("")){
                    list.add(((TextField) node).getText());
                }

            }
        }
        if (list.isEmpty()){
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("A field is empty!");
            dialog.setContentText("Fill the empty field");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(300, 150);
            dialog.showAndWait();
            return;
        }

        String[] cols = list.stream().toArray(String[]::new);
        try{
            //WPIS DO BAZY DANYCH
            LogClient.logInt.insertIntoDB(type_menu.getText(), cols);
            //OBSŁUGA OKIENKA DIALOGOWEGO
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Adding succeeded");
            dialog.setContentText("Log added to database!");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(300, 150);
            dialog.showAndWait();
        }
        catch (RemoteException e){
            //OBSŁUGA OKIENKA DIALOGOWEGO
            Alert dialog = new Alert(Alert.AlertType.ERROR,"Confirm", ButtonType.OK, ButtonType.CANCEL);
            dialog.setHeaderText("Adding succeeded");
            dialog.setContentText("New type has been added succesfully!");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(300, 150);
            dialog.showAndWait();
            System.err.println(e.getMessage());
        }

    }

}


