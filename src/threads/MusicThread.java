package threads;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicThread extends Thread{
	
	String mensaje;
    String sonido;
    int timeRep=0;
    boolean stop;
    public MusicThread(){
        
    }
    public MusicThread(String sonido){
        this.sonido=sonido;
    }
    public MusicThread(String sonido,boolean stop){
        this.sonido=sonido;
        this.stop = stop;
    }
    public void run(){
        repSonido(sonido);

    }
    
    public void stopa() {
    	stop = false;
    }

    public void setMensaje(String msj){
        this.mensaje = msj;
    }
    
    public void repSonido(String sonido){
        SourceDataLine soundLine = null;
        int BUFFER_SIZE = 64*1024;  // 64 KB
        // Set up an audio input stream piped from the sound file.
        try {
           File soundFile = new File(sonido);
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
           AudioFormat audioFormat = audioInputStream.getFormat();
           DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
           soundLine = (SourceDataLine) AudioSystem.getLine(info);
           soundLine.open(audioFormat);
           soundLine.start();
           int nBytesRead = 0;
           byte[] sampledData = new byte[BUFFER_SIZE];
           while (nBytesRead != -1) {
              nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
              if (nBytesRead >= 0) {
                 // Writes audio data to the mixer via this source data line.
                 soundLine.write(sampledData, 0, nBytesRead);
              }
           }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           ex.printStackTrace();
        } finally {
           soundLine.drain();
           soundLine.close();
        }
    }

}
