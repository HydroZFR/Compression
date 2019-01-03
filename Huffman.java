package compression;

public class Huffman {

//	private static String value;
	private static int[][] charArray; 
	private static int table[] = new int[256]; 
	private static Node myNode[]; 
	private static int lengthOfTable = 0;
	private static Node myTree; // A variable that holds the Tree
	private static int lengthOfNode = 0;// all increments or decrements are made to this value
	public static HuffmanTransversor hC; // The Class Responsible for Decoding the Huffman Tree

	public Huffman(int[][] value)// resever la String entrer par l'utilisateur et le metre en valeur "valeu"
	{
		for (int i = 0; i<value.length; i++) {
			for(int j = 0; j<value.length; j++) {
				if (value[i][j] == 0) value[i][j] = 1;
			}
		}
		frequencyTable(value);// appele la methode " frequencyTable()" et envoier le valeur recu pour calculer
		// le nombre d'ocurence
		nodeArrange();// appele de method "nodeArrange" pour cree des objet neud pour chaque lettre
		Node x = createTree();
		hC = new HuffmanTransversor(x, charArray, myNode);
	}

	public static void frequencyTable(int[][] value)// la methode respnsable pour calcule de nombre d'occurence des
													// lettres
	{
		charArray = value;
		for (int i = 0; i < charArray.length; i++) {// balaier tous les character present ds le tableux
			for (int j = 0; j < charArray[i].length; j++) {
				int chara = charArray[i][j];// (int)charArray[i];// convertir chaque character en decimale
				table[chara] += 1;// incrementer le conteur chaque fois que un caracter s'affiche
			}
		}

	}

	public static void nodeArrange() {
		int counter = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] > 0)
				counter++;
		}
		lengthOfTable = counter;

		counter = 0;
		myNode = new Node[lengthOfTable];

		for (int i = 0; i < 256; i++) {
			if (table[i] != 0) {
				myNode[counter] = new Node(table[i], i, null, null);
				counter++;
			}

		}

		lengthOfNode = myNode.length;

		sort();

	}

	public static Node createTree() {
		int cpt = 0;
		Node nodePrecedent = null;
		for (int i = 1; i < lengthOfNode; i++) {
			cpt++;
			Node unNode = new Node();
			if(cpt==1) {
				unNode.addNode(myNode[0], myNode[i]);
				nodePrecedent = unNode;
			}else {
				unNode.addNode(nodePrecedent, myNode[i]);
				nodePrecedent = unNode;
			}
		}
		return nodePrecedent;
	}

	private static void moveItems(int index, int length) {
		try {
			for (int i = index; i < length; i++)
				myNode[i] = myNode[i + 1];

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void sort() {
		Node temp;
		for (int i = lengthOfNode - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (myNode[j].frequency > myNode[j + 1].frequency) {
					temp = myNode[j + 1];
					myNode[j + 1] = myNode[j];
					myNode[j] = temp;
				}
			}
		}
		String ordreTrie = "";
        for (Node n : myNode) {
        	if (n.c == 1) 
             ordreTrie += " " + 0;
        	else ordreTrie += " " + n.c;
        }
        System.out.println("L'ordre du trie est le suivant : " + ordreTrie);
	}
}
