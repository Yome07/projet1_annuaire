package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChecker {

	public static boolean isBinFilePresent() {
        File directory = new File("src/main/java/ressources");
        Path path = Paths.get("../src/main/java/ressources");
        
        System.out.println("directory ressources exists ? : " + directory.exists());
        if (directory.exists() && directory.isDirectory()) {
        	File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".bin");
                }
            });
            System.out.println("dans if de isBinFilePresent");
            
            return files != null && files.length > 0;
        }
        System.out.println("dans else de isBinFilePresent");
        return false;
    }
}
