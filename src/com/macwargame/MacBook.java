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
    private Controller c;
    
    private int seconds = 0;
    private double mili = 0;
    
    private BufferedImage Macbook;

    public MacBook(double x, double y, double angle, double force, Textures tex, Controller c) {
        super(x, y);
        
        this.angle = angle;
        this.force = force;
        this.tex = tex;
        this.c = c;
        
        Macbook = tex.macbook[0];
    }

    @Override
    public void tick() {
        mili+=0.01666667; //per milisec

        if (Game.player1_turn) {

            x -= (int)(force*Math.cos(Math.toRadians(angle)));

        }
        if (Game.player2_turn) {

            x += (int)(force*Math.cos(Math.toRadians(angle)));

        }        
        
        y -= (force*Math.sin(Math.toRadians(angle))-(9.8*mili));

        //players rectangle
        if (x >= 62 && x <= 115 && y >= 604 && y <= 697) { // player 1 bound
            
        }
        if (x >= 1158 && x <= 1211 && y >= 604 && y <= 697) { // player 2 bound
            
        }
        // obtacle
        if (x >= 600 && x <= 696 && y >= 498 && y <=697) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("HIT A WALL!!!");
        }
        //bounds
        if (x > 1286 || x < 0) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
        }
        if (y > 697) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
        }
    }
    
    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Macbook, (int)x, (int)y, 70, 70, null);
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
