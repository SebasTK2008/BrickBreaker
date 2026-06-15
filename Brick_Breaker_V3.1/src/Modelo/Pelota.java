package Modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pelota {

    /**
     * @return the velocidadX
     */
    public int getVelocidadX() {
        return velocidadX;
    }

    /**
     * @return the velocidadY
     */
    public int getVelocidadY() {
        return velocidadY;
    }
    private Image imagenPelota;
    private int x, y; // Posición de la pelota
    private int velocidadX, velocidadY; // Velocidad de la pelota
    private int radio; // Radio de la pelota
    private int anchoPanel, altoPanel;
    private Barra barra=new Barra();// Dimensiones del área de dibujo

    public Pelota(int x, int y, int radio, int velocidadX, int velocidadY, int anchoPanel, int altoPanel) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.anchoPanel = anchoPanel;
        this.altoPanel = altoPanel;
        imagenPelota = new ImageIcon("src/Imagenes/pelota.png").getImage(); // Ruta a la imagen
    }

    public void mover() {
        x += getVelocidadX();
        y += getVelocidadY();

        // Detectar los bordes y cambiar la dirección si es necesario
        if (x - radio < 0 || x + radio > anchoPanel) {
            velocidadX = -getVelocidadX();
        }
        if (y - radio < 0 || y + radio > altoPanel) {
            velocidadY = -getVelocidadY();
        }
    }
  
    
    public void setX(int X){
        x=X;
    }
    public void setY(int Y){
        y=Y;
    }
   
    public void setVelocidadX(int velx){
        velocidadX=velx;
    }
     public void setVelocidadY(int vely){
        velocidadY=vely;
    }
    // Métodos getter{
            
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void rebotarVerticalmente() {
    velocidadY = -getVelocidadY();
        }
    public void rebotarHorizontalmente(){
        velocidadX=-getVelocidadX();
    }

public Image getImagen() {
        return imagenPelota; // Getter para la imagen
    }
    public int getRadio() {
        return radio;
    }
}

