package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/sample.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Tres En Raya");
        primaryStage.setResizable(false);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");

        Controller controller = loader.getController();
        controller.setScene(scene);
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

