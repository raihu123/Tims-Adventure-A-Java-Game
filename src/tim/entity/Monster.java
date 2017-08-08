/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.entity;

import java.awt.Graphics;
import static java.lang.Math.random;
import java.util.Random;
import tim.game.Game;
import tim.game.Handler;
import tim.game.Id;
import tim.tile.Tile;

/**
 *
 * @author Admin
 */
public class Monster extends Entity {

    
    private int frame = 0;
    private int frameDelay = 0;
    private Random random = new Random();
    
    public Monster(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
        
        int dir = random.nextInt(2);
        
        switch(dir){
          case 0:
                setVelX(-2);
                facing = 0;
                break;
          case 1:
                setVelX(2);
                facing = 1;
                break;
        }
        
        
        
        
        
    }

    
    
    
    
    
    
    @Override
    public void render(Graphics g) {
        if(facing == 0){ 
       g.drawImage(Game.monster[(frame+2)%4].getBufferedImage(),x,y,width,height,null);
      }else if(facing == 1){
         g.drawImage(Game.monster[frame%2].getBufferedImage(),x,y,width,height,null);
      }
    }

    @Override
    public void tick() {
        
        x+=velX;
        y+=velY;
        
        for(int i=0;i<handler.tile.size();i++){
           Tile t = handler.tile.get(i);
           if(t.isSolid()){
              if(getBoundsBottom().intersects(t.getBounds())){
                  setVelY(0);
                  if(falling) falling = false;
              }else if(!falling){
                falling = true;
                gravity = 0.8;
              }
              if(getBoundsLeft().intersects(t.getBounds())){
                  velX=2;
                  facing = 1;
              }
              if(getBoundsRight().intersects(t.getBounds())){
                  velX=-2;
                  facing =0;
              }
           }
        }
        
        
        if(falling){
          gravity+=0.1;
          setVelY((int)gravity);
        }
        
        
        if(velX!=0){
          frameDelay++;
          if(frameDelay>=2){
              frame++;
              if(frame>=2){
                 frame = 0;
              }
              frameDelay = 0;
          }
        
        }
        
    }
    
}
