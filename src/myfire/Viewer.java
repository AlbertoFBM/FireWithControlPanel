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
        
        TargetColor color1 = new TargetColor(new Color(0, 0, 0, 255), 0); 
        TargetColor color2 = new TargetColor(new Color(255, 0, 0, 255), 100);
        TargetColor color3 = new TargetColor(new Color(255, 255, 0, 255), 150);
        TargetColor color4 = new TargetColor(new Color(20, 20, 20, 255), 50);
        TargetColor color5 = new TargetColor(new Color(255, 255, 255, 255), 255);
        
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
}
