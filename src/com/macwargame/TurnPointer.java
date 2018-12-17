
package com.macwargame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class TurnPointer extends GameObject{
    private Textures tex;
    private BufferedImage pointer;
    private int width, height;
    
    public TurnPointer(double x, double y, Textures tex,  int width, int height) {
        super(x, y);
        this.tex = tex;
        this.width = width;
        this.height = height;
        pointer = tex.pointer[0];
    }
    
    public void render(Graphics g) {
        g.drawImage(pointer, (int)x, (int)y, width, height, null);
    }
    
    public void setState(int state) {
        if (state == 0) {
            pointer = null;
        } else if (state == 1) {
            pointer = tex.pointer[0];
        } 
    }
}
