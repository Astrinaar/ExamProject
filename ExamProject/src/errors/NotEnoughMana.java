/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class NotEnoughMana extends extendables.Error{

    public NotEnoughMana() {
    message = "Not enough mana";
    xPos = 325;
    yPos = 60;
    }

    
    

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }
    
}
