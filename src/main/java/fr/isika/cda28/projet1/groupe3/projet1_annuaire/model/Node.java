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
	private int left;
	private int right;
	private BinaryTreeToFile binaryTreeToFile = new BinaryTreeToFile();
	private RandomAccessFile raf;

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
		if (this.intern.getLastname().compareTo(node.intern.getLastname()) < 0) {
			return -1;
		}// else if (this.intern.getFirstname().compareTo(node.intern.getFirstname()) < 0) {
//			return -1;
//		} else if (this.intern.getDepartment().compareTo(node.intern.getDepartment()) < 0) {
//			return -1;
//		} else if (this.intern.getTraining().compareTo(node.intern.getTraining()) < 0) {
//			return -1;
//		} else if (this.intern.getYear() < node.intern.getYear()) {
//			return -1;
//		}

		if (this.intern.getLastname().compareTo(node.intern.getLastname()) > 0) {
			return 1;
		} //else if (this.intern.getFirstname().compareTo(node.intern.getFirstname()) > 0) {
//			return 1;
//		} else if (this.intern.getDepartment().compareTo(node.intern.getDepartment()) > 0) {
//			return 1;
//		} else if (this.intern.getTraining().compareTo(node.intern.getTraining()) > 0) {
//			return 1;
//		} else if (this.intern.getYear() < node.intern.getYear()) {
//			return 1;
//		}

		return 0;

	}

	public void deleteNode(Node nodeToDelete, RandomAccessFile raf, int indexParent) throws IOException {

		File file = new File("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin");
		raf = binaryTreeToFile.createRaf();
		if (file.exists() && file.length() > 0) {
			
			Node node = binaryTreeToFile.readNode(indexParent); // on lit la racine i= 0
			System.out.println("poition " +raf.getFilePointer());
			indexParent = (int) (raf.getFilePointer() - Node.BYTE_LENGTH_NODE) / Node.BYTE_LENGTH_NODE;
			if (this.compareTo(nodeToDelete) < 0) { // negatif -> droite
				if (this.getRightSon() == -1) {
					return;
				}
				System.out.println("je suis à droite" + this.compareTo(nodeToDelete));
				raf.seek(raf.getFilePointer()  - 4);
				int rightSon = raf.readInt();
				
				binaryTreeToFile.readNode(rightSon).deleteNode(nodeToDelete, raf, rightSon);
			} else if (this.compareTo(nodeToDelete) > 0) { // positif -> gauche
				if (this.getLeftSon() == -1) {
					return;
				}
				System.out.println("je suis à gauche" + this.compareTo(nodeToDelete));
				
				raf.seek(raf.getFilePointer()  - 8);
				int leftSon = raf.readInt();
				System.out.println("leftSon : " + leftSon);
				raf.seek(raf.getFilePointer() + 4);
				binaryTreeToFile.readNode(leftSon).deleteNode(nodeToDelete, raf, leftSon);
			} else { // 0 -> a supprimer
				System.out.println("j’ai trouvé le noeud");
				if (this.right == -1 && this.left == -1) { // feuille
					System.out.println("je suis une feuille");
					int indexEnfant = (int) (raf.getFilePointer() - Node.BYTE_LENGTH_NODE)/ Node.BYTE_LENGTH_NODE;
					System.out.println("index enfant " + indexEnfant);
					
					raf.seek((indexParent + 1) * Node.BYTE_LENGTH_NODE - 4); // position pere
					int indexALire = raf.readInt(); // j ai avance de 4 octets
					System.out.println("index a lire " + indexALire);
					System.out.println("raf " + raf.getFilePointer());
					if (indexEnfant == indexALire) {
						raf.seek(raf.getFilePointer() - 4);
						raf.writeInt(-1);
					} else {
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(-1);
					}

				} else if (this.right == -1 || this.left == -1) {// un enfant
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

	public Node nextNode() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
		raf.seek(raf.getFilePointer() + Node.BYTE_LENGTH_NODE - 4);
		int rightSon = raf.readInt();
		
		
		Node currentNode = binaryTreeToFile.readNode(rightSon);
		while (currentNode.left != -1) {
			raf.seek(raf.getFilePointer() + Node.BYTE_LENGTH_NODE - 8);
			int leftSon = raf.readInt();
			raf.seek(raf.getFilePointer() + 4);
			currentNode = binaryTreeToFile.readNode(leftSon);
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

