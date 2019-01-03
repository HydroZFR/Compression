package compression;

public class HuffmanTransversor {
	private Node rootNode;
	public String finalBitPattern = "";

	public HuffmanTransversor(Node myNode, int[][] charArray, Node[] nodes) {
		String temp;
		rootNode = myNode;
		System.out.println("Dictionnaire :");
		for (Node node : nodes) {
			temp = search(rootNode, "", node.c);
			if (node.c == 1) {
				System.out.println("\tValeur : " + 0 + " Code : " + temp);
			} else {
				System.out.println("\tValeur : " + node.c + " Code : " + temp);
			}
		}
		
		for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray.length; j++) {
                finalBitPattern += search(rootNode, "", charArray[i][j]);;
            }
        }
		
		finalBitPattern = finalBitPattern.replaceAll(" ", "");
		System.out.println("Code final : " + finalBitPattern);
		System.out.println("L'image une fois compressée est représentée avec : " + finalBitPattern.length() + " bits.");
		System.out.println("Soit un gain de : " + (100 - (double) finalBitPattern.length() / 512d * 100d) + "%.");

	}

	public String search(Node rootNode, String value, int charArray) {
		String valueL = "";
		if (rootNode != null) {
			if (rootNode.left != null)
				valueL = search(rootNode.left, value + "0", charArray);
			if (rootNode.c == charArray)
				return value;

			else {
				if (valueL == "") {
					return search(rootNode.right, value + "1", charArray);
				} else {
					return valueL;
				}
			}
		} else {
			return "";
		}
	}

}
