package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.BinaryTreeToFile;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller.ServiceNodeList;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {



	@Override
	public void start(Stage stage) throws IOException {

		WireframeBasic wireframe = new WireframeBasic();
		Scene scene = new Scene(wireframe.root, 1000, 800);
		
		
		BinaryTreeToFile binaryTreeToFile = new BinaryTreeToFile();
		Node node = binaryTreeToFile.readNode(0);
		RandomAccessFile raf = binaryTreeToFile.createRaf();
		List<Node> nodes;
		
		System.out.println("avant suppression");
		ServiceNodeList nodeList = new ServiceNodeList();
		
		nodes = nodeList.createListAlpha(0);
//		System.out.println(nodes.get(5));
//		for (Node n : nodes) {
//			System.out.println(n);
//		}
		
		node.deleteNode(nodes.get(0), raf, 0);
		System.out.println("Après suppression");
		for (Node n : nodes) {
			System.out.println(n);
		}

		stage.setTitle("Stud'Index");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}