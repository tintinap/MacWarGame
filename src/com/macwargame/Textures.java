package com.macwargame;

import com.macwargame.Main;
import com.macwargame.SpriteSheet;

import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage[] player1 = new BufferedImage[5];
    public BufferedImage[] macbook = new BufferedImage[3];
    public BufferedImage[] player2 = new BufferedImage[5];
    
    public BufferedImage[] head = new BufferedImage[2];
    public BufferedImage[] sframe = new BufferedImage[1];
    public BufferedImage[] pointer = new BufferedImage[1];
    
    public BufferedImage[] lifepoint1 = new BufferedImage[4];
    public BufferedImage[] lifepoint2 = new BufferedImage[4];
    
    public BufferedImage[] clock = new BufferedImage[60];

    private SpriteSheet ssp1 = null;
    private SpriteSheet ssp2 = null;
    
    private SpriteSheet ssobj = null;
    
    private SpriteSheet sslp = null;
    
    private SpriteSheet ssck = null;
    
    private SpriteSheet sshead = null;
    private SpriteSheet ssselectframe = null;
    
    private SpriteSheet sspt = null;

    
    public Textures(Main game) {
        sspt = new SpriteSheet(game.getSpritePointer());
                
        ssselectframe = new SpriteSheet(game.getSpriteSelectFrame());
        sshead = new SpriteSheet(game.getSpriteHead());
        
        ssobj = new SpriteSheet(game.getSpriteObject());
        
        sslp = new SpriteSheet(game.getSpriteLP());
        
        ssck = new SpriteSheet(game.getSpriteTimer());
        setMenuTextures(game);
    }
    
    public void setMenuTextures(Main game) {
        pointer[0] = sspt.grabImage(0,0,66,57);
        
        sframe[0] = ssselectframe.grabImage(0, 0, 300, 300);// SelectFrame
        head[0] = sshead.grabImage(0, 0, 300, 300);//female
        head[1] = sshead.grabImage(300, 0, 300, 300);//male
        
        lifepoint1[0] = sslp.grabLPImage(0,0); //full
        lifepoint1[1] = sslp.grabLPImage(0,100); //full-1
        lifepoint1[2] = sslp.grabLPImage(0,200); //full-2
        lifepoint1[3] = sslp.grabLPImage(0,300); //empty

        lifepoint2[0] = sslp.grabLPImage(420,0); //full
        lifepoint2[1] = sslp.grabLPImage(420,100); //full-1
        lifepoint2[2] = sslp.grabLPImage(420,200); //full-2
        lifepoint2[3] = sslp.grabLPImage(420,300); //empty
       

        int count = 0;
        for (int i=0; i<5; i++) {
            for(int j=0; j<12; j++) {
                clock[count] = ssck.grabClockImage(j*200, i*200);
                count++;
            }
        }
    }

    public void getTextures(Main game) {
        ssp1 = new SpriteSheet(game.getSpritePlayer(1));
        ssp2 = new SpriteSheet(game.getSpritePlayer(2));
        

        player1[0] = ssp1.grabPlayerImage(0,0,300,300); //stnad still
        player1[1] = ssp1.grabPlayerImage(300,0,300,300); //throw 1
        player1[2] = ssp1.grabPlayerImage(600,0,300,300); //throw 2
        player1[3] = ssp1.grabPlayerImage(900,0,300,300); //throw 3
        player1[4] = ssp1.grabPlayerImage(1200,0,300,300); //hit

        player2[0] = ssp2.grabPlayerImage(0,0,300,300); // hit
        player2[1] = ssp2.grabPlayerImage(300,0,300,300); //throw 3
        player2[2] = ssp2.grabPlayerImage(600,0,300,300); //throw 2
        player2[3] = ssp2.grabPlayerImage(900,0,300,300); //throw 1
        player2[4] = ssp2.grabPlayerImage(1200,0,300,300); //stand still

        macbook[0] = ssobj.grabImage(0,0,200,200);


        
    }

}
