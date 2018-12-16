package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.classes.EntityB;

import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA entA, LinkedList<EntityB> entB) {
        for(int i = 0; i <entB.size(); i++) {
            if (entA.getBounds(100, 100).intersects(entB.get(i).getBounds(70, 70))) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(EntityB entB, LinkedList<EntityA> entA) {
        for(int i = 0; i <entA.size(); i++) {
            if (entB.getBounds(70, 70).intersects(entA.get(i).getBounds(100, 100))) {
                return true;
            }
        }
        return false;
    }
}
