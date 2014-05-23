/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject;

import helpers.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

/**
 *
 * @author PK
 */
public class ExamProject extends StateBasedGame {

    private StateHandler stateHandler;
    private ImageArchive imgArchive;
    private MathTool mathTool;
    private SkillHelper skillHelper;
    private CombatState combatState;
    private CombatStateLoader combatStateLoader;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        // TODO code application logic here
        AppGameContainer app = new AppGameContainer(new ExamProject("Namae"));
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(59);
        app.setSmoothDeltas(true);
        app.start();
    }

    public ExamProject(String name) {
        super(name);
        imgArchive = new ImageArchive();
        skillHelper = new SkillHelper();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        initHelpers(container, this);
        stateHandler = new StateHandler(this);
        stateHandler.init(container, this);
        combatState = new CombatState(StateHandler.COMBATSTATE, stateHandler, skillHelper);
        addState(new MainMenu(StateHandler.MAINMENUSTATE, stateHandler));
        getState(StateHandler.MAINMENUSTATE).init(container, this);
        addState(combatState);
        getState(StateHandler.COMBATSTATE).init(container, this);
        combatStateLoader = new CombatStateLoader(combatState, skillHelper);
        combatStateLoader.init(container, this);
        stateHandler.setCombatStateLoader(combatStateLoader);
        enterState(StateHandler.MAINMENUSTATE);
    }

    public void initHelpers(GameContainer container, StateBasedGame game) throws SlickException {
        imgArchive.init(container, game);
        skillHelper.init(container, game);
    }
}
