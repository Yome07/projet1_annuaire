package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        WireframeBasic wireframe = new WireframeBasic();
        Scene scene = new Scene(wireframe.root, 1000, 800);
        stage.setTitle("Stud'Index");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}