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
	
	public String getLastNameLong() {
        String lastNameLong = this.lastname;

        if (lastNameLong.length() < LENGTH_MAX_LASTNAME) {
            for (int i = lastNameLong.length(); i < LENGTH_MAX_LASTNAME; i++) {
                lastNameLong += " ";
            }
        } else {
            
            lastNameLong = this.lastname.substring(0, LENGTH_MAX_LASTNAME);
        }
        return lastNameLong;
    }
	
	
	public String getFirstNameLong() {
        String firstNameLong = this.firstname;

        if (firstNameLong.length() < LENGTH_MAX_FIRSTNAME) {
            for (int i = firstNameLong.length(); i < LENGTH_MAX_FIRSTNAME; i++) {
                firstNameLong += " ";
            }
        } else {
            
            firstNameLong = this.firstname.substring(0, LENGTH_MAX_FIRSTNAME);
        }
        return firstNameLong;
    }

	public String getDepartmentLong() {
        String departmentLong = this.department;

        if (departmentLong.length() < LENGTH_MAX_DEPARTMENT) {
            for (int i = departmentLong.length(); i < LENGTH_MAX_DEPARTMENT; i++) {
                departmentLong += " ";
            }
        } else {
            
            departmentLong = this.department.substring(0, LENGTH_MAX_DEPARTMENT);
        }
        return departmentLong;
    }
	
	public String getTrainingLong() {
        String trainingLong = this.training;

        if (trainingLong.length() < LENGTH_MAX_TRAINING) {
            for (int i = trainingLong.length(); i < LENGTH_MAX_TRAINING; i++) {
                trainingLong += " ";
            }
        } else {
            
            trainingLong = this.training.substring(0, LENGTH_MAX_TRAINING);
        }
        return trainingLong;
    }
	
	

//getters et setters
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
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
