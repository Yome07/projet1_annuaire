package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.ServiceNodeList;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;

public class LauncherTest {

	public static void main(String[] args) throws IOException {
		BinaryTreeToFile binaryTreeToFile = new BinaryTreeToFile();
		Node node = binaryTreeToFile.readNode(0);
		RandomAccessFile raf = binaryTreeToFile.createRaf();
		List<Node> nodes;

		System.out.println("avant suppression");
		ServiceNodeList nodeList = new ServiceNodeList();

		nodes = nodeList.createListAlpha(0);
		System.out.println("taille liste " + nodes.size());
		System.out.println(nodes.get(0));
//		 for (Node n : nodes) {
//		 System.out.println(n);
//		 }

		node.deleteNode(nodes.get(0), raf, 0);
		System.out.println("Après suppression");
//		for (Node n : nodes) {
//		System.out.println(n);
//		}
		System.out.println("taille liste " + nodes.size());

	}

}
