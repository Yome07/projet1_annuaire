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
				Node node = new Node(interns.get(i), -1, -1); // creation du node a inserer
				
				// Si ce n’est pas le premier stagiaire, on doit l’insérer dans l’arbre.
				// appel de la méthode inserNode permettant de déterminer à quel endroit il sera insérer
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
	 * @param intern stagiaire 
	 */
	
	public void insertNode(int currentIndex, int indexToInsert, Intern intern, Node node) {
		System.out.println("intern avant readNode : " + intern); // Intern ok 
		
		Node nodeToInsert = new Node(intern, -1, -1);
		Intern internToInsert = nodeToInsert.getIntern();
		System.out.println("internToInsert avant appel de la methode read node : " +internToInsert);

		
		Node currentNode = readNode(currentIndex); // warning readNode modifie intern
		Intern currentIntern = currentNode.getIntern();
		System.out.println(intern);

		System.out.println("FG currentNode : " + currentNode.getLeftSon() + " FD currentNode : " + currentNode.getRightSon());
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
						&& internToInsert.getYear() < currentIntern.getYear())
				) {
			
			System.out.println("Je suis a gauche");
			
			if (currentNode.getLeftSon() == -1) {
				int left = currentNode.getLeftSon(); // recuperer un parent ???
				left = indexToInsert;
				writeNode(currentNode, indexToInsert);
			} else {
				insertNode(currentNode.getLeftSon(), indexToInsert, intern, node);
			}
		} else {
			
			System.out.println("Je suis a droite");
			
			if (currentNode.getRightSon() == -1) {
				int right = currentNode.getRightSon();
				right = indexToInsert;
				writeNode(currentNode, indexToInsert);
			} else {
				insertNode(currentNode.getRightSon(), indexToInsert, intern, node);
			}
		}
	}

	public Node readNode(int index) {
		System.out.println("----- Methode readNode -----");
		Node node = new Node();
		Intern intern = new Intern();
		try {
			
			System.out.println("");
			
			raf.seek(index * Node.BYTE_LENGTH_NODE);
			node.setIntern(intern);
			
			node.getIntern().setLastname(readString());
			node.getIntern().setFirstname(readString());
			node.getIntern().setDepartment(readString());
			node.getIntern().setTraining(readString());
			node.getIntern().setYear(raf.readInt());
			

			node.setLeftSon(raf.readInt());
			node.setRightSon(raf.readInt());

			System.out.println("CurretnIntern - FG : " +  node.getLeftSon() + " FD : " + node.getRightSon());

	
		} catch (IOException e) {
			//System.out.println("Erreur de lecture : " + e.getMessage());
			e.printStackTrace();
		}
		//System.out.println("Methode readNode - node.intern : " + node.intern);
		System.out.println("----- Fin Methode -----");
		return node;

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
			//System.out.println("Methode writeNode - Le stagiaire s'appelle " + lastname + firstname + " du " + department + ". Il est en " + training + " de " + year);

			raf.writeInt(node.getLeftSon());
			raf.writeInt(node.getRightSon());
			
			
		} catch (IOException e) {
			// System.out.println("Erreur d’écriture : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String readString() {
		System.out.println("----- Methode readString() -----");
		byte[] lengthAttributs = new byte[2 * Intern.STRING_MAX_LENGTH];
		int buffer = 0;
		try {
			buffer = this.raf.read(lengthAttributs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String myAttribute = new String(lengthAttributs);
		System.out.println(myAttribute);
		System.out.println("----- Fin Methode -----");
		return myAttribute;
	}

}
