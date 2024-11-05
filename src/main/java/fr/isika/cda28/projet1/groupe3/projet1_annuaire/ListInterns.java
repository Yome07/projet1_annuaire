package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListInterns {

	// Attributs
	public List<Intern> interns;

	// Methods
	public List<Intern> readDonFile() {
		String filePath = "src/main/java/ressources/STAGIAIREs_EXTRAIT.DON";
		interns = new ArrayList<Intern>();

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

		return interns;
	}

}
