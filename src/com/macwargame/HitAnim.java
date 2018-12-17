/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class HitAnim implements ActionListener {

    private Textures tex;
    private int h1 = 4;
    private int h2 = 0;
        
    public HitAnim(Textures tex) {
        this.tex = tex;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Game.State == Game.State.GAME) {
            //4
            if (Game.player1_turn) {
                if (h1 == 4) System.out.println("hit p1!!!1st");
                if (h1 != 4) System.out.println("hit p1!!! other");
                Player1.player1 = tex.player1[h1];
                if (h1 == 0) {
                    h1 = 4;
                    Player1.player1 = tex.player1[0];
                    System.out.println("p1 gotta stop!!!");
                    Game.lp1hpValue--;
                    Game.lp1.setLP(Game.lp1hpValue);
                    Game.hitAnimation.stop();

                } else {
                    h1-=4;
                }
//                System.out.println("h1 before = "+h1);
//                h1-=4;
//                System.out.println("h1 after = "+h1);
            }
            // 0
            if (Game.player2_turn) {
                if (h2 == 0) System.out.println("hit p2!!!1st");
                if (h2 != 0) System.out.println("hit p2!!! other");
                Player2.player2 = tex.player2[h2];
                if (h2 == 4) {
                    h2 = 0;
                    Player2.player2 = tex.player2[4];
                    System.out.println("p2 gotta stop!!!");
                    Game.lp2hpValue--;
                    Game.lp2.setLP(Game.lp2hpValue);
                    Game.hitAnimation.stop();
                }else {
                    h2+=4;
                }
//                System.out.println("h1 before = "+h1);
//                h2+=4;
//                System.out.println("h1 after = "+h1);
            }
        }
    }
}
