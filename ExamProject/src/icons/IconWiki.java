/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icons;

import extendables.Icon;
import helpers.ImageArchive;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class IconWiki extends Icon {

    public IconWiki() {
        texture = ImageArchive.getIconWiki();
        hotkey = "h";
        xPos = 762;
        tooltip = ImageArchive.getTooltipWiki();
        setBounds();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        texture.draw(xPos, yPos);
        if (showTooltip) {
            tooltip.draw(xPos - tooltip.getWidth(), yPos - tooltip.getHeight());
        }
    }


}
