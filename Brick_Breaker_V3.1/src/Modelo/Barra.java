
package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Barra {
    private int alto;
    private int ancho;
    private int posX;
    private int posY;
    private Image imagenBarra;
    
    public Barra(){  
        imagenBarra=new ImageIcon("src/Imagenes/barra0.png").getImage();
    }
    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    /**
     * @return the imagenBarra
     */
    public Image getImagenBarra() {
        return imagenBarra;
    }

    /**
     * @param imagenBarra the imagenBarra to set
     */
    public void setImagenBarra(Image imagenBarra) {
        this.imagenBarra = imagenBarra;
    }
    
    
}
