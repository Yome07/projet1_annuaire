package fr.isika.cda28.projet1.groupe3.projet1_annuaire.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;

public class Node {

	public final static int BYTE_LENGTH_NODE = Intern.BYTE_LENGTH_INTERN + 8;

	private Intern intern;
	private Node left;
	private Node right;
	private BinaryTreeToFile binaryTreeToFile;

	public Node(Intern intern, Node left, Node right) {
		super();
		this.intern = intern;
		this.left = left;
		this.right = right;
		this.binaryTreeToFile = new BinaryTreeToFile();
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
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) < 0) {
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

		if (this.intern.getLastname().compareTo(node.intern.getLastname()) > 0) {
			return 1;
		} else if (this.intern.getFirstname().compareTo(node.intern.getFirstname()) > 0) {
			return 1;
		} else if (this.intern.getDepartment().compareTo(node.intern.getDepartment()) > 0) {
			return 1;
		} else if (this.intern.getTraining().compareTo(node.intern.getTraining()) > 0) {
			return 1;
		} else if (this.intern.getYear() < node.intern.getYear()) {
			return 1;
		}

		return 0;

	}

	public void deleteNode(Node nodeToDelete, RandomAccessFile raf, int indexParent) throws IOException {

		File file = new File("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin");

		if (file.exists() && file.length() > 0) {
			binaryTreeToFile.readNode(indexParent); // on lit la racine i= 0
			indexParent = (int) (raf.getFilePointer() - Node.BYTE_LENGTH_NODE) / Node.BYTE_LENGTH_NODE;

			if (this.compareTo(nodeToDelete) < 0) { // negatif -> droite
				if (this.getRightSon() == null) {
					return;
				}
				this.right.deleteNode(nodeToDelete, raf, indexParent);
			} else if (this.compareTo(nodeToDelete) > 0) { // positif -> gauche
				if (this.getLeftSon() == null) {
					return;
				}
				this.left.deleteNode(nodeToDelete, raf, indexParent);
			} else { // 0 -> a supprimer

				if (this.right == null && this.left == null) { // feuille
					int indexEnfant = (int) raf.getFilePointer() / Node.BYTE_LENGTH_NODE;
					raf.seek((indexParent + 1) * Node.BYTE_LENGTH_NODE - 4); // position pere
					int indexALire = raf.readInt(); // j ai avance de 4 octets
					if (indexEnfant == indexALire) {
						raf.seek(raf.getFilePointer() - 4);
						raf.writeInt(-1);
					} else {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(-1);
					}

				} else if (this.right == null || this.left == null) {// un enfant
					int indexEnfant = (int) raf.getFilePointer() / Node.BYTE_LENGTH_NODE;
					raf.seek((indexParent + 1) * Node.BYTE_LENGTH_NODE - 4); // position pere
					int indexALire = raf.readInt();
					if (indexALire == -1) {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(-1);
					} else {
						raf.seek(raf.getFilePointer() - 4);
						raf.writeInt(-1);
					}

				} else { // 2 enfants -> appel deleteRoot
					// appel deleteRoot

//					this.deleteRoot(nodeToDelete, raf, indexParent);
				}
			}
		} else {
			System.out.println("Pas de fichier ou fichier vide");
		}
	}

	public Node nextNode() {
		Node currentNode = this.right;
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}

	public Node deleteRoot(Node currentNode, RandomAccessFile raf, int indexParent) throws IOException {
		// 2 enfants
		Node nextNode = nextNode();
		currentNode = nextNode; // on remplace les infos stagiaire, pas les index
		int currentIndex = (int) ((raf.getFilePointer() - Node.BYTE_LENGTH_NODE) / Node.BYTE_LENGTH_NODE);
		raf.seek(currentIndex);
		
		String lastname = currentNode.getIntern().getLastnameLong();
		raf.writeChars(lastname);
		String firstname = currentNode.getIntern().getFirstnameLong();
		raf.writeChars(firstname);
		String department = currentNode.getIntern().getDepartmentLong();
		raf.writeChars(department);
		String training = currentNode.getIntern().getTrainingLong();
		raf.writeChars(training);
		int year = currentNode.getIntern().getYear();
		raf.writeInt(year);
		
		raf.seek(raf.getFilePointer() + 8);
		deleteNode(nextNode(), raf, indexParent);
		
		return this; 
	}
	


}

