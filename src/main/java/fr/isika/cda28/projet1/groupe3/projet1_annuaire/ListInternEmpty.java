package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ListInternEmpty extends WireframeBasic {

	public ListInternEmpty() {
		super();
		modScene();
	}

	public void modScene() {

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
				} else {
					System.out.println("Aucun fichier sélectionné.");
				}
			}
		});
		
		readDonFile();
		uploadListInterns.setMinSize(140, 65);
		uploadListInterns.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		uploadListInterns.setWrapText(true); // to center

//		Label test = new Label("Hello John Doe");
		uploadListInterns.setLayoutX(20);
		uploadListInterns.setLayoutY(20);
		informationsDisplay.getChildren().add(uploadListInterns);

	}
	
	public void readDonFile() {
		String filePath = "src/main/java/ressources/STAGIAIREs_EXTRAIT.DON";
		List<Intern> interns = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			
			List<String> lines = stream.collect(Collectors.toList());
			Iterator<String> iterator = lines.iterator();

			
			while (iterator.hasNext()) {
				String firstname = iterator.next();
				String lastname = iterator.hasNext() ? iterator.next() : "";
				String department = iterator.hasNext() ? iterator.next() : "";
				String training = iterator.hasNext() ? iterator.next() : "";
				int year = iterator.hasNext() ? Integer.parseInt(iterator.next()) : 0;
				String separator = iterator.hasNext() ? iterator.next() : "";
				

				// Add intern to list 
				interns.add(new Intern(firstname, lastname, department, training, year));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		interns.forEach(System.out::println);
	}
			

		
		  
	}
	
