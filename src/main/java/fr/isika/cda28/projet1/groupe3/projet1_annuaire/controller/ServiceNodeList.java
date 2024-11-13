package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.WireframeBasic;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;

public class ServiceNodeList {

	// ******************************
	// Attribute
	// ******************************

	List<Node> nodesInterns;
	BinaryTreeToFile binaryTreeToFile;
	private RandomAccessFile raf;

	// ******************************
	// Constructor
	// ******************************

	public ServiceNodeList() {
		super();
		this.nodesInterns = new ArrayList<>();
		this.binaryTreeToFile = new BinaryTreeToFile();

	}

	// ******************************
	// Getter & Setter
	// ******************************

	public List<Node> getNodesInterns() {
		return nodesInterns;
	}

	// ******************************
	// Public Method
	// ******************************

	/**
	 * Crée une liste de nœuds (Node) triée par ordre alphabétique en parcourant
	 * l'arbre binaire. Utilise une traversée en ordre (in-order traversal) pour
	 * ajouter les nœuds à la liste.
	 * 
	 * @param index L'index du nœud de départ pour la traversée de l'arbre binaire.
	 * @return La liste triée des nœuds (Node) contenant des informations sur les
	 *         stagiaires.
	 * @throws IOException Si une erreur survient lors de la lecture des nœuds ou de
	 *                     l'ouverture du fichier.
	 */
	public List<Node> createListAlpha(int index) throws IOException {
		Node node = binaryTreeToFile.readNode(index); // lacroix index 0 || Chav index 1
		raf = binaryTreeToFile.createRaf();

		if (node.getLeftSon() > -1) {
			createListAlpha(node.getLeftSon());
		}

		nodesInterns.add(node);

		if (node.getRightSon() > -1) {
			createListAlpha(node.getRightSon());
		}

		return nodesInterns;
	}

	/**
	 * Affiche les éléments d'une liste de nœuds dans la console. Chaque nœud de la
	 * liste est imprimé en appelant la méthode toString() de Node.
	 * 
	 * @param list La liste des nœuds à afficher.
	 */
	public void readList(List<Node> list) {
		for (Node node : list) {
			System.out.println(node);

		}
	}

	/**
	 * Crée un fichier RandomAccessFile pour accéder à un fichier binaire en mode
	 * lecture-écriture. Le fichier utilisé est "STAGIAIREs_EXTRAIT.bin" dans le
	 * répertoire ressources.
	 * 
	 * @throws IOException Si une erreur survient lors de l'ouverture du fichier.
	 */
	public void createRaf() {
		try {
			raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
