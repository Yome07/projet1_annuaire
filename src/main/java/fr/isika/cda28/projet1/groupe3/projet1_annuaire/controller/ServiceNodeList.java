package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

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

	public int indexCalculation(int currentIndex, int position) {
		//binaryTreeToFile.createRaf();
		int index = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
			// Warning : sans cette instance de RAF, la methode createRaf() importe est null
			raf.seek((currentIndex + 1) * Node.BYTE_LENGTH_NODE - position);
			index = raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}

	public int searchIntern(int currentIndex, Intern searchedIntern) throws IOException {

		Node root = binaryTreeToFile.readNode(currentIndex);
		Intern internRoot = root.getIntern();

		if (searchedIntern.getLastname() == internRoot.getLastname()) {
			int index = (int) (raf.getFilePointer() / Node.BYTE_LENGTH_NODE);
			return index;
		}
		if ((searchedIntern.getLastname().compareTo(internRoot.getLastname())) < 0) {
			if (root.getLeftSon() == -1) {
				return -1;
			}
			return searchIntern(indexCalculation(currentIndex, 8), searchedIntern);
		} else {
			if (root.getRightSon() == -1) {
				return -1;
			}
			return searchIntern(indexCalculation(currentIndex, 4), searchedIntern);
		}
	}

}
