/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import extendables.UI;
import helpers.ImageArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import states.CombatState;
import states.StateHandler;

/**
 *
 * @author PK
 */
public class TutorialReachedLvl2 extends UI {

    private Image tutorialReachedLvl2;
    private Rectangle continueBounds;

    public TutorialReachedLvl2(CombatState combatState) {
        super(combatState);
        uiID = 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        xPos = 160;
        yPos = 100;
        tutorialReachedLvl2 = ImageArchive.getTutorialLvl2();

        bounds = new Rectangle(xPos, yPos, tutorialReachedLvl2.getWidth(), tutorialReachedLvl2.getHeight());
        exitBounds = new Rectangle(427 + xPos, 6 + yPos, 32, 38);
        continueBounds = new Rectangle(160 + xPos, 287 + yPos, 147, 32);
    }

    @Override
    public void clicked(int currentPage, Point p) {
        super.clicked(currentPage, p);
        if (continueBounds.contains(p)) {
            combatState.closeUI(uiID);
            StateHandler.enterState(0);
        }
    }

    @Override
    public void renderCurrentPage(int currentPage) {
        switch (currentPage) {
            case 0:
                tutorialReachedLvl2.draw(xPos, yPos);
                break;
        }
    }
}
