/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import viewmodel.Constants;
import static viewmodel.Constants.gameOption.GAME_SPEED;

/**
 *
 * @author Rasyid andriansyah
 */
public class Obstacle extends GameObject {
    // properti obstacle
    private final int obsType; // penentu tipenya (lantai/pilar)

    // inisialisasi library
    Random rand = new Random(); 
    Color color; // warna

    private int scoreValue;
    public boolean isColliding= false;
    

    public Obstacle(float x, float y, int width, int height, int obsType, int scoreValue) {
        super(x, y, width, height);
        this.obsType = obsType;
        this.scoreValue = scoreValue;
        setColorObstacle();
    }


    private void setColorObstacle() {
        // penentu warna obstacle
        // jika tipenya 0 (lantai) maka warnanya hijau
        if (obsType == 0) color = new Color(51, 255, 102);
        else {
            // jika tipenya 1 (bukan lantai) maka warnanya di random
            int r = ((Constants.gameOption.GAME_HEIGHT / 255) * height) % 255;
            int g = rand.nextInt(255);
            int b = rand.nextInt(255 - 100) + 100;
            color = new Color(r, g, b);
        }
    }

    public void update() {
        // mengupdate posisi dan collisionBox
        updatePos();
        updateCollisionBox();
    }

    @Override
    public void render(Graphics g) {
        // mengoverride fungsi render di parent
        g.setColor(color); // mengubah warna
        g.fillRect((int) x, (int) y, width, height); // membuat persegi panjang
        g.setColor(Color.red);
        g.drawString(Integer.toString(scoreValue), (int) x + width, (int) y + height-10);
    }

    private void updatePos() {
        // mengupdate posisi
        if (obsType > 0) {
            // Jika bukan lantai (obsType > 0), maka gerakkan dari bawah ke atas
            y -= GAME_SPEED;
        }
    }
    
    public void setCollisionBox(Rectangle collisionBox) {
        // mengatur collision box obstacle
        this.collisionBox = collisionBox;
    }

    public float getX() {
        // mengambil nilai x si obstacle
        return x;
    }

    public float getY() {
        // mengambil nilai y si obstacle
        return y;
    }
    
    public int getScoreValue() {
    return this.scoreValue;
    }




   
}


