/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import static tim.game.Game.ending;
import static tim.game.Game.lives;

/**
 *
 * @author Admin
 */
public class End extends Canvas {
    public  BufferedImage first;
    public End(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
         if(ending){
           try {
               BufferedImage first = ImageIO.read(getClass().getResource("/first.jpg"));
          } catch (IOException ex) {
             // Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
          }
           g.drawImage(first, 0, 0, getWidth(), getHeight(), this);
           g.setColor(Color.BLACK);
           g.setFont(new Font("Courier", Font.BOLD, 50));
           g.drawString("Thank You for Helping Tim Reach the SkyIsland \n The End." + lives, 610,400);
        }
    }
    
}
