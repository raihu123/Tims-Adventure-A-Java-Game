/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import static tim.game.Game.getFrameWidth;
import tim.game.Handler;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public class Cloud extends Entity{

    public BufferedImage cloudimg;
   
    
    public Cloud(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
       
        
        
        
    }

    @Override
    public void render(Graphics g) {
        try {
               cloudimg = ImageIO.read(getClass().getResource("/cloud.png"));
          } catch (IOException ex) {
              //Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(cloudimg, x, y, width, height, null);
    }

    @Override
    public void tick() {
        
        
        
    }
    
}
