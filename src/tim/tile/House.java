/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public class House extends Tile {

    
     public BufferedImage houseimg;
    public House(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void render(Graphics g) {
         try {
               houseimg = ImageIO.read(getClass().getResource("/house.png"));
          } catch (IOException ex) {
              //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(houseimg, x, y, width, height, null);
    }

    @Override
    public void tick() {
        
    }
    
}
