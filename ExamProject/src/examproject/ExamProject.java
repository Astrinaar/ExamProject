/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examproject;

import helpers.ImageArchive;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

/**
 *
 * @author PK
 */
public class ExamProject extends StateBasedGame{
    
    private StateHandler stateHandler;
    private ImageArchive imgArchive;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        // TODO code application logic here
        AppGameContainer app = new AppGameContainer(new ExamProject("Namae"));
        app.setDisplayMode(800, 600, false);
        app.start();
    }
    
    public ExamProject (String name){
        super(name);
        imgArchive = new ImageArchive();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        initHelpers(container, this);
        stateHandler = new StateHandler(this);
        stateHandler.init(container, this);
        addState(new MainMenu(StateHandler.MAINMENUSTATE, stateHandler));
        getState(StateHandler.MAINMENUSTATE).init(container, this);
        addState(new CombatState(StateHandler.COMBATSTATE, stateHandler));
        getState(StateHandler.COMBATSTATE).init(container, this);
        stateHandler.setCombatState((CombatState)getState(StateHandler.COMBATSTATE));        
        enterState(StateHandler.MAINMENUSTATE);
    }
    
    public void initHelpers(GameContainer container, StateBasedGame game) throws SlickException {
        imgArchive.init(container, game);
    }
}