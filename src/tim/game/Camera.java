/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.game;

import tim.entity.Entity;

/**
 *
 * @author Admin
 */
public class Camera {
    
    public int x, y ;

    
   
    
     void tick(Entity player) {
        setX(-player.getX()+Game.WIDTH);
        setY(-player.getY()+Game.HEIGHT); 
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

   
    
    
}
