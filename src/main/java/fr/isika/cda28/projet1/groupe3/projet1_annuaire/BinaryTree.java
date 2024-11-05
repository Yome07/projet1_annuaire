package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

public class BinaryTree {
	
	Node root;
	
	public BinaryTree() {
		super();
		this.root = null;
	}

	public void addRoot(Intern intern) {
		if (this.root == null) {
			this.root = new Node(intern);
			System.out.println("racine ajoutée avec stagiaire : " + intern);
		} else {
			System.out.println("L’arbre a déjà une racine.");
		}
	}
}
