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

}
