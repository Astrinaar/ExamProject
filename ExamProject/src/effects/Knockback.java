/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package effects;

import extendables.Effect;
import extendables.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Knockback extends Effect {

    private Entity victim;
    private float angle;
    private float speed = 1;

    public Knockback(Entity victim, float angle, float duration) {
        this.victim = victim;
        this.angle = angle;
        lifeTime = duration;
        victim.setKnockback(true);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)  {
        if (victim.isKnockback()) {
            super.update(container, game, delta);
            victim.setxPos(victim.getxPos() + speed * (float) Math.sin(angle) * delta);
            victim.setyPos(victim.getyPos() + speed * (float) Math.cos(angle) * delta);
        } else {
            lifeTime = 0;
        }
    }

}
