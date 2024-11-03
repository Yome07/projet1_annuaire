package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ListInternEmpty extends WireframeBasic {

	public ListInternEmpty() {
		super();
		modScene();
	}

	public void modScene() {
		
		Button uploadListInterns = new Button("Charger une listede statiaires");
		uploadListInterns.setMinSize(140, 65);
		uploadListInterns.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		uploadListInterns.setWrapText(true); // to center
		
//		Label test = new Label("Hello John Doe");
		uploadListInterns.setLayoutX(20); 
		uploadListInterns.setLayoutY(20);
		informationsDisplay.getChildren().add(uploadListInterns);


	}

}
