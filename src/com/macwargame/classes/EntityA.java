package com.macwargame.classes;

import java.awt.*;

public interface EntityA {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds(int width, int height);

    public double getX();
    public double getY();

}
