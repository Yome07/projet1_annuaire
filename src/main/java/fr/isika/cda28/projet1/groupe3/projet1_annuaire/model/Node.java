package fr.isika.cda28.projet1.groupe3.projet1_annuaire.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;

public class Node {

	// ******************************
	// Constant
	// ******************************

	public final static int BYTE_LENGTH_NODE = Intern.BYTE_LENGTH_INTERN + 8;

	// ******************************
	// Attribute
	// ******************************
	
	private Intern intern;
	private int left;
	private int right;
	private BinaryTreeToFile binaryTreeToFile = new BinaryTreeToFile();
	private RandomAccessFile raf;

	// ******************************
	// Constructor
	// ******************************

	public Node(Intern intern, int left, int right) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
//		this.binaryTreeToFile;
	}

	public Node() {
		super();
	}

	// *******************************************
	// Getters & Setters
	// *******************************************

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

	// *******************************************
	// Public method
	// *******************************************

	/**
	 * Supprime un nœud de l'arbre binaire dans le fichier en fonction de sa
	 * position (feuille, un enfant ou deux enfants). Parcourt l'arbre à partir du
	 * nœud parent pour trouver le nœud à supprimer, et met à jour les liens des
	 * nœuds parents.
	 *
	 * @param nodeToDelete Le nœud à supprimer.
	 * @param raf          Le fichier contenant l'arbre binaire.
	 * @param indexParent  L'index du parent du nœud à supprimer.
	 * @throws IOException En cas d'erreur d'entrée/sortie.
	 */
	public void deleteNode(Node nodeToDelete, RandomAccessFile raf, int indexParent) throws IOException {

			//this = noued courant
			int indexCourant = (int) (raf.getFilePointer() - Node.BYTE_LENGTH_NODE) / Node.BYTE_LENGTH_NODE;
		

			//Node node = binaryTreeToFile.readNode(indexParent);
			
			if (this.compareTo(nodeToDelete) < 0) {
				if (this.getRightSon() == -1) {
					System.out.println("noeud introuvable");
					return;
				
				} else {
					raf.seek(raf.getFilePointer()  - 4);
					int rightSon = raf.readInt();
					binaryTreeToFile.readNode(rightSon).deleteNode(nodeToDelete, raf, indexCourant);					
				}


			} else if (this.compareTo(nodeToDelete) > 0) {
				if (this.getLeftSon() == -1) {
					return;
				} else {
					raf.seek(raf.getFilePointer() + BYTE_LENGTH_NODE - 8);				
					int leftSon = raf.readInt();
				binaryTreeToFile.readNode(leftSon).deleteNode(nodeToDelete, raf, indexCourant);
				}
			} else { 
				if (this.right == -1 && this.left == -1) {
					
					//int indexEnfant = (int) (raf.getFilePointer() - Node.BYTE_LENGTH_NODE)/ Node.BYTE_LENGTH_NODE;
					
					raf.seek(((indexParent +1) * Node.BYTE_LENGTH_NODE) - 4);
					int indexFDParent = raf.readInt();
					if (indexFDParent == indexCourant) {
						raf.seek(raf.getFilePointer() - 4);
						raf.writeInt(-1);
					} else {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(-1);
					}

				} else if (this.right == -1 || this.left == -1) {
					//int indexEnfant = (int) raf.getFilePointer() / Node.BYTE_LENGTH_NODE;
					raf.seek((indexParent + 1) * Node.BYTE_LENGTH_NODE - 4);
					int indexALire = raf.readInt();
					if (indexALire == indexCourant) {
						if(this.getRightSon() == -1) {
							raf.seek(raf.getFilePointer() - 4);
							raf.writeInt(this.getLeftSon());
						} else {
							raf.seek(raf.getFilePointer() - 4);
							raf.writeInt(this.getRightSon());			
						}
					} else {
						if(this.getRightSon() == -1) {
							raf.seek(raf.getFilePointer() - 8);
							raf.writeInt(this.getLeftSon());
						} else {
							raf.seek(raf.getFilePointer() - 8);
							raf.writeInt(this.getRightSon());			
						}
					}

				} else {

					this.deleteRoot(nodeToDelete, raf, indexParent, indexCourant);
				}
			}
		
	}

	/**
	 * Récupère le nœud suivant en suivant les fils gauches à partir du fils droit.
	 *
	 * @return Le nœud suivant dans l'arbre binaire.
	 * @throws IOException En cas d'erreur d'entrée/sortie.
	 */
	public Node nextNode(RandomAccessFile raf) throws IOException {
		//RandomAccessFile raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
		//raf.seek(raf.getFilePointer() + Node.BYTE_LENGTH_NODE - 4);
		//int rightSon = raf.readInt();

		Node currentNode = binaryTreeToFile.readNode(this.right);
		while (currentNode.left != -1) {
			//raf.seek(raf.getFilePointer() + Node.BYTE_LENGTH_NODE - 8);
			//int leftSon = raf.readInt();
			//raf.seek(raf.getFilePointer() + 4);
			currentNode = binaryTreeToFile.readNode(currentNode.left);
		}
		return currentNode;
	}

	/**
	 * Supprime la racine de l'arbre en la remplaçant par le nœud suivant. Met à
	 * jour les informations du stagiaire dans le fichier, puis supprime le nœud
	 * suivant.
	 *
	 * @param currentNode Le nœud à supprimer (racine).
	 * @param raf         Le fichier contenant les nœuds.
	 * @param indexParent L'index du parent dans le fichier.
	 * @return L'instance actuelle pour enchaîner les appels.
	 * @throws IOException En cas d'erreur d'entrée/sortie.
	 */
	public void deleteRoot(Node currentNode, RandomAccessFile raf, int indexParent, int currentIndex) throws IOException {

		Node nextNode = nextNode(raf);
		nextNode.setRightSon(this.right);
		nextNode.setLeftSon(this.left);
		//currentNode = nextNode;
		//int currentIndex = (int) ((raf.getFilePointer() - Node.BYTE_LENGTH_NODE) / Node.BYTE_LENGTH_NODE);
		raf.seek(currentIndex * Node.BYTE_LENGTH_NODE);
		binaryTreeToFile.writeNode(nextNode, currentIndex);
		
		/*
		 * String lastname = currentNode.getIntern().getLastnameLong();
		 * raf.writeChars(lastname); String firstname =
		 * currentNode.getIntern().getFirstnameLong(); raf.writeChars(firstname); String
		 * department = currentNode.getIntern().getDepartmentLong();
		 * raf.writeChars(department); String training =
		 * currentNode.getIntern().getTrainingLong(); raf.writeChars(training); int year
		 * = currentNode.getIntern().getYear(); raf.writeInt(year);
		 */

		raf.seek(this.right);
		Node rightSon = binaryTreeToFile.readNode(this.right);
		rightSon.deleteNode(nextNode, raf, currentIndex);
		//deleteNode(nextNode(), raf, indexParent);

	}

	// ******************************
	// Override Methods
	// ******************************

	/**
	 * Vérifie l'égalité entre deux nœuds en comparant leurs objets `intern`.
	 *
	 * @param obj L'objet à comparer.
	 * @return true si les nœuds sont égaux, false sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Node node = (Node) obj;
		return intern.equals(node.intern);
	}

	/**
	 * Compare deux nœuds en fonction du nom de famille de leur stagiaire.
	 *
	 * @param node Le nœud à comparer.
	 * @return -1 si le nom de famille du nœud actuel est avant, 1 s'il est après, 0
	 *         s'ils sont égaux.
	 */
	public int compareTo(Node node) {
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) < 0) {
			return -1;
		}
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) > 0) {
			return 1;
		}
		return 0;
	}

}
