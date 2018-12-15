package com.macwargame;

import com.macwargame.classes.EntityA;
import com.macwargame.classes.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1286;
    public static final int HEIGHT = 760;
    public final String TITLE = "Mac War Game";

    private boolean running = false;
    private boolean throwing = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage ss_player1 = null;
    private BufferedImage ss_player2 = null;
    
    private BufferedImage ss_lp = null;
    private BufferedImage select_frame = null;
    
    private BufferedImage bg_game = null;
    private BufferedImage bg_menu = null;
    private BufferedImage bg_tutorial = null;
    private BufferedImage bg_select = null;
    private BufferedImage ss_object = null;
    private BufferedImage ss_head = null;
    
    private BufferedImage ss_clock = null;
    protected static Timer clock_counter;
    
    ///////////////////////////////////////Mouse x, y///////////////////////////////
    private double mp_x, mp_y, mr_x, mr_y;
    private double force, angle, o_side;

    protected static Timer throwAnimation;
    
    protected static Player1 p1;
    protected static Player2 p2;
    protected static Head p1_head;
    protected static Head p2_head;
    protected static boolean player1_turn = true;
    protected static boolean player2_turn = false;
    private Boolean p1_select_turn = true;
    private Boolean p2_select_turn = false;
    
    private String p1_tex = "sprite-1_left";
    private String p2_tex = "sprite-2_right";
    
    
    protected static boolean clicked =  false;
    
    private Lifepoint lp1;
    private Lifepoint lp2;
    private Clock ck;
    
    protected static Controller c;
    private Textures tex;
//    private Menu menu;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    
    
    
    BufferedImageLoader loader = new BufferedImageLoader();

    public static enum STATE {
        MENU,
        SELECT,
        GAME,
        TUTORIAL
    };

    public static STATE State = STATE.MENU;

    public void init() {
        requestFocus();
        try {
            //when launch menu
            bg_tutorial = loader.loadImage("img/tutorial.png");
            bg_menu = loader.loadImage("img/menu.png");
            bg_select = loader.loadImage("img/select.png");
            ss_head = loader.loadImage("img/select_head.png");
            select_frame = loader.loadImage("img/select_frame.png");
            //when launch select
//            ss_player1 = loader.loadImage("img/sprite-1_left.png");
//            ss_player2 = loader.loadImage("img/sprite-2_right.png");
 
            ss_lp  = loader.loadImage("img/LP.png");
            
            bg_game = loader.loadImage("img/game_bg.png");
            ss_object = loader.loadImage("img/ArcadeSprite.png");
            
            ss_clock = loader.loadImage("img/timer.png");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        tex = new Textures(this);
        
//        greenSelectFrame1 = new SelectFrame();
//        greenSelectFrame2 = new SelectFrame();
        p1_head = new Head(169-47, 398-39, tex);
        p2_head = new Head(898-40, 398-39, tex);
        
        lp1 = new Lifepoint(50, 25, tex, 1);
        lp2 = new Lifepoint(WIDTH-470, 25, tex, 2);

        ck = new Clock((WIDTH-940)/2 +410, 15, tex, this);
        clock_counter = new Timer(1000, ck);
        clock_counter.start();

        c = new Controller(this, tex);

        ea = c.getEntityA();
        eb = c.getEntityB();

        this.addMouseListener(new MouseInput(this));
        

    }

    private synchronized void start() {
        if (running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }


    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                if (State == State.MENU) {
                    System.out.println(updates + " Ticks, Fps " + frames + " Game.State == MENU");
                }else if (State == State.SELECT) {
                    if (p1_select_turn) {
                        System.out.println(updates + " Ticks, Fps " + frames + " Game.State == SELECT player1 : selecting");
                    } if (p2_select_turn) {
                        System.out.println(updates + " Ticks, Fps " + frames + " Game.State == SELECT player2 : selecting");
                    }
                    
                } else if (State == State.TUTORIAL) {
                    System.out.println(updates + " Ticks, Fps " + frames + " Game.State == TUTORIAL");
                } else if (State == State.GAME) {
                    if (player1_turn){
                        System.out.println(updates + " Ticks, Fps " + frames + " Game.State == GAME Turn: player1");
                    } else if (player2_turn){
                        System.out.println(updates + " Ticks, Fps " + frames + " Game.State == GAME Turn: player2");
                    }
                }
                
                updates = 0;
                frames = 0;
            }


        }
        stop();
    }

    private void tick() {
        if (State == STATE.GAME) {
            p1.tick();
            p2.tick();
            
            lp1.tick();
            lp2.tick();
            ck.tick();
            c.tick();
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////

        g.drawImage(image, 0 ,0, getWidth(), getHeight(), this);
        
        

        if (State == STATE.GAME) {
            
            g.drawImage(bg_game, 0, 0, getWidth(), getHeight(), null);
            
            p1.render(g);
            p2.render(g);
            c.render(g);
            
            lp1.render(g);
            lp2.render(g);
            ck.render(g);
            
        } else if (State == STATE.MENU) {
            g.drawImage(bg_menu, 0, 0, getWidth(), getHeight(),null);
        } else if (State == STATE.TUTORIAL) {
            g.drawImage(bg_tutorial, 0, 0, getWidth(), getHeight(),null);
        } else if (State == STATE.SELECT) {
            g.drawImage(bg_select, 0, 0, getWidth(), getHeight(), null);
            p1_head.render(g);
            p2_head.render(g);
        }

        //////////////////////////////////
        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }
    
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        
        if(Game.State == Game.State.MENU) {
            
            if (mx >= 493 && mx <= 793) {
                // Start button -> state.select
                if (my >= 290 && my <= 418) {
                    System.out.println("Go to SELECT state");
                    Game.State = Game.STATE.SELECT;
                }
                //Tutorial button ->state.tutorial
                if (my > 418 && my <= 542) {
                    System.out.println("Go to TURORIAL state");
                    Game.State = Game.STATE.TUTORIAL;
                }
                //Exit button
                if (my > 542 && my <= 668) {
                    System.exit(0);
                }
            }  
        }
        
        else if (State == STATE.TUTORIAL) {
            if (mx >= 1194 && mx <= 1252 && my >= 33 && my <= 92) {
               State = STATE.MENU;
            }
        }
        
        
        
        else if (State == STATE.SELECT) {
            
//            p1_tex = "sprite-1_left";
//            p2_tex = "sprite-1_right";

            if (p1_select_turn){
                if (my >= 222 && my <= 420) {
                        //select female
                    if (mx >= 438 && mx <= 634) {
                        p1_head.setHead(0);
                        p1_tex = "sprite-2_left";
                        p1_select_turn = false;
                        p2_select_turn = true;
                        System.out.println(p1_tex);
                    }
                    //select male
                    else if (mx >= 647 && mx <= 842) {
                        p1_head.setHead(1);
                        p1_tex = "sprite-1_left";
                        p1_select_turn = false;
                        p2_select_turn = true;
                        System.out.println(p1_tex);
                    }
                }
            } else if (p2_select_turn) {
                if (my >= 222 && my <= 420) {
                    //select female
                    if (mx >= 438 && mx <= 634) {
                        p2_head.setHead(0);
                        p2_tex = "sprite-2_right";
                        p2_select_turn = false;
                        p1_select_turn = true;
                        System.out.println(p2_tex);
                    }
                    //select male
                    else if (mx >= 647 && mx <= 842) {
                        p2_head.setHead(1);
                        p2_tex = "sprite-1_right";
                        p2_select_turn = false;
                        p1_select_turn = true;
                        System.out.println(p2_tex);
                    }
                }              
            }
            //confirm button
            if (mx >= 496 && mx <= 792 && my >= 535 && my <= 660) {
                System.out.println("p1_tex : "+p1_tex);
                System.out.println("p2_tex : "+p2_tex);
                try {
                    ss_player1 = loader.loadImage("img/"+p1_tex+".png");
                    ss_player2 = loader.loadImage("img/"+p2_tex+".png");
                    tex.getTextures(this);
                } catch(IOException er) {
                    er.printStackTrace();
                }
                p1 = new Player1(50,610, tex);
                throwAnimation = new Timer(50, new ThrowingAnim(tex));

                p2 = new Player2(WIDTH-140,610, tex);
                State = State.GAME;
            }

        }
        
        
        else if (State == STATE.GAME) {
            mp_x = e.getX();
            mp_y = e.getY();
            if (player1_turn) Player1.player1 = tex.player1[1];

            if (player2_turn) Player2.player2 = tex.player2[3];
        }
    }
    
    public void mouseReleased(MouseEvent e) {

        
        clicked = true;
        throwing = true;
        
        if (State == STATE.GAME) {
            mr_x = e.getX();
            mr_y = e.getY();
            force = Math.sqrt(Math.pow(mp_x - mr_x, 2) + Math.pow(mp_y - mr_y, 2));

            o_side = Math.sqrt(Math.pow(mp_x-mp_x, 2) + Math.pow(mp_y - mr_y, 2));

            angle = Math.asin(o_side/force);



            if (player1_turn) {
    //            c.addEntity(new Bullet(p1.getX(), p1.getY(), tex, this, c));
    //            c.addEntity(new MacBook(p1.getX(), p1.getY(), angle, force, tex));

    //            if (throwing) {
                System.out.println("p1_turn");
                    player1_turn = false;
                    player2_turn = true;
    //            }


            } else if (player2_turn) {
    //            c.addEntity(new Bullet(p2.getX(), p2.getY(), tex, this, c));
    //            c.addEntity(new MacBook(p2.getX(), p2.getY(), angle, force, tex));
                System.out.println("p2_turn");
                player1_turn = true;
                player2_turn = false;
            }
        
        }
    }

    public static void main(String args[]) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
    

    public BufferedImage getSpriteLP() {
        return ss_lp;
    }
    
    public BufferedImage getSpriteObject() {
        return ss_object;
    }
    
    public BufferedImage getSpritePlayer(int player) {
        if (player == 1) return ss_player1;
        if (player == 2) return ss_player2;
        return null;
    }
    
    public BufferedImage getSpriteSelectFrame() {
        return select_frame;
    }
    
    public BufferedImage getSpriteHead() {
        return ss_head;
    }

    public BufferedImage getSpriteTimer() {
        return ss_clock;
    }
    
    public double getForce() {
        return force;
    }
    
    public double getAngle() {
        return angle;
    }
   
}
