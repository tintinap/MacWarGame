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
//        int mx = e.getX();
//        int my = e.getY();
//        if(Game.State == Game.State.MENU) {
//            
//            if (mx >= 493 && mx <= 793) {
//                // Start button -> state.select
//                if (my >= 290 && my <= 418) {
//                    System.out.println("Go to SELECT state");
//                    Game.State = Game.STATE.SELECT;
//                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
//                }
//                //Tutorial button ->state.tutorial
//                if (my > 418 && my <= 542) {
//                    System.out.println("TURORIAL state");
//                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
//                    //Game.State = Game.STATE.
//                }
//                //Exit button
//                if (my > 542 && my <= 668) {
//                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
////                    System.exit(0);
//                }
//            } else {
//                Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//            }
//               
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        int mx = e.getX();
//        int my = e.getY();
//        if(Game.State == Game.State.MENU) {
//            
//            if (mx >= 493 && mx <= 793) {
//                // Start button -> state.select
//                if (my >= 290 && my <= 418) {
//                    System.out.println("Go to SELECT state");
//                    Game.State = Game.STATE.SELECT;
//                    Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//                }
//                //Tutorial button ->state.tutorial
//                if (my > 418 && my <= 542) {
//                    System.out.println("TURORIAL state");
//                    Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//                    //Game.State = Game.STATE.
//                }
//                //Exit button
//                if (my > 542 && my <= 668) {
//                    Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
////                    System.exit(0);
//                }
//            }
//               
//        }
    }
}
