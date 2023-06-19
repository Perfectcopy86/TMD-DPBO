/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Canvas;
import javax.swing.JFrame;
import viewmodel.Game;
import viewmodel.KeyInputs;
import static viewmodel.Constants.gameOption.GAME_HEIGHT;
import static viewmodel.Constants.gameOption.GAME_WIDTH;

/**
 *
 * @author Rasyid andriansyah
 */
public class GameWindow extends Canvas{
    JFrame frame;
    
    public GameWindow(Game game){
        // konstruktor
        String title = "Keep Standing | Playing"; // set title
        frame = new JFrame(title); // buat frame
        frame.addKeyListener(new KeyInputs(game)); // menambah key listener
        frame.add(game); // menambah game ke frame
        frame.setSize(GAME_WIDTH, GAME_HEIGHT); // set ukuran frame
        frame.setLocationRelativeTo(null); // set lokasi frame dibuat
        frame.setResizable(false); // set resizeable frame
        frame.setVisible(true); // set frame agar visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default close frame
    }
    
    public void CloseWindow() {
        frame.setVisible(false); // membuat frame invisible
        frame.dispose(); // membersihkan frame
    }
    
}
