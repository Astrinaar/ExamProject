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
    private StandardButton buttonSinglePlayer;
    

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
        buttonSinglePlayer = new StandardButton(ImageArchive.getButtonSinglerPlayer(), 264, 400) {
            @Override
            public void Click() {
                stateHandler.enterState(StateHandler.COMBATSTATE);
                stateHandler.loadCombatState("Rohan Dirt");
            }
        };
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
        buttonSinglePlayer.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        input = container.getInput();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            Point p = new Point(input.getMouseX(), input.getMouseY());
            if(buttonSinglePlayer.getBounds().contains(p)){
                buttonSinglePlayer.Click();
            }
        }
    }

}
