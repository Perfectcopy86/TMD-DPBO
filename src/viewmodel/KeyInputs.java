/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// mengakses properti
import viewmodel.Game.STATE;

/**
 *
 * @author Rasyid andriansyah
 */
public class KeyInputs implements KeyListener {
    private final Game game;

    public KeyInputs(Game game) {
        // konstruktor
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP -> game.getPlayer().setUp(true); // atas
            case KeyEvent.VK_LEFT -> game.getPlayer().setLeft(true); // kiri
            case KeyEvent.VK_DOWN -> game.getPlayer().setDown(true); //bawah
            case KeyEvent.VK_RIGHT -> game.getPlayer().setRight(true); // kanan
        }
        
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE) {
            game.gameState = STATE.GameOver; // ubah state menjadi game over
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> game.getPlayer().setUp(false);
            case KeyEvent.VK_LEFT -> game.getPlayer().setLeft(false);
            case KeyEvent.VK_DOWN -> game.getPlayer().setDown(false);
            case KeyEvent.VK_RIGHT -> game.getPlayer().setRight(false);
        }
    }
}
