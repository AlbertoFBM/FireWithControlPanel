package myfire;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Alberto
 */
public class Viewer extends Canvas implements Runnable {

    private Image chimenea;
    private Fire foc = new Fire(388, 388, 2);
    private Thread tFire = new Thread(foc);
    private Graphics g;

    public Fire getFoc() {
        return foc;
    }

    public void setFoc(Fire foc) {
        this.foc = foc;
    }

    public Viewer() {
        
        setBackground();
        tFire.start();
    }

    @Override
    public void paint(Graphics g) {
    
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(2);
            return;
        }
        
        g = bs.getDrawGraphics();        
        
        g.drawImage(chimenea, 0, 0,null);

        g.drawImage(foc, 390, 390, 430, 357, null);        
    
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
                paint(g);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void initPaleta(){
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
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
    
    private void setBackground() {
        try {

            chimenea = ImageIO.read(new File("src/Images/chimenea.jpg"));

        } catch (IOException e) {
            System.out.println("La imagen no se encuentra");
            e.printStackTrace();
        }
    }
    
    public static void paletaRoja(){
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
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
    
    public static void paletaVerde(){
        
        // He cambiado el rojo puro  por verde puro
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
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
    
    public static void paletaAzul(){
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
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
    
    public static void paletaAmarilla(){
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
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
    
    public static void paletaLila(){
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(108,70,117, 255), 100); // amarillo
        TargetColor color3 = new TargetColor(new Color(71,57,109, 255), 150); // amarillo
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
    
    public static void paletaRainbow(){
        
        FirePalette paleta = new FirePalette();
//                                          (color(r, g, b, a), tempreratura);
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); // fondo negro
        TargetColor color2 = new TargetColor(new Color(255,255,0, 255), 100); // amarillo
        TargetColor color3 = new TargetColor(new Color(0,255,0, 255), 150); // verde
        TargetColor color4 = new TargetColor(new Color(255,0,0, 255), 50); // rojo
        TargetColor color5 = new TargetColor(new Color(0,0,255, 255),200); // azul
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
}
