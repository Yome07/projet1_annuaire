package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ListInternEmpty extends WireframeBasic {

	public ListInternEmpty() {
		super();
		modScene();
	}

	public void modScene() {

		Label test = new Label("Hello John Doe");
		test.setLayoutX(20); 
		test.setLayoutY(20);
		informationsDisplay.getChildren().add(test);


	}

}
