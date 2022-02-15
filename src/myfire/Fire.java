package myfire;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Alberto
 */
public class Fire extends BufferedImage {

    private MyFire myFire;
    private Viewer viewer;
    private int[][] matrizTemperaturas;
    private FirePalette paleta = new FirePalette();
    private int SPARKS = 70;
    private int COOL = 40;
    private int BRILLO = 50;
    private boolean runningFire = true;
    private int velocityFire = 50;
    private float brightness;
    private boolean convoluted;
    private int width = 388;
    private int height = 388;
    private int[][] matrix;

    //private boolean[][] booleans;

    public FirePalette getPaleta() {
        return paleta;
    }

    public void setPaleta(FirePalette paleta) {
        this.paleta = paleta;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public boolean isConvoluted() {
        return convoluted;
    }

    public void setConvoluted(boolean convoluted) {
        this.convoluted = convoluted;
    }

    public int getSPARKS() {
        return SPARKS;
    }

    public void setSPARKS(int SPARKS) {
        this.SPARKS = SPARKS;
    }

    public int getCOOL() {
        return COOL;
    }

    public void setCOOL(int COOL) {
        this.COOL = COOL;
    }

    public int getBRILLO() {
        return BRILLO;
    }

    public void setBRILLO(int BRILLO) {
        this.BRILLO = BRILLO;
    }

    public boolean isRunningFire() {
        return runningFire;
    }

    public void setRunningFire(boolean runningFire) {
        this.runningFire = runningFire;
    }

    public int getVelocityFire() {
        return velocityFire;
    }

    public void setVelocityFire(int velocityFire) {
        this.velocityFire = velocityFire;
    }

    public Fire(int width, int height, int imageType, Viewer vie) {
        super(width, height, imageType);
        this.viewer = vie;
        matrizTemperaturas = new int[388][388];

    }
//
//    @Override
//    public void run() {
//        while (true) {
//
//            try {
//                Thread.sleep(velocityFire);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            if (runningFire) {
//                runFire();
//
//            }
//        }
//    }

    public void runFire() {
        if (!convoluted) {
            createSparks();
            createCool();
            temperatureEvolve();
            createFlameImage();
        } else {
            createSparksConvoluted();
            //createCoolConvolution();
            temperatureEvolveConvolution();
            createFlameImageConvoluted();
        }
    }

    public void createSparks() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            int aux = (int) (Math.random() * 99); // Variable auxiliar para colocar el random

            if (aux <= this.getSPARKS()) {
                matrizTemperaturas[i][matrizTemperaturas.length - 1] = 255;
            }
        }
    }

    private void createSparksConvoluted() {
        int red;
        int green;
        int blue;
        //this.viewer.getConvolution().getImagen().getWidth()
        //this.viewer.getConvolution().getImagen().getHeight()
        matrix = new int[388][388];

        for (int i = 1; i < this.width - 1; i++) {
            for (int j = 1; j < this.height - 1; j++) {
                red = new Color(this.viewer.getConvolution().getImagen().getRGB(i, j), true).getRed();
                green = new Color(this.viewer.getConvolution().getImagen().getRGB(i, j), true).getGreen();
                blue = new Color(this.viewer.getConvolution().getImagen().getRGB(i, j), true).getBlue();
                brightness = (red * 0.2116f + green * 0.7152f + blue * 0.0722f);

                if (brightness > BRILLO) {
                    createSparksConvoluted2(i, j);
                    createCoolsConvoluted(i, j);
                }
            }
        }
    }

    private void createSparksConvoluted2(int i, int j) {

        int aux = (int) (Math.random() * 99); // Variable auxiliar para colocar el random

        if (aux <= this.getSPARKS()) {
            matrix[i][j] = 255;
        }

    }

    public void createCool() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            int aux = (int) (Math.random() * 99);

            if (aux <= this.getCOOL()) {
                matrizTemperaturas[i][matrizTemperaturas.length - 1] = 0;
            }
        }
    }

    private void createCoolsConvoluted(int i, int j) {

        int aux = (int) (Math.random() * 99);

        if (aux <= this.getCOOL()) {
            matrix[i][j] = 0;
        }

    }

    public void temperatureEvolve() {
        int increment = (int) 0.7;
        int mT[][] = matrizTemperaturas;
        for (int j = mT[0].length - 2; j >= 0; j--) {
            for (int i = 1; i < mT.length - 1; i++) {
                mT[i][j] = (mT[i][j + 1] + mT[i + 1][j + 1]
                        + mT[i - 1][j + 1] + mT[i - 1][j]
                        + mT[i + 1][j]) / 5 - increment;
            }
        }
    }

    private void temperatureEvolveConvolution() {

        int mT[][] = matrix;
        for (int i = 1; i < mT.length - 1; i++) {
            for (int j = 1; j < mT[0].length - 2; j++) {
                
                mT[i][j] = (int)((mT[i][j + 1] + mT[i + 1][j + 1] 
                        + mT[i - 1][j + 1] + mT[i - 1][j] 
                        + mT[i + 1][j] + mT[i][j] + mT[i-1][j-1]) / 3.5);
            }
        }
    }

    public void createFlameImage() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            for (int j = 0; j < matrizTemperaturas[0].length; j++) {
                int p = this.getPaleta().getColor(matrizTemperaturas[i][j]);
                this.setRGB(i, j, p);
            }
        }
    }

    private void createFlameImageConvoluted() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 255) {
                    matrix[i][j] = 255;
                } else if (matrix[i][j] < 0) {
                    matrix[i][j] = 0;
                }
                int p = this.getPaleta().getColor(matrix[i][j]);
                this.setRGB(i, j, p);
            }
        }
    }

    public void play() {
        this.setRunningFire(true);
    }

    public void pause() {
        this.setRunningFire(false);
    }
}
