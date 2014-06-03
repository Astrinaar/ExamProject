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

/**
 *
 * @author PK
 */
public class TutorialFirstCombat extends UI {

    private Image tutorialFirstCombat1;
    private Image tutorialFirstCombat2;
    private Image tutorialFirstCombat3;
    private Image tutorialFirstCombat4;

    private Rectangle continueBounds;

    public TutorialFirstCombat(CombatState combatState) {
        super(combatState);
        uiID = 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        xPos = 160;
        yPos = 100;
        tutorialFirstCombat1 = ImageArchive.getTutorialFirstCombat1();
        tutorialFirstCombat2 = ImageArchive.getTutorialFirstCombat2();
        tutorialFirstCombat3 = ImageArchive.getTutorialFirstCombat3();
        tutorialFirstCombat4 = ImageArchive.getTutorialFirstCombat4();
        bounds = new Rectangle(xPos, yPos, tutorialFirstCombat1.getWidth(), tutorialFirstCombat1.getHeight());
        exitBounds = new Rectangle(427 + xPos, 6 + yPos, 32, 38);
        continueBounds = new Rectangle(160 + xPos, 287 + yPos, 147, 32);
    }

    @Override
    public void clicked(int currentPage, Point p) {
        super.clicked(currentPage, p);
        if (continueBounds.contains(p)) {
            if(currentPage == 0){
               this.currentPage = 1; 
            } else {
                if(currentPage == 1){
                    this.currentPage = 2;
                } else {
                    if(currentPage == 2){
                        this.currentPage = 3;
                    } else{
                        if(currentPage == 3){
                            this.currentPage = 0;
                            combatState.closeUI(1);
                            
                        }
                    }
                }
            }
            
        } 
    }

    @Override
    public void renderCurrentPage(int currentPage) {
        switch (currentPage) {
            case 0:
                tutorialFirstCombat1.draw(xPos, yPos);
                break;
            case 1:
                tutorialFirstCombat2.draw(xPos, yPos);
                break;
            case 2:
                tutorialFirstCombat3.draw(xPos, yPos);
                break;
            case 3:
                tutorialFirstCombat4.draw(xPos, yPos);
                break;
        }
    }

}
