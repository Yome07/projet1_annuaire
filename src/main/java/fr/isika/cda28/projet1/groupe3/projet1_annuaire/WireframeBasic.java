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

public class WireframeBasic  {
	
	public Scene createScene() {

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		---- COLORS ----
//		F87A53 orange
//		E6C767 sable
//		898121 kaki
//		4C4B16 kaki foncé

		// 1. Title
//		primaryStage.setTitle("Stud'Index");

		// 2. Pane Root
		BorderPane root = new BorderPane();

		// 3. Components
		// 3.1 NavBarMenu
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

		// 3.2 Display area
		AnchorPane areaDisplay = new AnchorPane();

		Pane informationsDisplay = new Pane();
		informationsDisplay.setMinWidth(630);
		informationsDisplay.setStyle("-fx-background-color: #E6C767;");

		// 3.3 Logo Stud'Index
		Circle logo = new Circle(65);
		Image logoDisplay = new Image("file:///Users/mariannelavergne/Desktop/ISIKA/Eclipse%20/Projet1/projet1_annuaire/src/main/java/fr/isika/cda28/projet1/groupe3/projet1_annuaire/FOR%20MANAGING%20INTERNS.png");
		logo.setFill(new ImagePattern(logoDisplay));
		

		// 3.4 disposition of the anchorPane
		areaDisplay.getChildren().addAll(logo, informationsDisplay);
		areaDisplay.setLeftAnchor(logo, 30.00);
		areaDisplay.setTopAnchor(logo, 30.00);

		areaDisplay.setTopAnchor(informationsDisplay, 15.00);
		areaDisplay.setBottomAnchor(informationsDisplay, 71.00);
		areaDisplay.setLeftAnchor(informationsDisplay, 189.00);

		// 4. Add components to the pane
		root.setTop(navBarMenu);
		root.setCenter(areaDisplay);

		// layout mac
		root.setStyle("-fx-font-family: 'Proxima Nova'");
		// 5. Set scene with the pane (root) and size
		return new Scene(root, 1000, 800); 

		// 6. Set the scene to the stage
//		primaryStage.setScene(scene);
//
//		// 7. Show stage
//		primaryStage.show();
		
		
}

//	public static void main(String[] args) {
//		launch();
//
//	}

}
