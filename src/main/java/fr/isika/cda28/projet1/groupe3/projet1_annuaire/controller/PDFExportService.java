package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;

public class PDFExportService {

	// ******************************
	// Public Method
	// ******************************

	/**
	 * Exporte la liste des stagiaires dans un fichier texte. Chaque stagiaire est
	 * inscrit avec son nom, prénom, département, promotion et année. Le fichier est
	 * structuré avec un en-tête suivi des informations des stagiaires.
	 * 
	 * @param internsList La liste des stagiaires à exporter.
	 * @param file        Le fichier où les données des stagiaires seront
	 *                    enregistrées.
	 * @throws RuntimeException Si une erreur survient lors de l'écriture dans le
	 *                          fichier.
	 */
	public void exportInterns(List<Intern> internsList, File file) {
		try (FileWriter fileWriter = new FileWriter(file)) {
			// En-tête
			fileWriter.write("Liste des stagiaires : \n");
			fileWriter.write("Nom\t\tPrénom\t\tDépartement\t\tPromotion\t\tAnnée\n");
			fileWriter.write("------------------------------------------------------------\n");

			// Données
			for (Intern intern : internsList) {
				String line = String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n", intern.getLastname(), intern.getFirstname(),
						intern.getDepartment(), intern.getTraining(), intern.getYear());
				fileWriter.write(line);

			}
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors de l'export du fichier PDF", e);
		}
	}

}
