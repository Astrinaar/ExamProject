/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki;

import extendables.SlickClass;
import extendables.UI;
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
public class Wiki extends UI {


    private Image wikiWelcomeScreen;
    private Image wikiStatusEffects;
    private Image wikiMonsters;

    private Rectangle statusEffectsBounds;
    private Rectangle monstersBounds;

    public Wiki(CombatState combatState) {
        super(combatState);
        uiID = 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        xPos = 50;
        yPos = 50;
        wikiWelcomeScreen = ImageArchive.getWikiWelcomeScreen();
        wikiStatusEffects = ImageArchive.getWikiStatusEffects();
        wikiMonsters = ImageArchive.getWikiMonsters();
        bounds = new Rectangle(xPos, yPos, wikiWelcomeScreen.getWidth(), wikiWelcomeScreen.getHeight());
        statusEffectsBounds = new Rectangle(85, 168, 105, 15);
        monstersBounds = new Rectangle(85, 201, 70, 15);
        exitBounds = new Rectangle(702, 61, 33, 33);
    }

    @Override
    public void renderCurrentPage(int currentPage) {
        switch (currentPage) {
            case 0:
                wikiWelcomeScreen.draw(xPos, yPos);
                break;
            case 1:
                wikiStatusEffects.draw(xPos, yPos);
                break;
            case 2:
                wikiMonsters.draw(xPos, yPos);
                break;
        }
    }

    @Override
    public void clicked(int currentPage, Point p) {
        if (statusEffectsBounds.contains(p)) {
            this.currentPage = 1;
        } else {
            if (monstersBounds.contains(p)) {
                this.currentPage = 2;
            } else {
                if (exitBounds.contains(p)) {
                    combatState.closeUI(0);
                    currentPage = 0;
                }
            }
        }
    }
}
