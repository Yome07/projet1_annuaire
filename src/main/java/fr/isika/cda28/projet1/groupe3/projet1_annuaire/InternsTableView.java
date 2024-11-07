package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

public class InternsTableView extends VBox {

	private ArrayList<Intern> interns;
	public TableView<Intern> internTableView;
	public InternsTableView(ArrayList<Intern> interns) {

		this.internTableView = new TableView<Intern>();
		this.interns = interns;
		internTableView.setEditable(true);
		// colonne nom
		TableColumn<Intern, String> lastnameColumn = new TableColumn<Intern, String>("nom");

		// largeur colonne

		lastnameColumn.setMinWidth(100);

		lastnameColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("nom"));

		// ajouter la colonne à la table view

		internTableView.getColumns().add(lastnameColumn);

//		// gestionnaire d'évènement pour les cellules
//		
//		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Intern, String>>() {
//			@Override
//			public void handle(CellEditEvent<Intern, String> event) {
//				// Je récupère l'objet qui correspond à la ligne modifiée
//				((Intern) internTableView.getItems().get((event.getTablePosition().getRow())))
//						.setName(event.getNewValue());// On récupère la nouvelle valeur dans l'event
//			}
//		});
//		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // On autotrise à transformer la case en
//																		// Textfield
//		nameColumn.setEditable(true);// on autorise la modification des colonnes

		
		//colonne prenom
		TableColumn<Intern, String> firstnameColumn = new TableColumn<Intern, String>("prénom");
		
		firstnameColumn.setMinWidth(100);

		firstnameColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("prénom"));

		// ajouter la colonne à la table view

		internTableView.getColumns().add(firstnameColumn);
		
		// colonne department
		TableColumn<Intern, String> departmentColumn = new TableColumn<Intern, String>("Département");
		
		departmentColumn.setMinWidth(100);

		departmentColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("Département"));

		// ajouter la colonne à la table view

		internTableView.getColumns().add(departmentColumn);
		
		// training
		
		TableColumn<Intern, String> trainingColumn = new TableColumn<Intern, String>("Formation");
		
		trainingColumn.setMinWidth(100);

		trainingColumn.setCellValueFactory(new PropertyValueFactory<Intern, String>("Formation"));

		// ajouter la colonne à la table view

		internTableView.getColumns().add(trainingColumn);
		
		// year
		
		TableColumn<Intern, Integer> yearColumn = new TableColumn<Intern, Integer>("année");
				
		yearColumn.setMinWidth(100);

		yearColumn.setCellValueFactory(new PropertyValueFactory<Intern, Integer>("année"));

		// ajouter la colonne à la table view

		internTableView.getColumns().add(yearColumn);
		
		this.getChildren().add(internTableView);
		
		
	}
}
