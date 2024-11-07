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
        ListInternEmpty listeVide = new ListInternEmpty(); 
        ServiceNodeList test = new ServiceNodeList();
//        test.createListAlpha(0);
        test.readList(test.createListAlpha(0));
        Scene scene = listeVide.createScene(); 
        stage.setTitle("Stud'Index");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}