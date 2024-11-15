package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.FileChecker;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.ServiceNodeList;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.User;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.view.ConnectedInternsTableView;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.view.ConnectionView;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.view.InternsTableView;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.view.ViewAddIntern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.view.ViewListInternEmpty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class WireframeBasic extends BorderPane {

//	---- COLORS ----
//	F87A53 orange
//	E6C767 sable
//	898121 kaki
//	4C4B16 kaki foncé

	// ******************************
	// Attribute
	// ******************************

	protected BorderPane informationsDisplay;
	User user;
	ConnectionView connectionView;

	// ******************************
	// Constant
	// ******************************

	public User getUser() {
		return user;
	}

	public WireframeBasic(User user) {

		this.user = user;

		// ----- COMPONENTS -----
		// NavBarMenu
		HBox navBarMenu = new HBox();
		navBarMenu.setStyle("-fx-background-color: #898121;");
		navBarMenu.setAlignment(Pos.CENTER);
		Button home = new Button("Accueil");
		Button handleUsers = new Button("Gestion des utilisateurs");
		Button searchInterns = new Button("Recherche de stagiaires");
		Button internsList = new Button("Liste des stagiaires");
		Button connectedInternsList = new Button("Liste des stagiaires");
		Button addInterns = new Button("Ajout de stagiaire");
		Button connection = new Button("Connexion");

		Button[] buttonNavBarMenu = { home, handleUsers, searchInterns, internsList, addInterns, connection,
				connectedInternsList };
		for (Button button : buttonNavBarMenu) {
			button.setMinSize(140, 65);
			navBarMenu.setMargin(button, new Insets(25));
			button.setStyle("-fx-background-color: #E6C767; -fx-font-size: 16;");
			button.setWrapText(true); // to center

		}
		if (user.getConnected() == true) {
			navBarMenu.getChildren().addAll(home, handleUsers, searchInterns, connectedInternsList, addInterns);
		} else {
			navBarMenu.getChildren().addAll(home, handleUsers, searchInterns, internsList, connection);

		}

		// Display area
		AnchorPane areaDisplay = new AnchorPane();

		informationsDisplay = new BorderPane();
		informationsDisplay.setMinWidth(630);
		informationsDisplay.setStyle("-fx-background-color: #E6C767;");

		// Logo Stud'Index
		Circle logo = new Circle(65);
//		Image logoDisplay = new Image(getClass().getResourceAsStream("ressources/logo.png"));
//		logo.setFill(new ImagePattern(logoDisplay));

		// Default content
		Label defaultContent = new Label("Bienvenue sur Stud'Index !");

		informationsDisplay.setCenter(defaultContent);

		// layout anchorPane
		areaDisplay.getChildren().addAll(logo, informationsDisplay);
		areaDisplay.setLeftAnchor(logo, 30.00);
		areaDisplay.setTopAnchor(logo, 30.00);

		areaDisplay.setTopAnchor(informationsDisplay, 15.00);
		areaDisplay.setBottomAnchor(informationsDisplay, 71.00);
		areaDisplay.setLeftAnchor(informationsDisplay, 189.00);

		// Add components to the pane
		this.setTop(navBarMenu);
		this.setCenter(areaDisplay);

		// layout mac
		this.setStyle("-fx-font-family: 'Proxima Nova'");

		// -----NAVIGATION -----

		// button to Home
		home.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				ViewHome viewHome = new ViewHome();
//				informationsDisplay.setCenter(viewHome.modScene());
			}
		});

		// button to HandleUsers
		handleUsers.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				ViewHandleUsers viewHandleUsers = new ViewHandleUsers();
//				informationsDisplay.setCenter(viewHandleUsers.modScene());
			}
		});

		// button to SearchInterns
		searchInterns.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				ViewSearchInterns viewSearchInterns = new ViewSearchInterns();
//				informationsDisplay.setCenter(viewSearchInterns.modScene());
			}
		});

		// button to ViewInternsList
		internsList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				/*
				 * Test pour savoir si la liste de stagiaires est vide ou non à faire
				 */

				boolean binFileExists = FileChecker.isBinFilePresent();
				if (binFileExists) {
					try {
						informationsDisplay.setCenter(new InternsTableView().modScene());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					informationsDisplay.setCenter(new ViewListInternEmpty().modScene());
				}

			}
		});

		// button to ViewInternsList
		connectedInternsList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				/*
				 * Test pour savoir si la liste de stagiaires est vide ou non à faire
				 */

				boolean binFileExists = FileChecker.isBinFilePresent();
				if (binFileExists) {
					try {
						informationsDisplay.setCenter(new ConnectedInternsTableView().modScene());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					informationsDisplay.setCenter(new ViewListInternEmpty().modScene());
				}

			}
		});

		// button t oAddInterns
		addInterns.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ViewAddIntern viewAddInterns = new ViewAddIntern();
				informationsDisplay.setCenter(viewAddInterns.modScene());
			}
		});

		// button to connection
		connection.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ConnectionView connectionView = new ConnectionView();
				informationsDisplay.setCenter(connectionView.modScene());
			}
		});
	}

	// ******************************
	// Getter & Setter
	// ******************************

	public void setUser(User user) {
		this.user = user;
	}

	// ******************************
	// Public Method
	// ******************************

	public Scene createScene() {
		return new Scene(this, 1000, 800);
	}

}
