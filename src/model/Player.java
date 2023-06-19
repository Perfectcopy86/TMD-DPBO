/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import static viewmodel.Constants.gameOption.*;
/**
 *
 * @author Rasyid andriansyah
 */
public class Player extends GameObject {
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean inAir = true;
    
    private float playerSpeed = 5.0f;
    private float jumpStrength = 1.0f;
    private float airSpeed = 0;
    private float gravity = 0.3f;
    private float xSpeed = 0;
    
    private int score = 0;
    private int standing = 0;
    private int count = 0;
    private int tempY = 0;
    private boolean status = false;
    private boolean isColliding = false;

    
    public Player(int x, int y) {
        // konstruktor, set properti parent
        super(x, y, 50, 50);
    }
    
    public void update(ArrayList<Obstacle> ob){
        // mengupdate posisi obstable
        updatePos(ob);
        updateCollisionBox();
    }
    
    @Override
    public void render(Graphics g){
        // mengoverride method parent
        
       // bentuk palyer nya
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/asset/char2.png"));
        g.drawImage(image, (int)x, (int)y,50, 50, null);


        
        // skor standing dan skor result 
        g.setFont(new java.awt.Font("Segoe UI", 1, 13));
        g.setColor(Color.WHITE);
        g.fillRoundRect(600, 5, 100, 20, 15, 15);
        g.fillRoundRect(600, 35, 100, 20, 15, 15);
        g.setColor(Color.decode("#3A1070"));
        g.drawString("Standing : " + Integer.toString(this.standing), 600, 20);
        g.drawString("Score : " + Integer.toString(this.score), 600, 50);
    }
    
    public void updatePos(ArrayList<Obstacle> AOb) {
    // Mengupdate kondisi obstacle dan player
    if (left && right || !left && !right) {
        // Jika klik kanan dan kiri atau tidak klik kanan dan kiri
        // Kecepatan player biasa (mengikuti game)
        xSpeed = 0;
    } else if (left) {
        // Jika klik kiri
        // Kecepatan player berkurang
        xSpeed -= playerSpeed;
    } else if (right) {
        // Jika klik kanan
        // Kecepatan player bertambah
        xSpeed += playerSpeed + 1;
    }

    if (xSpeed > 4) {
        // Kecepatan maks 4
        xSpeed = 4;
    } else if (xSpeed < -4) {
        // Kecepatan min -4
        xSpeed = -4;
    }

    // Lompat di udara
    if (up) {
        inAir = true;
        airSpeed -= jumpStrength;
    }

    // Di lantai
    if (!inAir && !isOnFloor(AOb)) {
        inAir = true;
    }

    // Di udara
    if (inAir) {
        airSpeed += gravity;
    }
    
    Obstacle collidingObstacle = null;
    for (Obstacle ob : AOb) {
        // Untuk setiap obstacle
        if (getBoundBottom().intersects(ob.getCollisionBox())) {
            inAir = false;
            airSpeed = 0;
            y = ob.getCollisionBox().y - height;
            
            if (ob.isColliding== false) {
                collidingObstacle = ob;
                ob.isColliding = true;
                standing += 1;
                score += ob.getScoreValue();
            }
            //loncat di obstacle
            if (up) {
                inAir = true;
                airSpeed -= jumpStrength;
            }

            break;
        }

        // Jika player nabrak
        // Kembalikan ke speed normal
        if (getBoundRight().intersects(ob.getCollisionBox())) {
            // Jika nabrak kanan
            xSpeed = GAME_SPEED;
            x = ob.getCollisionBox().x + ob.getCollisionBox().width + 1;
        }
        
        if (getBoundLeft().intersects(ob.getCollisionBox())) {
            // Jika nabrak kiri
            xSpeed = GAME_SPEED;
            x = ob.getCollisionBox().x + ob.getCollisionBox().width + 1;
        }
        
        if (getBoundLeft().x < 0) {
            // Jika nabrak kiri sampai mentok ke frame game
            x = 0;
        }
        if (getBoundRight().x > 930) {
            // Jika nabrak kanan sampai mentok ke frame game
            x = 930;
        }
    }

    x += xSpeed;
    y += airSpeed;
}


    
    public boolean isOnFloor(ArrayList<Obstacle> AOb){
        // method mengecek apakah di lantai
        for(Obstacle ob : AOb){
            // jika batas bawah player beririsan dgn collision box
            if(getBoundBottom().intersects(ob.getCollisionBox())) return true;
            // System.out.println("FALL");
        }
        return false;
    }
    
    public Rectangle getBoundBottom(){
        // membuat batas bawah
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }
    
    public Rectangle getBoundTop(){
        // membuat batas atas
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }
    
    public Rectangle getBoundRight(){
        // membuat batas kanan
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }
    
    public Rectangle getBoundLeft(){
        // membuat batas kiri
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }

    public void setLeft(boolean left) {
        // set player ke kiri
        this.left = left;
    }

    public void setUp(boolean up) {
        // set player ke atas
        this.up = up;
    }

    public void setRight(boolean right) {
        // set player ke kanan
        this.right = right;
    }
    
    public void setDown(boolean down) {
        // set player ke bawah
        this.down = down;
    }
    
    public void setScore(int score) {
        // set skor 
        this.score = score;
    }
    
    public void setStanding(int standing) {
        // set skor standing
        this.standing = standing;
    }
    
    public int getStanding() {
        // mendapatkan skor 
        return this.standing;
    }
    
    public int getScore() {
        // mendapatkan skor 
        return this.score;
    }
}
