package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.classes.EntityB;
import com.macwargame.Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();

    EntityA entA;
    EntityB entB;

//    Random r = new Random();
    private Game game;

    Textures tex;

    public Controller(Game game, Textures tex) {

        this.game = game;
        this.tex = tex;
//        for (int i=0; i < 20; i++) {
//            addEntity(new Enemy(r.nextInt(Game.WIDTH),100, tex));
//        }

//        addBullet(new Bullet(100, 760, game));
        //spwan when the game starts.
//        for(int x = 0; x < Game.WIDTH; x +=64) {
//            addEnemy(new Enemy(r.nextInt(Game.WIDTH), 0, tex));
//        }
    }

//    public void createEnemy(int enemy_count) {
//        for (int i=0; i<enemy_count; i++) {
//            addEntity(new Enemy(r.nextInt(Game.WIDTH),100, tex, this, game));
//        }
//    }

    public void tick() {
        // A class
        for (int i = 0;i <ea.size(); i++) {
            entA = ea.get(i);

            entA.tick();
        }
        //B class
        for (int i = 0;i <eb.size(); i++) {
            entB = eb.get(i);

            entB.tick();
        }
    }

    public void render(Graphics g) {
        //A Class
        for (int i = 0;i <ea.size(); i++) {
            entA = ea.get(i);

            entA.render(g);
        }
        //B Class
        for (int i = 0;i <eb.size(); i++) {
            entB = eb.get(i);

            entB.render(g);
        }
    }

    public void addEntity(EntityA block) { ea.add(block); }

    public void removeEntity(EntityA block) {
        ea.remove(block);
    }

    public void addEntity(EntityB block) { eb.add(block); }

    public void removeEntity(EntityB block) {
        eb.remove(block);
    }

    public LinkedList<EntityA> getEntityA() {
        return ea;
    }

    public LinkedList<EntityB> getEntityB() {
        return eb;
    }
}