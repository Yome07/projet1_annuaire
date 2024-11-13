package fr.isika.cda28.projet1.groupe3.projet1_annuaire.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ViewListInternEmpty extends VBox {

	// ******************************
	// Attribute
	// ******************************

	private Node root;

	// ******************************
	// Constructor
	// ******************************

	public ViewListInternEmpty() {
		super();
		this.root = null;
	}

	// ******************************
	// Public Method
	// ******************************

	/**
	 * Crée et configure un formulaire permettant de charger une liste de stagiaires
	 * depuis un fichier. Si un fichier est sélectionné, il est copié dans le
	 * répertoire de ressources et un arbre binaire est créé. Affiche un message
	 * d'erreur en cas de problème.
	 * 
	 * @return VBox contenant le bouton pour charger la liste de stagiaires.
	 */
	public VBox modScene() {

		VBox centerComponentsVbox = new VBox();
		Button uploadListInterns = new Button("Charger une liste de stagiaires");

		uploadListInterns.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				File selectedFile = fileChooser.showOpenDialog(null);

				if (selectedFile != null) {
					Path destinationDirPath = Paths.get("src/main/java/ressources/").toAbsolutePath();

					try {

						Files.createDirectories(destinationDirPath);

						Path destinationFilePath = destinationDirPath.resolve(selectedFile.getName());
						Files.copy(selectedFile.toPath(), destinationFilePath);

					} catch (IOException e) {
						Label errorUploadFile = new Label("Erreur lors de l'enregistrement du fichier");
						if (centerComponentsVbox.getChildren().size() == 1) {
							centerComponentsVbox.getChildren().add(errorUploadFile);
						}
					}
					BinaryTreeToFile binaryTree = new BinaryTreeToFile();
					binaryTree.createBinaryTree();

				} else {
					Label noSuchFile = new Label("Aucun fichier sélectionné");
					if (centerComponentsVbox.getChildren().size() == 1) {
						centerComponentsVbox.getChildren().add(noSuchFile);
					}
				}
			}
		});

		uploadListInterns.setMinSize(140, 65);
		uploadListInterns.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		uploadListInterns.setWrapText(true);

		centerComponentsVbox.getChildren().add(uploadListInterns);
		centerComponentsVbox.setAlignment(Pos.CENTER);

		return centerComponentsVbox;

	}

}
