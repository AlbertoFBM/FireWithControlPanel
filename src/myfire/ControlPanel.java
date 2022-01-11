package myfire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
//
/**
 *
 * @author Alberto
 */
public class ControlPanel extends JPanel{
    
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
        
        this.setVisible(true);
        this.setBackground(Color.gray);
//        this.setLayout(new GridBagLayout());
        JButton b1 = new JButton("PLAY");
        JButton b2 = new JButton("STOP");
        JButton b3 = new JButton("BACKGROUND");
        add(b1);
        add(b2);
        add(b3);
        
        JSlider jslider1 = new JSlider();
//        add(jslider1, BorderLayout.WEST);
        
       
    }
    
    
    
        
        
        
}

