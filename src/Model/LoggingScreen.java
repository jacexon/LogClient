package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoggingScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image icon = new Image(getClass().getResourceAsStream("../Model/key.png"));
        primaryStage.getIcons().add(icon);
        Parent root = FXMLLoader.load(getClass().getResource("../Model/LoggingScreen.fxml"));
        primaryStage.setTitle("Authorization");
        primaryStage.setScene(new Scene(root, 450, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
