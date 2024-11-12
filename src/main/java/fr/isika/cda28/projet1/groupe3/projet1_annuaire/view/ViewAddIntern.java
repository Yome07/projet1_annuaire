package fr.isika.cda28.projet1.groupe3.projet1_annuaire.view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.WireframeBasic;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewAddIntern extends WireframeBasic {

	// ******************************
	// Attributes
	// ******************************

	VBox infoVBox = new VBox(5);
	Label errorIsEmpty = new Label("Veuillez remplir tous les champs en respectant les cases.");
	Label infoAdd = new Label();
	BinaryTreeToFile instance = new BinaryTreeToFile();
	
	


	// *******************************************
	// Constructor
	// *******************************************
	public ViewAddIntern() {
		super(new User(null, null));
		modScene();
	}

	// *******************************************
	// Public methods
	// *******************************************

	/**
	 * Converts the first letter of the input string to uppercase and the rest to
	 * lowercase, after verifying that the input is not empty.
	 * 
	 * @param firstnameGetText The input string to be modified.
	 * @return A string with the first letter capitalized and the rest in lowercase.
	 *         Returns an empty string if the input is empty.
	 */
	public String toUpperCaseFirst(String firstnameGetText) {
		firstnameGetText.trim();

		if (firstnameGetText.isEmpty()) {
			if (!(infoVBox.getChildren().contains(errorIsEmpty))) {
				infoVBox.getChildren().add(0, errorIsEmpty);
			}
			return "";
		}

		String firstLetterToUpper = firstnameGetText.substring(0, 1).toUpperCase();
		String rest = firstnameGetText.substring(1, firstnameGetText.length()).toLowerCase();
		firstLetterToUpper += rest;
		return firstLetterToUpper;
	}

	/**
	 * Tests whether the given string matches the provided regular expression.
	 * 
	 * @param regex           The regular expression to test the string against.
	 * @param variableGetText The string input to be matched.
	 * @return boolean True if the string matches the regex, false otherwise.
	 */
	public boolean testRegex(String regex, String variableGetText) {
		Pattern compiledVariablePattern = Pattern.compile(regex);
		Matcher matcherVariable = compiledVariablePattern.matcher(variableGetText);

		if (matcherVariable.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a form for adding a new intern and sets up the scene for user input.
	 * 
	 * @return VVBox containing the form for adding a new intern.
	 */
	public VBox modScene() {
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

		infoVBox.setAlignment(Pos.CENTER);
//		infoVBox.getChildren().add(infoAdd);

		// errorHandle when you try to validate the entry
		Label errorLastname = new Label(
				"Erreur dans le Nom : veuillez entrer uniquement des lettres avec ou sans accents.");
		Label errorFirstname = new Label(
				"Erreur dans le Prenom : veuillez entrer uniquement des lettres avec ou sans accents.");
		Label errorDepartment = new Label(
				"Erreur dans le Departement : veuillez entrer uniquement des chiffres (sauf Corse : 2A ou 2B).");
		Label errorTraining = new Label(
				"Erreur dans la promotion : veuillez entrer uniquement le sigle suivi du numero\nde la promotion en deux chiffres.");
		Label errorYear = new Label(
				"Erreur dans l'annee : Veuillez entrer uniquement une annee comprise\nentre 1900 et l'annee en cours.");

		// layout general
		formVBox.setAlignment(Pos.CENTER);
		formVBox.setPadding(new Insets(50));
		formVBox.getChildren().addAll(titleHBox, formGridPane, infoVBox, buttonHBox);
		
		

		buttonValidateForm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String lastnameVerified = "";
				String firstnameVerified = "";
				String departmentVerified = "";
				String trainingVerified = "";
				int yearVerified = 0;

				// VERIFY LASTNAME
				String lastname = lastnameTF.getText().toUpperCase().trim();
				/*
				 * (?i) insensitive case (?![×Þß÷þø]) not in the string [-'ŒA-ZÀ-ÿ] allows all
				 * in the expression
				 */
				String lastnamePattern = "(?i)^(?:(?![×Þß÷þø])[-'ŒA-ZÀ-ÿ]){2,25}$";
				if (testRegex(lastnamePattern, lastname)) {
					infoVBox.getChildren().remove(errorLastname);
					lastnameVerified = lastname;
				} else {
					if (!(infoVBox.getChildren().contains(errorLastname))) {
						infoVBox.getChildren().add(errorLastname);
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
					infoVBox.getChildren().remove(errorFirstname);
					firstnameVerified = firstname;
				} else {
					if (!(infoVBox.getChildren().contains(errorFirstname))) {
						infoVBox.getChildren().add(errorFirstname);
					}
				}

				// VERIFY DEPARTMENT
				String department = departmentTF.getText().trim().toUpperCase();
				/*
				 * 0[1-9] : 01-09 [1-8][0-9] : 10-89 9[0-5] : 90-95 97[1-6] : 971-976
				 */
				String departmentPattern = "^(0[1-9]|[1-8][0-9]|9[0-5]|2[A-B]||97[1-6])${2,3}";
				if (testRegex(departmentPattern, department)) {
					infoVBox.getChildren().remove(errorDepartment);
					departmentVerified = department;
				} else {
					if (!(infoVBox.getChildren().contains(errorDepartment))) {
						infoVBox.getChildren().add(errorDepartment);
					}
				}

				// VERIFY TRAINING
				String training = trainingTF.getText().trim().toUpperCase();
				/*
				 * [a-zA-Z]{2,10} : 2 to 10 letters upper or lower case [1-9]{1,2} : 1 or 2
				 * numbers between 1 to 99
				 */
				String trainingPattern = "^[a-zA-Z]{2,10}[1-9]{1,2}$";
				if (testRegex(trainingPattern, training)) {
					infoVBox.getChildren().remove(errorTraining);
					trainingVerified = training;
				} else {
					if (!(infoVBox.getChildren().contains(errorTraining))) {
						infoVBox.getChildren().add(errorTraining);
					}
				}

				// VERIFY YEAR
				int currentYear = LocalDate.now().getYear();
				String yearString = yearTF.getText();

				/*
				 * Test year with Regular Expression 19[0-9]{2} : 1900 to 1999 2[0-9]{3} : 2000
				 * to 2999
				 */
				String yearPattern = "^(19[0-9]{2}|2[0-9]{3})$";
				Pattern compiledYearPattern = Pattern.compile(yearPattern);
				Matcher matcherYear = compiledYearPattern.matcher(yearString);
				if (matcherYear.matches()) {

					int yearInt = Integer.parseInt(yearString);
					// remove error if match
					infoVBox.getChildren().remove(errorYear);

					// save the year if it's <= to currentYEar
					if (yearInt <= currentYear) {
						infoVBox.getChildren().remove(errorYear);
						yearVerified = yearInt;
					} else {
						// add errorYear if the year it's in the future
						if (!(infoVBox.getChildren().contains(errorYear))) {
							infoVBox.getChildren().add(errorYear);
						}
					}
				} else {
					// add errorYEar if format is incorrect
					if (!(infoVBox.getChildren().contains(errorYear))) {
						infoVBox.getChildren().add(errorYear);
					}
				}

				
				
				
				// CREATE NEW INTERN
				if (lastnameVerified != "" && firstnameVerified != "" && departmentVerified != ""
						&& trainingVerified != "" && yearVerified != 0) {
					infoVBox.getChildren().remove(errorIsEmpty);

					Intern newIntern = new Intern(lastnameVerified, firstnameVerified, departmentVerified,
							trainingVerified, yearVerified);
					Node newNode = new Node(newIntern, -1, -1);
					
					
					
					try {
						 File file = new File("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin"); 
						int index = instance.numbersInternsFile(file);
						instance.insertNode(0, index, newIntern, newNode);
						infoAdd.setText("Stagiaire ajouté avec succès !");
					} catch (Exception e) {
						infoAdd.setText("Les donnees sont correctes mais il y a eu une erreur pendant l'ajout :\n le stagiaire n'a pas ete ajouté.");
					}
					
					System.out.println(newIntern);

				} else {
					infoAdd.setText("Le stagiaire n'as pas été ajouté.");

				}

			}

		});

		return formVBox;

	}

}
