/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tim.entity.Cloud;
import tim.entity.Entity;
import tim.entity.Monster;
import tim.entity.Player;
import tim.entity.Sun;
import static tim.game.Id.treehouse;
import tim.tile.Coin;
import tim.tile.House;
import tim.tile.Tile;
import tim.tile.Treehouse;
import tim.tile.Wall;

/**
 *
 * @author Admin
 */
public class Handler extends JPanel {
    
    public LinkedList<Entity> entity  = new LinkedList<Entity>();
    public LinkedList<Tile> tile =  new LinkedList<Tile>();
    
    public Handler(){
     
    }
    
    public void render(Graphics g){
        for(Entity en:entity){
           en.render(g);
        }
        
        for(Tile ti:tile){
           ti.render(g);
        }
    }
    
    public void tick(){
         for(Entity en:entity){
           en.tick();
        }
        
        for(Tile ti:tile){
           ti.tick();
        }
    }
    
    public void addEntity(Entity en){
       entity.add(en);
    }
    
    public void removeEntity(Entity en){
       entity.remove(en);
    }
    public void addTile(Tile ti){
       tile.add(ti);
    }
    
    public void removeTile(Tile ti){
       tile.remove(ti);
    }

    public void createlevel(BufferedImage level){

        int width = level.getWidth();
        int height = level.getHeight();
        
        for(int y = 0;y<height; y++){
           for(int x = 0; x<width; x++){
              int pixel = level.getRGB(x, y);
              
              int red = (pixel >> 16) & 0xff;
              int green = (pixel >> 8) & 0xff;
              int blue = (pixel)  & 0xff;
              
              if(red == 57 && green == 57 && blue == 200 ) addTile(new Treehouse(x*64, y*64, 192 ,265, true, Id.treehouse, this));
              if(red == 0 && green == 0 && blue == 0 ) addTile(new Wall (x*64, y*64, 64 ,64, true, Id.wall, this));
              if(red == 0 && green == 0 && blue == 255 ) addEntity(new Player (x*64, y*64, 64 ,64, false, Id.player, this)); 
              if(red == 255 && green == 0 && blue == 0 ) addEntity(new Monster (x*64, y*64, 64 ,64, true, Id.monster, this));
              if(red == 222 && green == 15 && blue == 15 ) addTile(new Coin (x*64, y*64, 64 ,64, true, Id.coin, this));
              if(red == 97 && green == 97 && blue == 97 ) addEntity(new Cloud (x*64, y*64, 64 ,64, false, Id.cloud, this)); 
              if(red == 255 && green == 252 && blue == 0 ) addEntity(new Sun (x*64, y*64, 256 ,256, false, Id.sun, this)); 
              if(red == 0 && green == 255 && blue == 0 ) addTile(new House (x*64, y*64, 200 ,200, true, Id.house, this));
           }
        }
      
    }
    
    
   public void clearlevel(){
      entity.clear();
      tile.clear();
   } 
    
    
    
   
    
    
}
