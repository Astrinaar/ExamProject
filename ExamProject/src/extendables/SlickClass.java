/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extendables;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public interface SlickClass {
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException;
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;
}
