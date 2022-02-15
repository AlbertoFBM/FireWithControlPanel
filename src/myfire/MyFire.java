package myfire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alberto
 */
public class MyFire extends JFrame{
    
    private Viewer v = new Viewer(this);
    private ControlPanelConvolution controlPanelConvolution = new ControlPanelConvolution(this, v);
    private ControlPanel cp = new ControlPanel(this, v);

    public Viewer getV() {
        return v;
    }

    public void setV(Viewer v) {
        this.v = v;
    }

    public ControlPanelConvolution getControlPanelConvolution() {
        return controlPanelConvolution;
    }

    public void setControlPanelConvolution(ControlPanelConvolution controlPanelConvolution) {
        this.controlPanelConvolution = controlPanelConvolution;
    }

    public MyFire() {

        initComponents();
        initViewer();
    }

    private void initComponents() {
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        cp.setPreferredSize(new Dimension(300, 100));
        cp.setMinimumSize(new Dimension(200, 100));
        //cp.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0f;
        gbc.weighty = 1f;
        this.add(cp, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7f;
        gbc.weighty = 1f;
              
        this.add(v, gbc);
        this.setSize(1515, 910);
        this.setTitle("MY FIRE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String filepath = "src/Images/soundFire.wav";
        //cp.getMusicClass().startMusic(filepath);
        
        
        Toolkit miPantalla = Toolkit.getDefaultToolkit();

        Dimension tamanoPantalla = miPantalla.getScreenSize();

        int anchoPantalla = tamanoPantalla.width;
        int altoPantalla = tamanoPantalla.height;
        
        this.setLocation(anchoPantalla / 10, altoPantalla / 10);
        
        this.setResizable(false);  
        this.setVisible(true);
    }

    private void initViewer() {

        Thread tViewer = new Thread(v);
        tViewer.start();
    }
    
    public static void main(String[] args) {

        new MyFire();
    }
}
