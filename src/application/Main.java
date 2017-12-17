package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("../TestBouton.fxml"));
            page.getStylesheets().add("../Interface.css");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("InstrumentVirtuel");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}