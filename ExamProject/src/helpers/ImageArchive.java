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
    private static Image backgroundMainMenu;
    private static Image backgroundBottomBorder;

    //Icons
    private static Image iconFireball;
    private static Image iconSentry;
    private static Image iconPush;
    private static Image iconCDDark;
    

    //Buttons
    private static Image buttonSinglerPlayer;

    //Player related
    private static Image player;
    private static Image playerSlowedTint;
    private static Image leenFace;

    //Enemies
    private static Image zombie;
    private static Image bigZombie;

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
        backgroundMainMenu = new Image("res/MainMenuBG.JPG");
        backgroundBottomBorder = new Image("res/BottomBorder.png");

        //Icons
        iconFireball = new Image("res/IconFireball.png");
        iconSentry = new Image("res/IconSentry.png");
        iconPush = new Image("res/IconPush.png");
        iconCDDark = new Image("res/IconCDDark.png");

        //Buttons
        buttonSinglerPlayer = new Image("res/ButtonSinglePlayer.png");

        //Player related
        player = new Image("res/Player.png");
        playerSlowedTint = new Image("res/PlayerSlowed.png");
        leenFace = new Image("res/LeenFace50.png");

        //Enemies
        zombie = new Image("res/Zombie.png");
        bigZombie = new Image("res/BigZombie.png");

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

    public static Image getBackgroundMainMenu() {
        return backgroundMainMenu;
    }

    public static Image getBackgroundBottomBorder() {
        return backgroundBottomBorder;
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
