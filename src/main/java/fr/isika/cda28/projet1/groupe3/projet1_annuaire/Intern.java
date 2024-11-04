package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Intern {

	// attributs
	public String nom;
	public String prenom;
	public String department;
	public String training;
	public int annee;

	public Intern() {
		super();
	}

	// constructor
	public Intern(String nom, String prenom, String department, String training, int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.department = department;
		this.training = training;
		this.annee = annee;
	}

	@Override
	public String toString() {
		return "Intern : " + nom + " " + prenom + ", departement : " + department + ", promo : " + training + " en " + annee;
	}

}
