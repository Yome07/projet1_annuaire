package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	/**
     * Initialise et affiche la scène principale.
     * 
     * @param stage Fenêtre principale.
     * @throws IOException Si une erreur survient lors de la création de la scène.
     */
	@Override
	public void start(Stage stage) throws IOException {

		WireframeBasic wireframe = new WireframeBasic(new User(null, null));
		Scene scene = new Scene(wireframe, 1000, 800);

		stage.setTitle("Stud'Index");
		stage.setScene(scene);
		stage.show();
	}

    /**
     * Point d'entrée de l'application.
     */
	public static void main(String[] args) {
		launch();
	}

}