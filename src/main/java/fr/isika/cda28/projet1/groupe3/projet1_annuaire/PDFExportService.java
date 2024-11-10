package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class PDFExportService {
	
	public void exportInterns(List<Intern> internsList, File file) {
		try (FileWriter fileWriter = new FileWriter(file)){
			//En-tête
			fileWriter.write("Liste des stagiaires : \n");
			fileWriter.write("Nom\t\tPrénom\t\tDépartement\t\tPromotion\t\tAnnée\n");
			fileWriter.write("------------------------------------------------------------\n");
			
			//Données
			for (Intern intern : internsList) {
				String line = String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
                		intern.getLastname(),
                		intern.getFirstname(),
                		intern.getDepartment(),
                		intern.getTraining(),
                		intern.getYear());
				fileWriter.write(line);
				
			}
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors de l'export du fichier PDF", e);
		}
	}

}
