/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

import helpers.ImageArchive;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class Icon implements SlickClass {

    protected Image texture;
    protected float xPos;
    protected float yPos = 554;
    protected String hotkey;
    protected int cooldown = 0;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)  {
        g.setColor(Color.white);
        texture.draw(xPos, yPos);
        g.drawString(hotkey, xPos, yPos);
        if(cooldown > 0){
            ImageArchive.getIconCDDark().draw(xPos, yPos);
            g.drawString("" + (int) cooldown / 100, xPos + 15, yPos + 11);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)  {

    }

}
