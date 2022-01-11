package myfire;

import java.awt.Color;

/**
 *
 * @author Alberto
 */
public class TargetColor {
    
    private Color color;
    private int temperatura;

    public TargetColor(Color c, int temp){
        color = c;
        temperatura = temp;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    
    
    
}
