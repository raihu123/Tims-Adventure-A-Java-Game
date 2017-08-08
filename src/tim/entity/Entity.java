/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import tim.game.Game;
import static tim.game.Game.lives;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public abstract class Entity {
    
    public int x;
    public int y;
    public int width , height;
    public int facing = 0; //0 for left 1 for right
    
    
    public int velX;
    public int velY;
    
    public boolean solid;
    private Id id;
    
    public boolean falling = true;
    public boolean jumping = false;
    
    public double gravity = 0.0;
    
    public Handler handler;
    
    public Entity(int x, int y, int width, int height, boolean solid, Id id, Handler handler){
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
      this.solid = solid;
      this.id = id;
      this.handler = handler;
    }
    
    public abstract void render (Graphics g) ;
    
    
    public abstract void tick();
    
    public void die(){
       handler.removeEntity(this);
       Game.lives--;
       Game.showDeathScreen = true;
    }
    

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the solid
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * @param solid the solid to set
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    

    /**
     * @param velX the velX to set
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    

    /**
     * @param velY the velY to set
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * @return the id
     */
    public Id getId() {
        return id;
    }
    
    public Rectangle getBounds(){
      return new Rectangle(getX(),getY(),width,height);
    }
    
     public Rectangle getBoundsTop(){
      return new Rectangle(getX()+10,getY(),width-20,5);
     }
    
  
     public Rectangle getBoundsBottom(){
      return new Rectangle(getX()+10,getY()+height-5,width-20,5);
     }
     
     public Rectangle getBoundsLeft(){
      return new Rectangle(getX(),getY()+10,5,height-20);
     }
     
      public Rectangle getBoundsRight(){
      return new Rectangle(getX()+width-5,getY()+10,5,height-20);
      }

}
