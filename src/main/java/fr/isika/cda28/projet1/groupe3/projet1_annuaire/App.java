package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

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



	@Override
	public void start(Stage stage) {

		WireframeBasic wireframe = new WireframeBasic(new User(null, null));
		Scene scene = new Scene(wireframe, 1000, 800);


		stage.setTitle("Stud'Index");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}