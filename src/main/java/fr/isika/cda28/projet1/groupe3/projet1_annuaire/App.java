package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {

		ListInternEmpty listeVide = new ListInternEmpty();
		ServiceNodeList serviceNodeList = new ServiceNodeList();
		List interns = new ArrayList();
		InternsTableView internsTableView = new InternsTableView(interns);

		Scene scene = internsTableView.createScene();

		stage.setTitle("Stud'Index");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}