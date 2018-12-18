package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.classes.EntityB;
import com.macwargame.Main;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();

    EntityA entA;
    EntityB entB;

    private Main game;

    Textures tex;

    public Controller(Main game, Textures tex) {

        this.game = game;
        this.tex = tex;

    }


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
