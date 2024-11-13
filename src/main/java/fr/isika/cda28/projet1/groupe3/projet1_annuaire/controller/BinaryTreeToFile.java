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

	// ******************************
	// Attribute
	// ******************************
	
	public Node root;
	public RandomAccessFile raf;

	// ******************************
	// Constructor
	// ******************************
	
	public BinaryTreeToFile() {
		super();
		this.root = null;
		createRaf();
	}

	// ******************************
	// Public Method
	// ******************************
	
	/**
	 * Crée un fichier RandomAccessFile en mode lecture-écriture pour accéder aux
	 * données des stagiaires. Le fichier est situé à l'emplacement spécifié et est
	 * ouvert en mode "rw".
	 * 
	 * @return Le fichier RandomAccessFile ouvert pour manipulation des données.
	 * @throws IOException Si une erreur survient lors de l'ouverture du fichier.
	 */
	public RandomAccessFile createRaf() {
		try {
			this.raf = new RandomAccessFile("src/main/java/ressources/STAGIAIRES.bin", "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return raf;
	}

	/**
	 * Crée un arbre binaire à partir des données des stagiaires. Chaque stagiaire
	 * est inséré dans l'arbre en utilisant la méthode insertNode pour déterminer sa
	 * position. Chaque nœud est ensuite écrit dans le fichier après son insertion.
	 * 
	 * @throws IOException Si une erreur survient lors de la lecture ou de
	 *                     l'écriture des données.
	 */
	public void createBinaryTree() {
		try {
			readDonFile();

			for (int i = 0; i < interns.size(); i++) {
				Node node = new Node(interns.get(i), -1, -1); // creation du node a inserer

				if (i > 0) {
					insertNode(0, i, interns.get(i), node);
				}

				writeNode(node, i);

			}

			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insère un nouveau nœud dans l'arbre binaire en fonction de la comparaison des
	 * informations de l'intern. Si l'intern à insérer est "plus petit" que l'intern
	 * du nœud courant, il est inséré à gauche, sinon à droite. L'insertion se fait
	 * récursivement jusqu'à ce qu'une position libre (fils gauche ou droit) soit
	 * trouvée.
	 * 
	 * @param currentIndex  L'indice du nœud courant à partir duquel l'insertion
	 *                      doit commencer.
	 * @param indexToInsert L'indice du nœud à insérer.
	 * @param intern        L'intern à insérer dans l'arbre.
	 * @param node          Le nœud représentant l'intern à insérer.
	 */
	public void insertNode(int currentIndex, int indexToInsert, Intern intern, Node node) {
		System.out.println("intern avant readNode : " + intern);

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

	
	/**
	 * Lit un nœud depuis le fichier à l'index spécifié.
	 * Les informations du stagiaire (nom, prénom, département, formation, année) ainsi que les indices des fils gauche et droit sont récupérées.
	 * 
	 * @param index L'index du nœud à lire.
	 * @return Le nœud lu avec les données du stagiaire.
	 * @throws IOException Si une erreur survient lors de la lecture des données.
	 */
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

	/**
	 * Écrit l'index du nœud à insérer à la position spécifiée dans le fichier.
	 * Utilise la position du nœud courant pour déterminer où insérer l'index dans le fichier.
	 * 
	 * @param indexToInsert L'index du nœud à insérer.
	 * @param currentIndex L'index du nœud courant à partir duquel on insère.
	 * @param position La position dans le nœud où l'index doit être écrit.
	 * @throws IOException Si une erreur survient lors de l'écriture des données.
	 */
	public void writeIndex(int indexToInsert, int currentIndex, int position) {
		try {

			raf.seek((currentIndex + 1) * Node.BYTE_LENGTH_NODE - position);
			raf.writeInt(indexToInsert);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Écrit un nœud (stagiaire) dans le fichier à l'index spécifié.
	 * Les informations du stagiaire (nom, prénom, département, formation, année) ainsi que les indices des fils gauche et droit sont enregistrées.
	 * 
	 * @param node Le nœud à écrire dans le fichier.
	 * @param index L'index à la position duquel le nœud doit être écrit.
	 * @throws IOException Si une erreur survient lors de l'écriture des données.
	 */
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
			e.printStackTrace();
		}
	}

	/**
	 * Lit une chaîne de caractères à partir du fichier, avec une longueur fixe.
	 * Les caractères sont lus un par un jusqu'à ce que la chaîne atteigne la longueur maximale définie.
	 * 
	 * @return La chaîne de caractères lue, sans espaces superflus.
	 * @throws IOException Si une erreur survient lors de la lecture des caractères.
	 */
	public String readString() {
		char[] chars = new char[Intern.STRING_MAX_LENGTH];
		try {
			for (int i = 0; i < Intern.STRING_MAX_LENGTH; i++) {
				chars[i] = raf.readChar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String myAttribute = new String(chars).trim();
		return myAttribute;
	}

	/**
	 * Calcule le nombre de stagiaires dans un fichier en fonction de sa taille.
	 * Le nombre d'éléments est déterminé en divisant la taille du fichier par la longueur d'un nœud.
	 * 
	 * @param file Le fichier à analyser.
	 * @return Le nombre de stagiaires (nœuds) présents dans le fichier.
	 */
	public int numbersInternsFile(File file) {
		int lengthFile = (int) file.length();
		int index = lengthFile / Node.BYTE_LENGTH_NODE;
		return index;
	}
}
