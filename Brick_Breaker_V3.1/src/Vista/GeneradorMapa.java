
package Vista;

import Juego.VideoJuego;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GeneradorMapa {
    public int mapa[][];
    public int anchobloque;
    public int altobloque;
    private Random rand;
    private Image imagen1, imagen2, imagen3;
    private int bloquesTotales=21;
    private int puntuacion=0;
    public GeneradorMapa(int filas, int columnas){
        mapa=new int[filas][columnas];
        Random random = new Random();
        int c=0;
        for(int i=0; i<mapa.length; i++){
            for(int j=0; j<mapa[0].length; j++){               
                   c=random.nextInt(3) + 1;
                    mapa[i][j]=c; 
                  
            }
        }
        imagen1 = new ImageIcon("src/Imagenes/l3-Photoroom.png").getImage();
        imagen2 = new ImageIcon("src/Imagenes/l2-Photoroom.png").getImage();
         imagen3 = new ImageIcon("src/Imagenes/l1-Photoroom.png").getImage();
        anchobloque=540/columnas;
        altobloque=150/filas;
    }
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] > 0) {
                    if(mapa[i][j]==3){
                    g.drawImage(imagen3, j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque, null);
                    /*g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque);*/
                    }
                    if(mapa[i][j]==2){
                        g.drawImage(imagen2, j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque, null);
                    /*g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque);*/
                    }
                    if(mapa[i][j]==1){
                        g.drawImage(imagen1, j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque, null);
                    /*g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * anchobloque + 80, i * altobloque + 50, anchobloque, altobloque);*/
                    }
                }
            }
        }
    }
    
   public void setValorbloque(int filas, int columnas) {
        if (mapa[filas][columnas] > 0) {
            mapa[filas][columnas]--; // Reduce el valor del bloque

            // Verificar si el bloque fue destruido
            if (mapa[filas][columnas] == 0) {
                bloquesTotales--;
                puntuacion+=5;// Decrementa el contador de bloques totales
            }
        }
    }

    public int getBloquesTotales() {
        return bloquesTotales; // Devuelve la cantidad de bloques restantes
    }
    
    public void setBloquesTotales(int x){
    bloquesTotales=x;
    }
    public int getPuntuacion() {
        return puntuacion; // Devuelve la cantidad de bloques restantes
    }
    
    public void setPuntuacion(int x){
    puntuacion=x;
    }
}