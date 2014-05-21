/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extendables;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class AbstractButton implements SlickClass{

    protected Image texture;
    protected Rectangle bounds;
    protected float xPos;
    protected float yPos;
    
    public AbstractButton(Image texture, float xPos, float yPos){
        this.texture = texture;
        this.xPos = xPos;
        this.yPos = yPos;
        bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        texture.draw(xPos, yPos);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    public void Click(){
        
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }
    
    
    
}
