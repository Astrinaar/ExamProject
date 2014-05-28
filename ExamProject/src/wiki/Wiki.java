/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wiki;

import extendables.SlickClass;
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
public class Wiki implements SlickClass{
    
    private CombatState combatState;
    
    private Image wikiWelcomeScreen;
    private Image wikiStatusEffects;
    private Image wikiMonsters;
    
    private int currentPage;
    private Rectangle bounds;
    private Rectangle statusEffectsBounds;
    private Rectangle monstersBounds;
    private Rectangle exitBounds;

    public Wiki(CombatState combatState) {
        this.combatState = combatState;
        
    }

    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        wikiWelcomeScreen = ImageArchive.getWikiWelcomeScreen();
        wikiStatusEffects = ImageArchive.getWikiStatusEffects();
        wikiMonsters = ImageArchive.getWikiMonsters();
        currentPage = 0;
        bounds = new Rectangle(50, 50, wikiWelcomeScreen.getWidth(), wikiWelcomeScreen.getHeight());
        statusEffectsBounds = new Rectangle(85, 168, 105, 15);
        monstersBounds = new Rectangle(85, 201, 70, 15);
        exitBounds = new Rectangle(702, 61, 33, 33);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        renderCurrentPage(currentPage);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        Input input = container.getInput();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            Point p = new Point(input.getMouseX(), input.getMouseY());
            if(bounds.contains(p)){
                clicked(currentPage, p);
            } else {
               combatState.closeWiki();
            }
        }
    }
    
    private void renderCurrentPage(int currentPage){
        switch(currentPage){
            case 0:
                wikiWelcomeScreen.draw(50, 50);
                break;
            case 1:
                wikiStatusEffects.draw(50, 50);
                break;
            case 2:
                wikiMonsters.draw(50, 50);
                break;
        }
    }
    
    public void clicked(int currentPage, Point p){
        if(statusEffectsBounds.contains(p)){
            this.currentPage = 1;
        } else {
            if(monstersBounds.contains(p)){
                this.currentPage = 2;
            } else {
                if(exitBounds.contains(p)){
                    combatState.closeWiki();
                }
            }
        }
    }
}
