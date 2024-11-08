package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
	String lastnameVerified = "";
	String firstnameVerified = "";
	String departmentVerified = "";
	String trainingVerified = "";
	int yearVerified = 0;

	// Constructors
	public ViewAddIntern() {
		super();
		modScene();
	}

	// Methods
	public String toUpperCaseFirst(String firstnameGetText) {
		String firstLetterToUpper = firstnameGetText.substring(0, 1).toUpperCase();
		String rest = firstnameGetText.substring(1, firstnameGetText.length()).toLowerCase();
		firstLetterToUpper += rest;
		return firstLetterToUpper;
	}

	public boolean testRegex(String regex, String variableGetText) {
		Pattern compiledVariablePattern = Pattern.compile(regex);
		Matcher matcherVariable = compiledVariablePattern.matcher(variableGetText);

		if (matcherVariable.matches()) {
			return true;
		} else {
			return false;
		}
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

		// Handle Error when completed form
		VBox errorVBox = new VBox(15);
		errorVBox.setAlignment(Pos.CENTER);

		Label errorLastname = new Label(
				"Erreur dans le Nom : veuillez entrer uniquement des lettres avec ou sans accents.");
		Label errorFirstname = new Label(
				"Erreur dans le Prenom : veuillez entrer uniquement des lettres avec ou sans accents.");
		Label errorDepartment = new Label(
				"Erreur dans le Departement : veuillez entrer uniquement des chiffres (sauf Corse : 2A ou 2B).");
		Label errorTraining = new Label(
				"Erreur dans la promotion : veuillez entrer uniquement le sigle suivi du numero de la promotion.");
		Label errorYear = new Label(
				"Erreur dans l'annee : Veuillez entrer uniquement une annee comprise entre 1900 et l'annee en cours.");
		Label errorIsNull = new Label("Veuillez remplir tous les champs en respectant les cases.");

		formVBox.setAlignment(Pos.CENTER);
		formVBox.setPadding(new Insets(50));
		formVBox.getChildren().addAll(titleHBox, formGridPane, errorVBox, buttonHBox);

		buttonValidateForm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// VERIFY LASTNAME
				String lastname = lastnameTF.getText().toUpperCase().trim();
				// (?i) insensitive case --- (?![×Þß÷þø]) not in the string --- [-'ŒA-ZÀ-ÿ]
				// allows all in the expression
				String lastnamePattern = "(?i)^(?:(?![×Þß÷þø])[-'ŒA-ZÀ-ÿ]){2,25}$";
				if (testRegex(lastnamePattern, lastname)) {
					errorVBox.getChildren().remove(errorLastname);
					lastnameVerified = lastname;
				} else {
					if(!(errorVBox.getChildren().contains(errorLastname))) {
						errorVBox.getChildren().add(errorLastname);
					}
				}

				// VERIFY FIRSTNAME
				String firstname = firstnameTF.getText();
				firstname = toUpperCaseFirst(firstname);
				/*
				 * (?i) insensitive case (?![×Þß÷þø]) not in the string [-'œa-zÀ-ÿ] allows all
				 * in the expression
				 */
				String firstnamePattern = "(?i)^(?:(?![×Þß÷þø])[-'œa-zÀ-ÿ]){2,25}$";
				if (testRegex(firstnamePattern, firstname)) {
					errorVBox.getChildren().remove(errorFirstname);
					firstnameVerified = firstname;
				} else {
					if(!(errorVBox.getChildren().contains(errorFirstname))) {
						errorVBox.getChildren().add(errorFirstname);
					}
				}

				// VERIFY DEPARTMENT
				String department = departmentTF.getText().trim().toUpperCase();
				/*
				 * 0[1-9] - 01-09 [1-8][0-9] - 10-89 9[0-5] - 90-95 97[1-6] - 971-976
				 */
				String departmentPattern = "^(0[1-9]|[1-8][0-9]|9[0-5]|2[A-B]||97[1-6])${2,3}";
				if (testRegex(departmentPattern, department)) {
					errorVBox.getChildren().remove(errorDepartment);
					departmentVerified = department;
				} else {
					if(!(errorVBox.getChildren().contains(errorDepartment))) {
						errorVBox.getChildren().add(errorDepartment);
					}
				}

				// VERIFY TRAINING
				String training = trainingTF.getText().trim().toUpperCase();
				/*
				 * [a-zA-Z]{2,10} - 2 a 10 lettres majuscules ou minuscules [1-9]{1,2} - 1 ou 2
				 * chiffres de 1 a 99
				 */
				String trainingPattern = "^[a-zA-Z]{2,10}[1-9]{1,2}$";
				if (testRegex(trainingPattern, training)) {
					errorVBox.getChildren().remove(errorTraining);
					trainingVerified = training;
				} else {
					if(!(errorVBox.getChildren().contains(errorTraining))) {
						errorVBox.getChildren().add(errorTraining);
					}
				}

				// VERIFY YEAR
				String yearString = yearTF.getText();
				int yearInt = Integer.parseInt(yearTF.getText());

				int currentYear = LocalDate.now().getYear();
				String yearPattern = "^(19[0-9]{2}|2[0-9]{3})$";
				Pattern compiledYearPattern = Pattern.compile(yearPattern);
				Matcher matcherYear = compiledYearPattern.matcher(yearString);
				if (matcherYear.matches()) {
					System.out.println("Bon pattern");
					errorVBox.getChildren().remove(errorYear);
					if (yearInt <= currentYear) {
						System.out.println("1900-2024");
						errorVBox.getChildren().remove(errorYear);
						yearVerified = yearInt;
					} else {
						if(!(errorVBox.getChildren().contains(errorYear))) {
							System.out.println("en dehors de 1900-2024");
							errorVBox.getChildren().add(errorYear);
						}
					}
				} else {
					if(!(errorVBox.getChildren().contains(errorYear))) {
						System.out.println("mauvais pattern");
						errorVBox.getChildren().add(errorYear);
					}
				}

				// serveur
				if(!(lastnameVerified != null) && ) {
					errorVBox.getChildren().remove(errorIsNull);
					System.out.println(
							lastnameVerified + firstnameVerified + departmentVerified + trainingVerified + yearVerified);
					Intern newIntern = new Intern(lastnameVerified, firstnameVerified, departmentVerified, trainingVerified,
							yearVerified);
					System.out.println(newIntern);
					Node newNode = new Node(newIntern, -1, -1);
				} else {
					errorVBox.getChildren().add(0, errorIsNull);// errorIsNull 
				}

			}

		});

		informationsDisplay.setCenter(formVBox);

	}

}
