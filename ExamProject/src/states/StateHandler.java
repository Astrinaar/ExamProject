/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import examproject.ExamProject;
import helpers.CombatStateLoader;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class StateHandler {

    private final ExamProject examProject;
    public static final int MAINMENUSTATE = 0;
    private CombatState combatState;
    private CombatStateLoader combatStateLoader;
    public static final int COMBATSTATE = 1;

    public StateHandler(ExamProject examProject) {
        this.examProject = examProject;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    public void enterState(int id) {
        examProject.enterState(id);
    }

    public void loadCombatState(String level) {
        combatStateLoader.loadCombatState(level);
    }

    public void setCombatState(CombatState combatState, CombatStateLoader combatStateLoader) {
        this.combatState = combatState;
        this.combatStateLoader = combatStateLoader;
    }

}
