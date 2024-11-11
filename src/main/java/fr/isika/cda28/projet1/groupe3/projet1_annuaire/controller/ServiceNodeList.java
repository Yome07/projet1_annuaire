package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.WireframeBasic;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;

public class ServiceNodeList {

	// Attributs
	List<Node> nodesInterns;
	BinaryTreeToFile binaryTreeToFile;
	private RandomAccessFile raf;

	// constructors
	public ServiceNodeList() {
		super();
		this.nodesInterns = new ArrayList<>();
		this.binaryTreeToFile = new BinaryTreeToFile();

	}

	// getters setters
	public List<Node> getNodesInterns() {
		return nodesInterns;
	}

	// methods

	public List<Node> createListAlpha(int index) {
		Node node = binaryTreeToFile.readNode(index); // lacroix index 0 || Chav index 1

		if (node.getLeftSon() > -1) {

			createListAlpha(node.getLeftSon());
		}
//		}else if (node.getLeftSon()==-1) {

		nodesInterns.add(node);

		if (node.getRightSon() > -1) {

			createListAlpha(node.getRightSon());
		}

		return nodesInterns;
	}

	public void readList(List<Node> list) {
		for (Node node : list) {
			System.out.println(node);

		}
	}

	public void createRaf() {
		try {
			raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int indexCalculation(int currentIndex, int position) { // 0 8 || 1, 4
		int index = 0;

		try {
			System.out.println("le pointeur dans indexcalcuation est a " + raf.getFilePointer());
			raf.seek((currentIndex + 1) * Node.BYTE_LENGTH_NODE - position);
			index = raf.readInt();
			System.out.println(index); // 1 || 2
			if (position == 8) {
				raf.seek(raf.getFilePointer() + 4);
			}

			System.out.println(raf.getFilePointer() + "<- pointeur ||| index -> " + index);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return index;
	}

	public int findNextIndex(Node currentNode) throws IOException {
		List<Node> listNodes = createListAlpha(0);

		int index = listNodes.indexOf(currentNode);
		Node nextNode = listNodes.get(index - 1);

		System.out.println("int dans findNextIndex est : " + searchIndexIntern(0, nextNode.getIntern())
				+ " l'intern est " + nextNode.getIntern());
		return searchIndexIntern(0, nextNode.getIntern());
	}

	public Node previousNode(Intern searchedIntern) throws IOException {
		createRaf();
		int index = searchIndexIntern(0, searchedIntern);
		System.out.println("index dans nextNode : " + index);
		Node node = binaryTreeToFile.readNode(index);

		if (node.getRightSon() == -1) {
			int nextIndex = findNextIndex(node);
			System.out.println("la sortie de next node est : " + binaryTreeToFile.readNode(nextIndex));
			return binaryTreeToFile.readNode(nextIndex);
		}

		Node currentNode = binaryTreeToFile.readNode(node.getRightSon());

		while (currentNode.getLeftSon() != -1) {
			currentNode = binaryTreeToFile.readNode(currentNode.getLeftSon());
		}

		return currentNode;
	}

	public int searchIndexIntern(int currentIndex, Intern searchedIntern) throws IOException { // 0, Lacroix
		raf.seek(currentIndex * Node.BYTE_LENGTH_NODE);
		Node root = binaryTreeToFile.readNode(currentIndex); // 0 noeud lacroix // 1 noeud cahveneau || 2 noeud garijo
		Intern internRoot = root.getIntern(); // intern lacroix // intern chaveneau || intern garijo

		if (searchedIntern.getLastname().compareTo(internRoot.getLastname()) == 0) {

			System.out.println(raf.getFilePointer() + "<- pointeur || root est : " + root);
			int index = (int) (raf.getFilePointer() / Node.BYTE_LENGTH_NODE);

			return index; // 2
		}
		if ((searchedIntern.getLastname().compareTo(internRoot.getLastname())) < 0) {
			System.out.println("a gauche dans search"); // je suis ici parce que garijo est plus petit que lacroix
			if (root.getLeftSon() == -1) {
				return -1;
			}

			return searchIndexIntern(indexCalculation(currentIndex, 8), searchedIntern); // 1, garijo
		} else {
			System.out.println("a droite dans search");
			if (root.getRightSon() == -1) {
				return -1;
			}
			return searchIndexIntern(indexCalculation(currentIndex, 4), searchedIntern); // ?? garijo
		}
	}

	public Node deleteRoot(Node currentNode) throws IOException { // chone
		if (currentNode.getLeftSon() == -1 && currentNode.getRightSon() == -1) {// pas d’enfant
			return null;
		}

		if (currentNode.getLeftSon() == -1 && currentNode.getRightSon() != -1) { // un enfant à droite
			return binaryTreeToFile.readNode(currentNode.getRightSon());
		}

		if (currentNode.getLeftSon() != -1 && currentNode.getRightSon() == -1) { // un enfant à gauche
			return binaryTreeToFile.readNode(currentNode.getLeftSon());
		}

		// 2 enfants
		Node nextNode = previousNode(currentNode.getIntern());
		currentNode = nextNode;
		deleteNode(currentNode);
		return null; // return l’objet dans lequel on se trouve
	}

	public void deleteNode(Node nodeToDeleted) {
		Node node = binaryTreeToFile.readNode(0);
		if (node.getIntern().getLastname().compareTo(nodeToDeleted.getIntern().getLastname()) < 0) { // valeur < valeurRechercher ––> on va à droite
			if (node.getRightSon() == -1) {
				return;
			}
			Node rightSonNode = binaryTreeToFile.readNode(node.getRightSon());
			if (rightSonNode.getIntern().getLastname().compareTo(nodeToDeleted.getIntern().getLastname()) == 0) {
				rightSonNode = deleteRoot(node);
			} else {
				this.filsDroit.supprimerNoeud(nodeToDeleted);
			}
		} else { // valeur > valeurRechercher ––> on va à gauche
			if (this.filsGauche == null)
				return;
			if (this.filsGauche.valeur.equals(nodeToDeleted))
				this.filsGauche = this.filsGauche.supprimerRacine();
			else
				this.filsGauche.supprimerNoeud(nodeToDeleted);
		}
	}

}
