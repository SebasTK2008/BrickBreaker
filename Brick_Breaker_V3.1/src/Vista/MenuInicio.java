package Vista;

import Juego.VideoJuego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuInicio extends JPanel implements ActionListener {
    private JButton jugarButton;
    private Image fondo;

    public MenuInicio() {
        fondo = new ImageIcon(getClass().getResource("/Imagenes/fondoMenu.png")).getImage();

        // Crear el botón y personalizar su apariencia
        jugarButton = new JButton("Jugar");
        jugarButton.setFont(new Font("Serif", Font.BOLD, 18));
        jugarButton.setForeground(Color.WHITE);
        jugarButton.setBackground(new Color(128, 0, 128)); // Morado
        jugarButton.setFocusPainted(false);
        jugarButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Efecto de hover para cambiar color al pasar el cursor
        jugarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jugarButton.setBackground(new Color(150, 0, 150)); // Morado más claro
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jugarButton.setBackground(new Color(128, 0, 128));
            }
        });

        jugarButton.addActionListener(this);
        
        // Establecer un tamaño preferido para el botón
        jugarButton.setPreferredSize(new Dimension(150, 40));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dibujar el fondo
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Serif", Font.BOLD, 30));

        // Dibujar borde del texto para el título
        g.drawString("BIENVENIDO A BRICK BREAKER", 78, 308); // Arriba izquierda
        g.drawString("BIENVENIDO A BRICK BREAKER", 82, 308); // Arriba derecha
        g.drawString("BIENVENIDO A BRICK BREAKER", 78, 312); // Abajo izquierda
        g.drawString("BIENVENIDO A BRICK BREAKER", 82, 312); // Abajo derecha

        // Color y posición del texto principal
        g.setColor(Color.WHITE);
        g.drawString("BIENVENIDO A BRICK BREAKER", 80, 310); // Centro

        // Establecer la posición del botón
        jugarButton.setBounds(250, 350, 150, 40); // Cambiar la posición según sea necesario
        add(jugarButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jugarButton) {
            // Cambia a la pantalla del juego
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new VideoJuego());
            frame.revalidate(); // Actualiza la ventana
        }
    }
}
