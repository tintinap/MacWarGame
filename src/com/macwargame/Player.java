package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.GameObject;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {

    private double velX = 0;
    private double velY = 0;

//    private BufferedImage player;
    private Textures tex;

    Animation anim;

    public Player(double x, double y, Textures tex) {
        super(x, y);
        this.tex = tex;

        anim = new Animation(30, tex.player[0], tex.player[1], tex.player[2]);
//        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//
//        player = ss.grabImage(2,5,35,40);
    }

    public void tick() {
        x+=velX;
        y+=velY;

        if (x <= 0) x = 0;
        if (x >= 1280-35) x = 1280-35;
        if (y <= 0) y = 0;
        if (y >= 760-40) y = 760-40;

        anim.runAnimation();

    }

    public void render(Graphics g) {
        //g.drawImage(tex.player[0], (int)x, (int)y,null);
        anim.drawAnimation(g, x, y, 0);
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
