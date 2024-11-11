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

	public int indexCalculation(int currentIndex, int position) {
		int index = 0;
		try {

			raf.seek((currentIndex + 1) * Node.BYTE_LENGTH_NODE - position);
			index = raf.readInt();
			if (position == 8) {
				raf.seek(raf.getFilePointer() + 4);
				System.out.println(
						"si je suis a guache mon pointeur est deeplace de +4 dans la methode indexcalculation : "
								+ raf.getFilePointer());
			}
			raf.seek(0);
			System.out.println(raf.getFilePointer() + "<- pointeur ||| index -> " + index);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return index;
	}
	
	public int findNextIndex(Node currentNode) throws IOException {
		List<Node> listNodes = createListAlpha(0);
		
		System.out.println(currentNode);
		int index = listNodes.indexOf(currentNode);
		System.out.println("index currentIndex dans la liste : " + index);
		Node nextNode = listNodes.get(index +1);
//		System.out.println("le parent du stagiaire a supprimer est : " + nextNode);
//		System.out.println("index retourné : " +searchIndexIntern(0, nextNode.getIntern()));
		return searchIndexIntern(0, nextNode.getIntern());
	}

	public Node nextNode(Intern searchedIntern) throws IOException {
		createRaf();
		System.out.println("raf : " + raf);
		int index = searchIndexIntern(0, searchedIntern); // augereau
		System.out.println("l index de depart est : " + index);
		Node node = binaryTreeToFile.readNode(index);
		System.out.println("le noeud du stagiaire a supprimer est : " + node);
		System.out.println("index du fils droit est " + node.getRightSon());
		
		if(node.getRightSon() == -1) {
			System.out.println("noeud garijo : " + raf.getFilePointer());
			int nextIndex = findNextIndex(node);
			System.out.println("nextIndex : " + nextIndex);
			System.out.println(binaryTreeToFile.readNode(nextIndex));
			return binaryTreeToFile.readNode(nextIndex);
		}
		
		Node currentNode = binaryTreeToFile.readNode(node.getRightSon());
		System.out.println("le fils droit du noeud a supprimer est : " + currentNode);
		System.out.println("fils gauche du fils droit " + currentNode.getLeftSon());

		while (currentNode.getLeftSon() != -1) {
			System.out.println("le fils gauche du noeud a supprimer est : " + currentNode);
			currentNode = binaryTreeToFile.readNode(currentNode.getLeftSon());
		}
		System.out.println("noeud courant " + currentNode);

		return currentNode; //
	}

	public int searchIndexIntern(int currentIndex, Intern searchedIntern) throws IOException {

		Node root = binaryTreeToFile.readNode(currentIndex);
		System.out.println("root de searchIntern  est " + root); // noeud lacroix
		Intern internRoot = root.getIntern(); // lacroix

		if (searchedIntern.getLastname().compareTo(internRoot.getLastname()) == 0) {
			System.out.println("le pointeurt est a " + raf.getFilePointer());
			int index = (int) (raf.getFilePointer() / Node.BYTE_LENGTH_NODE);
			
			System.out.println("index quand searchIntern = intern de root : " + index);

			return index;
		}
		if ((searchedIntern.getLastname().compareTo(internRoot.getLastname())) < 0) {
			System.out.println("a gauche dans search");
			if (root.getLeftSon() == -1) {
				return -1;
			}

			return searchIndexIntern(indexCalculation(currentIndex, 8), searchedIntern);
		} else {
			System.out.println("a droite dans search");
			if (root.getRightSon() == -1) {
				return -1;
			}
			return searchIndexIntern(indexCalculation(currentIndex, 4), searchedIntern);
		}
	}
	
//	public Node deleteRoot(Node currentNode) {
//		if (currentNode.getLeftSon() == -1 && currentNode.getRightSon() == -1)  // pas d’enfant
//			return null;
//		
//		if (currentNode.getLeftSon() == -1 && currentNode.getRightSon() != -1)  // un enfant à droite
//			return binaryTreeToFile.readNode(currentNode.getRightSon());
//		
//		if (currentNode.getLeftSon() != -1 && currentNode.getRightSon() == -1)  // un enfant à gauche
//			return binaryTreeToFile.readNode(currentNode.getLeftSon());
//		
//		Node nextNode = nextNode();
//		this.valeur = noeudSuccesseur.valeur;
//		this.supprimerNoeud(this.valeur);
//		return this; // return l’objet dans lequel on se trouve
//	}

}
