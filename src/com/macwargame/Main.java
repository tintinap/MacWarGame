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
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Main extends Canvas implements Runnable {
    public static final int WIDTH = 1286;
    public static final int HEIGHT = 760;
    public final String TITLE = "Mac War Game";

    private boolean running = false;

    protected static boolean throwing_p1 = false;
    protected static boolean throwing_p2 = false;
    protected static boolean recieving = true;
    
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
    private BufferedImage ss_pointer = null;
    
    private static BufferedImage icon = null;
    
    private BufferedImage ss_clock = null;
    protected static Timer clock_counter;
    
  
    private Audio bg_sound;
    private boolean mFirstRound = true;
    private boolean gFirstRound = true;
    private boolean wFirstRound = true;
    
    ///////////////////////////////////////Mouse x, y///////////////////////////////
    private double mp_x, mp_y, mr_x, mr_y;
    private double force, angle, o_side, distance;

    protected static Timer throwAnimation;
    protected static Timer hitAnimation;
    
    protected static Player1 p1;
    protected static Player2 p2;
    private  Head p1_head;
    private  Head p2_head;
    protected static boolean player1_turn = true;
    protected static boolean player2_turn = false;
    private Boolean p1_select_turn = true;
    private Boolean p2_select_turn = false;
    
    private String p1_tex = "sprite-1_left";
    private String p2_tex = "sprite-2_right";
    
    private Head winner_head;
     private Head winner_head2;
    
    private SelectFrame leftSelectFrame;
    private SelectFrame rightSelectFrame;
    
    private TurnPointer leftPointer;
    private TurnPointer rightPointer;
    
    
    protected static boolean clicked =  false;
    
    protected static Lifepoint lp1;
    protected static Lifepoint lp2;
    protected static int lp1hpValue = 3;
    protected static int lp2hpValue = 3;
    private Clock ck;
    
    protected static Controller c;
    private Textures tex;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    
    
    
    BufferedImageLoader loader = new BufferedImageLoader();
    private String winner;

    public static enum STATE {
        MENU,
        SELECT,
        GAME,
        TUTORIAL,
        WINNER
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
            ss_pointer = loader.loadImage("img/pointer.png");
            ss_lp  = loader.loadImage("img/LP.png");
            
            bg_game = loader.loadImage("img/game_bg.png");
            ss_object = loader.loadImage("img/mac.png");
            
            ss_clock = loader.loadImage("img/timer.png");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        tex = new Textures(this);
        
        leftSelectFrame = new SelectFrame(420, 222,tex, 205,205);
        rightSelectFrame = new SelectFrame(666, 222,tex, 205,205);
        rightSelectFrame.setState(0);
        p1_head = new Head(169-47, 398-39, tex);
        p2_head = new Head(898-40, 398-39, tex);
        
        winner_head = new Head(493, 307,tex);
        winner_head2 = new Head(493, 307, tex);
        
        bg_sound = new Audio();
        

        this.addKeyListener(new KeyInput(this));
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
                } else if (State == State.WINNER){
                     System.out.println(updates + " Ticks, Fps " + frames + " Game.State == WINNER");
                }
                
                updates = 0;
                frames = 0;
            }


        }
        stop();
    }

    private void tick() {
        if (State == STATE.MENU) {
            if (mFirstRound) {
                bg_sound.menu_music.play();
                bg_sound.menu_music.loop();
            } mFirstRound = false;
            
        }
        
        if (State == STATE.GAME) {
            if (gFirstRound) {
                bg_sound.menu_music.stop();
                bg_sound.battle_music.play();
                bg_sound.battle_music.loop();
            } gFirstRound = false;

            
            p1.tick();
            p2.tick();
            
            lp1.tick();
            lp2.tick();
            ck.tick();
            c.tick();
            if (player1_turn) {
                leftPointer.setState(1);
                rightPointer.setState(0);
            } else if (player2_turn) {
                leftPointer.setState(0);
                rightPointer.setState(1);
            }
            if (lp1hpValue == 0 || lp2hpValue == 0)  {
                if (lp1hpValue == 0) {
                    winner = "p2";
                    winner_head2.setX(500);
                    winner_head2.setY(357-25);
                } else if (lp2hpValue == 0) {
                    winner = "p1";
                    winner_head.setX(500);
                    winner_head.setY(357-25);
                }

                State = STATE.WINNER;
            }
        }
        
        if (State == STATE.WINNER) {
            if (wFirstRound) {
                bg_sound.battle_music.stop();
                bg_sound.victory_music.play();
            } wFirstRound = false;
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
            leftPointer.render(g);
            rightPointer.render(g);
            
        } else if (State == STATE.MENU) {
            g.drawImage(bg_menu, 0, 0, getWidth(), getHeight(),null);
        } else if (State == STATE.TUTORIAL) {
            g.drawImage(bg_tutorial, 0, 0, getWidth(), getHeight(),null);
        } else if (State == STATE.SELECT) {
            g.drawImage(bg_select, 0, 0, getWidth(), getHeight(), null);
            p1_head.render(g);
            p2_head.render(g);
            leftSelectFrame.render(g);
            rightSelectFrame.render(g);
        } else if (State == STATE.WINNER) {
            try {
                g.drawImage(loader.loadImage("img/"+winner+"_winner.png"), 0, 0, getWidth(), getHeight(), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (winner == "p1") {
                winner_head.render(g);
            } else if(winner == "p2") {
                winner_head2.render(g);
            }
        }

        //////////////////////////////////
        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //start state  = 1 0
        if (State == STATE.SELECT) {

            //choose
            if (key == KeyEvent.VK_LEFT) {
                System.out.println("pressed1");
                leftSelectFrame.setState(1);
                rightSelectFrame.setState(0);
            } else if (key == KeyEvent.VK_RIGHT) {
                System.out.println("pressed2");
                leftSelectFrame.setState(0);
                rightSelectFrame.setState(1);
            }

            if (p1_select_turn) {
                if(leftSelectFrame.checkTexture()) {
                    if(key == KeyEvent.VK_SPACE) {
                        p1_head.setHead(0);
                        winner_head = p1_head;

                        p1_tex = "sprite-2_left";
                        p1_select_turn = false;
                        p2_select_turn = true;
                        System.out.println(p1_tex);
                    }
                } else if(rightSelectFrame.checkTexture()) {
                    if(key == KeyEvent.VK_SPACE) {
                        p1_head.setHead(1);
                        winner_head = p1_head;

                        p1_tex = "sprite-1_left";
                        p1_select_turn = false;
                        p2_select_turn = true;
                        System.out.println(p1_tex);
                    }
                }
            } else if (p2_select_turn) {
                if(leftSelectFrame.checkTexture()) {
                    if(key == KeyEvent.VK_SPACE) {
                        p2_head.setHead(0);
                        winner_head2 = p2_head;

                        p2_tex = "sprite-2_right";
                        p2_select_turn = false;
                        p1_select_turn = true;
                        System.out.println(p2_tex);
                    }
                } else if(rightSelectFrame.checkTexture()) {
                    if(key == KeyEvent.VK_SPACE) {
                        p2_head.setHead(1);
                        winner_head2 = p2_head;

                        p2_tex = "sprite-1_right";
                        p2_select_turn = false;
                        p1_select_turn = true;
                        System.out.println(p2_tex);
                    }
                }
            }
            //confirm
            if (key == KeyEvent.VK_ENTER) {
                if (p1_tex == "sprite-1_left") {
                    p1_head.setHead(1);
                    winner_head = p1_head;
                }
                if (p2_tex == "sprite-2_right") {
                    p2_head.setHead(0);
                    winner_head2 = p2_head;
                }
                System.out.println("p1_tex : "+p1_tex);
                System.out.println("p2_tex : "+p2_tex);
                try {
                    ss_player1 = loader.loadImage("img/"+p1_tex+".png");
                    ss_player2 = loader.loadImage("img/"+p2_tex+".png");
                    tex.getTextures(this);
                } catch(IOException er) {
                    er.printStackTrace();
                }
                //////////////////////////////////////////////////////////////////////
                p1 = new Player1(50,610, tex);
                throwAnimation = new Timer(50, new ThrowingAnim(tex));
                hitAnimation = new Timer (100, new HitAnim(tex));

                p2 = new Player2(WIDTH-140,610, tex);
                leftPointer = new TurnPointer(85, 570, tex, 25, 25);
                rightPointer = new TurnPointer(WIDTH-100, 570, tex, 25, 25);
                
                c = new Controller(this, tex);

                ea = c.getEntityA();
                eb = c.getEntityB();
                
                lp1 = new Lifepoint(50, 25, tex, 1);
                lp2 = new Lifepoint(WIDTH-470, 25, tex, 2);

                ck = new Clock((WIDTH-940)/2 +410, 15, tex, this);
                clock_counter = new Timer(1000, ck);
                clock_counter.start();
                //////////////////////////////////////////////////////////////////////
                State = State.GAME;
            }
        
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }
    
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        
        if(Main.State == Main.State.MENU) {
            
            if (mx >= 493 && mx <= 793) {
                // Start button -> state.select
                if (my >= 290 && my <= 418) {
                    System.out.println("Go to SELECT state");
                    Main.State = Main.STATE.SELECT;
                }
                //Tutorial button ->state.tutorial
                if (my > 418 && my <= 542) {
                    System.out.println("Go to TURORIAL state");
                    Main.State = Main.STATE.TUTORIAL;
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
        
 
        else if (State == STATE.GAME) {
            mp_x = e.getX();
            mp_y = e.getY();
            if (player1_turn && !throwing_p1 && !throwing_p2) Player1.player1 = tex.player1[1];

            if (player2_turn && !throwing_p1 && !throwing_p2) Player2.player2 = tex.player2[3];
        }
        
        
        else if (State == STATE.WINNER){
            if (mx >= 1194 && mx <= 1252 && my >= 33 && my <= 92) {
                /*=========================================================reset all==========================================================*/
                lp1hpValue = 3;
                lp2hpValue = 3;
                p1_head.setHead(3);
                p2_head.setHead(3);
                
                winner_head.setHead(3);
                winner_head2.setHead(3);

                if (winner_head == p1_head){
                    winner_head.setX(169-47);
                    winner_head.setY(398-39);
                } else if (winner_head == p2_head){
                    winner_head.setX(898-40);
                    winner_head.setY(398-39);
                }
                
                if (winner_head2 == p1_head){
                    winner_head2.setX(169-47);
                    winner_head2.setY(398-39);
                } else if (winner_head2 == p2_head){
                    winner_head2.setX(898-40);
                    winner_head2.setY(398-39);
                }
                
                player1_turn = true;
                player2_turn = false;
                
                p1_select_turn = true;
                p2_select_turn = false;
                
                throwing_p1 = false;
                throwing_p2 = false;
                recieving = true;
                
                clicked =  false;
                
                p1_tex = "sprite-1_left";
                p2_tex = "sprite-2_right";
                
                mFirstRound = true;
                gFirstRound = true;
                wFirstRound = true;
                
                
                /*=========================================================reset all==========================================================*/
               State = STATE.MENU;
            }
        }
    }
    
    public void mouseReleased(MouseEvent e) {

        if (State == STATE.GAME) {
            clicked = true;
            
            mr_x = e.getX();
            mr_y = e.getY();
            distance = (Math.sqrt(Math.pow(mp_x - mr_x, 2) + Math.pow(mp_y - mr_y, 2)));
            force = (Math.sqrt(Math.pow(mp_x - mr_x, 2) + Math.pow(mp_y - mr_y, 2)))* 0.16;
            o_side = Math.sqrt(Math.pow(mp_x-mp_x, 2) + Math.pow(mp_y - mr_y, 2));

            angle = Math.toDegrees(Math.asin(o_side/distance));
            
            if (Double.isNaN(angle)) {
                angle = 0;
            }
            
            if (force >= 20) { //max Force
                force = 20;
            }

            System.out.println("Distance is "+distance);
            System.out.println("Angle is "+angle);
            System.out.println("Force is "+force);

            if (player1_turn && !throwing_p1 && !throwing_p2) {
                throwing_p1 = true;
                player1_turn = false;
                player2_turn = true;
            } else if (player2_turn && !throwing_p1 && !throwing_p2) {
                throwing_p2 = true;
                
                player1_turn = true;
                player2_turn = false;
                

            }
        
        }
    }

    public static void main(String args[]) {
        BufferedImageLoader iconloader = new BufferedImageLoader();
        Main game = new Main();

        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            icon = iconloader.loadImage("img/mac.png");
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
    public BufferedImage getSpritePointer() {
        return ss_pointer;
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
