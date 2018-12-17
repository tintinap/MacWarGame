
package com.macwargame;

import com.macwargame.classes.EntityA;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player1 extends GameObject implements EntityA {
    
    private Textures tex;
    protected static BufferedImage player1;

    public Player1(double x, double y, Textures tex) {
        super(x, y);
        this.tex = tex;
        
       player1 = tex.player1[0];
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player1, (int)x, (int)y, 100, 100, null);
    }
    
    
    
    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
