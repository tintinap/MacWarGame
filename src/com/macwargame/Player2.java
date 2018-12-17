
package com.macwargame;

import com.macwargame.classes.EntityA;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player2 extends GameObject implements EntityA{
    
    private Textures tex;
    protected static BufferedImage player2;

    public Player2(double x, double y, Textures tex) {
        super(x, y);
        this.tex = tex;
        
       player2 = tex.player2[4];
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(player2, (int)x, (int)y, 100, 100, null);
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
