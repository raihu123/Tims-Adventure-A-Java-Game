/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import tim.game.Game;
import tim.gfx.Button;

/**
 *
 * @author Admin
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    
    public int x, y;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i =0; i<Game.launcher.buttons.length; i++){
           Button button = Game.launcher.buttons[i];
           
           if(x>=button.getX() && y>= button.getY()&&x<=button.getX()+button.getWidth() && y<= button.getY()+ button.getHeight()) button.triggerEvent();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x =  e.getX();
        y = e.getY();
    }
    
}
