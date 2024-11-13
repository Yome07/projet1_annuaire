package fr.isika.cda28.projet1.groupe3.projet1_annuaire.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.PDFExportService;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.ServiceNodeList;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ConnectedInternsTableView extends VBox {

	// ******************************
	// Attributes
	// ******************************

	private List<Intern> internsList = new ArrayList<>();
	private List<Node> nodesInterns;
	public TableView<Intern> internTableView;
	private PDFExportService pdfExportService;
	User user;

	// ******************************
	// Constructor
	// ******************************

	public ConnectedInternsTableView() throws IOException {
		super();
		modScene();
	}

	public ConnectedInternsTableView(List<Intern> internsList) throws IOException {
		super();
		this.internsList = internsList;
		modScene();
	}

	// ******************************
	// Public Method
	// ******************************

	/**
	 * Crée et configure l'interface graphique pour afficher et modifier la liste
	 * des stagiaires. Initialise les éléments de la scène, y compris le titre, le
	 * tableau des stagiaires avec colonnes éditables, et un bouton pour
	 * l'exportation en PDF.
	 * 
	 * @return VBox contenant l'ensemble des éléments de la scène (tableau, boutons,
	 *         etc.).
	 * @throws IOException Si une erreur survient lors du chargement des données.
	 */
	public VBox modScene() throws IOException {
		this.pdfExportService = new PDFExportService();
		VBox conteneurVBox = new VBox();
		Label internsListLabel = new Label("Liste des Stagiaires");

		internsListLabel.setStyle("-fx-font-size: 25;");
		HBox titleHBox = new HBox(30);
		titleHBox.getChildren().add(internsListLabel);
		titleHBox.setAlignment(Pos.TOP_CENTER);
		titleHBox.setPadding(new Insets(30, 0, 30, 0));

		this.internTableView = new TableView<Intern>();
		internTableView.setEditable(true);

		ServiceNodeList nodeList = new ServiceNodeList();
		nodesInterns = nodeList.createListAlpha(0);
		for (Node node : nodesInterns) {
			internsList.add(node.getIntern());
		}

		TableColumn<Intern, String> lastnameColumn = new TableColumn<Intern, String>("Nom");

		lastnameColumn.setMinWidth(100);

		lastnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));

		internTableView.getColumns().add(lastnameColumn);

		lastnameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setLastname(event.getNewValue());
			}
		});
		lastnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		lastnameColumn.setEditable(true);

		TableColumn<Intern, String> firstnameColumn = new TableColumn<Intern, String>("Prénom");

		firstnameColumn.setMinWidth(100);

		firstnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));

		internTableView.getColumns().add(firstnameColumn);

		firstnameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setFirstname(event.getNewValue());
			}
		});

		firstnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		firstnameColumn.setEditable(true);

		TableColumn<Intern, String> departmentColumn = new TableColumn<Intern, String>("Département");

		departmentColumn.setMinWidth(100);

		departmentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment()));

		internTableView.getColumns().add(departmentColumn);

		departmentColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {

				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setDepartment(event.getNewValue());
			}
		});
		departmentColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		departmentColumn.setEditable(true);

		TableColumn<Intern, String> trainingColumn = new TableColumn<Intern, String>("Formation");

		trainingColumn.setMinWidth(100);

		trainingColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTraining()));

		internTableView.getColumns().add(trainingColumn);

		trainingColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setTraining(event.getNewValue());
			}
		});
		trainingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trainingColumn.setEditable(true);

		TableColumn<Intern, Integer> yearColumn = new TableColumn<Intern, Integer>("Année");

		yearColumn.setMinWidth(100);

		yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());

		internTableView.getColumns().add(yearColumn);

		yearColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, Integer>>() {
			@Override
			public void handle(CellEditEvent<Intern, Integer> event) {
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setYear(event.getNewValue());
			}
		});

		yearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		yearColumn.setEditable(true);

		TableColumn<Intern, Void> deleteColumn = new TableColumn<>("Supprimer");

		deleteColumn.setMinWidth(100);

		deleteColumn.setCellFactory(param -> new TableCell<Intern, Void>() {
			private final Button deleteButton = new Button("Supprimer");
			{
				deleteButton.setMinSize(90, 30);
				deleteButton.setStyle("-fx-background-color: #F87A53; -fx-font-size: 12;");
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteButton);
				}
			}
		});

		internTableView.getColumns().add(deleteColumn);

		internTableView.setItems(FXCollections.observableArrayList(this.internsList));

		Button printButton = new Button("Imprimer en PDF");

		printButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleExport();
			}
		});

		printButton.setMinSize(150, 50);
		printButton.setStyle("-fx-background-color: #F87A53; -fx-font-size: 16;");
		HBox buttonHBox = new HBox(30);
		buttonHBox.getChildren().add(printButton);
		buttonHBox.setAlignment(Pos.CENTER_RIGHT);
		buttonHBox.setPadding(new Insets(30, 30, 30, 0));

		conteneurVBox.getChildren().addAll(titleHBox, internTableView, buttonHBox);
		return conteneurVBox;
	}

	/**
	 * Crée la liste des stagiaires en générant une liste triée des nœuds et en
	 * extrayant les stagiaires associés.
	 * 
	 * @throws IOException Si une erreur survient lors de la lecture des données.
	 */
	public void createInternsList() throws IOException {
		ServiceNodeList nodeList = new ServiceNodeList();
		nodesInterns = nodeList.createListAlpha(0);
		for (Node node : nodesInterns) {
			internsList.add(node.getIntern());
		}

	}

	/**
	 * Gère l'export de la liste des stagiaires en PDF. Affiche une boîte de
	 * dialogue pour entrer le nom du fichier, sélectionne le dossier de
	 * destination, puis lance l'exportation en utilisant le service de génération
	 * de PDF. En cas de succès, une alerte est affichée avec le chemin du fichier
	 * exporté. En cas d'erreur, une alerte affiche l'erreur rencontrée.
	 */
	private void handleExport() {
		try {

			TextInputDialog dialog = new TextInputDialog("stagiaires.pdf");
			dialog.setTitle("Nom du fichier");
			dialog.setHeaderText(null);
			dialog.setContentText("Entrez le nom du fichier :");

			Optional<String> fileName = dialog.showAndWait();
			if (!fileName.isPresent()) {
				return;
			}

			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Choisir le dossier d’enregistrement");

			File directory = directoryChooser.showDialog((Window) internTableView.getScene().getWindow());

			if (directory != null) {
				File file = new File(directory, fileName.get());

				pdfExportService.exportInterns(internsList, file);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Export réussi");
				alert.setHeaderText(null);
				alert.setContentText(
						"La liste des stagiaires a été exportée avec succès dans : " + file.getAbsolutePath());
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur lors de l'export");
			alert.setContentText("Une erreur est survenue lors de l'export : " + e.getMessage());
			alert.showAndWait();
		}
	}
}
