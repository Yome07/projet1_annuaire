package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ServiceNodeList {

	// Attributs
	List<Node> nodesInterns;
	BinaryTreeToFile binaryTreeToFile;

	// constructors
	public ServiceNodeList() {
		super();
		this.nodesInterns = new ArrayList<>();
		this.binaryTreeToFile = new BinaryTreeToFile();
		// this.raf = BinaryTreeToFile.createRaf();
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
	
	

//	public void createListAlpha(int index) { //1
//
//		Node node = binaryTreeToFile.readNode(index); // lacroix index 0 || Chav index 1
//		
//		
//		if (node.getLeftSon() > -1) { //FG 1
//			int indexLeftSon = node.getLeftSon(); //1
//			try {
//				binaryTreeToFile.raf.seek(indexLeftSon * Node.BYTE_LENGTH_NODE); // pointeur 1*212
//				createListAlpha(indexLeftSon); //1
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			Node child = binaryTreeToFile.raf.seek();
//			nodesInterns.add(node);// nomnre octet
//		}
//		
//		nodesInterns.add(node);
//		// quand j arrive ici, node vaut chavenau et je veux lacroix 
////		
//		
//	}

}
