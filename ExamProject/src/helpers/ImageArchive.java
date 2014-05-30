/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class ImageArchive {

    //Backgrounds
    private static Image backgroundGrass;
    private static Image backgroundDirt;
    private static Image backgroundMainMenu;
    private static Image backgroundBottomBorder;
    private static Image backgroundPausedDarkener;

    //UI
    private static Image talentTree;

    //Tutorials
    private static Image tutorialFirstCombat1;
    private static Image tutorialFirstCombat2;
    private static Image tutorialFirstCombat3;
    private static Image tutorialFirstCombat4;
    private static Image tutorialLvl2;
    private static Image tutorialLvl5;
    private static Image tutorialLvl10;
    private static Image tutorialLvl15;

    //Wiki
    private static Image wikiWelcomeScreen;
    private static Image wikiStatusEffects;
    private static Image wikiMonsters;

    //Icons
    private static Image iconFireball;
    private static Image iconSentry;
    private static Image iconPush;
    private static Image iconCDDark;
    private static Image iconWiki;

    //Tooltips
    private static Image tooltipFireball;
    private static Image tooltipSentry;
    private static Image tooltipPush;
    private static Image tooltipLife;
    private static Image tooltipMana;
    private static Image tooltipWiki;

    //Buttons
    private static Image buttonSinglerPlayer;

    //Player related
    private static Image player;
    private static Image playerSlowedTint;
    private static Image leenFace;

    //Enemies
    private static Image zombie;
    private static Image bigZombie;
    private static Image skeletonSummoner;

    //Projectiles
    private static Image weakFireball;
    private static Image arrow;
    private static Image arrowFire;
    private static Image push;

    //Effects
    private static Image effectScorchedGround;

    //Skills
    private static Image sentry;

    public ImageArchive() {

    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //Backgrounds
        backgroundGrass = new Image("res/BackgroundGrass.png");
        backgroundDirt = new Image("res/BackgroundDirt.png");
        backgroundMainMenu = new Image("res/MainMenuBG.JPG");
        backgroundBottomBorder = new Image("res/BottomBorder.png");
        backgroundPausedDarkener = new Image("res/PausedDarkener.png");

        //UI
        talentTree = new Image("res/TalentTree.png");

        //Tutorials
        tutorialFirstCombat1 = new Image("res/TutorialFirstCombat1.png");
        tutorialFirstCombat2 = new Image("res/TutorialFirstCombat2.png");
        tutorialFirstCombat3 = new Image("res/TutorialFirstCombat3.png");
        tutorialFirstCombat4 = new Image("res/TutorialFirstCombat4.png");
        tutorialLvl2 = new Image("res/TutorialLvl2.png");
        tutorialLvl5 = new Image("res/TutorialLvl5.png");
        tutorialLvl10 = new Image("res/TutorialLvl10.png");
        tutorialLvl15 = new Image("res/TutorialLvl15.png");

        //Wiki
        wikiWelcomeScreen = new Image("res/WikiWelcomeScreen.png");
        wikiStatusEffects = new Image("res/WikiStatusEffects.png");
        wikiMonsters = new Image("res/WikiMonsters.png");

        //Icons
        iconFireball = new Image("res/IconFireball.png");
        iconSentry = new Image("res/IconSentry.png");
        iconPush = new Image("res/IconPush.png");
        iconCDDark = new Image("res/IconCDDark.png");
        iconWiki = new Image("res/IconWiki.png");

        //Tooltips
        tooltipFireball = new Image("res/TooltipFireball.png");
        tooltipSentry = new Image("res/TooltipSentry.png");
        tooltipPush = new Image("res/TooltipPush.png");
        tooltipLife = new Image("res/TooltipLife.png");
        tooltipMana = new Image("res/TooltipMana.png");
        tooltipWiki = new Image("res/TooltipWiki.png");

        //Buttons
        buttonSinglerPlayer = new Image("res/ButtonSinglePlayer.png");

        //Player related
        player = new Image("res/Player.png");
        playerSlowedTint = new Image("res/PlayerSlowed.png");
        leenFace = new Image("res/LeenFace50.png");

        //Enemies
        zombie = new Image("res/Zombie.png");
        bigZombie = new Image("res/BigZombie.png");
        skeletonSummoner = new Image("res/SkeletonSummoner.png");

        //Projectiles
        //Effects
        effectScorchedGround = new Image("res/EffectScorchedGround1.png");

        //Skills
        sentry = new Image("res/Sentry1.png");
    }

    //Backgrounds
    public static Image getBackgroundGrass() {
        return backgroundGrass;
    }

    public static Image getBackgroundDirt() {
        return backgroundDirt;
    }

    public static Image getBackgroundMainMenu() {
        return backgroundMainMenu;
    }

    public static Image getBackgroundBottomBorder() {
        return backgroundBottomBorder;
    }

    public static Image getBackgroundPausedDarkener() {
        return backgroundPausedDarkener;
    }

    //UI
    public static Image getTalentTree() {
        return talentTree;
    }

    //Tutorial
    public static Image getTutorialFirstCombat1() {
        return tutorialFirstCombat1;
    }

    public static Image getTutorialFirstCombat2() {
        return tutorialFirstCombat2;
    }

    public static Image getTutorialFirstCombat3() {
        return tutorialFirstCombat3;
    }

    public static Image getTutorialFirstCombat4() {
        return tutorialFirstCombat4;
    }

    public static Image getTutorialLvl2() {
        return tutorialLvl2;
    }

    public static Image getTutorialLvl5() {
        return tutorialLvl5;
    }

    public static Image getTutorialLvl10() {
        return tutorialLvl10;
    }

    public static Image getTutorialLvl15() {
        return tutorialLvl15;
    }
    
    

    //Wiki
    public static Image getWikiWelcomeScreen() {
        return wikiWelcomeScreen;
    }

    public static Image getWikiStatusEffects() {
        return wikiStatusEffects;
    }

    public static Image getWikiMonsters() {
        return wikiMonsters;
    }

    //Icons
    public static Image getIconFireball() {
        return iconFireball;
    }

    public static Image getIconSentry() {
        return iconSentry;
    }

    public static Image getIconPush() {
        return iconPush;
    }

    public static Image getIconCDDark() {
        return iconCDDark;
    }

    public static Image getIconWiki() {
        return iconWiki;
    }

    //Tooltips
    public static Image getTooltipFireball() {
        return tooltipFireball;
    }

    public static Image getTooltipSentry() {
        return tooltipSentry;
    }

    public static Image getTooltipPush() {
        return tooltipPush;
    }

    public static Image getTooltipLife() {
        return tooltipLife;
    }

    public static Image getTooltipMana() {
        return tooltipMana;
    }

    public static Image getTooltipWiki() {
        return tooltipWiki;
    }

    //Buttons
    public static Image getButtonSinglerPlayer() {
        return buttonSinglerPlayer;
    }

    //Player related
    public static Image getPlayer() {
        return player;
    }

    public static Image getPlayerSlowedTint() {
        return playerSlowedTint;
    }

    public static Image getLeenFace() {
        return leenFace;
    }

    //Enemies
    public static Image getZombie() {
        return zombie;
    }

    public static Image getBigZombie() {
        return bigZombie;
    }

    public static Image getSkeletonSummoner() {
        return skeletonSummoner;
    }

    //Projectiles
    public static Image getWeakFireball() {
        try {
            return new Image("res/WeakFireball.png");
        } catch (SlickException ex) {
            Logger.getLogger(ImageArchive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image getArrow() {
        try {
            return new Image("res/Arrow1.png");
        } catch (SlickException ex) {
            Logger.getLogger(ImageArchive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image getArrowFire() {
        try {
            return new Image("res/ArrowFire.png");
        } catch (SlickException ex) {
            Logger.getLogger(ImageArchive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image getPush() {
        try {
            return new Image("res/Push.png");
        } catch (SlickException ex) {
            Logger.getLogger(ImageArchive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Effects
    public static Image getEffectScorchedGround() {
        return effectScorchedGround;
    }

    //Skills
    public static Image getSentry() {
        return sentry;
    }

}
