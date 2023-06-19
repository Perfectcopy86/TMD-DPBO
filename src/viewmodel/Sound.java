/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Rasyid andriansyah
 */
public class Sound {
    public Clip playSound(Clip clip, String filename){     
        // konstruktor
        try {
            // mengambil audio input
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("resources/" + filename).getAbsoluteFile());
       
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start(); 
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
            
        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
        return clip;
    }
    
    public void stopSound(Clip clip){

        clip.stop();
    }
}
