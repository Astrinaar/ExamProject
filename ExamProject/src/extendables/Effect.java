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
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class Effect implements SlickClass {

    protected Image texture;
    protected float xPos;
    protected float yPos;
    protected float xPosMiddle;
    protected float yPosMiddle;
    protected float lifeTime;
    

    public Effect(){
        
    }
    
    public Effect(float xPos, float yPos, Image texture) {
        this.texture = texture;
        this.xPos = xPos - (texture.getWidth()/2);
        this.yPos = yPos - (texture.getHeight()/2);
        xPosMiddle = xPos;
        yPosMiddle = yPos;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g){
        texture.draw(xPos, yPos);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta){
        lifeTime -= 0.1041667f * delta;
    }

    public float getxPos() {
        return xPos;
    }

    public float getxPosMiddle() {
        return xPosMiddle;
    }
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }
    
    public float getyPosMiddle() {
        return yPosMiddle;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

}
