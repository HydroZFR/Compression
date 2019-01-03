package compression;

public class Matrice {

	private int mat[][];

	public Matrice() {
		mat = new int[8][8];
        int i = 0, j = 0;

        for (i = 0 ; i < 8 ; i++)
        {
            for (j = 0 ; j < 8 ; j++)
            {
                mat[i][j] = (int) (Math.random()*255);
            }
        }
		/*mat[0][0] = 139;
		mat[0][1] = 144;
		mat[0][2] = 149;
		mat[0][3] = 153;
		mat[0][4] = 155;
		mat[0][5] = 155;
		mat[0][6] = 155;
		mat[0][7] = 155;

		mat[1][0] = 144;
		mat[1][1] = 151;
		mat[1][2] = 153;
		mat[1][3] = 156;
		mat[1][4] = 159;
		mat[1][5] = 156;
		mat[1][6] = 156;
		mat[1][7] = 156;

		mat[2][0] = 150;
		mat[2][1] = 155;
		mat[2][2] = 160;
		mat[2][3] = 163;
		mat[2][4] = 158;
		mat[2][5] = 156;
		mat[2][6] = 156;
		mat[2][7] = 156;

		mat[3][0] = 159;
		mat[3][1] = 161;
		mat[3][2] = 162;
		mat[3][3] = 160;
		mat[3][4] = 160;
		mat[3][5] = 159;
		mat[3][6] = 159;
		mat[3][7] = 159;

		mat[4][0] = 159;
		mat[4][1] = 160;
		mat[4][2] = 161;
		mat[4][3] = 162;
		mat[4][4] = 162;
		mat[4][5] = 155;
		mat[4][6] = 155;
		mat[4][7] = 155;

		mat[5][0] = 161;
		mat[5][1] = 161;
		mat[5][2] = 161;
		mat[5][3] = 161;
		mat[5][4] = 160;
		mat[5][5] = 157;
		mat[5][6] = 157;
		mat[5][7] = 157;

		mat[6][0] = 162;
		mat[6][1] = 162;
		mat[6][2] = 161;
		mat[6][3] = 163;
		mat[6][4] = 162;
		mat[6][5] = 157;
		mat[6][6] = 157;
		mat[6][7] = 157;

		mat[7][0] = 162;
		mat[7][1] = 162;
		mat[7][2] = 161;
		mat[7][3] = 161;
		mat[7][4] = 163;
		mat[7][5] = 158;
		mat[7][6] = 158;
		mat[7][7] = 158;*/
	}

	public int[][] dct() {
		return Dct.dctTransform(mat);
	}

	public int[][] getMat() {
		return mat;
	}

}
