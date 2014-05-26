/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extendables;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class Error implements SlickClass{
    
    protected String message;
    protected float lifeTime = 300;
    protected float xPos;
    protected float yPos;
    protected float hundredPerSec = 0.1041667f;

    public Error() {
    }
    
    

    public Error(String message, float lifeTime, float xPos, float yPos) {
        this.message = message;
        this.lifeTime = lifeTime;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta){
        lifeTime -= hundredPerSec * delta;
    }

    @Override
    public  void render(GameContainer container, StateBasedGame game, Graphics g){
        g.setColor(Color.red);
        g.drawString(message, xPos, yPos);
    }

    public float getLifeTime() {
        return lifeTime;
    }
    
    
    
    
    
    
}
