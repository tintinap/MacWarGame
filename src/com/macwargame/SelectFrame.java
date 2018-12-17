
package com.macwargame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SelectFrame extends GameObject{

    private Textures tex;
    private BufferedImage sFrame;
    private int width, height;
    
    public SelectFrame(double x, double y, Textures tex, int width, int height) {
        super(x, y);
        this.tex = tex;
        this.width = width;
        this.height = height;
        sFrame = tex.sframe[0];
    }
    
    public void render(Graphics g) {
        g.drawImage(sFrame, (int)x, (int)y, width, height, null);
    }
    
    public void setState(int state) {
        if (state == 0) {
            sFrame = null;
        } else if (state == 1) {
            sFrame = tex.sframe[0];
        } 
    }
    
    public boolean checkTexture() {
        if (sFrame == null) {
            return false;
        }
        return true;
    }
}
