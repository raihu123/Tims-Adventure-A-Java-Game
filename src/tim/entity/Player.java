/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import tim.game.Game;
import static tim.game.Game.launcher;
import tim.game.Handler;
import tim.game.Id;
import tim.tile.Tile;

/**
 *
 * @author Admin
 */
public class Player extends Entity {
    
    private int frame = 0;
    private int frameDelay = 0;
    
    private boolean animate = false;
    
    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }


    
    @Override
    public void render (Graphics g){
       if(facing == 0){ 
       g.drawImage(Game.player[frame+6].getBufferedImage(),x,y,width,height,null);
      }else if(facing == 1){
         g.drawImage(Game.player[frame%6].getBufferedImage(),x,y,width,height,null);
      }
    }
    
    @Override
    public void tick(){
       x+=velX;
       y+=velY;
       if(x<=0)  x = 0;
       if(velX!=0) animate = true;
       else animate = false;
       try{
       for(Tile t: handler.tile){
          if(!t.solid) break;
           if(getBounds().intersects(t.getBounds()) && t.getId() == Id.treehouse){
              Game.ending = true;
              Game.gotoend();
              
          }
          if(getBounds().intersects(t.getBounds()) && t.getId() == Id.coin){
              Game.coins++;
              t.die();
          }
             if(getBoundsTop().intersects(t.getBounds()) && t.getId() != Id.coin ){
                 setVelY(0);
                if(jumping) {
                   jumping = false;
                   gravity = 0.8;
                   falling = true;
                }
             }
              if(getBoundsBottom().intersects(t.getBounds())&& t.getId() != Id.coin){
                 setVelY(0);
                if(falling) falling = false;
             }else{
                if(!falling && !jumping){
                   gravity = 0.8;
                   falling = true;
                }
              }
              if(getBoundsLeft().intersects(t.getBounds())&& t.getId() != Id.coin){
                 setVelX(0);
                 x = t.getX()+t.width;
             }
              if(getBoundsRight().intersects(t.getBounds())&& t.getId() != Id.coin){
                 setVelX(0);
                 x = t.getX()-t.width;
             }
             
              
          
          
       }
       }catch(Exception ex) {
       
        }
       
       
       
       for(int i =0;i<handler.entity.size();i++){
         Entity e = handler.entity.get(i);
         
         if(e.getId()==Id.monster){
             if(getBoundsBottom().intersects(e.getBounds())){
                e.die();
             }else if(getBounds().intersects(e.getBounds())){
              die();
           }
         
         }
       }
       
       
       
       
       
       
       if(jumping){
         gravity -=0.1;
           setVelY((int)-gravity);
           if(gravity<=0.8){
             jumping = false;
             falling = true;
           }
       }
       
       if(falling){
          gravity +=0.1;
           setVelY((int)gravity);
       }
       
       if(animate){
            frameDelay++;
          if(frameDelay>=3){
              frame++;
              if(frame>=6){
                 frame = 0;
              }
              frameDelay = 0;
          }
       }
      
       
    }
    
}
