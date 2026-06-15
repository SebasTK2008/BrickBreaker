package Vista;
import Juego.*;
import javax.swing.*;
public class Main {
   public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Juego Bola");
        frame.setBounds(10, 100, 700, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MenuInicio()); // Mostrar el menú de inicio
        frame.setVisible(true);
    }
}