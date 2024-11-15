package fr.isika.cda28.projet1.groupe3.projet1_annuaire.model;

public class Intern {

	// ******************************
	// Constant
	// ******************************
	
	public final static int STRING_MAX_LENGTH = 25;
	public final static int BYTE_LENGTH_INTERN = 2 * 4 * STRING_MAX_LENGTH + 4;

	// ******************************
	// Attribute
	// ******************************
	
	private String lastname;
	private String firstname;
	public String department;
	private String training;
	private int year;

	// ******************************
	// Constructor
	// ******************************
	
	public Intern() {
		super();
		lastname = "";
		firstname = "";
		department = "";
		training = "";
		year = 0;
	}

	public Intern(String lastname, String firstname, String department, String training, int year) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.department = department;
		this.training = training;
		this.year = year;

	}

	// *******************************************
	// Getters & Setters
	// *******************************************
	
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// *******************************************
	// Public Method
	// *******************************************

	public String getLastnameLong() {
		String lastNameLong = this.lastname;

		if (lastNameLong.length() < STRING_MAX_LENGTH) {
			for (int i = this.lastname.length(); i < STRING_MAX_LENGTH; i++) {
				lastNameLong += " ";
			}
		} else {

			lastNameLong = this.lastname.substring(0, STRING_MAX_LENGTH);
		}
		return lastNameLong;
	}

	public String getFirstnameLong() {
		String firstNameLong = this.firstname;

		if (firstNameLong.length() < STRING_MAX_LENGTH) {
			for (int i = this.firstname.length(); i < STRING_MAX_LENGTH; i++) {
				firstNameLong += " ";
			}
		} else {

			firstNameLong = this.firstname.substring(0, STRING_MAX_LENGTH);
		}
		return firstNameLong;
	}

	public String getDepartmentLong() {
		String departmentLong = this.department;

		if (departmentLong.length() < STRING_MAX_LENGTH) {
			for (int i = this.department.length(); i < STRING_MAX_LENGTH; i++) {
				departmentLong += " ";
			}
		} else {

			departmentLong = this.department.substring(0, STRING_MAX_LENGTH);
		}
		return departmentLong;
	}

	public String getTrainingLong() {
		String trainingLong = this.training;

		if (trainingLong.length() < STRING_MAX_LENGTH) {
			for (int i = this.training.length(); i < STRING_MAX_LENGTH; i++) {
				trainingLong += " ";
			}
		} else {

			trainingLong = this.training.substring(0, STRING_MAX_LENGTH);
		}
		return trainingLong;
	}
	
	// ******************************
    // Override Methods
    // ******************************
	
	@Override
	public String toString() {
		return "toString Intern : " + lastname + " " + firstname + ", departement : " + department + ", promo : "
				+ training + " en " + year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Intern intern = (Intern) obj;
		return lastname.equals(intern.lastname) && firstname.equals(intern.firstname)
				&& department.equals(intern.department) && training.equals(intern.training) && year == intern.year;
	}

}
