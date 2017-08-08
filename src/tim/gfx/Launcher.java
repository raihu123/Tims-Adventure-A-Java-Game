/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.gfx;

import java.awt.Color;
import java.awt.Graphics;
import tim.game.Game;

/**
 *
 * @author Admin
 */
public class Launcher {
    
    public Button [] buttons;
    
    public Launcher(){
      buttons = new Button[2];
      
      buttons[0] = new Button(412,300,230,100,"Start Game");
      buttons[1] = new Button(415,400,220,100,"Exit Game");
    
    }
 
    
    
    public void render (Graphics g){
       g.setColor(Color.red);
       g.fillRect(0, 0, Game.getFrameWidth() + 50, Game.getFrameHeight() + 50);
       
       for(int i =0; i<buttons.length;i++){
          buttons[i].render(g);
       }
       
    }
    
}
