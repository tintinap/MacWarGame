package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.Game;
import com.macwargame.GameObject;
import com.macwargame.classes.EntityB;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityB {

    private Textures tex;
    private Game game;
    private Controller c;
    
    Animation anim;

    public Bullet(double x, double y, Textures tex, Game game, Controller c) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;

//        anim = new Animation(30, tex.bullet[0], tex.bullet[1], tex.bullet[2]);

//        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//        image = ss.grabImage(1,2,32,32);
    }

    public void tick() {
        if (Game.player1_turn) {
//            System.out.println("p1_turn");
            x-=10;
        }
        if (Game.player2_turn) {
//            System.out.println("p2_turn");
            x+=10;
        }
//        y -= 10;
        
        
        if (Physics.Collision(this, game.ea)) {
//            c.removeEntity(this);
            System.out.println("COLLISION DETECTED");
        }
        
        if (x > 1286 || x < 0) c.removeEntity(this);



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
