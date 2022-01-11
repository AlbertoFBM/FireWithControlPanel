package myfire;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Alberto
 */
public class FirePalette {
    
    private int nColors = 256;
    private Color[] paleta = new Color[nColors];
    private ArrayList<TargetColor> targetColors = new ArrayList<>();
    
    public FirePalette(){
        
    }

    public int getnColors() {
        return nColors;
    }

    public void setnColors(int nColors) {
        this.nColors = nColors;
    }

    public void setColores(Color[] colores) {
        this.paleta = colores;
    }

    public void setTargetColors(ArrayList<TargetColor> targetColors) {
        this.targetColors = targetColors;
    }
    
    public void addColor(TargetColor tColor){
        targetColors.add(tColor);
    }
    
    public int getColor(int temperatura){
        
        try{
            if(temperatura >= 0 && temperatura<=256){
            int aux;
            aux = (this.paleta[temperatura].getAlpha()<<24
                    | this.paleta[temperatura].getRed()<<16
                    | this.paleta[temperatura].getGreen()<<8
                    | this.paleta[temperatura].getBlue());
            return aux;
            
        }else{
            return 0;
        }
        }catch(NullPointerException e){
            System.out.println("e");
        }
        return 0;
    }
    
    public void createPalette(){
        targetColors.sort(Comparator.comparing(ordenaColores -> ordenaColores.getTemperatura()));
        
        for(int i=1; i<targetColors.size(); i++){
            interpolarColor(targetColors.get(i-1), targetColors.get(i));
        }
    }
    
    private void interpolarColor(TargetColor colorInicial, TargetColor colorFinal){
        // coger dos colores, y pasarlos a rgba
        float colorInicialR = colorInicial.getColor().getRed();
        float colorInicialG = colorInicial.getColor().getGreen();
        float colorInicialB = colorInicial.getColor().getBlue();
        float colorInicialA = colorInicial.getColor().getAlpha();
        float colorFinalR = colorFinal.getColor().getRed();
        float colorFinalG = colorFinal.getColor().getGreen();
        float colorFinalB = colorFinal.getColor().getBlue();
        float colorFinalA = colorFinal.getColor().getAlpha();
        
        int steps = colorFinal.getTemperatura() - colorInicial.getTemperatura();
        
        float incrementoR = colorFinalR - colorInicialR;
        float incrementoG = colorFinalG - colorInicialG;
        float incrementoB = colorFinalB - colorInicialB;
        float incrementoA = colorFinalA - colorInicialA;
        
        paleta[colorInicial.getTemperatura()] = colorInicial.getColor();
        
        float incrementoStepsR = incrementoR / steps;
        float incrementoStepsG = incrementoG / steps;
        float incrementoStepsB = incrementoB / steps;
        float incrementoStepsA = incrementoA / steps;
            
        for(int i=1; i<steps; i++){
            int r = Math.round(colorInicialR+incrementoStepsR*i);
            int g = Math.round(colorInicialG+incrementoStepsG*i);
            int b = Math.round(colorInicialB+incrementoStepsB*i);
            int a = Math.round(colorInicialA+incrementoStepsA*i);
            Color colorAuxiliar = new Color(r,g,b,a);
            paleta[colorInicial.getTemperatura()+i] = colorAuxiliar; 
        }
        
        paleta[colorFinal.getTemperatura()] = colorFinal.getColor();
    }
}
