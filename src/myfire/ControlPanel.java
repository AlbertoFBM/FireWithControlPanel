package myfire;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Alberto
 */
public class ControlPanel extends JPanel {

    private final MyFire myFire;
    private final Viewer viewer;
    private ControlPanelConvolution controlPanelConvolution;
    private final Events event = new Events();
    private final Music musicClass = new Music();
    private final Color backgroundColor = new Color(44, 42, 50);
    private JButton b1, b2, b3, b4;
    private JSlider slider_1, slider_2, slider_3;
    private JLabel label_0, label_1, label_2, label_3;
    private JRadioButton rb1, rb2, rb3, rb4, rb5, rb6;

    public ControlPanel(MyFire myFire, Viewer viewer) {
        this.myFire = myFire;
        this.viewer = viewer;
        initComponents();
    }

    public Music getMusicClass() {
        return musicClass;
    }

    public MyFire getMyFire() {
        return myFire;
    }

    public ControlPanelConvolution getControlPanelConvolution() {
        return controlPanelConvolution;
    }

    public void initComponents() {

        this.setBackground(backgroundColor);
        this.setLayout(null);

        //******************* JBUTTON ***********************
        b1 = new JButton();
        b1.setIcon(new ImageIcon("src/Images/play.png"));
        b1.setActionCommand("boton1");
        b1.setBorder(null);
        b1.setBackground(new Color(44, 42, 50, 255));
        b1.setBounds(65, 100, 48, 48);
        b1.addActionListener(event);
        this.add(b1);

        b2 = new JButton();
        b2.setIcon(new ImageIcon("src/Images/stop.png"));
        b2.setActionCommand("boton2");
        b2.setBorder(null);
        b2.setBackground(new Color(44, 42, 50, 255));
        b2.setBounds(125, 100, 48, 48);
        b2.addActionListener(event);
        this.add(b2);

        b3 = new JButton();
        b3.setIcon(new ImageIcon("src/Images/backg.png"));
        b3.setActionCommand("boton3");
        b3.setBorder(null);
        b3.setBackground(new Color(44, 42, 50, 255));
        b3.setBounds(190, 100, 48, 48);
        b3.addActionListener(event);
        this.add(b3);

        b4 = new JButton("GO TO CONVOLUTION >>");
        b4.setActionCommand("boton4");
        b4.setBounds(50, 600, 200, 50);
        b4.addActionListener(event);
        b4.setBorder(null);
        this.add(b4);

        //******************* JSLIDER ***********************
        // (orientation, min, max , donde aparece la flecha al inicio); 
        slider_1 = new JSlider(JSlider.VERTICAL, 0, 100, this.getMyFire().getV().getFoc().getSPARKS());

        slider_1.setBounds(25, 220, 55, 150);
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
        this.add(slider_1);

        slider_2 = new JSlider(JSlider.VERTICAL, 0, 100, this.getMyFire().getV().getFoc().getCOOL());
        slider_2.setBounds(125, 220, 55, 150);
        slider_2.setInverted(false);
        slider_2.setMajorTickSpacing(25);
        slider_2.setMinorTickSpacing(5);
        slider_2.setPaintTicks(true);
        slider_2.setPaintLabels(true);
        slider_2.setSnapToTicks(true);
        slider_2.setToolTipText("Cools/s");
        slider_2.addChangeListener(event);
        this.add(slider_2);

        slider_3 = new JSlider(JSlider.VERTICAL, 0, 250, this.getMyFire().getV().getVelocity());
        slider_3.setBounds(225, 220, 55, 150);
        slider_3.setInverted(true);
        slider_3.setMajorTickSpacing(50);
        slider_3.setMinorTickSpacing(25);
        slider_3.setPaintTicks(true);
        slider_3.setPaintLabels(true);
        slider_3.setSnapToTicks(true);
        slider_3.setToolTipText("Pixels/s");
        slider_3.addChangeListener(event);
        this.add(slider_3);

        //******************* JLABEL ***********************
        label_0 = new JLabel("MY FIRE", JLabel.CENTER);
        label_0.setForeground(Color.white);
        label_0.setFont(new Font("Serif", Font.BOLD, 24));
        label_0.setBounds(80, 0, 150, 100);
        this.add(label_0);

        label_1 = new JLabel("SPARKS", JLabel.CENTER);
        label_1.setForeground(Color.white);
        label_1.setFont(new Font("Serif", Font.BOLD, 24));
        label_1.setBounds(0, 180, 100, 50);
        this.add(label_1);

        label_2 = new JLabel("COOLS", JLabel.CENTER);
        label_2.setForeground(Color.white);
        label_2.setFont(new Font("Serif", Font.BOLD, 24));
        label_2.setBounds(100, 180, 100, 50);
        this.add(label_2);

        label_3 = new JLabel("SLEEP", JLabel.CENTER);
        label_3.setForeground(Color.white);
        label_3.setFont(new Font("Serif", Font.BOLD, 24));
        label_3.setBounds(200, 180, 100, 50);
        this.add(label_3);

        //******************* JRADIOBUTTON ***********************
        ButtonGroup grupo = new ButtonGroup();
        rb1 = new JRadioButton("ROJO", true);
        rb2 = new JRadioButton("AZUL", false);
        rb3 = new JRadioButton("VERDE", false);
        rb4 = new JRadioButton("AMARILLO", false);
        rb5 = new JRadioButton("LILA", false);
        rb6 = new JRadioButton("ARCO IRIS", false);

        rb1.setFont(new Font("Serif", Font.BOLD, 24));
        rb2.setFont(new Font("Serif", Font.BOLD, 24));
        rb3.setFont(new Font("Serif", Font.BOLD, 24));
        rb4.setFont(new Font("Serif", Font.BOLD, 24));
        rb5.setFont(new Font("Serif", Font.BOLD, 24));
        rb6.setFont(new Font("Serif", Font.BOLD, 24));

        rb1.setBackground(backgroundColor);
        rb2.setBackground(backgroundColor);
        rb3.setBackground(backgroundColor);
        rb4.setBackground(backgroundColor);
        rb5.setBackground(backgroundColor);
        rb6.setBackground(backgroundColor);

        rb1.setForeground(Color.RED);
        rb2.setForeground(Color.BLUE);
        rb3.setForeground(Color.GREEN);
        rb4.setForeground(Color.YELLOW);
        rb5.setForeground(new Color(108, 70, 117, 255));
        rb6.setForeground(Color.WHITE);

        rb1.setBounds(0, 400, 120, 50);
        rb2.setBounds(125, 400, 120, 50);
        rb3.setBounds(0, 450, 120, 50);
        rb4.setBounds(125, 450, 175, 50);
        rb5.setBounds(0, 500, 120, 50);
        rb6.setBounds(125, 500, 175, 50);

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

        this.add(rb1);
        this.add(rb2);
        this.add(rb3);
        this.add(rb4);
        this.add(rb5);
        this.add(rb6);
    }

    private class Events implements ChangeListener, ActionListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            getMyFire().getV().getFoc().setSPARKS(slider_1.getValue());
            getMyFire().getV().getFoc().setCOOL(slider_2.getValue());
            getMyFire().getV().setVelocity(slider_3.getValue());
            getMyFire().getV().getFocOriginal().setSPARKS(slider_1.getValue());
            getMyFire().getV().getFocOriginal().setCOOL(slider_2.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "boton1":

                    getMusicClass().playMusic();
                    getMyFire().getV().play();
                    System.out.println("\nEl fuego ha reanudado.");
                    break;

                case "boton2":

                    getMusicClass().stopMusic();
                    getMyFire().getV().pause();
                    System.out.println("\nEl fuego está pausado.");
                    break;

                case "boton3":
                    System.out.println("\nCambiando la imagen");
                    getMyFire().getControlPanelConvolution().openFile();
                    break;

                case "red":

                    System.out.println("\nCambiando llama a RED");
                    getMyFire().getV().paletaRoja();
                    break;

                case "blue":

                    System.out.println("\nCambiando llama a BLUE");
                    getMyFire().getV().paletaAzul();
                    break;

                case "green":

                    System.out.println("\nCambiando llama a GREEN");
                    getMyFire().getV().paletaVerde();
                    break;

                case "yellow":

                    System.out.println("\nCambiando llama a YELLOW");
                    getMyFire().getV().paletaAmarilla();
                    break;

                case "purple":

                    System.out.println("\nCambiando llama a PURPLE");
                    getMyFire().getV().paletaLila();
                    break;

                case "rainbow":

                    System.out.println("\nCambiando llama a RAINBOW");
                    getMyFire().getV().paletaRainbow();
                    break;

                case "boton4":

                    System.out.println("\nCambiando a CONVOLUTION");
                    ControlPanel.this.removeAll();
                    controlPanelConvolution = new ControlPanelConvolution(myFire, viewer);
                    controlPanelConvolution.setBounds(0, 0, 400, 870);
                    ControlPanel.this.add(controlPanelConvolution);
                    controlPanelConvolution.setVisible(true);
                    repaint();
                    break;
            }
        }
    }

    public class Music {

        private File musicPath;
        private AudioInputStream audioInput;
        private Clip clip;

        public Music() {
            try {
                clip = AudioSystem.getClip();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void startMusic(String filepath) {

            try {
                musicPath = new File(getMyFire().getFilepath());
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

        public void playMusic() {
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
