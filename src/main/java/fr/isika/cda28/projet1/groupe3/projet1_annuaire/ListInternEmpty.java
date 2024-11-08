package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ListInternEmpty extends VBox {

	private Node root;

	public ListInternEmpty() {
		super();

		this.root = null;

	}

	public Button modScene() {

		Button uploadListInterns = new Button("Charger une liste de statiaires");

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
			        List<Intern> interns = new ArrayList<>();
			        InternsTableView internsTableView = new InternsTableView(interns);
//			        informationsDisplay.getChildren().add(internsTableView);
				} else {
					System.out.println("Aucun fichier sélectionné.");
				}
			}
		});

		uploadListInterns.setMinSize(140, 65);
		uploadListInterns.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		uploadListInterns.setWrapText(true);

		return uploadListInterns;
	}
	

}
