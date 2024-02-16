package bureau.visitorbureau;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BureauApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        primaryStage.setTitle("Monroe-West Monroe Visitor Bureau");
        primaryStage.setScene(new Scene(root, 803, 610));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}