package tim.game;


import tim.input.KeyInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import tim.entity.Entity;
import tim.entity.Player;
import tim.gfx.Launcher;
import tim.gfx.Sprite;
import tim.gfx.SpriteSheet;
import tim.input.MouseInput;
import tim.tile.Wall;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 270;
    public static final int HEIGHT  = WIDTH/14*10;
    public static final int SCALE = 4;
    public static final String TITLE = "Tim's Journey To Sky Island";
    public static Handler handler;
    public static SpriteSheet sheet;
    
    public static Sprite grass;
    public static Sprite player [] = new Sprite[12];
    public static Sprite monster [] = new Sprite[4]; 
    public static Sprite coin [] = new Sprite[10];
    
    public static Camera cam;
    public static Launcher launcher;
    public static MouseInput mouseinput =  new MouseInput();
    
    private Thread thread;
    private boolean running =  false;
    private BufferedImage image;
    private BufferedImage backgroundimg;
    private BufferedImage first;
    private BufferedImage death;    
    
    public static int coins = 0;
    
    public static int lives = 2;
    public static int deathScreenTime = 0;
    
    
    public static boolean showDeathScreen = true;
    public boolean firsttime = true;
    public static boolean playing = false;
    public static boolean ending = false;
    
    
    private synchronized void start(){
       if(running) return;
       running  = true;
       thread =  new Thread(this,"Thread");
       thread.start();
    }
        
    private synchronized void stop(){
       if(!running) return;
       running = false;
       try{
          thread.join();
       } catch (InterruptedException e){
          e.printStackTrace();
       }
    }
    
    @Override
    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        while(running){
          long now = System.nanoTime();
          delta+=(now-lastTime)/ns;
          lastTime = now;
          while(delta>=1){
            tick();
            ticks++;
            delta--;
          }
          render();
          frames++;
          if(System.currentTimeMillis()-timer>1000){
             timer+=1000;
             System.out.println(frames + " Frames Per Second " + ticks + " updates Per Second");
             frames = 0;
             ticks = 0;
          }
        }   
        stop();
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        
//         g.setColor(Color.BLACK);
//         g.fillRect(0, 0, getWidth(), getHeight());
         
         
        
        if(showDeathScreen && !firsttime){
           try {
               death = ImageIO.read(getClass().getResource("/death.jpg"));
          } catch (IOException ex) {
              //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(death, 0, 0, getWidth(), getHeight(), this);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Courier", Font.BOLD, 50));
           g.drawImage(Game.player[0].getBufferedImage(), 500, 300, 100, 100, null);
           g.drawString("x" + lives, 610,400);
        }
        if(showDeathScreen && firsttime){
           try {
              first = ImageIO.read(getClass().getResource("/first.jpg"));
          } catch (IOException ex) {
             // Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(first, 0, 0, getWidth(), getHeight(), this);
           g.setColor(Color.BLACK);
           g.setFont(new Font("Courier", Font.BOLD, 50));
           g.drawString("Help Tim get to the top of the World", 175,150);
           g.setColor(Color.BLACK);
           g.setFont(new Font("Courier", Font.BOLD, 50));
           g.drawImage(Game.player[0].getBufferedImage(), 500, 300, 100, 100, null);
           g.drawString("x" + lives, 610,400);
        }
        
      
        
        
        
        
        
        if(lives<=0){
           try {
               death = ImageIO.read(getClass().getResource("/death.jpg"));
          } catch (IOException ex) {
             // Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(death, 0, 0, getWidth(), getHeight(), this);
           g.setColor(Color.WHITE);
           g.setFont(new Font("Courier", Font.BOLD, 50));
           g.drawString("Game Over", 520,400);
           
           
        }
       
        if(!showDeathScreen && !firsttime && playing){
            try {
              backgroundimg = ImageIO.read(getClass().getResource("/background.jpg"));
          } catch (IOException ex) {
              //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
          //Graphics g = bs.getDrawGraphics();
         
          
          g.drawImage(backgroundimg, 0, 0, getWidth(), getHeight(), this);
          g.drawImage(Game.coin[0].getBufferedImage(), 20, 20, 75, 75, null);
          g.setColor(Color.WHITE);
          g.setFont(new Font("Courier", Font.BOLD, 20));
          g.drawString("x" + coins, 100, 95);
          g.translate(cam.getX(), cam.getY()); 
          handler.render(g);
        }else if(!playing) launcher.render(g);
        
        g.dispose();
        bs.show();
        
    }
    
    public static void gotoend(){
         handler.clearlevel();
         End end = new End();
        
    }
    
    public void tick(){
      if(playing)handler.tick();
      for(Entity e:handler.entity){
         if(e.getId()== Id.player){
           cam.tick(e);
         }
      }
      for(Entity e:handler.entity){
         if(e.getId()== Id.player){
           cam.tick(e);
         }
      }
      if(showDeathScreen) deathScreenTime++;
      if(deathScreenTime>=180){
        showDeathScreen = false;
        deathScreenTime = 0;
        handler.clearlevel();
        handler.createlevel(image);
        firsttime = false;
        if(lives<=0){
          playing = false;
          ending = false;
          BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
          Graphics g = bs.getDrawGraphics();
           launcher.render(g);
        }
      }
      
    }
    
    
    
    public Game(){
      Dimension size  = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
      setPreferredSize(size);
      setMaximumSize(size);
      setMinimumSize(size);
    }
     public static Sound jump;
    private void init(){
       handler = new Handler();
       sheet = new SpriteSheet("/StripeSheet.png");
       cam = new Camera();
       launcher = new Launcher();
       addKeyListener(new KeyInput());
       addMouseListener(mouseinput);
       addMouseMotionListener(mouseinput);
       
       grass = new Sprite(sheet, 1, 1);
       
       
       for(int i =0;i<player.length;i++){
          player[i] = new Sprite(sheet,i+1,16);
       }
       
       for(int i =0;i<monster.length;i++){
          monster[i] = new Sprite(sheet,i+1,15);
       }
       for(int i =0;i<coin.length;i++){
          coin[i] = new Sprite(sheet,i+1,14);
       }
     
       
       
        try {
            image = ImageIO.read(getClass().getResource("/level.png"));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        jump = new Sound("/jump.wav");
    }
    
    public static int getFrameWidth(){
       return WIDTH*SCALE;
    }
    
    public static int getFrameHeight(){
       return HEIGHT*SCALE;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game =  new Game();
        JFrame frame =  new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
      
        
    }

    
}
