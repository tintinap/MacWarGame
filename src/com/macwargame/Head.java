/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class Head extends GameObject{

    private Textures tex;
    private BufferedImage head;
    
    public Head(double x, double y, Textures tex) {
        super(x, y);
        this.tex = tex;
        
        head = null;
    }
    
    public void render(Graphics g) {
        g.drawImage(head, (int)x, (int)y, 300, 300, null);
    }
    
    public void setHead(int sex) {
        if (sex == 0) {
            head = tex.head[0];
        } else if (sex == 1) {
            head = tex.head[1];
        } else if (sex == 3) {
            head = null;
        }
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    
}
