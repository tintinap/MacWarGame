
package com.macwargame;

import com.macwargame.classes.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class MacBook extends GameObject implements EntityB{
    
    private double angle;
    private Textures tex;
    private double force;
    private Controller c;
    
    private int seconds = 0;
    private double mili = 0;
    
    private double startX;
    
    private BufferedImage Macbook;

    public MacBook(double x, double y, double angle, double force, Textures tex, Controller c) {
        super(x, y);
        this.startX = x;
        this.angle = angle;
        this.force = force;

        this.tex = tex;
        this.c = c;
        
        Macbook = tex.macbook[0];
    }

    @Override
    public void tick() {
        mili+=0.01666667; //per milisec

        if (Game.player1_turn && !Game.throwing_p1 && Game.throwing_p2) {

            x -= (int)(force*Math.cos(Math.toRadians(angle)));
            

            if (x >= 62-50 && x <= 115 && y >= 604-40 && y <= 697) { // player 1 bound
                c.removeEntity(this);
                mili = 0;
                System.out.println("HIT P1");
                //hit anim
                Game.hitAnimation.start();
                
                Game.throwing_p2 = false;
                Game.recieving = true;
                Game.clock_counter.start();                                                                                                   
            }

        }
        else  if (Game.player2_turn && Game.throwing_p1 && !Game.throwing_p2) {

            x += (int)(force*Math.cos(Math.toRadians(angle)));
            
            if (x >= 1158-40 && x <= 1211 && y >= 604-40 && y <= 697) { // player 2 bound
                c.removeEntity(this);
                mili = 0;
                System.out.println("HIT P2");
                //hit anim
                Game.hitAnimation.start();

                Game.throwing_p1 = false;
                Game.recieving = true;
                Game.clock_counter.start();
            }
        }        
        if (x == startX){
            y = 1000;
            c.removeEntity(this);
            System.out.println("YOU CANT LAUNCH TWICE.");
            
        } else {
            y -= (force*Math.sin(Math.toRadians(angle))-(9.8*mili));
        }


        // obstacle
        if (x >= 600-60 && x <= 696 && y >= 498-35 && y <=697) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("HIT THE WALL!!!");
            if(Game.throwing_p2) Game.throwing_p2 = false;
            if(Game.throwing_p1) Game.throwing_p1 = false;
            Game.recieving = true;
            Game.clock_counter.start();
        }
        //bounds
        if (x > 1286 || x < -70) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
            if(Game.throwing_p2) Game.throwing_p2 = false;
            if(Game.throwing_p1) Game.throwing_p1 = false;
            Game.recieving = true;
            Game.clock_counter.start();
        }
        if (y > 697) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
            if(Game.throwing_p2) Game.throwing_p2 = false;
            if(Game.throwing_p1) Game.throwing_p1 = false;
            Game.recieving = true;
            Game.clock_counter.start();
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
