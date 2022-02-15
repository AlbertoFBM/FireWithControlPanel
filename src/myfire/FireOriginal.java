package myfire;

/**
 *
 * @author Alberto
 */
public class FireOriginal extends Fire {

    public FireOriginal(int width, int height, int imageType, Viewer viewer) {
        super(width, height, imageType, viewer);
    }

    public void runFire() {

        createSparks();
        createCool();
        temperatureEvolve();
        createFlameImage();
    }

    @Override
    public void createSparks() {
        super.createSparks();
    }

    @Override
    public void createCool() {
        super.createCool();
    }
    
    @Override
    public void temperatureEvolve() {
        super.temperatureEvolve();
    }

    @Override
    public void createFlameImage() {
        super.createFlameImage();
    }

    @Override
    public void pause() {
        super.pause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void play() {
        super.play(); //To change body of generated methods, choose Tools | Templates.
    }
    

}
