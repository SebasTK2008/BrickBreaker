package Modelo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Musica{

    private Clip clip;

    // Método para cargar y reproducir el archivo de música
    public void reproducirMusica(String filepath) {
        try {
            // Cargar el archivo de audio
           File file = new File(getClass().getResource("/Sonidos/MusicaFondo.wav").getFile());
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                // Reproducir en bucle la música
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("No se encontró el archivo de audio.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Método para detener la música
    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}