package com.macwargame;

import com.macwargame.classes.EntityB;
import com.macwargame.Game;
import com.macwargame.GameObject;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {
    
    private double x;
    private double y;

    private Textures tex;
    Random r = new Random();
    private Game game;
    private Controller c;

    Animation anim;

    private int speed = r.nextInt(3)+1;

    public Enemy(int x, double y, Textures tex, Controller c, Game game) {
        super(x, y);
        this.tex = tex;
        this.c = c;
        this.game = game;

//        anim = new Animation(30, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
    }

    public void tick() {
        y += speed;

        if (y > (Game.HEIGHT)) {
            speed = r.nextInt(3)+1;
            y = 0;
            x = r.nextInt(Game.WIDTH);
        }

        if (Physics.Collision(this, game.ea)) {
            c.removeEntity(this);
            game.setEnemy_killed(game.getEnemy_killed() + 1);
        }

        anim.runAnimation();
    }

    public void render(Graphics g) {
        //g.drawImage(tex.enemy[0], (int)x, (int)y, null);
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
}
