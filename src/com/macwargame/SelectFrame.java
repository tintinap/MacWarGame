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
public class SelectFrame extends GameObject{

    private Textures tex;
    private BufferedImage sFrame;
    private int width, height;
    
    public SelectFrame(double x, double y, Textures tex, int width, int height) {
        super(x, y);
        this.tex = tex;
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics g) {
        g.drawImage(sFrame, (int)x, (int)y, width, height, null);
    }
}
