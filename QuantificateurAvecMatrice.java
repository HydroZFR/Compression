package compression;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class QuantificateurAvecMatrice {

    private int[][] matrixDeBase;
    private int[][] matrixEchelonn�e;
    private int[][] matrixQuantifi�e;
    private Integer lvlOfQuantification;
    private Integer numVer;

    public QuantificateurAvecMatrice(int[][] matrix, Integer lvlOfQuantification) {
	this.matrixDeBase = matrix;
	this.lvlOfQuantification = lvlOfQuantification;
	matrixEchelonn�e = new int[8][8];
	matrixQuantifi�e = new int[8][8];
	System.out.println(quantifier());
    }

    private String quantifier() {
	numVer = 0;
	absoluteAll();
	scaleTo();
	leveliseTo();
	draw(matrixEchelonn�e);
	draw(matrixQuantifi�e);
	return "---------------------------Matrice de base" + toStringofMatrix(matrixDeBase)
		+ "\n---------------------------Matrice echelonn�e" + toStringofMatrix(matrixEchelonn�e)
		+ "\n---------------------------Matrice quantifi�e" + toStringofMatrix(matrixQuantifi�e);
    }

    private void draw(int[][] unematrix) {
	numVer++;
	try {
	    BufferedImage image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);
	    for (int i = 0; i < unematrix.length; i++) {
		for (int j = 0; j < unematrix.length; j++) {
		    int a = (int) unematrix[i][j];
		    Color newColor = new Color(a, a, a);
		    image.setRGB(j, i, newColor.getRGB());
		}
	    }
	    BufferedImage resized = resize(image, 500, 500);
	    File output = new File("C:\\Users\\casta\\Desktop\\GrayScale" + numVer + ".jpg");
	    ImageIO.write((RenderedImage) resized, "jpg", output);
	} catch (Exception e) {
	}
    }

    private BufferedImage resize(BufferedImage img, int height, int width) {
	Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = resized.createGraphics();
	g2d.drawImage(tmp, 0, 0, null);
	g2d.dispose();
	return resized;
    }

    private void absoluteAll() {
	for (int i = 0; i < matrixQuantifi�e.length; i++) {
	    for (int j = 0; j < matrixQuantifi�e.length; j++) {
		matrixDeBase[i][j] = Math.abs(matrixDeBase[i][j]);
	    }
	}
    }

    private void scaleTo() {
        Double Vmax = 0D;
        int x = 0;
        int y = 0;
        for (int i = 0; i < matrixDeBase.length; i++) {
            for (int j = 0; j < matrixDeBase.length; j++) {
                if (matrixDeBase[i][j] > Vmax) {
                    Vmax = (double) matrixDeBase[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        if (Vmax >= 255)
            Vmax = (double) 255;
        matrixDeBase[x][y] = (int) Math.round(Vmax);
        System.out.println("\nVMAX=" + Vmax);

        for (int i = 0; i < matrixDeBase.length; i++) {
            for (int j = 0; j < matrixDeBase.length; j++) {
                matrixEchelonn�e[i][j] = (int) Math.round(matrixDeBase[i][j] / Vmax * 255);
            }
        }
    }

    private void leveliseTo() {
	Integer lengthOfEachLvl = 255 / (lvlOfQuantification);
	ArrayList<Integer> levels = new ArrayList<Integer>();
	Integer totallenght = 0;
	for (int k = 0; k < lvlOfQuantification; k++) {
	    totallenght += lengthOfEachLvl;
	    if (k == lvlOfQuantification - 1) {
		while (totallenght < 255)
		    totallenght += 1;
	    }
	    levels.add(totallenght);
	}
	System.out.println(levels);

	for (int i = 0; i < matrixDeBase.length; i++) {
	    for (int j = 0; j < matrixDeBase.length; j++) {
		matrixQuantifi�e[i][j] = 0;
		for (int h = 0; h < levels.size(); h++) {
		    if (matrixEchelonn�e[i][j] >= levels.get(h)) {
			matrixQuantifi�e[i][j] = levels.get(h);
		    }
		}
	    }
	}
    }

    private String toStringofMatrix(int[][] uneMatrix) {
	String text = "";
	for (int i = 0; i < uneMatrix.length; i++) {
	    text += "\n";
	    for (int j = 0; j < uneMatrix.length; j++) {
		text += "\t\t" + uneMatrix[i][j];
	    }
	}
	return text;
    }
    
    public int[][] getMatrice(){
    	return this.matrixQuantifi�e;
    }
    
    public String getMatriceString(){
    	String text="";
    	for(int i = 0; i < matrixQuantifi�e.length; i++){
    		   for(int j = 0; j < matrixQuantifi�e.length; j++){
    			   text += matrixQuantifi�e[i][j];
    		   }
    		}
    	return text;
    }
    
    
}