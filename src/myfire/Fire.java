package myfire;

import java.awt.image.BufferedImage;

/**
 *
 * @author Alberto
 */
public class Fire extends BufferedImage implements Runnable {

    private final int[][] matrizTemperaturas;
    private static FirePalette paleta = new FirePalette();
    private static int SPARKS = 70; 
    private static int COOL = 30;
    private static boolean runningFire = true;
    private static int velocityFire = 50;

    public static int getSPARKS() {
        return SPARKS;
    }

    public static void setSPARKS(int SPARKS) {
        Fire.SPARKS = SPARKS;
    }

    public static int getCOOL() {
        return COOL;
    }

    public static void setCOOL(int COOL) {
        Fire.COOL = COOL;
    }

    public static boolean isRunningFire() {
        return runningFire;
    }

    public static void setRunningFire(boolean runningFire) {
        Fire.runningFire = runningFire;
    }

    public static FirePalette getPaleta() {
        return paleta;
    }

    public static void setPaleta(FirePalette paleta) {
        Fire.paleta = paleta;
    }

    public static int getVelocityFire() {
        return velocityFire;
    }

    public static void setVelocityFire(int velocityFire) {
        Fire.velocityFire = velocityFire;
    }

    public Fire(int width, int height, int imageType) {
        super(width, height, imageType);

        matrizTemperaturas = new int[388][388];

    }

    @Override
    public void run() {
        while (true) {
            
            try{
                Thread.sleep(velocityFire);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
            if(runningFire){
                createSparks();
                createCool();
                temperatureEvolve();
                createFlameImage();
            }   
        }
    }

    private void createSparks() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            int aux = (int) (Math.random() * 99); // Variable auxiliar para colocar el random

            if (aux <= SPARKS) {
                matrizTemperaturas[i][matrizTemperaturas.length - 1] = 255;
            }
        }
    }

    private void createCool() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            int aux = (int) (Math.random() * 99);

            if (aux <= COOL) {
                matrizTemperaturas[i][matrizTemperaturas.length - 1] = 0;
            }
        }
    }

    private void temperatureEvolve() {
        int increment = (int) 0.7;
        int mT[][] = matrizTemperaturas;
        for (int j = mT[0].length - 2; j >= 0; j--) {
            for (int i = 1; i < mT.length - 1; i++) {
                mT[i][j] = (mT[i][j + 1] + mT[i + 1][j + 1] + mT[i - 1][j + 1] + mT[i - 1][j] + mT[i + 1][j]) / 5 - increment;
            }
        }
    }    

    private void createFlameImage() {

        for (int i = 0; i < matrizTemperaturas.length; i++) {
            for(int j=0; j<matrizTemperaturas[0].length; j++){
                
                int p = this.getPaleta().getColor(matrizTemperaturas[i][j]);
                this.setRGB(i, j, p);
            }
        }
    }
    
    public static void play(){
        Fire.setRunningFire(true);
    }
    
    public static void pause(){
        Fire.setRunningFire(false);
    }
}
