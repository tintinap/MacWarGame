package com.macwargame;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    public  SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int x, int y, int width, int height) {
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }
    
    public BufferedImage grabClockImage(int x, int y) {
        BufferedImage img = image.getSubimage(x, y, 200, 200);
        return img;
    }
    
    public BufferedImage grabLPImage(int x, int y) {
        BufferedImage img = image.getSubimage(x, y, 420, 100);
        return img;
        
    }
    
    public BufferedImage grabPlayerImage(int x, int y, int width, int height) {
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }
    
    public BufferedImage grabObjectImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        return img;
    }
}
