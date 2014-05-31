/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import GUI.StandardButton;
import examproject.ExamProject;
import helpers.ImageArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class MainMenu extends BasicGameState {

    private int id;
    private StateHandler stateHandler;
    private Input input;
    private Image background;
    private StandardButton buttonFirstCombat;
    private StandardButton buttonBossFight;
    private StandardButton buttonReachedLvl2;
    private StandardButton buttonReachedLvl5;
    private StandardButton buttonReachedLvl10;
    private StandardButton buttonReachedLvl15;

    public MainMenu(int id, StateHandler stateHandler) {
        this.id = id;
        this.stateHandler = stateHandler;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = ImageArchive.getBackgroundMainMenu();
        buttonFirstCombat = new StandardButton(ImageArchive.getButtonFirstCombat(), 264, 200) {
            @Override
            public void Click() {
                CombatState.firstCombat = true;
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Grass");
            }
        };
        buttonBossFight = new StandardButton(ImageArchive.getButtonBossFight(), 264, 250) {
            @Override
            public void Click() {
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");

            }
        };
        buttonReachedLvl2 = new StandardButton(ImageArchive.getButtonReachedLvl2(), 129, 300) {
            @Override
            public void Click() {
                CombatState.setShowTutorialReachedLvl2(true);
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");

            }
        };
        buttonReachedLvl5 = new StandardButton(ImageArchive.getButtonReachedLvl5(), 399, 300) {
            @Override
            public void Click() {
                CombatState.setShowTutorialReachedLvl5(true);
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");
            }
        };
        buttonReachedLvl10 = new StandardButton(ImageArchive.getButtonReachedLvl10(), 129, 350) {
            @Override
            public void Click() {
                CombatState.setShowTutorialReachedLvl10(true);
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");
            }
        };
        buttonReachedLvl15 = new StandardButton(ImageArchive.getButtonReachedLvl15(), 399, 350) {
            @Override
            public void Click() {
                CombatState.setShowTutorialReachedLvl15(true);
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");
            }
        };
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
        buttonFirstCombat.render(container, game, g);
        buttonBossFight.render(container, game, g);
        buttonReachedLvl2.render(container, game, g);
        buttonReachedLvl5.render(container, game, g);
        buttonReachedLvl10.render(container, game, g);
        buttonReachedLvl15.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        input = container.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Point p = new Point(input.getMouseX(), input.getMouseY());
            if (buttonFirstCombat.getBounds().contains(p)) {
                buttonFirstCombat.Click();
            } else {
                if (buttonBossFight.getBounds().contains(p)) {
                    buttonBossFight.Click();
                } else {
                    if (buttonReachedLvl2.getBounds().contains(p)) {
                        buttonReachedLvl2.Click();
                    } else {
                        if (buttonReachedLvl5.getBounds().contains(p)) {
                            buttonReachedLvl5.Click();
                        } else {
                            if (buttonReachedLvl10.getBounds().contains(p)) {
                                buttonReachedLvl10.Click();
                            } else {
                                if (buttonReachedLvl15.getBounds().contains(p)) {
                                    buttonReachedLvl15.Click();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
