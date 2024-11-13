package fr.isika.cda28.projet1.groupe3.projet1_annuaire.view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.WireframeBasic;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.FileChecker;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConnectionView extends WireframeBasic {

	// ******************************
	// Attributes
	// ******************************

	VBox infoVBox = new VBox(5);
	Label errorIsEmpty = new Label("Veuillez remplir tous les champs en respectant les cases.");
	Label infoAdd = new Label("mot de passe et/ou email incorrects");
	User user;

	// *******************************************
	// Constructor
	// *******************************************
	public ConnectionView() {
		super(new User(null, null));
		modScene();
	}

	// *******************************************
	// Public methods
	// *******************************************

	/**
	 * Creates a form for adding a new intern and sets up the scene for user input.
	 * 
	 * @return VVBox containing the form for adding a new intern.
	 */
	public VBox modScene() {
		VBox formVBox = new VBox();
		GridPane formGridPane = new GridPane();

		Label titleLabel = new Label("Connexion");
		titleLabel.setStyle("-fx-font-size: 25;");
		HBox titleHBox = new HBox(30);
		titleHBox.getChildren().add(titleLabel);
		titleHBox.setAlignment(Pos.TOP_CENTER);
		titleHBox.setPadding(new Insets(0, 0, 50, 0));

		Label emailLabel = new Label("Email");
		TextField emailTF = new TextField();
		emailTF.setPromptText("ex. : DOE");
		emailLabel.setPadding(new Insets(15, 0, 15, 0));

		Label passwordLabel = new Label("Mot de passe");
		PasswordField passwordTF = new PasswordField();
		passwordTF.setPromptText("ex. : John");
		passwordLabel.setPadding(new Insets(15, 0, 15, 0));

		formGridPane.add(emailLabel, 0, 0);
		formGridPane.add(emailTF, 1, 0, 2, 1);
		formGridPane.add(passwordLabel, 0, 1);
		formGridPane.add(passwordTF, 1, 1, 2, 1);

		formGridPane.setAlignment(Pos.CENTER);

		Button buttonValidateForm = new Button("Valider");
		buttonValidateForm.setMinSize(150, 50);
		buttonValidateForm.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		HBox buttonHBox = new HBox(30);
		buttonHBox.getChildren().add(buttonValidateForm);
		buttonHBox.setAlignment(Pos.CENTER_RIGHT);
		buttonHBox.setPadding(new Insets(50, 0, 0, 0));

		infoVBox.setAlignment(Pos.CENTER);
//		infoVBox.getChildren().add(infoAdd);

		// layout general
		formVBox.setAlignment(Pos.CENTER);
		formVBox.setPadding(new Insets(50));
		formVBox.getChildren().addAll(titleHBox, formGridPane, infoVBox, buttonHBox);

		buttonValidateForm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				user = new User(emailTF.getText(), passwordTF.getText());
				if (user.connection()) {
					user.setConnected(true);
					buttonValidateForm.getScene().setRoot(new WireframeBasic(user));
					boolean binFileExists = FileChecker.isBinFilePresent();
					System.out.println("Bin file present: " + binFileExists);
					if (binFileExists) {
						// new WireframeBasic(user);
						System.out.println("connectionView user : " + user);
						try {
							informationsDisplay.setCenter(new ConnectedInternsTableView().modScene());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						informationsDisplay.setCenter(new ViewListInternEmpty().modScene());
					}
				} else {
					infoAdd.setText("mot de passe et/ou email incorrects");
				}
			}

		});

		return formVBox;

	}

	public User getUser() {
		return this.user;
	}

}
