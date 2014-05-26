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
public class IconSentry extends Icon {

    public IconSentry() {
        texture = ImageArchive.getIconSentry();
        hotkey = "2";
        xPos = 372;
        tooltip = ImageArchive.getTooltipSentry();
        setBounds();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        super.update(container, game, delta);
        cooldown = (int) PlayerHandler.getPlayer().getSkill1CD();

    }

}
