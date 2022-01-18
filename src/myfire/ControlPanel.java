package myfire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Alberto
 */
public class ControlPanel extends JPanel {

    private Events event = new Events();
    private Color backgroundColorRadioButtons = new Color(37, 149, 138);
    private Color backgroundColor = new Color(44, 42, 50);
    private JButton b1, b2;
    private JSlider slider_1, slider_2, slider_3;
    private JLabel label_1, label_2, label_3;
    private JRadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    private InputStream music;
    private String filepath = "src/Images/soundFire.wav";
    private Music musicClass = new Music();

    public Music getMusicClass() {
        return musicClass;
    }

    public ControlPanel() {

        initComponents();

    }

    public void initComponents() {

        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(7, 2));

        b1 = new JButton("PLAY");
        b1.setIcon(new ImageIcon("src/Images/play.png"));
        b1.setActionCommand("boton1");
        b1.addActionListener(event);

        b2 = new JButton("STOP");
        b2.setIcon(new ImageIcon("src/Images/stop.png"));
        b2.setActionCommand("boton2");
        b2.addActionListener(event);

        add(b1);
        add(b2);

        // (orientation, min, max , donde aparece la flecha al inicio); 
        slider_1 = new JSlider(JSlider.HORIZONTAL, 0, 100, Fire.getSPARKS());
        // se invierte el relleno del jslider
        slider_1.setInverted(false);
        // las rayitas que marcan los números
        slider_1.setMajorTickSpacing(25); // rayas grandes
        slider_1.setMinorTickSpacing(5);  // rayas pequeñas
        slider_1.setPaintTicks(true);
        // los números
        slider_1.setPaintLabels(true);
        // el slider se mueve al limite cercano
        slider_1.setSnapToTicks(true);
        // Texto cuando te posas sobre el slider
        slider_1.setToolTipText("Sparks/s");

        slider_1.addChangeListener(event);

        add(slider_1);

        label_1 = new JLabel("<-- SPARKS", JLabel.CENTER);
        label_1.setForeground(Color.red);
        label_1.setFont(new Font("Serif", Font.BOLD, 24));
        add(label_1);

        label_2 = new JLabel("COOLS -->", JLabel.CENTER);
        label_2.setForeground(Color.cyan);
        label_2.setFont(new Font("Serif", Font.BOLD, 24));
        add(label_2);

        slider_2 = new JSlider(JSlider.HORIZONTAL, 0, 100, Fire.getCOOL());
        slider_2.setInverted(false);
        slider_2.setMajorTickSpacing(25);
        slider_2.setMinorTickSpacing(5);
        slider_2.setPaintTicks(true);
        slider_2.setPaintLabels(true);
        slider_2.setSnapToTicks(true);
        slider_2.setToolTipText("Cools/s");
        slider_2.addChangeListener(event);

        add(slider_2);

        slider_3 = new JSlider(JSlider.VERTICAL, 0, 250, Fire.getVelocityFire());
        slider_3.setInverted(true);
        slider_3.setMajorTickSpacing(50);
        slider_3.setMinorTickSpacing(25);
        slider_3.setPaintTicks(true);
        slider_3.setPaintLabels(true);
        slider_3.setSnapToTicks(true);
        slider_3.setToolTipText("Pixels/s");
        slider_3.addChangeListener(event);

        add(slider_3);

        label_3 = new JLabel("<-- SLEEP", JLabel.CENTER);
        label_3.setForeground(Color.yellow);
        label_3.setFont(new Font("Serif", Font.BOLD, 24));
        this.add(label_3);
     
        ButtonGroup grupo = new ButtonGroup();
        rb1 = new JRadioButton("ROJO", true);
        rb2 = new JRadioButton("AZUL", false);
        rb3 = new JRadioButton("VERDE", false);
        rb4 = new JRadioButton("AMARILLO", false);
        rb5 = new JRadioButton("LILA", false);
        rb6 = new JRadioButton("ARCO IRIS", false);

        rb1.setFont(new Font("Serif", Font.BOLD, 32));
        rb2.setFont(new Font("Serif", Font.BOLD, 32));
        rb3.setFont(new Font("Serif", Font.BOLD, 32));
        rb4.setFont(new Font("Serif", Font.BOLD, 32));
        rb5.setFont(new Font("Serif", Font.BOLD, 32));
        rb6.setFont(new Font("Serif", Font.BOLD, 32));

        rb1.setBackground(backgroundColorRadioButtons);
        rb2.setBackground(backgroundColorRadioButtons);
        rb3.setBackground(backgroundColorRadioButtons);
        rb4.setBackground(backgroundColorRadioButtons);
        rb5.setBackground(backgroundColorRadioButtons);
        rb6.setBackground(backgroundColorRadioButtons);

        rb1.setForeground(Color.RED);
        rb2.setForeground(Color.BLUE);
        rb3.setForeground(Color.GREEN);
        rb4.setForeground(Color.YELLOW);
        rb5.setForeground(new Color(108, 70, 117, 255));
        rb6.setForeground(Color.WHITE);

        grupo.add(rb1);
        grupo.add(rb2);
        grupo.add(rb3);
        grupo.add(rb4);
        grupo.add(rb5);
        grupo.add(rb6);

        rb1.setActionCommand("red");
        rb2.setActionCommand("blue");
        rb3.setActionCommand("green");
        rb4.setActionCommand("yellow");
        rb5.setActionCommand("purple");
        rb6.setActionCommand("rainbow");

        rb1.addActionListener(event);
        rb2.addActionListener(event);
        rb3.addActionListener(event);
        rb4.addActionListener(event);
        rb5.addActionListener(event);
        rb6.addActionListener(event);

        add(rb1);
        add(rb2);
        add(rb3);
        add(rb4);
        add(rb5);
        add(rb6);
    }

    private class Events implements ChangeListener, ActionListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            Fire.setSPARKS(slider_1.getValue());
            Fire.setCOOL(slider_2.getValue());
            Fire.setVelocityFire(slider_3.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "boton1":

                    musicClass.playMusic();
                    Fire.play();
                    System.out.println("\nEl fuego ha reanudado.");
                    break;
                    
                case "boton2":

                    musicClass.stopMusic();
                    Fire.pause();
                    System.out.println("\nEl fuego está pausado.");
                    break;
                    
                case "red":

                    System.out.println("\nCambiando llama a RED");
                    Viewer.paletaRoja();
                    break;
                    
                case "blue":

                    System.out.println("\nCambiando llama a BLUE");
                    Viewer.paletaAzul();
                    break;
                    
                case "green":

                    System.out.println("\nCambiando llama a GREEN");
                    Viewer.paletaVerde();
                    break;
                    
                case "yellow":

                    System.out.println("\nCambiando llama a YELLOW");
                    Viewer.paletaAmarilla();
                    break;
                    
                case "purple":

                    System.out.println("\nCambiando llama a PURPLE");
                    Viewer.paletaLila();
                    break;
                    
                case "rainbow":

                    System.out.println("\nCambiando llama a RAINBOW");
                    Viewer.paletaRainbow();
                    break;
            }
        }
    }

    public class Music{

        private File musicPath;
        private AudioInputStream audioInput;
        private Clip clip;
        
        public Music(){
            try{
                
            clip = AudioSystem.getClip();
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        public void startMusic(String filepath) {
            
            try {

                musicPath = new File(filepath);

                if (musicPath.exists()) {
                    audioInput = AudioSystem.getAudioInputStream(musicPath);
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);

                } else {
                    System.out.println("No puedo encontrar la música");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
        public void playMusic(){
            System.out.println("\nReanudando música...");
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        public void stopMusic() {
            System.out.println("\nPausando música...");
            clip.stop();
        }
    }
}
