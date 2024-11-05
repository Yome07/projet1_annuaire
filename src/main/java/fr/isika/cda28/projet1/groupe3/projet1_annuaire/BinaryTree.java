package fr.isika.cda28.projet1.groupe3.projet1_annuaire;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

/*
 * Cette classe décrit la contruction de l’arbre binaire dans un fichier binaire
 * @param raf : fichier binaire
 */
public class BinaryTree extends ListInterns {

	public Node root;
	public RandomAccessFile raf;
	public final static int NODE_SIZE = Intern.BYTE_LENGTH_INTERN + 2 * 4;

	public BinaryTree() {
		super();
		this.root = null;
	}

	/*
	 * méthode pour créer le fichier binaire
	 */
	public void createRaf() {
		try {
			this.raf = new RandomAccessFile("src/main/java/ressources/STAGIAIREs_EXTRAIT.bin", "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * méthode pour créer un arbre binaire
	 */
	public void createBinaryTree() {
		try {
			// Lecture du fichier fourni
			readDonFile();
			// Création du fichier binaire
			createRaf();

			// On parcourt la liste de stagiaires
			for(int i = 0; i < interns.size() -1; i++) {
				// Création du nœud avec le stagiaire n°i et les index de nœud sans fils
				Node node = new Node(interns.get(i), -1, -1);
				
				// Si ce n’est pas le premier stagiaire, on doit l’insérer dans l’arbre.
				// appel de la méthode inserNode permettant de déterminer à quel endroit il sera insérer
				if (i > 0) {
					insertNode(0, i, interns.get(i));
				}

				// Écriture du nœud à la fin du fichier
				writeNode(node, i);
			}

			// Fermeture du fichier
			raf.close();

		} catch (IOException e) {
			// System.out.println("Erreur de creation d'arbre binaire : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * méthode pour insérer un nœud dans l’arbre
	 * 
	 * @param currentNode position du nœud actuel dans le fichier
	 * 
	 * @param indexToInsert position du nouveau nœud à insérer
	 * @param intern stagiaire 
	 */
	public void insertNode(int currentIndex, int indexToInsert, Intern intern) {

		Node currentNode = readNode(currentIndex, intern);
		Node nodeToInsert = readNode(indexToInsert, intern);
		if (nodeToInsert.intern.getLastnameLong().compareTo(currentNode.intern.getLastnameLong()) < 0
				|| (nodeToInsert.intern.getLastnameLong() == currentNode.intern.getLastnameLong()
						&& nodeToInsert.intern.getFirstnameLong().compareTo(currentNode.intern.getFirstnameLong()) < 0)
				|| (nodeToInsert.intern.getFirstnameLong() == currentNode.intern.getFirstnameLong()
						&& nodeToInsert.intern.getDepartmentLong().compareTo(currentNode.intern.getDepartmentLong()) < 0)
				|| (nodeToInsert.intern.getDepartmentLong() == currentNode.intern.getDepartmentLong()
						&& nodeToInsert.intern.getTrainingLong().compareTo(currentNode.intern.getTrainingLong()) < 0)
				|| (nodeToInsert.intern.getTrainingLong() == currentNode.intern.getTrainingLong()
						&& nodeToInsert.intern.getYear() < currentNode.intern.getYear())) {

			if (currentNode.getLeftSon() == -1) {
				int left = currentNode.getLeftSon();
				left = indexToInsert;
				writeNode(currentNode, indexToInsert);
			} else {
				insertNode(currentNode.getLeftSon(), indexToInsert, intern);
			}
		} else {
			if (currentNode.getRightSon() == -1) {
				int right = currentNode.getRightSon();
				right = indexToInsert;
				writeNode(currentNode, indexToInsert);
			} else {
				insertNode(currentNode.getRightSon(), indexToInsert, intern);
			}
		}
	}

	public Node readNode(int index, Intern intern) {
		Node node = new Node(intern);
		try {
			raf.seek(index * NODE_SIZE);
			node.intern.lastname = readString();
			node.intern.firstname = readString();
			node.intern.department = readString();
			node.intern.training = readString();
			node.intern.year = raf.readInt();
			int left = node.getLeftSon();
			left = raf.readInt();
			int right = node.getRightSon();
			right = raf.readInt();
		} catch (IOException e) {
			//System.out.println("Erreur de lecture : " + e.getMessage());
			e.printStackTrace();
		}
		return node;

	}

	public void writeNode(Node node, int index) {
		try {
			raf.seek(index * NODE_SIZE);
			String lastname = node.intern.getLastnameLong();
			raf.writeChars(lastname);
			String firstname = node.intern.getFirstnameLong();
			raf.writeChars(firstname);
			String department = node.intern.getDepartmentLong();
			raf.writeChars(department);
			String training = node.intern.getTrainingLong();
			raf.writeChars(training);
			int year = node.intern.getYear();
			raf.writeInt(year);

			raf.writeInt(node.getLeftSon());
			raf.writeInt(node.getRightSon());

		} catch (IOException e) {
			// System.out.println("Erreur d’écriture : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String readString() {
		byte[] lengthAttributs = new byte[2 * Intern.STRING_MAX_LENGTH];
		int buffer = 0;
		try {
			buffer = this.raf.read(lengthAttributs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(lengthAttributs + " " + buffer);
		String myAttribute = new String(lengthAttributs);

		return myAttribute;
	}

}
