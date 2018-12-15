/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Lifepoint extends GameObject{
    
    private Textures tex;
    private BufferedImage lp;

    public Lifepoint(double x, double y, Textures tex, int side) {
        super(x, y);
        
        this.tex = tex;
        if (side == 1)lp = tex.lifepoint1[0];
        if (side == 2)lp = tex.lifepoint2[0];
        
    }
    
    public void render(Graphics g) {
        g.drawImage(lp, (int)x, (int)y, null);
    }
    
    
    public void tick() {
        
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
