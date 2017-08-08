/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.tile;

import java.awt.Color;
import java.awt.Graphics;
import tim.game.Game;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public class Wall extends Tile {

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

        
    @Override
    public void render(Graphics g) {
         g.drawImage(Game.grass.getBufferedImage(),x,y,width,height,null);
    }

    @Override
    public void tick() {
   
    }
    
    
    
}
