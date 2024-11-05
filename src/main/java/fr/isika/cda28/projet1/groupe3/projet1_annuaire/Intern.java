package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Intern {

	public final static int STRING_MAX_LENGTH = 50;
	public final static int BYTE_LENGTH_INTERN = 2 * 4 * STRING_MAX_LENGTH + 4;

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

		if (lastNameLong.length() < STRING_MAX_LENGTH) {
			for (int i = lastNameLong.length(); i < STRING_MAX_LENGTH; i++) {
				lastNameLong += "*";
			}
		} else {

			lastNameLong = this.lastname.substring(0, STRING_MAX_LENGTH);
		}

	}

	public void setFirstNameLong() {
		String firstNameLong = this.firstname;

		if (firstNameLong.length() < STRING_MAX_LENGTH) {
			for (int i = firstNameLong.length(); i < STRING_MAX_LENGTH; i++) {
				firstNameLong += "*";
			}
		} else {

			firstNameLong = this.firstname.substring(0, STRING_MAX_LENGTH);
		}

	}

	public void setDepartmentLong() {
		String departmentLong = this.department;

		if (departmentLong.length() < STRING_MAX_LENGTH) {
			for (int i = departmentLong.length(); i < STRING_MAX_LENGTH; i++) {
				departmentLong += "*";
			}
		} else {

			departmentLong = this.department.substring(0, STRING_MAX_LENGTH);
		}

	}

	public void setTrainingLong() {
		String trainingLong = this.training;

		if (trainingLong.length() < STRING_MAX_LENGTH) {
			for (int i = trainingLong.length(); i < STRING_MAX_LENGTH; i++) {
				trainingLong += "*";
			}
		} else {

			trainingLong = this.training.substring(0, STRING_MAX_LENGTH);
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
