/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class ImageArchive {
    
    public static Image backgroundGrass;
    
    
    public ImageArchive(){
        
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        backgroundGrass = new Image("res/BackgroundGrass.png");
    }
}
