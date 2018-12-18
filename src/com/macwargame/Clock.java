/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import static com.macwargame.Main.WIDTH;
import static com.macwargame.Main.player1_turn;
import static com.macwargame.Main.player2_turn;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;


public class Clock extends GameObject implements ActionListener{
    
    private Textures tex;
    private BufferedImage clock;
    protected static int ckindex;
    private Main game;
    private Audio sound;

    public Clock(double x, double y, Textures tex, Main game) {
        super(x, y);
        
        sound = new Audio();
        this.tex = tex;
        ckindex = 30;
        clock = tex.clock[30];
        this.game = game;
        
    }
    
    public void render(Graphics g) {
        g.drawImage(clock, (int)x, (int)y, 100, 100, null);
    }
    
    
    public void tick() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.State == Main.State.GAME) {
            if (Main.clicked) {
                ckindex = 30;
                Main.clock_counter.stop();
                Main.clicked = false;
                if (!Main.player1_turn){

                        Main.c.addEntity(new MacBook(Main.p1.getX(), Main.p1.getY(), game.getAngle(), game.getForce(), tex, Main.c));
//                        Main.c.addEntity(new Bullet(Main.p1.getX(), Main.p1.getY(), tex, game, Main.c));

                }
                if (!Main.player2_turn){

                        Main.c.addEntity(new MacBook(Main.p2.getX(), Main.p2.getY(), game.getAngle(), game.getForce(), tex, Main.c));
//                        Main.c.addEntity(new Bullet(Main.p2.getX()+20, Main.p2.getY(), tex, game, Main.c));

                }
                Main.throwAnimation.start();

            }
            if (!Main.clicked) {
                if (ckindex == 59) {
                    sound.timeUp.play();
                    if (Main.player1_turn) {
                        player1_turn = false;
                        player2_turn = true;

                    } else if (Main.player2_turn) {
                        player1_turn = true;
                        player2_turn = false;
                    }
                    ckindex = 30;
                }
            }
            clock = tex.clock[ckindex];
            ckindex++;
            int i = 0;
            System.out.println(i);
        }
    }

}