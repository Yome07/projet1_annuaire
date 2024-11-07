package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class InternsTableView extends WireframeBasic {

	private List<Intern> internsList = new ArrayList<>();
	private List<Node> nodesInterns;
	public TableView<Intern> internTableView;

	public InternsTableView(List<Intern> internsList) {

		this.internsList = internsList;
		VBox conteneurVBox = new VBox();
		Label internsListLabel = new Label("Liste des Stagiaires");

		this.internTableView = new TableView<Intern>();
		internTableView.setEditable(true);
		
		// Création de la liste des stagiaires
		ServiceNodeList nodeList = new ServiceNodeList();
		nodesInterns = nodeList.createListAlpha(0);
		for (Node node : nodesInterns) {
			internsList.add(node.getIntern());
			
		}
		
		// colonne nom
		TableColumn<Intern, String> lastnameColumn = new TableColumn<Intern, String>("Nom");

		// largeur colonne
		lastnameColumn.setMinWidth(100);

		lastnameColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("lastname"));

		// ajouter la colonne à la table view
		internTableView.getColumns().add(lastnameColumn);

		// gestionnaire d'évènement pour les cellules
		lastnameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				// Je récupère l'objet qui correspond à la ligne modifiée
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setLastname(event.getNewValue());// On récupère la nouvelle valeur dans l'event
			}
		});
		lastnameColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // On autotrise à transformer la case en
		// Textfield
		lastnameColumn.setEditable(true);// on autorise la modification des colonnes

		// colonne prenom
		TableColumn<Intern, String> firstnameColumn = new TableColumn<Intern, String>("Prénom");

		firstnameColumn.setMinWidth(100);

		firstnameColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("firstname"));

		// ajouter la colonne à la table view
		internTableView.getColumns().add(firstnameColumn);
		
		// gestionnaire d'évènement pour les cellules	
		firstnameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				// Je récupère l'objet qui correspond à la ligne modifiée
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setFirstname(event.getNewValue());// On récupère la nouvelle valeur dans l'event
			}
		});
		
		firstnameColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // On autotrise à transformer la case en
		
		// Textfield
		firstnameColumn.setEditable(true);// on autorise la modification des colonnes

		// colonne department
		TableColumn<Intern, String> departmentColumn = new TableColumn<Intern, String>("Département");

		departmentColumn.setMinWidth(100);

		departmentColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("department"));

		// ajouter la colonne à la table view
		internTableView.getColumns().add(departmentColumn);
		
		// gestionnaire d'évènement pour les cellules		
		departmentColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				// Je récupère l'objet qui correspond à la ligne modifiée
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setDepartment(event.getNewValue());// On récupère la nouvelle valeur dans l'event
			}
		});
		departmentColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // On autotrise à transformer la case en
		
		// Textfield
		departmentColumn.setEditable(true);// on autorise la modification des colonnes

		// colonne training
		TableColumn<Intern, String> trainingColumn = new TableColumn<Intern, String>("Formation");

		trainingColumn.setMinWidth(100);

		trainingColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("training"));

		// ajouter la colonne à la table view
		internTableView.getColumns().add(trainingColumn);
	
		// gestionnaire d'évènement pour les cellules	
		trainingColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
			@Override
			public void handle(CellEditEvent<Intern, String> event) {
				// Je récupère l'objet qui correspond à la ligne modifiée
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setTraining(event.getNewValue());// On récupère la nouvelle valeur dans l'event
			}
		});
		trainingColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // On autotrise à transformer la case en
		// Textfield
		trainingColumn.setEditable(true);// on autorise la modification des colonnes

		// colonne année
		TableColumn<Intern, Integer> yearColumn = new TableColumn<Intern, Integer>("Année");

		yearColumn.setMinWidth(100);

		yearColumn.setCellValueFactory(new PropertyValueFactory<Intern, Integer>("year"));

		// ajouter la colonne à la table view
		internTableView.getColumns().add(yearColumn);
		
		// gestionnaire d'évènement pour les cellules		
		yearColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, Integer>>() {
			@Override
			public void handle(CellEditEvent<Intern, Integer> event) {
				// Je récupère l'objet qui correspond à la ligne modifiée
				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
						.setYear(event.getNewValue());// On récupère la nouvelle valeur dans l'event
			}
		});
	
		yearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())); 
		
		yearColumn.setEditable(true);// on autorise la modification des colonnes
		
		// Je donne à mon tableau la liste de stagiaires à afficher
		// observable list permet de lever des alertes quand la liste est modifier. Si
		// il y a une modification dans la liste le tableau sera modifié
		internTableView.setItems(FXCollections.observableArrayList(this.internsList));

		// this.getChildren().add(internTableView);
		conteneurVBox.getChildren().addAll(internsListLabel, internTableView);
		informationsDisplay.setCenter(conteneurVBox);
	}
}
