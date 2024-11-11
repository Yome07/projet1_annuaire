package fr.isika.cda28.projet1.groupe3.projet1_annuaire.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Intern;
import fr.isika.cda28.projet1.groupe3.projet1_annuaire.model.Node;

/*
 * Cette classe décrit la contruction de l’arbre binaire dans un fichier binaire
 * @param raf : fichier binaire
 */
public class BinaryTreeToFile extends ListInterns {

	public Node root;
	public RandomAccessFile raf;

	public BinaryTreeToFile() {
		super();
		this.root = null;
		createRaf();
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

			// On parcourt la liste de stagiaires
			for (int i = 0; i < interns.size(); i++) {
				// Création du nœud avec le stagiaire n°i et les index de nœud sans fils
				Node node = new Node(interns.get(i), -1, -1); // creation du node a inserer

				// Si ce n’est pas le premier stagiaire, on doit l’insérer dans l’arbre.
				// appel de la méthode inserNode permettant de déterminer à quel endroit il sera
				// insérer
				if (i > 0) {
					insertNode(0, i, interns.get(i), node);
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
	 * 
	 * @param intern stagiaire
	 */

	public void insertNode(int currentIndex, int indexToInsert, Intern intern, Node node) {
		System.out.println("intern avant readNode : " + intern); // Intern ok

		Node nodeToInsert = new Node(intern, -1, -1);
		Intern internToInsert = nodeToInsert.getIntern();
		System.out.println("internToInsert avant appel de la methode read node : " + internToInsert);

		Node currentNode = readNode(currentIndex);
		Intern currentIntern = currentNode.getIntern();
		System.out.println(intern);
		System.out.println("Le currentIntern est  : " + currentIntern);
		System.out.println(
				"FG currentNode : " + currentNode.getLeftSon() + " FD currentNode : " + currentNode.getRightSon());
		System.out.println("firstname currentIntern : " + currentIntern.getLastname());
		System.out.println("firstname InternToInsert : " + internToInsert.getLastname());

		if (internToInsert.getLastname().compareTo(currentIntern.getLastname()) < 0

				|| (internToInsert.getLastname() == currentIntern.getLastname()
						&& internToInsert.getFirstname().compareTo(currentIntern.getFirstname()) < 0)

				|| (internToInsert.getFirstname() == currentIntern.getFirstname()
						&& internToInsert.getDepartment().compareTo(currentIntern.getDepartment()) < 0)

				|| (internToInsert.getDepartment() == currentIntern.getDepartment()
						&& internToInsert.getTraining().compareTo(currentIntern.getTraining()) < 0)

				|| (internToInsert.getTraining() == currentIntern.getTraining()
						&& internToInsert.getYear() < currentIntern.getYear())) {

			System.out.println("Je suis a gauche");

			if (currentNode.getLeftSon() == -1) {

				System.out.println("Avant writeNode --- FG currentNode : " + currentNode.getLeftSon()
						+ " FD currentNode : " + currentNode.getRightSon());
//				currentNode.setLeftSon(indexToInsert);
				writeIndex(indexToInsert, currentIndex, 8);
				writeNode(nodeToInsert, indexToInsert);
				System.out.println("Méthose inserNode - Le currentIntern s'appelle " + currentIntern.getLastname() + " "
						+ currentIntern.getFirstname() + " du " + currentIntern.getDepartment() + ". Il est en "
						+ currentIntern.getTraining() + " de " + currentIntern.getYear());
				System.out.println("Methode insertNode - Le internToInsert s'appelle " + internToInsert.getLastname()
						+ " " + internToInsert.getFirstname() + " du " + internToInsert.getDepartment() + ". Il est en "
						+ internToInsert.getTraining() + " de " + internToInsert.getYear());
				System.out.println("Index à insérer à gauche " + indexToInsert);
				System.out.println("Après writeNode - FG currentNode : " + currentNode.getLeftSon()
						+ " FD currentNode : " + currentNode.getRightSon());

			} else {
				insertNode(currentNode.getLeftSon(), indexToInsert, intern, node);
			}
		} else {

			System.out.println("Je suis a droite");

			if (currentNode.getRightSon() == -1) {

				System.out.println("Avant writeNode --- FG currentNode : " + currentNode.getLeftSon()
						+ " FD currentNode : " + currentNode.getRightSon());
//				currentNode.setLeftSon(indexToInsert);
				writeIndex(indexToInsert, currentIndex, 4);
				writeNode(nodeToInsert, indexToInsert);
				System.out.println("Méthose inserNode - Le currentIntern s'appelle " + currentIntern.getLastname() + " "
						+ currentIntern.getFirstname() + " du " + currentIntern.getDepartment() + ". Il est en "
						+ currentIntern.getTraining() + " de " + currentIntern.getYear());
				System.out.println("Methode insertNode - Le stagiaire s'appelle " + internToInsert.getLastname() + " "
						+ internToInsert.getFirstname() + " du " + internToInsert.getDepartment() + ". Il est en "
						+ internToInsert.getTraining() + " de " + internToInsert.getYear());
				System.out.println("Index à insérer à droite " + indexToInsert);
				System.out.println("Après writeIndex - FG currentNode : " + currentNode.getLeftSon()
						+ " FD currentNode : " + currentNode.getRightSon());
			} else {
				insertNode(currentNode.getRightSon(), indexToInsert, intern, node);
			}
		}
	}

	public Node readNode(int index) {
		Node node = new Node();
		Intern intern = new Intern();
		try {


			raf.seek(index * Node.BYTE_LENGTH_NODE);
			node.setIntern(intern);

			node.getIntern().setLastname(readString());
			node.getIntern().setFirstname(readString());
			node.getIntern().setDepartment(readString());
			node.getIntern().setTraining(readString());
			node.getIntern().setYear(raf.readInt());

			node.setLeftSon(raf.readInt());
			node.setRightSon(raf.readInt());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;

	}

	public void writeIndex(int indexToInsert, int currentIndex, int position) {
		try {

			raf.seek((currentIndex + 1) * Node.BYTE_LENGTH_NODE - position);
			raf.writeInt(indexToInsert);
		} catch (IOException e) {
			// System.out.println("Erreur d’écriture : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void writeNode(Node node, int index) {
		try {
			raf.seek(index * Node.BYTE_LENGTH_NODE);

			String lastname = node.getIntern().getLastnameLong();
			raf.writeChars(lastname);
			String firstname = node.getIntern().getFirstnameLong();
			raf.writeChars(firstname);
			String department = node.getIntern().getDepartmentLong();
			raf.writeChars(department);
			String training = node.getIntern().getTrainingLong();
			raf.writeChars(training);
			int year = node.getIntern().getYear();
			raf.writeInt(year);
			raf.writeInt(node.getLeftSon());
			raf.writeInt(node.getRightSon());

		} catch (IOException e) {
			// System.out.println("Erreur d’écriture : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String readString() {
		char[] chars = new char[Intern.STRING_MAX_LENGTH];
		try {
			for (int i = 0; i < Intern.STRING_MAX_LENGTH; i++) {
				chars[i] = raf.readChar();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String myAttribute = new String(chars).trim();
		return myAttribute;
	}

	public int numbersInternsFile(File file) {
		int lengthFile = (int) file.length();
		int index = lengthFile / Node.BYTE_LENGTH_NODE;
		return index;
	}
}
