/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

import helpers.ImageArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import states.CombatState;

/**
 *
 * @author PK
 */
public abstract class UI implements SlickClass {

    protected CombatState combatState;
    protected int uiID;

    protected int currentPage;
    protected Rectangle bounds;
    protected Rectangle exitBounds;

    public UI(CombatState combatState) {
        this.combatState = combatState;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        currentPage = 0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        renderCurrentPage(currentPage);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        Input input = container.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Point p = new Point(input.getMouseX(), input.getMouseY());
            if (bounds.contains(p)) {
                clicked(currentPage, p);
            } else {
                combatState.closeUI(uiID);
            }
        }
    }

    public void renderCurrentPage(int currentPage) {

    }

    public void clicked(int currentPage, Point p) {
        if (exitBounds.contains(p)) {
            combatState.closeUI(uiID);
        }
    }
}
