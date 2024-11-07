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

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ListInternEmpty extends WireframeBasic {
	//ArrayList<Intern> interns; 
	private Node root;

	public ListInternEmpty() {
		super();
		modScene();
		this.root = null;
	}

	public void modScene() {

		Button uploadListInterns = new Button("Charger une liste de statiaires");
//		Button displayTableView = new Button("Afficher la liste des stagiaires");
//		// tableView
//		VBox tableViewBox = new VBox();
//		InternsTableView internTableView = new InternsTableView(interns);
		
		uploadListInterns.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				File selectedFile = fileChooser.showOpenDialog(null);

				// Vérifier si un fichier a été sélectionné
				if (selectedFile != null) {
					Path destinationDirPath = Paths.get("src/main/java/ressources/").toAbsolutePath();

					try {
						Files.createDirectories(destinationDirPath);

						Path destinationFilePath = destinationDirPath.resolve(selectedFile.getName());

						Files.copy(selectedFile.toPath(), destinationFilePath);

						System.out.println("Fichier enregistré avec succès dans : " + destinationFilePath);

					} catch (IOException ioException) {
						System.out.println("Erreur lors de l'enregistrement du fichier : " + ioException.getMessage());
					}
					BinaryTreeToFile binaryTree = new BinaryTreeToFile();
					binaryTree.createBinaryTree();
				    ServiceNodeList test = new ServiceNodeList();
				    test.readList(test.createListAlpha(0));
					//InternsTableView internsTableView = new InternsTableView(new ArrayList<Intern>());
					//Scene scene = internsTableView.createScene();

				} else {
					System.out.println("Aucun fichier sélectionné.");
				}
			}

		});
//		displayTableView.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				scene.setRoot(tableViewBox);
//				internTableView.internTableView.setItems(FXCollections.observableArrayList(interns));
//			}
//		});

		uploadListInterns.setMinSize(140, 65);
		uploadListInterns.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		uploadListInterns.setWrapText(true); // to center

//		Label test = new Label("Hello John Doe");

		uploadListInterns.setLayoutX(20);
		uploadListInterns.setLayoutY(20);
		informationsDisplay.getChildren().add(uploadListInterns);

	}

	
}
