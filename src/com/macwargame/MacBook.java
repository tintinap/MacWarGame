/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import com.macwargame.classes.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class MacBook extends GameObject implements EntityB{
    
    private double angle;
    private Textures tex;
    private double force;
    
    private BufferedImage Macbook;

    public MacBook(double x, double y, double angle, double force, Textures tex) {
        super(x, y);
        
        this.angle = angle;
        this.force = force;
        this.tex = tex;
        
        Macbook = tex.macbook[0];
    }

    @Override
    public void tick() {
        //projectile's move //TODO need collision check 
        if (Game.player1_turn && !Game.player2_turn) {
            System.out.println("p1's turn");
            x--;
        }
        if (!Game.player1_turn && Game.player2_turn) {
            System.out.println("p2's turn");
            x++;
        }
        y += (int)(x*Math.tan(Math.toRadians(angle))-(-9.8*x/2 * Math.pow(force, 2)* Math.pow(Math.cos(Math.toRadians(angle)), 2)));
//        Clock.ckindex = 30;
        
    }
    
    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Macbook, (int)x, (int)y, null);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
    
}
