/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class CombatState extends BasicGameState{

    private int id;
    private StateHandler stateHandler;
    private Image background;

    public CombatState(int id, StateHandler stateHandler) {
        this.id = id;
        this.stateHandler = stateHandler;
    }
            
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
       
    }

    public void setBackground(Image background) {
        this.background = background;
    }
    
    
    
}
