package fr.isika.cda28.projet1.groupe3.projet1_annuaire.model;

public class Node {

	public final static int BYTE_LENGTH_NODE = Intern.BYTE_LENGTH_INTERN + 8;

	private Intern intern;
	private int left;
	private int right;
	private Node parent;

	public Node(Intern intern, int left, int right) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
	}

	public Node(Intern intern, int left, int right, Node parent) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
		this.parent = parent;
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

	@Override
	public String toString() {
		return "Node [intern=" + intern + ", left=" + left + ", right=" + right + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Node node = (Node) obj;
		return intern.equals(node.intern);

	}
	
	public int compareTo(Node node) {
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) < 0 ) {
			return -1;
		} else if (this.intern.getFirstname().compareTo(node.intern.getFirstname()) < 0) {
			return -1;
		} else if (this.intern.getDepartment().compareTo(node.intern.getDepartment()) < 0) {
			return -1;
		} else if (this.intern.getTraining().compareTo(node.intern.getTraining()) < 0) {
			return -1;
		} else if (this.intern.getYear() < node.intern.getYear()) {
			return -1;
		} 
		
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) > 0 ) {
			return 1;
		} else if (this.intern.getFirstname().compareTo(node.intern.getFirstname()) > 0) {
			return 1;
		} else if (this.intern.getDepartment().compareTo(node.intern.getDepartment()) >0) {
			return 1;
		} else if (this.intern.getTraining().compareTo(node.intern.getTraining()) > 0) {
			return 1;
		} else if (this.intern.getYear() < node.intern.getYear()) {
			return 1;
		} 
		
		return 0;
		
	}

}
