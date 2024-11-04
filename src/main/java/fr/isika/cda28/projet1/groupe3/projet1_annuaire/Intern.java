package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Intern {

	// attributs
	public String lastname;
	public String firstname;
	public String department;
	public String training;
	public int year;

	public Intern() {
		super();
	}

	// constructor
	public Intern(String lastname, String firstname, String department, String training, int year) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.department = department;
		this.training = training;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Intern : " + lastname + " " + firstname + ", departement : " + department + ", promo : " + training + " en " + year;
	}

}
