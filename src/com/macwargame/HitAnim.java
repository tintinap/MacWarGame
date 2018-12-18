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
        if (Main.State == Main.State.GAME) {
            //4
            if (Main.player1_turn) {
                if (h1 == 4) System.out.println("hit p1!!!1st");
                if (h1 != 4) System.out.println("hit p1!!! other");
                Player1.player1 = tex.player1[h1];
                if (h1 == 0) {
                    h1 = 4;
                    Player1.player1 = tex.player1[0];
                    System.out.println("p1 gotta stop!!!");
                    Main.lp1hpValue--;
                    Main.lp1.setLP(Main.lp1hpValue);
                    Main.hitAnimation.stop();

                } else {
                    h1-=4;
                }
            }
            // 0
            if (Main.player2_turn) {
                if (h2 == 0) System.out.println("hit p2!!!1st");
                if (h2 != 0) System.out.println("hit p2!!! other");
                Player2.player2 = tex.player2[h2];
                if (h2 == 4) {
                    h2 = 0;
                    Player2.player2 = tex.player2[4];
                    System.out.println("p2 gotta stop!!!");
                    Main.lp2hpValue--;
                    Main.lp2.setLP(Main.lp2hpValue);
                    Main.hitAnimation.stop();
                }else {
                    h2+=4;
                }
            }
        }
    }
}
