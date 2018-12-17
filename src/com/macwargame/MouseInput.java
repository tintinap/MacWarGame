package com.macwargame;

import com.macwargame.Game;
import java.awt.Cursor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    
    Game game;
    public MouseInput(Game game) {
        this.game = game;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        game.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
