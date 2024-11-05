package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Node {

	public Intern intern;

	private int left;

	private int right;

	public Node(Intern intern, int left, int right) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
	}

	public Node(Intern intern) {
		super();
		this.intern = intern;
		this.left = 0;
		this.right = 0;
	}
	
	

	public Node() {
		super();
	}

	public Intern getIntern() {
		return intern;
	}

	public void setIntern(Intern intern) {
		this.intern = intern;
	}

	public int getLeftSon() {
		return left;
	}

	public void setLeftSon(int left) {
		this.left = left;
	}

	public int getRightSon() {
		return right;
	}

	public void setRightSon(int right) {
		this.right = right;
	}

	// Méthode récursive

//	public void addIntern(Intern newIntern) {
//		int nextNode = (this.intern.lastname.compareTo(newIntern.lastname) < 0) ? this.right : this.left;
//
//		if (nextNode == 0) {
//			if (this.intern.lastname.compareTo(newIntern.lastname) < 0) {
//				this.right = new Node(intern, null, null);
//			} else {
//				this.left = new Node(intern, null, null);
//			}
//		}else {
//			nextNode.addIntern(newIntern);
//		}
//	}

}