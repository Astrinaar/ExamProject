/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skills;

import Player.*;
import extendables.Enemy;
import extendables.Entity;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.SkillHelper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Sentry extends Entity {

    private float reloadTime = 50;
    private Entity target;

    public Sentry(float xPos, float yPos) {
        super(xPos, yPos);
        setLife(1000);
        texture = ImageArchive.getSentry();
        Player player = PlayerHandler.getPlayer();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        if (target != null) {
            texture.setRotation((float) Math.toDegrees(MathTool.getAngleBetweenTwoPointsInvY(xPos, yPos, target.getxPosMiddle(), target.getyPosMiddle(), PlayerProjectileManager.sentryFireXOffset)));
        }
        texture.draw(xPos - (texture.getWidth() / 2), yPos - (texture.getHeight() / 2));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        enforceBorders(container);
        reloadTime -= hundredPerSec * delta;
        life -= hundredPerSec * delta;
        if (reloadTime <= 0) {
            target = SkillHelper.sentryFire(this);
            reloadTime = 50;
        }
    }

}
