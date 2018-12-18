
package com.macwargame;

import com.macwargame.classes.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class MacBook extends GameObject implements EntityB{
    
    private Audio sound;
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
        
        sound = new Audio();
        
        Macbook = tex.macbook[0];
    }

    @Override
    public void tick() {
        mili+=0.01666667; //per milisec

        if (Main.player1_turn && !Main.throwing_p1 && Main.throwing_p2) {

            x -= (int)(force*Math.cos(Math.toRadians(angle)));
            

            if (x >= 62-50 && x <= 115 && y >= 604-40 && y <= 697) { // player 1 bound
                c.removeEntity(this);
                mili = 0;
                System.out.println("HIT P1");
                //hit anim
                sound.playerHurt.play();
                Main.hitAnimation.start();

                
                Main.throwing_p2 = false;
                Main.recieving = true;
                Main.clock_counter.start();                                                                                                   
            }

        }
        else  if (Main.player2_turn && Main.throwing_p1 && !Main.throwing_p2) {

            x += (int)(force*Math.cos(Math.toRadians(angle)));
            
            if (x >= 1158-40 && x <= 1211 && y >= 604-40 && y <= 697) { // player 2 bound
                c.removeEntity(this);
                mili = 0;
                System.out.println("HIT P2");
                //hit anim
                sound.playerHurt.play();
                Main.hitAnimation.start();


                Main.throwing_p1 = false;
                Main.recieving = true;
                Main.clock_counter.start();
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
        if (x >= 600-55 && x <= 696 && y >= 498-35 && y <=697) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("HIT THE WALL!!!");
            sound.hitSurface.play();
            if(Main.throwing_p2) Main.throwing_p2 = false;
            if(Main.throwing_p1) Main.throwing_p1 = false;
            Main.recieving = true;
            Main.clock_counter.start();
        }
        //bounds
        if (x > 1286 || x < -70) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
//            sound.hitSurface.play();
            if(Main.throwing_p2) Main.throwing_p2 = false;
            if(Main.throwing_p1) Main.throwing_p1 = false;
            Main.recieving = true;
            Main.clock_counter.start();
        }
        if (y > 697 && (!(x >= 62-50 && x <= 115 && y >= 604-40 && y <= 697) || !(x >= 1158-40 && x <= 1211 && y >= 604-40 && y <= 697))) {
            c.removeEntity(this);
            mili = 0;
            System.out.println("DELETED.");
            sound.hitSurface.play();
            if(Main.throwing_p2) Main.throwing_p2 = false;
            if(Main.throwing_p1) Main.throwing_p1 = false;
            Main.recieving = true;
            Main.clock_counter.start();
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
