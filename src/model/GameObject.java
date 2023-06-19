/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Rasyid andriansyah
 */
public abstract class GameObject {
    // variabel posisi
    protected float x;
    protected float y;
    // variabel ukuran
    protected int width;
    protected int height;
    // box pendeteksi collision
    protected Rectangle collisionBox;
    
    public GameObject(float x, float y, int width, int height) {
        // konstruktor
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // buat box baru
        this.collisionBox = new Rectangle((int)x, (int)y, width, height);
    }
    
    protected void updateCollisionBox(){
        // mengupdate posisi box
        this.collisionBox.x = (int) x;
        this.collisionBox.y = (int) y;
    }

    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }
    
    public abstract void render(Graphics g);
}
