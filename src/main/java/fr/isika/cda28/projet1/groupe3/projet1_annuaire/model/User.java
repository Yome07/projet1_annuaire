package fr.isika.cda28.projet1.groupe3.projet1_annuaire.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class User {

	// ******************************
	// Attributes
	// ******************************
	
	private String email;

	private String password;

	private Boolean connected;

	// ******************************
	// Constructor
	// ******************************
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.connected = false;
	}

	// ******************************
	// Getter & Setter
	// ******************************
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	// ******************************
	// Public Method
	// ******************************
	
	/**
	 * Vérifie si l'email et le mot de passe fournis correspondent à ceux enregistrés dans le fichier "user.txt".
	 * 
	 * @return `true` si les identifiants sont valides, sinon `false`.
	 */
	public Boolean connection() {
		try (FileReader fr = new FileReader("src/main/java/ressources/user.txt")) {
			BufferedReader br = new BufferedReader(fr);

			if (this.email.equals(br.readLine()) && this.password.equals(br.readLine())) {

				return true;
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
