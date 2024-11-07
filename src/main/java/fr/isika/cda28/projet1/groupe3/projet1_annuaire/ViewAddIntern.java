package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewAddIntern extends WireframeBasic {

	// Attributs
	
	

	// Constructors
	public ViewAddIntern() {
		super();
		modScene();
	}

	// Methods
	public String toUpperCaseFirst(String string) {
		String firstLetter = string.substring(0, 1).toUpperCase();
		String rest = string.substring(1, string.length());
		firstLetter += rest;
		return firstLetter;
	}
	
	
	
	public void modScene() {
		VBox formVBox = new VBox();
		GridPane formGridPane = new GridPane();

		Label titleLabel = new Label("Ajouter un stagiaire");
		titleLabel.setStyle("-fx-font-size: 25;");
		HBox titleHBox = new HBox(30);
		titleHBox.getChildren().add(titleLabel);
		titleHBox.setAlignment(Pos.TOP_CENTER);
		titleHBox.setPadding(new Insets(0, 0, 50, 0));

		Label lastnameLabel = new Label("Nom");
		TextField lastnameTF = new TextField();
		lastnameTF.setPromptText("ex. : DOE");
		lastnameLabel.setPadding(new Insets(15, 0, 15, 0));

		Label firstnameLabel = new Label("Prenom");
		TextField firstnameTF = new TextField();
		firstnameTF.setPromptText("ex. : John");
		firstnameLabel.setPadding(new Insets(15, 0, 15, 0));

		Label departmentLabel = new Label("Departement");
		TextField departmentTF = new TextField();
		departmentTF.setPromptText("ex. : 83");
		departmentLabel.setPadding(new Insets(15, 40, 15, 0));

		Label trainingLabel = new Label("Promotion");
		TextField trainingTF = new TextField();
		trainingTF.setPromptText("ex. : CDA 28");
		trainingLabel.setPadding(new Insets(15, 0, 15, 0));

		Label yearLabel = new Label("Annee");
		TextField yearTF = new TextField();
		yearTF.setPromptText("ex. : 1984");
		yearLabel.setPadding(new Insets(15, 0, 15, 0));

		formGridPane.add(lastnameLabel, 0, 0);
		formGridPane.add(lastnameTF, 1, 0, 2, 1);
		formGridPane.add(firstnameLabel, 0, 1);
		formGridPane.add(firstnameTF, 1, 1, 2, 1);
		formGridPane.add(departmentLabel, 0, 2);
		formGridPane.add(departmentTF, 1, 2, 2, 1);
		formGridPane.add(trainingLabel, 0, 3);
		formGridPane.add(trainingTF, 1, 3, 2, 1);
		formGridPane.add(yearLabel, 0, 4);
		formGridPane.add(yearTF, 1, 4, 2, 1);
		formGridPane.setAlignment(Pos.CENTER);

		Button buttonValidateForm = new Button("Valider");
		buttonValidateForm.setMinSize(150, 50);
		buttonValidateForm.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		HBox buttonHBox = new HBox(30);
		buttonHBox.getChildren().add(buttonValidateForm);
		buttonHBox.setAlignment(Pos.CENTER_RIGHT);
		buttonHBox.setPadding(new Insets(50, 0, 0, 0));

		formVBox.setAlignment(Pos.CENTER);
		formVBox.setPadding(new Insets(50));
		formVBox.getChildren().addAll(titleHBox, formGridPane, buttonHBox);
		
		

		buttonValidateForm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String lastname = lastnameTF.getText().toUpperCase().trim();
				System.out.println(lastname);
				String firstname = firstnameTF.getText();
				firstname = toUpperCaseFirst(firstname);
				System.out.println(firstname);
				
				
				String department = departmentTF.getText().trim().toUpperCase();
				String departmentPattern = "^(0[1-9]|[1-8][0-9]|9[0-5]|2[A-B]|2[a-b]|97[1-6])$";
				Pattern compiledPattern = Pattern.compile(departmentPattern);
				Matcher matcher = compiledPattern.matcher(department);
				if (matcher.matches()) {
					System.out.println("Departement valide");
				}else {
					System.out.println("Departement invalide");
				}
				
				
				String training = trainingTF.getText().trim().toUpperCase();

				int year = Integer.parseInt(yearTF.getText());

				Intern newIntern = new Intern();
				Node newNode = new Node(newIntern, -1, -1);
			}

		});

		informationsDisplay.setCenter(formVBox);

	}
	
	}
	


