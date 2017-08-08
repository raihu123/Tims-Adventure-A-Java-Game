/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public abstract class Tile {
    
    public int x;
    public int y;
    public int width , height;
    private int velX;
    private int velY;
    
    public boolean solid;
    private Id id;
    public Handler handler; 
    
    public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler){
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
      handler.removeTile(this);
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
    
}
