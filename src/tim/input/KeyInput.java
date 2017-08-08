/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tim.entity.Entity;
import tim.entity.Player;
import tim.game.Game;
import tim.game.Id;

/**
 *
 * @author Admin
 */
public class KeyInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        for(Entity en:Game.handler.entity){
            if(en.getId() == Id.player){
                switch(key){
                    case KeyEvent.VK_W:
                        Game.jump.play();
                        if(!en.jumping){
                            en.jumping = true;
                            en.gravity = 8.0;
                        }
                        break;
                    case KeyEvent.VK_A:
                        en.setVelX(-5);
                        en.facing = 0;
                        break;
                    case KeyEvent.VK_D:
                        en.setVelX(5);
                        en.facing = 1;
                        break;
                }
          }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(Entity en:Game.handler.entity){
            if(en.getId() == Id.player){
                switch(key){
                case KeyEvent.VK_W:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_S:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(0);
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(0);
                    break;
            
            }
            }
          
        }
    }
    
}
