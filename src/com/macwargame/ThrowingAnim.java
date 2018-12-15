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
public class ThrowingAnim implements ActionListener {

    private Textures tex;
    private int act1 = 0;
    private int act2 = 4;
    
    public ThrowingAnim(Textures tex) {
        this.tex = tex;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Game.State == Game.State.GAME) {
            //1 to 3
            if (!Game.player1_turn) {
                act1++;
                Player1.player1 = tex.player1[act1];
                if (act1 == 3) {
                    act1 = 0;
                    Player1.player1 = tex.player1[0];
                    Game.throwAnimation.stop();
                    Game.clock_counter.start();
                }
            }
            // 3 to 1
            if (!Game.player2_turn) {
                act2--;
                Player2.player2 = tex.player2[act2];
                if (act2 == 1) {
                    act2 = 4;
                    Player2.player2 = tex.player2[4];
                    Game.throwAnimation.stop();
                    Game.clock_counter.start();
                }
            }
        }
    }
}
