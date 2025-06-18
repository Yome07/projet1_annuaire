package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChecker {

	// ******************************
	// Public Method
	// ******************************
	
	/**
	 * Checks whether a binary file (.bin) is present in the "ressources" directory.
	 * The method scans the specified directory for files with the ".bin" extension.
	 * If any .bin files are found, it returns true; otherwise, it returns false.
	 * 
	 * @return true if at least one .bin file is present in the "ressources" directory, false otherwise.
	 */
	public static boolean isBinFilePresent() {
        File directory = new File("src/main/java/ressources");
        Path path = Paths.get("../src/main/java/ressources");
        
        if (directory.exists() && directory.isDirectory()) {
        	File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".bin");
                }
            });
            
            return files != null && files.length > 0;
        }
        return false;
    }
}
