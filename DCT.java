package compression;

final class Dct {

	final static int m = 8;
	final static int n = 8;

	// Function to find discrete cosine transform and print it
	static int[][] dctTransform(int matrix[][]) {
		int i, j, k, l;

		// dct will store the discrete cosine transform
		int[][] dct = new int[m][n];

		double ci, cj, dct1, sum;

		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				// ci and cj depends on frequency as well as
				// number of row and columns of specified matrix
				if (i == 0)
					ci = (1 / Math.sqrt(m));
				else
					ci = (Math.sqrt(2) / Math.sqrt(m));

				if (j == 0)
					cj = (1 / Math.sqrt(n));
				else
					cj = (Math.sqrt(2) / Math.sqrt(n));

				// sum will temporarily store the sum of
				// cosine signals
				sum = 0;
				for (k = 0; k < m; k++) {
					for (l = 0; l < n; l++) {
						dct1 = (int) (matrix[k][l] * Math.cos((2 * k + 1) * i * Math.PI / (2 * m))
								* Math.cos((2 * l + 1) * j * Math.PI / (2 * n)));
						sum = sum + dct1;
					}
				}
				double a = ci * cj * sum;
				dct[i][j] = (int) Math.round(a);
			}
		}
		return dct;
	}
}