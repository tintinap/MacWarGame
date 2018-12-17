/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macwargame;

import static com.macwargame.Game.WIDTH;
import static com.macwargame.Game.player1_turn;
import static com.macwargame.Game.player2_turn;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class Clock extends GameObject implements ActionListener{
    
    private Textures tex;
    private BufferedImage clock;
    protected static int ckindex;
    private Game game;

    public Clock(double x, double y, Textures tex, Game game) {
        super(x, y);
        
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
        if (Game.State == Game.State.GAME) {
            if (Game.clicked) {
                ckindex = 30;
                Game.clock_counter.stop();
                Game.clicked = false;
                if (!Game.player1_turn){
//                    if (Game.recieving) {
                        Game.c.addEntity(new MacBook(Game.p1.getX(), Game.p1.getY(), game.getAngle(), game.getForce(), tex, Game.c));
    //                    Game.c.addEntity(new Bullet(Game.p1.getX(), Game.p1.getY(), tex, game, Game.c));
//                        Game.recieving = false;
//                    }
                }
                if (!Game.player2_turn){
//                    if (Game.recieving) {
                        Game.c.addEntity(new MacBook(Game.p2.getX(), Game.p2.getY(), game.getAngle(), game.getForce(), tex, Game.c));
    //                    Game.c.addEntity(new Bullet(Game.p2.getX()+20, Game.p2.getY(), tex, game, Game.c));
//                        Game.recieving = false;
//                    }
                }
                Game.throwAnimation.start();

            }
            if (!Game.clicked) {
                if (ckindex == 59) {
                    if (Game.player1_turn) {
                        player1_turn = false;
                        player2_turn = true;

                    } else if (Game.player2_turn) {
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