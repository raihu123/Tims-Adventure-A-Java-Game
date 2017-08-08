/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.tile;

import java.awt.Graphics;
import tim.entity.Entity;
import tim.game.Game;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public class Coin extends Tile {

    
    private int frame = 0;
    private int frameDelay = 0;
    
    public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void render(Graphics g) {
         g.drawImage(Game.coin[frame%10].getBufferedImage(), x, y, width, height,null);
    }

    @Override
    public void tick() {
                
              frameDelay++;
              if(frameDelay>=10){
                  frame++;
                  if(frame>=10){
                     frame = 0;
                  }
                  frameDelay = 0;
              }
    }
    
}
