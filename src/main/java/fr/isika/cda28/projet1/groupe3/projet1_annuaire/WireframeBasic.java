package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class WireframeBasic {

//	---- COLORS ----
//	F87A53 orange
//	E6C767 sable
//	898121 kaki
//	4C4B16 kaki foncé

	protected BorderPane root;
	protected BorderPane informationsDisplay;

	public WireframeBasic() {

		// Pane Root
		root = new BorderPane();

		// Components
		// NavBarMenu
		HBox navBarMenu = new HBox();
		navBarMenu.setStyle("-fx-background-color: #898121;");
		navBarMenu.setAlignment(Pos.CENTER);
		Button home = new Button("Accueil");
		Button handleUsers = new Button("Gestion des utilisateurs");
		Button searchInterns = new Button("Recherche de stagiaires");
		Button internsList = new Button("Liste des stagiaires");
		Button addInterns = new Button("Ajout de stagiaire");

		Button[] buttonNavBarMenu = { home, handleUsers, searchInterns, internsList, addInterns };
		for (Button button : buttonNavBarMenu) {
			button.setMinSize(140, 65);
			navBarMenu.setMargin(button, new Insets(25));
			button.setStyle("-fx-background-color: #E6C767; -fx-font-size: 16;");
			button.setWrapText(true); // to center

		}

		navBarMenu.getChildren().addAll(home, handleUsers, searchInterns, internsList, addInterns);

		// Display area
		AnchorPane areaDisplay = new AnchorPane();

		informationsDisplay = new BorderPane();
		informationsDisplay.setMinWidth(630);
		informationsDisplay.setStyle("-fx-background-color: #E6C767;");

		// Logo Stud'Index

		Circle logo = new Circle(65);

		Image logoDisplay = new Image(getClass().getResourceAsStream("ressources/logo.png"));

		logo.setFill(new ImagePattern(logoDisplay));

		// disposition of the anchorPane
		areaDisplay.getChildren().addAll(logo, informationsDisplay);
		areaDisplay.setLeftAnchor(logo, 30.00);
		areaDisplay.setTopAnchor(logo, 30.00);

		areaDisplay.setTopAnchor(informationsDisplay, 15.00);
		areaDisplay.setBottomAnchor(informationsDisplay, 71.00);
		areaDisplay.setLeftAnchor(informationsDisplay, 189.00);

		// Add components to the pane
		root.setTop(navBarMenu);
		root.setCenter(areaDisplay);

		// layout mac
		root.setStyle("-fx-font-family: 'Proxima Nova'");
	}

	public Scene createScene() {
		return new Scene(root, 1000, 800);
	}

}
