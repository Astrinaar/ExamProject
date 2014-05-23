/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package effects;

import extendables.Effect;
import helpers.SkillHelper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class fireballGroundEffect extends Effect{
    
    private float radius = 40;
    private float damage = 0.25f;

    public fireballGroundEffect(float xPos, float yPos, Image texture) {
        super(xPos, yPos, texture);
        lifeTime = 500;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);
        SkillHelper.areaDamageInclusive(xPosMiddle, yPosMiddle, radius, damage * 0.1041667f * delta);
    }
    
    
    
}
