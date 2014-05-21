/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

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

    //Buttons
    private static Image buttonSinglerPlayer;

    //Player related
    private static Image player;
    private static Image playerSlowedTint;
    private static Image leenFace;

    //Enemies
    private static Image zombie;
    private static Image bigZombie;

    public ImageArchive() {

    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //Backgrounds
        backgroundGrass = new Image("res/BackgroundGrass.png");
        backgroundMainMenu = new Image("res/MainMenuBG.JPG");

        //Buttons
        buttonSinglerPlayer = new Image("res/ButtonSinglePlayer.png");

        //Player related
        player = new Image("res/Player.png");
        playerSlowedTint = new Image("res/PlayerSlowed.png");
        leenFace = new Image("res/LeenFace.png");
        
        //Enemies
        zombie = new Image("res/Zombie.png");
        bigZombie = new Image("res/BigZombie.png");
    }

    //Backgrounds
    public static Image getBackgroundGrass() {
        return backgroundGrass;
    }

    public static Image getBackgroundMainMenu() {
        return backgroundMainMenu;
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
    
}
