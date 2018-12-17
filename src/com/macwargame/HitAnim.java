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
    private int count = 0;
        
    public HitAnim(Textures tex) {
        this.tex = tex;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Game.State == Game.State.GAME) {
            //4
            if (!Game.player1_turn) {
                if (count == 1) {
                    count = 0;
                    Player1.player1 = tex.player1[0];
                    Game.hitAnimation.stop();
                } else {
                    Player1.player1 = tex.player1[4];
                }
            }
  
            // 0
            if (!Game.player2_turn) {
                if (count == 1) {
                    count = 0;
                    Player2.player2 = tex.player2[4];
                    Game.hitAnimation.stop();
                } else {
                    Player1.player1 = tex.player1[0];
                }
            }
            count++;
        }
    }
}
