package myfire;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alberto
 */
public class ControlPanelConvolution extends JPanel {

    private Viewer viewer;
    private MyFire myFire;
    private ControlPanel cp;
    private Events events = new Events();
    private JButton returnButton, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    private JLabel l1;
    private JFormattedTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    private String filepath;
    private JSlider jslider1;

    private int zoomFactor = 1;
    private PointerInfo pi;
    private Robot robot;
    private Timer t;

    private JTabbedPane pestanyes;

    public ControlPanelConvolution(MyFire myFire, Viewer viewer) {
        this.myFire = myFire;
        this.viewer = viewer;
        try {
            this.robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
        
        this.setLayout(null);
        this.setBackground(new Color(72, 72, 72));
        initComponents();
    }

    public MyFire getMyFire() {
        return myFire;
    }

    public void openFile() {

        filepath = "";

        String userLocation = System.getProperty("src/images");
        JFileChooser fileChooser = new JFileChooser(userLocation);

        FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int file = fileChooser.showOpenDialog(this);

        if (file == JFileChooser.APPROVE_OPTION) {
            filepath = fileChooser.getSelectedFile().getPath();
            this.getMyFire().getV().setFilepath(filepath);
        }
    }

    private void initComponents() {

        l1 = new JLabel("CONVOLUTION");
        l1.setForeground(Color.white);
        l1.setBounds(62, 0, 200, 100);
        l1.setFont(new Font("Serif", Font.PLAIN, 24));
        this.add(l1);

        returnButton = new JButton();
        returnButton.setBounds(0,0,40,40);
        returnButton.setIcon(new ImageIcon("src/Images/return.png"));
        returnButton.setBackground(new Color(72, 72, 72, 255));
        returnButton.setActionCommand("return");
        returnButton.setBorder(null);
        returnButton.addActionListener(events);
        this.add(returnButton);
        
        b1 = new JButton();
        b1.setIcon(new ImageIcon("src/Images/backg.png"));
        b1.setActionCommand("boton_cambiarFondo");
        b1.addActionListener(events);
        b1.setBounds(120, 80, 50, 50);
        b1.setBorder(null);
        b1.setBackground(new Color(72, 72, 72));
        b1.setFont(new Font("Serif", Font.PLAIN, 15));
        this.add(b1);

        b2 = new JButton("Oscuridad");
        b2.setActionCommand("boton_oscuridad");
        b2.addActionListener(events);
        b2.setBounds(20, 150, 120, 80);
        b2.setBackground(new Color(50, 50, 50));
        b2.setForeground(Color.white);
        b2.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b2);

        b3 = new JButton("Bordes");
        b3.setActionCommand("boton_bordes");
        b3.addActionListener(events);
        b3.setBounds(160, 150, 120, 80);
        b3.setBackground(new Color(50, 50, 50));
        b3.setForeground(Color.white);
        b3.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b3);

        b5 = new JButton("Alivio");
        b5.setActionCommand("boton_alivio");
        b5.addActionListener(events);
        b5.setBounds(20, 250, 120, 80);
        b5.setBackground(new Color(50, 50, 50));
        b5.setForeground(Color.white);
        b5.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b5);

        b6 = new JButton("Nitidez");
        b6.setActionCommand("boton_nitidez");
        b6.addActionListener(events);
        b6.setBounds(160, 250, 120, 80);
        b6.setBackground(new Color(50, 50, 50));
        b6.setForeground(Color.white);
        b6.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b6);

        b4 = new JButton("Sin Filtro");
        b4.setActionCommand("boton_sinFiltro");
        b4.addActionListener(events);
        b4.setBounds(86, 350, 120, 80);
        b4.setBackground(new Color(50, 50, 50));
        b4.setForeground(Color.white);
        b4.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b4);

        b7 = new JButton("Personalizado");
        b7.setActionCommand("boton_personalizado");
        b7.addActionListener(events);
        b7.setBounds(60, 450, 180, 80);
        b7.setBackground(new Color(50, 50, 50));
        b7.setForeground(Color.white);
        b7.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(b7);

        /*JLabel zoomLabel = new JLabel(new ImageIcon(this.getMyFire().getV().getConvolution().getImagen()));

        this.add(zoomLabel);

        zoomLabel.addMouseListener(events);

        ActionListener zoomListener = (ActionEvent e) -> {
            pi = MouseInfo.getPointerInfo();
            Point p = pi.getLocation();
            Rectangle r = new Rectangle(
                    p.x - (100),
                    p.y - (100),
                    200,
                    200);
            BufferedImage temp = robot.createScreenCapture(r);
            Graphics g = this.getMyFire().getV().getConvolution().getImagen().getGraphics();
            g.drawImage(temp, 0, 0, 256, 256, null);
            g.setColor(new Color(255, 0, 0, 128));
            int x = (256 / 2) - 1;
            int y = (256 / 2) - 1;
            g.drawLine(0, y, 256, y);
            g.drawLine(x, 0, x, 256);
            g.dispose();
            zoomLabel.repaint();
        };
        t = new Timer(40, zoomListener);
        t.start();*/

        t1 = new JFormattedTextField("-1");
        t1.setBounds(75, 550, 30, 30);
        this.add(t1);

        t2 = new JFormattedTextField("-1");
        t2.setBounds(135, 550, 30, 30);
        this.add(t2);

        t3 = new JFormattedTextField("0");
        t3.setBounds(195, 550, 30, 30);
        this.add(t3);

        t4 = new JFormattedTextField("-1");
        t4.setBounds(75, 600, 30, 30);
        this.add(t4);

        t5 = new JFormattedTextField("0");
        t5.setBounds(135, 600, 30, 30);
        this.add(t5);

        t6 = new JFormattedTextField("1");
        t6.setBounds(195, 600, 30, 30);
        this.add(t6);

        t7 = new JFormattedTextField("0");
        t7.setBounds(75, 650, 30, 30);
        this.add(t7);

        t8 = new JFormattedTextField("1");
        t8.setBounds(135, 650, 30, 30);
        this.add(t8);

        t9 = new JFormattedTextField("1");
        t9.setBounds(195, 650, 30, 30);
        this.add(t9);
        
        /*slider_2 = new JSlider(JSlider.VERTICAL, 0, 100, this.getMyFire().getV().getFoc().getCOOL());
        slider_2.setBounds(125, 220, 55, 150);
        slider_2.setInverted(false);
        slider_2.setMajorTickSpacing(25);
        slider_2.setMinorTickSpacing(5);
        slider_2.setPaintTicks(true);
        slider_2.setPaintLabels(true);
        slider_2.setSnapToTicks(true);
        slider_2.setToolTipText("Cools/s");
        slider_2.addChangeListener(event);

        this.add(slider_2);*/
        
        jslider1 = new JSlider(JSlider.HORIZONTAL, 0, 250, this.getMyFire().getV().getFoc().getBRILLO());
        jslider1.setBounds(50, 700, 225, 75);
        jslider1.setInverted(false);
        jslider1.setMajorTickSpacing(50);
        jslider1.setMinorTickSpacing(25);
        jslider1.setPaintTicks(true);
        jslider1.setPaintLabels(true);
        jslider1.setSnapToTicks(false);
        jslider1.setToolTipText("Bightness");
        jslider1.addChangeListener(events);
        
        this.add(jslider1);
    }

    private class Events implements ActionListener, MouseListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "boton_cambiarFondo":
                    openFile();
                    break;
                case "boton_oscuridad":
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getOscuridad());
                    getMyFire().getV().getFoc().setConvoluted(true);
                    break;
                case "boton_sinFiltro":
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getFiltroInicial());
                    getMyFire().getV().getFoc().setConvoluted(false);
                    break;
                case "boton_bordes":
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getBordes());
                    getMyFire().getV().getFoc().setConvoluted(true);
                    break;
                case "boton_alivio":
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getAlivio());
                    getMyFire().getV().getFoc().setConvoluted(true);
                    break;
                case "boton_nitidez":
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getNitidez());
                    getMyFire().getV().getFoc().setConvoluted(true);
                    break;
                case "boton_personalizado":
                    int aux[][] = new int[3][3];
                    aux[0][0] = Integer.parseInt(t1.getText());
                    aux[0][1] = Integer.parseInt(t2.getText());
                    aux[0][2] = Integer.parseInt(t3.getText());
                    aux[1][0] = Integer.parseInt(t4.getText());
                    aux[1][1] = Integer.parseInt(t5.getText());
                    aux[1][2] = Integer.parseInt(t6.getText());
                    aux[2][0] = Integer.parseInt(t7.getText());
                    aux[2][1] = Integer.parseInt(t8.getText());
                    aux[2][2] = Integer.parseInt(t9.getText());
                    getMyFire().getV().getConvolution().setPersonalizado(aux);
                    getMyFire().getV().getConvolution().setKernelSelected(getMyFire().getV().getConvolution().getPersonalizado());
                    getMyFire().getV().getFoc().setConvoluted(true);
                    break;
                case "return":
                    System.out.println("\nVolviendo a MYFIRE");
                    ControlPanelConvolution.this.removeAll();
                    cp = new ControlPanel(myFire, viewer);
                    cp.setBounds(0, 0, 400, 870);
                    ControlPanelConvolution.this.add(cp);
                    cp.setVisible(true);
                    repaint();
                    break;
                default:
                    System.out.println("\nSe ha producido un error\n");
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (zoomFactor == 2) {
                zoomFactor = 4;
            } else if (zoomFactor == 4) {
                zoomFactor = 8;
            } else if (zoomFactor == 8) {
                zoomFactor = 2;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            getMyFire().getV().getFoc().setBRILLO(jslider1.getValue());
        }

    }

}
