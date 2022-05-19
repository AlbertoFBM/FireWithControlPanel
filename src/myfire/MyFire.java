package myfire;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alberto
 */
public class MyFire extends JFrame{
    
    private final Viewer v = new Viewer(this);
    private final ControlPanelConvolution controlPanelConvolution = new ControlPanelConvolution(this, v);
    private final ControlPanel cp = new ControlPanel(this, v);
    private final String filepath = "src/Images/theGrandFinale.wav";

    public Viewer getV() {
        return v;
    }

    public ControlPanelConvolution getControlPanelConvolution() {
        return controlPanelConvolution;
    }

    public String getFilepath() {
        return filepath;
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
        cp.getMusicClass().startMusic(filepath);
        
        
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