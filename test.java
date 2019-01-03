package compression;

public class test {

	private static Huffman huffman;
	private static int[][] value;

	public static void main(String[] args) {
		Matrice m = new Matrice();
		int[][] matDCT = m.dct();
		System.out.println("------------------ DCT ------------------\n");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				System.out.printf("%d\t", matDCT[i][j]);
			System.out.println();
		}
		QuantificateurAvecMatrice mo = new QuantificateurAvecMatrice(matDCT, 40);
		value = mo.getMatrice();
		try {
			huffman = new Huffman(value);
		} catch (Exception e) {
			
			System.out.println(e);
		}

	}

}
