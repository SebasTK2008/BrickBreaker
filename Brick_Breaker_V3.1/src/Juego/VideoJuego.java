package Juego;
import Modelo.*;
import Vista.GeneradorMapa;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VideoJuego extends JPanel implements KeyListener, ActionListener {
    private Musica musicaFondo;
    private Barra barra=new Barra();
    private int nivel=1;
    private boolean play=false;
    private Timer timer;
    private int retraso=5;
    private int tamañoBarra=150;
    private int vidas=3;
    private boolean perdioVida = false;
    private GeneradorMapa mapa;
    private Image imagenLevel1;
    private Image imagenLevel2;
    private Image imagenLevel3;
    private Image imagenBarra;
    private Image imagenBloquePiedra;
    private Pelota pelota= new Pelota(120, 350, 10, 3, 3, 682, 600);
    
    public VideoJuego(){
        mapa= new GeneradorMapa(3, 7);
        barra.setPosX(350); //=310, 550, 150, 12
        barra.setPosY(540);
        barra.setAncho(150);
        barra.setAlto(25);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(retraso, this);
        timer.start();
        musicaFondo = new Musica();
        musicaFondo.reproducirMusica("src/Sonidos/MusicaFondo.wav");
        imagenLevel1 = new ImageIcon("src/Imagenes/fondoB.png").getImage();
        imagenLevel2 = new ImageIcon("src/Imagenes/fondoA.png").getImage();
        imagenLevel3 = new ImageIcon("src/Imagenes/background.png").getImage();
        imagenBarra = new ImageIcon("src/Imagenes/barra1.png").getImage();
        imagenBloquePiedra=new ImageIcon("src/Imagenes/bloquedePiedra.png").getImage();
     addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX()< 0) {
            barra.setPosX(0);
        } else if (e.getX() + tamañoBarra > 692) {
            barra.setPosX(692-barra.getAncho());
        } else {
            barra.setPosX(e.getX()); 
        }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) { }
        });
        
    }
    
    public void detenerMusica() {
        musicaFondo.detenerMusica();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Dibuja el fondo
    switch(nivel){
        case 1:
            g.drawImage(imagenLevel1, 1, 1, getWidth(), getHeight(), null);
            break;
        case 2:
            g.drawImage(imagenLevel2, 1, 1, getWidth(), getHeight(), null);
            break;
        case 3:
            g.drawImage(imagenLevel3, 1, 1, getWidth(), getHeight(), null);
                break;
    }
    
    // Dibuja el mapa
    mapa.draw((Graphics2D)g);
    // Bordes del juego
    g.setColor(Color.ORANGE);
    g.fillRect(0, 0, 4, 592);
    g.fillRect(0, 0, 692, 3);
    g.fillRect(682, 0, 4, 592);
    // Puntuación, nivel y vidas
    g.setColor(Color.white);
    g.setFont(new Font("serif", Font.BOLD, 20));
    g.drawString("PUNTUACION: " + mapa.getPuntuacion(), 500, 30);
    g.drawString("NIVEL: " + nivel, 250, 30);
    g.drawString("VIDAS: " + vidas, 30, 30);
    // Dibuja la barra
    g.drawImage(barra.getImagenBarra(), barra.getPosX(), barra.getPosY(), barra.getAncho(), barra.getAlto(), null);
    // Dibuja la pelota
    g.drawImage(pelota.getImagen(), pelota.getX(), pelota.getY(), pelota.getRadio() * 2, pelota.getRadio() * 2, null);
    // Dibuja bloque piedra
    g.drawImage(imagenBloquePiedra, 70, 400, 80, 50, null);
    // Verificación de nivel completado
    if (mapa.getBloquesTotales() <= 0 && nivel < 3) {
        play = false;
        pelota.setVelocidadX(0);
        pelota.setVelocidadY(0);
        g.setColor(Color.GREEN);
        g.setFont(new Font("serif", Font.BOLD, 28));
        g.drawString("FELICIDADES, HAS SUPERADO EL NIVEL " + nivel + " :)", 80, 300);
        g.setColor(Color.white);
        g.drawString("PUNTUACION: " + mapa.getPuntuacion(), 220, 350);
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 24));
        g.drawString("PRESIONA ENTER PARA PASAR AL SIGUIENTE NIVEL", 43, 390);
        detenerMusica();
    }
    if (mapa.getBloquesTotales() <= 0 && nivel == 3) {
        play = false;
        pelota.setVelocidadX(0);
        pelota.setVelocidadY(0);
        g.setColor(Color.GREEN);
        g.setFont(new Font("serif", Font.BOLD, 28));
        g.drawString("FELICIDADES, HAS GANADO :)", 150, 300);
        g.setColor(Color.white);
        g.drawString("PUNTUACION: " + mapa.getPuntuacion(), 220, 350);
       g.setColor(Color.white);
        g.drawString("PRESIONA ENTER PARA VOLVER A JUGAR", 65, 390);
        detenerMusica();
    }
    
    // Verificación de vida perdida
if (vidas == 0) {
    play = false;
    pelota.setVelocidadX(0);
    pelota.setVelocidadY(0);
    g.setColor(Color.RED);
    g.setFont(new Font("serif", Font.BOLD, 28));
    g.drawString("TE QUEDASTE SIN VIDAS:( ", 170, 300);
   g.setColor(Color.white);
    g.drawString("PUNTUACION: " + mapa.getPuntuacion(), 220, 350);
    g.setColor(Color.white);
    g.drawString("PRESIONA ENTER PARA VOLVER A JUGAR", 65, 390);
} else if (perdioVida) {
    if (vidas > 0) {
        g.setColor(Color.orange);
        g.setFont(new Font("serif", Font.BOLD, 28));
        g.drawString("TE QUEDAN " + vidas + " VIDAS", 170, 300);
       g.setColor(Color.white);
        g.drawString("PRESIONA ENTER PARA CONTINUAR", 65, 390);
    }
}
}
        
   private void verificarColisionBarra() {
    // Calcular la posición de la bola
    int bolaX = pelota.getX();
    int bolaY = pelota.getY();
    int bolaRadio = pelota.getRadio();
    
    // Obtener la posición de la barra
    int barraX = barra.getPosX();
    int barraY = 554; // Posición fija de la barra, ajusta si es necesario
    int barraAncho = barra.getAncho();
    
    // Verificar si la bola está en contacto con la barra
    if (bolaY + bolaRadio * 2 >= barraY && bolaY + bolaRadio * 2 <= barraY + 10) { // 10 es el grosor de la barra
        if (bolaX + bolaRadio >= barraX && bolaX <= barraX + barraAncho) {
            // Calcular la posición de impacto en la barra
            int impacto = bolaX - barraX; // Distancia desde el lado izquierdo de la barra
            double porcentaje = (double) impacto / barraAncho; // Porcentaje del impacto

            // Cambiar el ángulo de rebote basado en la posición del impacto
            if (porcentaje < 0.3) {
                // Rebote hacia la izquierda
                pelota.setVelocidadX(-Math.abs(pelota.getVelocidadX())); // Cambiar dirección
            } else if (porcentaje > 0.7) {
                // Rebote hacia la derecha
                pelota.setVelocidadX(Math.abs(pelota.getVelocidadX())); // Cambiar dirección
            }
            
            pelota.rebotarVerticalmente(); // Hacer que la bola rebote
        }
    }
}

    
    private void siguienteNivel(){
    int puntaje = mapa.getPuntuacion();
    if(nivel==1){
        mapa=new GeneradorMapa(3, 7);
        barra.setAncho(150);
        pelota.setVelocidadX(3);
        pelota.setVelocidadY(3);
    }
    else if(nivel==2){
        mapa=new GeneradorMapa(4, 8);
        barra.setAncho(110);
        mapa.setBloquesTotales(4*8);
        pelota.setVelocidadX(4);
        pelota.setVelocidadY(4);
    }else if(nivel==3){
        mapa=new GeneradorMapa(5, 9);
        barra.setAncho(80);
        mapa.setBloquesTotales(5*9);
        pelota.setVelocidadX(5);
        pelota.setVelocidadY(5);
    }
    mapa.setPuntuacion(puntaje); 
}
    
    private void verificarColisionBloque(){
        A: for(int i=0; i<mapa.mapa.length; i++){
            for(int j=0; j<mapa.mapa[0].length; j++){
                if(mapa.mapa[i][j]>0){
                    int bloqueX= j*mapa.anchobloque+80;
                    int bloqueY=i*mapa.altobloque+50;
                    int anchobloque=mapa.anchobloque;
                    int altobloque=mapa.altobloque;
                    Rectangle rectangulo=new Rectangle(bloqueX, bloqueY, anchobloque, altobloque);
                    Rectangle bolaRectan = new Rectangle(pelota.getX(), pelota.getY(), pelota.getRadio() * 2, pelota.getRadio() * 2);
                    Rectangle bloqueRectan= rectangulo;
                    Rectangle bloquePiedra=new Rectangle(70, 400, 80, 50);
                   if (bolaRectan.intersects(bloqueRectan)) {
    mapa.setValorbloque(i, j);

    // Calcular el centro de la pelota y las coordenadas del bloque
    double bolaCentroX = pelota.getX() + pelota.getRadio();
    double bolaCentroY = pelota.getY() + pelota.getRadio();
    double bloqueCentroX = bloqueRectan.x + bloqueRectan.width / 2;
    double bloqueCentroY = bloqueRectan.y + bloqueRectan.height / 2;

    // Calcular las distancias entre los centros
    double distanciaX = bolaCentroX - bloqueCentroX;
    double distanciaY = bolaCentroY - bloqueCentroY;

    // Determinar la dirección del rebote
    if (Math.abs(distanciaX) > Math.abs(distanciaY)) {
        // Rebote horizontal
        if (distanciaX > 0) {
            // La pelota está a la derecha del bloque
            pelota.rebotarHorizontalmente(); // Rebote hacia la izquierda
        } else {
            // La pelota está a la izquierda del bloque
            pelota.rebotarHorizontalmente(); // Rebote hacia la derecha
        }
    } else {
        // Rebote vertical
        if (distanciaY > 0) {
            // La pelota está debajo del bloque
            pelota.rebotarVerticalmente(); // Rebote hacia arriba
        } else {
            // La pelota está encima del bloque
            pelota.rebotarVerticalmente(); // Rebote hacia abajo
        }
    }
    break A;
}
                    if (bolaRectan.intersects(bloquePiedra)) {
    // Calcular el centro de la bola
    int bolaCentroX = pelota.getX() + pelota.getRadio();
    int bolaCentroY = pelota.getY() + pelota.getRadio();

    // Calcular las distancias desde el centro de la bola a cada lado del bloque
    double distanciaIzquierda = Math.abs(bolaCentroX - bloquePiedra.x);
    double distanciaDerecha = Math.abs(bolaCentroX - (bloquePiedra.x + bloquePiedra.width));
    double distanciaSuperior = Math.abs(bolaCentroY - bloquePiedra.y);
    double distanciaInferior = Math.abs(bolaCentroY - (bloquePiedra.y + bloquePiedra.height));

    // Determina el lado de la colisión
    double menorDistancia = Math.min(Math.min(distanciaIzquierda, distanciaDerecha), Math.min(distanciaSuperior, distanciaInferior));

    if (menorDistancia == distanciaIzquierda) { // Lado izquierdo
        pelota.rebotarHorizontalmente();
    } else if (menorDistancia == distanciaDerecha) { // Lado derecho
        pelota.rebotarHorizontalmente();
    } else if (menorDistancia == distanciaSuperior) { // Parte superior
        pelota.rebotarVerticalmente();
    } else if (menorDistancia == distanciaInferior) { // Parte inferior
        pelota.rebotarVerticalmente();
    } else {
        rebotarDiagonalmente(bloquePiedra); // Rebote en diagonal si toca una esquina
    }
    break A;
}

               }
            }
        }
    }
   private void rebotarDiagonalmente(Rectangle bloque) {
    // Calcular el centro de la pelota
    int bolaCentroX = pelota.getX() + pelota.getRadio();
    int bolaCentroY = pelota.getY() + pelota.getRadio();

    // Vector normal entre el centro de la pelota y el centro del bloque
    double normalX = bolaCentroX - (bloque.x + bloque.width / 2.0);
    double normalY = bolaCentroY - (bloque.y + bloque.height / 2.0);

    // Obtener la longitud de la normal
    double normalLength = Math.sqrt(normalX * normalX + normalY * normalY);

    // Normalizar el vector normal
    normalX /= normalLength;
    normalY /= normalLength;

    // Calcular el producto punto
    double dotProduct = pelota.getVelocidadX() * normalX + pelota.getVelocidadY() * normalY;

    // Calcular la velocidad reflejada
    double reflectedVelocidadX = pelota.getVelocidadX() - 2 * dotProduct * normalX; 
    double reflectedVelocidadY = pelota.getVelocidadY() - 2 * dotProduct * normalY; 

    // Calcular la magnitud de la velocidad original
    double magnitudOriginal = Math.sqrt(pelota.getVelocidadX() * pelota.getVelocidadX() + pelota.getVelocidadY() * pelota.getVelocidadY());
    
    // Normalizar la nueva dirección y multiplicar por la magnitud original
    double nuevaVelocidadX = reflectedVelocidadX * (magnitudOriginal / Math.sqrt(reflectedVelocidadX * reflectedVelocidadX + reflectedVelocidadY * reflectedVelocidadY));
    double nuevaVelocidadY = reflectedVelocidadY * (magnitudOriginal / Math.sqrt(reflectedVelocidadX * reflectedVelocidadX + reflectedVelocidadY * reflectedVelocidadY));
    
    // Asignar la nueva velocidad reflejada
    pelota.setVelocidadX((int) nuevaVelocidadX);
    pelota.setVelocidadY((int) nuevaVelocidadY);
}

   @Override
public void actionPerformed(ActionEvent e) {
    if (play) {
        if (pelota.getY() > 560) {
            vidas--;
            play = false;
            perdioVida = true;
            musicaFondo.detenerMusica();
            repaint();
        } else {
            verificarColisionBarra();
            verificarColisionBloque();
            pelota.mover();
        }
    }
    requestFocus(); 
    repaint();
}
    
    @Override
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (barra.getPosX() >= 610) {
            barra.setPosX(610);
        } else {
            moverDerecha();
        }
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (barra.getPosX() < 10) {
            barra.setPosX(10);
        } else {
            moverIzquierda();
        }
    }
    
    // Reiniciar el juego si se han perdido todas las vidas
if (pelota.getY() > 560 && vidas <= 0) {
    if (!play) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Reiniciar el juego
            pelota.setX(120);
            pelota.setY(350);
            pelota.setVelocidadX(3);
            pelota.setVelocidadY(3);
            barra.setPosX(310);
            mapa.setPuntuacion(0);
            mapa.setBloquesTotales(21);
            nivel = 1;
            siguienteNivel();
            musicaFondo.reproducirMusica("src/Sonidos/MusicaFondo.wav");
            vidas = 3; 
            perdioVida = false; 
            repaint();
        }
    }
}
    
    // Avanzar al siguiente nivel
    if (e.getKeyCode() == KeyEvent.VK_ENTER && mapa.getBloquesTotales() <= 0 && nivel == 3 && vidas > 0) {
        if (!play) {
            // Reiniciar para jugar nuevamente
            pelota.setX(120);
            pelota.setY(350);
           barra.setPosX(310);
            mapa.setPuntuacion(0);
            mapa.setBloquesTotales(21);
            nivel = 1;
            siguienteNivel();
            musicaFondo.reproducirMusica("src/Sonidos/MusicaFondo.wav");
            repaint();
        }
    }

    if (e.getKeyCode() == KeyEvent.VK_ENTER && mapa.getBloquesTotales() <= 0 && nivel < 3 && vidas > 0) {
        if (!play) {
            pelota.setX(120);
            pelota.setY(350);
            pelota.setVelocidadX(3);
            pelota.setVelocidadY(3);
            barra.setPosX(310); 
            nivel++;
            siguienteNivel();
            musicaFondo.reproducirMusica("src/Sonidos/MusicaFondo.wav");
            repaint();
        }
    }

    // Reinicia la pelota y vuelve a activar el juego
    if (pelota.getY() > 560 && vidas > 0 && perdioVida) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (nivel){
                case 1:
                    pelota.setX(120);
                    pelota.setY(350);
                    pelota.setVelocidadX(3);
                    pelota.setVelocidadY(3);
                    break; 
                case 2:
                    pelota.setX(120);
                    pelota.setY(350);
                    pelota.setVelocidadX(4);
                    pelota.setVelocidadY(4);
                    break; 
                case 3:
                    pelota.setX(120);
                    pelota.setY(350);
                    pelota.setVelocidadX(5);
                    pelota.setVelocidadY(5);
                    break;
            }  
            play = true;
            perdioVida = false; // Restablece la bandera para la próxima pérdida
            musicaFondo.reproducirMusica("src/Sonidos/MusicaFondo.wav");
            repaint();
        }
    }
}

         
     @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void moverDerecha(){
        play = true;
        barra.setPosX(barra.getPosX()+30);
    }
    public void moverIzquierda(){
        play = true;
        barra.setPosX(barra.getPosX()-30);
    }
     
}