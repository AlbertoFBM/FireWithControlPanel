package myfire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alberto
 */
public class MyFire extends JFrame {

    public final Viewer v = new Viewer();
    public final ControlPanel cp = new ControlPanel();

    public MyFire() {

        initComponents();
        initViewer();
        initControlPanel();
    }

    public static void main(String[] args) {

        new MyFire().setVisible(true);
    }

    private void initComponents() {

        this.setBounds(0, 0, 1400, 900);
        this.setResizable(true);
        this.setTitle("MY FIRE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
    }

    private void initViewer() {

        Thread tViewer = new Thread(v);
        add(v, BorderLayout.CENTER);
        tViewer.start();
    }
    
    private void initControlPanel(){
        
        add(cp, BorderLayout.WEST);
       
    }
}
