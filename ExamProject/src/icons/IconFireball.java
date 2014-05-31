/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icons;

import Player.PlayerHandler;
import extendables.Icon;
import helpers.ImageArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class IconFireball extends Icon{

    public IconFireball() {
    texture = ImageArchive.getIconFireball();
    hotkey = "1";
    xPos = 330;
    tooltip = ImageArchive.getTooltipFireball();
    setBounds();
    }

    
}
