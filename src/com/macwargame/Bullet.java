package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.Main;
import com.macwargame.GameObject;
import com.macwargame.classes.EntityB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityB {

    private Textures tex;
    private Main game;
    private Controller c;
    
    Animation anim;

    public Bullet(double x, double y, Textures tex, Main game, Controller c) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;

//        anim = new Animation(30, tex.bullet[0], tex.bullet[1], tex.bullet[2]);

//        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//        image = ss.grabImage(1,2,32,32);
    }

    public void tick() {
        /*if (Main.player1_turn) {
//            System.out.println("p1_turn");
            x-=10;
        }
        if (Main.player2_turn) {
//            System.out.println("p2_turn");
            x+=10;
        }
//        y -= 10;
        
        
        if (Physics.Collision(this, game.ea)) {
//            c.removeEntity(this);
            System.out.println("COLLISION DETECTED");
        }
        
        if (x > 1286 || x < 0) c.removeEntity(this);*/
///////////////////////////////////////////////////////////////////////
        if (Main.player1_turn) {

            x -= 10;
            

            if (x >= 62-50 && x <= 115 && y >= 604-40 && y <= 697) { // player 1 bound
                c.removeEntity(this);
                System.out.println("HIT P1");
                //hit anim
//                Main.hitAnimation.start();
                ////////////////////////////////copy here to macbok
                // lifepoint mechanism
                Main.lp1hpValue--;
                Main.lp1.setLP(Main.lp1hpValue);
                
                ////////////////////////////////
                Main.clock_counter.start();
                
            }

        }
        if (Main.player2_turn) {

            x += 10;
            
            if (x >= 1158-60 && x <= 1211 && y >= 604-40 && y <= 697) { // player 2 bound
                c.removeEntity(this);
                System.out.println("HIT P2");
                //hit anim
//                Main.hitAnimation.start();
                ////////////////////////////////copy here to macbok
                Main.lp2hpValue--;
                Main.lp2.setLP(Main.lp2hpValue);
                
                ////////////////////////////////
                Main.clock_counter.start();
            }
        }        
        
//        y -= (force*Math.sin(Math.toRadians(angle))-(9.8*mili));

        //players rectangle


        // obstacle
//        if (x >= 600-60 && x <= 696 && y >= 498-35 && y <=697) {
//            c.removeEntity(this);
//            mili = 0;
//            System.out.println("HIT THE WALL!!!");
//            Main.clock_counter.start();
//        }
        //bounds
        if (x > 1286 || x < 0) {
            c.removeEntity(this);
//            mili = 0;
            System.out.println("DELETED.");
            Main.clock_counter.start();
        }
        if (y > 697) {
            c.removeEntity(this);
//            mili = 0;
            System.out.println("DELETED.");
            Main.clock_counter.start();
        }

        
//////////////////////////////////////////////////////////////////////        
//        anim.runAnimation();
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void render(Graphics g) {
        g.drawImage(tex.macbook[0], (int)x, (int)y, 70, 70, null);
//        anim.drawAnimation(g, x, y, 0);
    }

    @Override
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
