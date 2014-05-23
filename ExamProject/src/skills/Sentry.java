/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skills;

import extendables.Enemy;
import extendables.Entity;
import helpers.ImageArchive;
import helpers.SkillHelper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Sentry extends Entity {

    private float reloadTime = 50;

    public Sentry(float xPos, float yPos) {
        super(xPos, yPos);
        setLife(100);
        texture = ImageArchive.getSentry();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        enforceBorders(container);
        reloadTime -= hundredPerSec * delta;
        if (reloadTime <= 0) {
            SkillHelper.sentryFire(this);
            reloadTime = 50;
        }
    }

}
