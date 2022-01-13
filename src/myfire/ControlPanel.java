package myfire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//
/**
 *
 * @author Alberto
 */
public class ControlPanel extends JPanel{
    
    private Events event = new Events();
    
    private JSlider slider_1, slider_2;
    
    public ControlPanel(){
        /*
        setLayout(new BorderLayout());
        
        // Boton PLAY
        JButton b1 = new JButton(new ImageIcon("src/Images/play.png"));

        add(b1, BorderLayout.NORTH);
        
        // Boton PAUSE
        JButton b2 = new JButton(new ImageIcon("src/Images/pause.png"));
        
        add(b2, BorderLayout.CENTER);
        
        // Boton STOP
        JButton b3 = new JButton(new ImageIcon("src/Images/stop.png"));
        
        add(b3, BorderLayout.SOUTH);
        */
        initComponents();
    }
    
    public void initComponents(){
        
        this.setBackground(new Color(44,52,55));
        this.setLayout(new GridLayout(3,2));
//        this.setLayout(new GridBagLayout());

        JButton b1 = new JButton("PLAY");
        b1.setIcon(new ImageIcon("src/Images/play.png"));
        b1.setActionCommand("boton1");
        b1.addActionListener(event);
        
        JButton b2 = new JButton("STOP");
        b2.setIcon(new ImageIcon("src/Images/stop.png"));
        b2.setActionCommand("boton2");
        b2.addActionListener(event);
        
        JButton background = new JButton("BACKGROUND");
//        background.addActionListener(event);
        
        add(b1);
        add(b2);
        
//        b1.addActionListener();
        JLabel label_1 = new JLabel("SPARKS", JLabel.CENTER);
        label_1.setForeground(Color.red);
//        label_1.setAlignmentX(Component.CENTER_ALIGNMENT);

//        add(label_1);
        
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
        
        add(background);
        
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
    }
    
    private class Events implements ChangeListener, ActionListener{

        @Override
        public void stateChanged(ChangeEvent e) {

            Fire.setSPARKS(slider_1.getValue());

            Fire.setCOOL(slider_2.getValue());

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "boton1":
                    Viewer.setRunning(true);
                    break;
                case "boton2":
                    Viewer.setRunning(false);
                    break;
            }
        }
    }
}

