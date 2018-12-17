
package com.macwargame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Lifepoint extends GameObject{
    
    private Textures tex;
    private BufferedImage[] templp;
    private BufferedImage lp;

    public Lifepoint(double x, double y, Textures tex, int side) {
        super(x, y);
        templp = new BufferedImage[4];
        this.tex = tex;
        if (side == 1) {
            for (int i = 0; i < 4; i++) {
                this.templp[i] = tex.lifepoint1[i];
            }
            this.lp = tex.lifepoint1[0];
        }
        if (side == 2) {
            for (int i = 0; i < 4; i++) {
                this.templp[i] = tex.lifepoint2[i];
            }
            this.lp = tex.lifepoint2[0];
        }
        
    }
    
    public void render(Graphics g) {
        g.drawImage(lp, (int)x, (int)y, null);
    }
    
    public void setLP(int hp) {
        if (hp == 3) this.lp = templp[0];
        if (hp == 2) this.lp = templp[1];
        if (hp == 1) this.lp = templp[2];
        if (hp == 0) this.lp = templp[3];
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
