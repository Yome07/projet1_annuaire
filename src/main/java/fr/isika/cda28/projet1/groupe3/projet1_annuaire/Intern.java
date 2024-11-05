package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Intern {

	public final static int LENGTH_MAX_LASTNAME = 50;
	public final static int LENGTH_MAX_FIRSTNAME = 50;
	public final static int LENGTH_MAX_DEPARTMENT = 50;
	public final static int LENGTH_MAX_TRAINING = 50;
	public final static int LENGTH_INTERN_BYTE = 2 * LENGTH_MAX_LASTNAME + 2 * LENGTH_MAX_FIRSTNAME
			+ 2 * LENGTH_MAX_DEPARTMENT + 2 * LENGTH_MAX_TRAINING + 4;

	// attributs
	public String lastname;
	public String firstname;
	public String department;
	public String training;
	public int year;

	public Intern() {
		super();
		lastname = "";
		firstname = "";
		department = "";
		training = "";

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

	public void setLastNameLong() {
		String lastNameLong = this.lastname;

		if (lastNameLong.length() < LENGTH_MAX_LASTNAME) {
			for (int i = lastNameLong.length(); i < LENGTH_MAX_LASTNAME; i++) {
				lastNameLong += " ";
			}
		} else {

			lastNameLong = this.lastname.substring(0, LENGTH_MAX_LASTNAME);
		}

	}

	public void setFirstNameLong() {
		String firstNameLong = this.firstname;

		if (firstNameLong.length() < LENGTH_MAX_FIRSTNAME) {
			for (int i = firstNameLong.length(); i < LENGTH_MAX_FIRSTNAME; i++) {
				firstNameLong += " ";
			}
		} else {

			firstNameLong = this.firstname.substring(0, LENGTH_MAX_FIRSTNAME);
		}

	}

	public void setDepartmentLong() {
		String departmentLong = this.department;

		if (departmentLong.length() < LENGTH_MAX_DEPARTMENT) {
			for (int i = departmentLong.length(); i < LENGTH_MAX_DEPARTMENT; i++) {
				departmentLong += " ";
			}
		} else {

			departmentLong = this.department.substring(0, LENGTH_MAX_DEPARTMENT);
		}

	}

	public void setTrainingLong() {
		String trainingLong = this.training;

		if (trainingLong.length() < LENGTH_MAX_TRAINING) {
			for (int i = trainingLong.length(); i < LENGTH_MAX_TRAINING; i++) {
				trainingLong += " ";
			}
		} else {

			trainingLong = this.training.substring(0, LENGTH_MAX_TRAINING);
		}

	}

//getters et setters

	public String getLastnameLong() {
		return lastname;
	}

	public String getFirstnameLong() {
		return firstname;
	}

	public String getDepartmentLong() {
		return department;
	}

	public String getTrainingLong() {
		return training;
	}

	public String getLastname() {
		return lastname.trim();
	}

	public String getFirstname() {
		return firstname.trim();
	}

	public String getDepartment() {
		return department.trim();
	}

	public String getTraining() {
		return training.trim();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Intern : " + lastname + " " + firstname + ", departement : " + department + ", promo : " + training
				+ " en " + year;
	}

}
