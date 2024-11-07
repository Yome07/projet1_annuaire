package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import javafx.stage.Stage;

public class ListInternEmpty extends WireframeBasic {

	private Node root;

	public ListInternEmpty() {
		super();
		modScene();
		this.root = null;
	}

	public void modScene() {

		VBox centerComponentsVbox = new VBox();
		Button uploadListInterns = new Button("Charger une liste de stagiaires");

		uploadListInterns.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				File selectedFile = fileChooser.showOpenDialog(null);

				// Vérifier si un fichier a été sélectionné
				if (selectedFile != null) {
					Path destinationDirPath = Paths.get("src/main/java/ressources/").toAbsolutePath();

					try {
						centerComponentsVbox.getChildren().remove(1);
						
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
		uploadListInterns.setWrapText(true); // to center

//		Label test = new Label("Hello John Doe");
		centerComponentsVbox.getChildren().add(uploadListInterns);
		centerComponentsVbox.setAlignment(Pos.CENTER);
		informationsDisplay.setCenter(centerComponentsVbox);

	}

}
