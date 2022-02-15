package myfire;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Alberto
 */
public class Convolution {

    private Color color;
    private BufferedImage imagen;
    private BufferedImage imagenConvolution;    
    private String filepath = "src/images/flores.jpg";
    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private final int[][] bordes = {
        {0, 1, 0},
        {1, -4, 1},
        {0, 1, 0}
    };
    private final int[][] oscuridad = {
        {-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, -1}
    };

    private final int[][] filtroInicial = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    };

    private final int[][] alivio = {
        {-1, -1, 0},
        {-1, 0, 1},
        {0, 1, 1}
    };

    private final int[][] nitidez = {
        {-1, -1, -1},
        {-1, 9, -1},
        {-1, -1, -1}
    };

    private int[][] personalizado = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };

    private int[][] kernelSelected = filtroInicial;    

    public Convolution() {

    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getImagenConvolution() {
        return imagenConvolution;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }    
    
    public int[][] getBordes() {
        return bordes;
    }

    public int[][] getOscuridad() {
        return oscuridad;
    }

    public int[][] getFiltroInicial() {
        return filtroInicial;
    }

    public int[][] getAlivio() {
        return alivio;
    }

    public int[][] getNitidez() {
        return nitidez;
    }

    public int[][] getPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado(int[][] personalizado) {
        this.personalizado = personalizado;
    }
    
    public int[][] getKernelSelected() {
        return kernelSelected;
    }

    public void setKernelSelected(int[][] kernelSelected) {
        this.kernelSelected = kernelSelected;
    }
    
    /**
     *  
     * @return array 
     */
    private int[][] convertToArray() {
        int width = this.getImagen().getWidth();
        int height = this.getImagen().getHeight();
        red = new int[width][height];
        green = new int[width][height];
        blue = new int[width][height];
        int[][] array = new int[width][height];
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array[0].length - 1; j++) {

                color = new Color(this.getImagen().getRGB(i, j));
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();

                int pixel = (red[i][j] + green[i][j] + blue[i][j]);
                array[i][j] = pixel;
            }
        }
        return array;
    }

    /**
     * 
     * @param imagenConvolucionada
     * @return array
     */
    public int[][] convertConvolutionToArray(BufferedImage imagenConvolucionada){
        
        int width = imagenConvolucionada.getWidth();
        int height = imagenConvolucionada.getHeight();
        red = new int[width][height];
        green = new int[width][height];
        blue = new int[width][height];
        int[][] array = new int[width][height];
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array[0].length - 1; j++) {
                
                color = new Color(imagenConvolucionada.getRGB(i, j));
                red[i][j] = color.getRed();
                green[i][j] = color.getGreen();
                blue[i][j] = color.getBlue();
                
                int pixel = (red[i][j] + green[i][j] + blue[i][j]);
                array[i][j] = pixel;
                
                               
            }
        }    
        return array;
    }
    
    public void calculatePixelConvoluted(BufferedImage imagenConvolucionada){
        int[][] matrix = convertConvolutionToArray(imagenConvolucionada);
        int rgb;
        int mRed;
        int mGreen;
        int mBlue;

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[0].length - 1; j++) {

                mRed = ((red[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (red[i][j - 1] * (kernelSelected[1][0]))
                        + (red[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (red[i - 1][j] * (kernelSelected[0][1]))
                        + (red[i][j] * (kernelSelected[1][1]))
                        + (red[i + 1][j] * (kernelSelected[2][1]))
                        + (red[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (red[i][j + 1] * (kernelSelected[1][2]))
                        + (red[i + 1][j + 1] * (kernelSelected[2][2])));

                mGreen = ((green[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (green[i][j - 1] * (kernelSelected[1][0]))
                        + (green[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (green[i - 1][j] * (kernelSelected[0][1]))
                        + (green[i][j] * (kernelSelected[1][1]))
                        + (green[i + 1][j] * (kernelSelected[2][1]))
                        + (green[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (green[i][j + 1] * (kernelSelected[1][2]))
                        + (green[i + 1][j + 1] * (kernelSelected[2][2])));

                mBlue = ((blue[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (blue[i][j - 1] * (kernelSelected[1][0]))
                        + (blue[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (blue[i - 1][j] * (kernelSelected[0][1]))
                        + (blue[i][j] * (kernelSelected[1][1]))
                        + (blue[i + 1][j] * (kernelSelected[2][1]))
                        + (blue[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (blue[i][j + 1] * (kernelSelected[1][2]))
                        + (blue[i + 1][j + 1] * (kernelSelected[2][2])));

                mRed = (int) (mRed / divisionKernel());
                mGreen = (int) (mGreen / divisionKernel());
                mBlue = (int) (mBlue / divisionKernel());

                if (mRed < 0) {
                    mRed = 0;
                } else if (mRed > 255) {
                    mRed = 255;
                }

                if (mGreen < 0) {
                    mGreen = 0;
                } else if (mGreen > 255) {
                    mGreen = 255;
                }

                if (mBlue < 0) {
                    mBlue = 0;
                } else if (mBlue > 255) {
                    mBlue = 255;
                }

                rgb = mRed << 16 | mGreen << 8 | mBlue;
                imagenConvolucionada.setRGB(i, j, new Color(rgb).getRGB());
                

            }

        }
    }
    public void calculatePixel() {

        int[][] matrix = convertToArray();
        int rgb;
        int mRed;
        int mGreen;
        int mBlue;

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[0].length - 1; j++) {

                mRed = ((red[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (red[i][j - 1] * (kernelSelected[1][0]))
                        + (red[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (red[i - 1][j] * (kernelSelected[0][1]))
                        + (red[i][j] * (kernelSelected[1][1]))
                        + (red[i + 1][j] * (kernelSelected[2][1]))
                        + (red[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (red[i][j + 1] * (kernelSelected[1][2]))
                        + (red[i + 1][j + 1] * (kernelSelected[2][2])));

                mGreen = ((green[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (green[i][j - 1] * (kernelSelected[1][0]))
                        + (green[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (green[i - 1][j] * (kernelSelected[0][1]))
                        + (green[i][j] * (kernelSelected[1][1]))
                        + (green[i + 1][j] * (kernelSelected[2][1]))
                        + (green[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (green[i][j + 1] * (kernelSelected[1][2]))
                        + (green[i + 1][j + 1] * (kernelSelected[2][2])));

                mBlue = ((blue[i - 1][j - 1] * (kernelSelected[0][0]))
                        + (blue[i][j - 1] * (kernelSelected[1][0]))
                        + (blue[i + 1][j - 1] * (kernelSelected[2][0]))
                        + (blue[i - 1][j] * (kernelSelected[0][1]))
                        + (blue[i][j] * (kernelSelected[1][1]))
                        + (blue[i + 1][j] * (kernelSelected[2][1]))
                        + (blue[i - 1][j + 1] * (kernelSelected[0][2]))
                        + (blue[i][j + 1] * (kernelSelected[1][2]))
                        + (blue[i + 1][j + 1] * (kernelSelected[2][2])));

                mRed = (int) (mRed / divisionKernel());
                mGreen = (int) (mGreen / divisionKernel());
                mBlue = (int) (mBlue / divisionKernel());

                if (mRed < 0) {
                    mRed = 0;
                } else if (mRed > 255) {
                    mRed = 255;
                }

                if (mGreen < 0) {
                    mGreen = 0;
                } else if (mGreen > 255) {
                    mGreen = 255;
                }

                if (mBlue < 0) {
                    mBlue = 0;
                } else if (mBlue > 255) {
                    mBlue = 255;
                }

                rgb = mRed << 16 | mGreen << 8 | mBlue;
                imagen.setRGB(i, j, new Color(rgb).getRGB());

            }

        }

    }

    private int divisionKernel() {
        int sum = 0;
        for (int i = 0; i < this.getKernelSelected().length; i++) {
            for (int j = 0; j < this.getKernelSelected().length; j++) {

                sum += this.getKernelSelected()[i][j];

            }
        }
        if (sum <= 0) {
            sum = 1;
        }
        return sum;
    }
}