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

	public List<Node> createListAlpha(int index) throws IOException {
		Node node = binaryTreeToFile.readNode(index); // lacroix index 0 || Chav index 1
		raf = binaryTreeToFile.createRaf();
		
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

}
