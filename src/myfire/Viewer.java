package myfire;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Alberto
 */
public class Viewer extends Canvas implements Runnable {

    private Image chimenea;
    private MyFire myFire;
    private Fire foc = new Fire(388, 388, 2, this);
    //private Thread tFire = new Thread(foc);
    private Graphics g;
    private Convolution convolution = new Convolution();
    private String filepath = "src/images/arbol.jpg";
    private BufferedImage imagenOriginal;
    private BufferedImage imagenConvolucionada;
    private int velocity = 40;
    private boolean runningFire = true;

    public Viewer(MyFire myFire) {
        this.myFire = myFire;
        //this.setBackground(new Color(0,0,0));
        this.setBackground(new Color(36, 36, 36));
        //tFire.start();

        try {
            imagenOriginal = ImageIO.read(new File(filepath));
            imagenConvolucionada = ImageIO.read(new File(filepath));
            imagenConvolucionada.getScaledInstance(388, 388, 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.getConvolution().setImagen(imagenConvolucionada);
    }

    public BufferedImage getImagenOriginal() {
        return imagenOriginal;
    }

    public void setImagenOriginal(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }

    public BufferedImage getImagenConvolucionada() {
        return imagenConvolucionada;
    }

    public void setImagenConvolucionada(BufferedImage imagenConvolucionada) {
        this.imagenConvolucionada = imagenConvolucionada;
    }

    public boolean isRunningFire() {
        return runningFire;
    }

    public void setRunningFire(boolean runningFire) {
        this.runningFire = runningFire;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
        setIgOriginal();
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Fire getFoc() {
        return foc;
    }

    public void setFoc(Fire foc) {
        this.foc = foc;
    }

    public Convolution getConvolution() {
        return convolution;
    }

    public void setIgOriginal() {

        try {
            imagenOriginal = ImageIO.read(new File(filepath));
            imagenConvolucionada = ImageIO.read(new File(filepath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.getConvolution().setImagen(imagenConvolucionada);
        this.getConvolution().calculatePixelConvoluted(imagenConvolucionada);
    }

    @Override
    public void paint(Graphics g) {
        
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();

        this.setIgOriginal();
        //g.drawImage(chimenea, 0, 0,null);
        g.drawImage(this.getImagenOriginal(), 0, 0, 400, 350, null);

        g.drawImage(this.getImagenConvolucionada(), 400, 0, 400, 350, null);

        g.setColor(Color.black);
        g.fillRect(800, 0, 400, 350);
        g.drawImage(this.getFoc(), 800, 0, 400, 350, null);

        g.drawImage(this.getImagenOriginal(), 350, 350, 520, 520, null);

        g.drawImage(this.getFoc(), 350, 350, 520, 520, null);

        g.dispose();
          
        bs.show();
    }

    
    @Override
    public void run() {

        initPaleta();
       
        while (true) {
            g = this.getGraphics();
            if (g == null) {
                System.out.println();
            } else {
                if(runningFire){
                    paint(g);
                foc.runFire();
                }
                
            }
            try {
                Thread.sleep(velocity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void initPaleta() {

        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(255, 0, 0, 255), 100); // rojo puro
        TargetColor color3 = new TargetColor(new Color(255, 255, 0, 255), 150); // amarillo
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        this.foc.setPaleta(paleta);
    }

    public void paletaRoja() {
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(255, 0, 0, 255), 100); // rojo puro
        TargetColor color3 = new TargetColor(new Color(255, 255, 0, 255), 150); // amarillo
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        Fire.setPaleta(paleta);

//        this.foc.setPaleta(paleta);
    }

    public void paletaVerde() {

        // He cambiado el rojo puro  por verde puro
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(0, 50, 0, 255), 100); // verde
        TargetColor color3 = new TargetColor(new Color(0, 255, 0, 255), 150); // amarillo
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        Fire.setPaleta(paleta);
    }

    public void paletaAzul() {

        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(0, 0, 50, 255), 100); // azul fuerte
        TargetColor color3 = new TargetColor(new Color(0, 0, 255, 255), 150); // azul puro
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        Fire.setPaleta(paleta);

    }

    public void paletaAmarilla() {

        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(50, 50, 0, 255), 100); // amarillo
        TargetColor color3 = new TargetColor(new Color(255, 255, 0, 255), 150); // amarillo
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        Fire.setPaleta(paleta);
    }

    public void paletaLila() {

        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(108, 70, 117, 255), 100); // amarillo
        TargetColor color3 = new TargetColor(new Color(71, 57, 109, 255), 150); // amarillo
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50); // gris oscuro
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255); // blanco

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);

        paleta.createPalette();

        Fire.setPaleta(paleta);
    }

    public void paletaRainbow() {

        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 0), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(255, 255, 0, 255), 100); // amarillo
        TargetColor color3 = new TargetColor(new Color(0, 255, 0, 255), 150); // verde
        TargetColor color4 = new TargetColor(new Color(255, 0, 0, 255), 50); // rojo
        TargetColor color5 = new TargetColor(new Color(0, 0, 255, 255), 200); // azul
        TargetColor color6 = new TargetColor(new Color(255, 0, 255, 255), 255); // rosa

        paleta.addColor(color1);
        paleta.addColor(color2);
        paleta.addColor(color3);
        paleta.addColor(color4);
        paleta.addColor(color5);
        paleta.addColor(color6);

        paleta.createPalette();

        Fire.setPaleta(paleta);
    }
    
    public void play() {
        this.setRunningFire(true);
    }

    public void pause() {
        this.setRunningFire(false);
    }
}
