package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class Node {

	public Intern intern;
	
	private Node left;
	
	private Node right;

	public Node(Intern intern, Node left, Node right) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
	}
	
	

	public Node(Intern intern) {
		super();
		this.intern = intern;
		this.left = null;
		this.right = null;
	}



	public Intern getIntern() {
		return intern;
	}

	public void setIntern(Intern intern) {
		this.intern = intern;
	}

	public Node getLeftSon() {
		return left;
	}

	public void setLeftSon(Node left) {
		this.left = left;
	}

	public Node getRightSon() {
		return right;
	}

	public void setRightSon(Node right) {
		this.right = right;
	}
	
	
}
