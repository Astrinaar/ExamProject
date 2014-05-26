/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemies;

import extendables.Boss;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.MoveRegister;
import helpers.RandomTool;
import helpers.SkillHelper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class SkeletonSummoner extends Boss {

    private float skill0CD;
    private float castingDelay = 200;
    private boolean shootingFireballs = false;
    private float fireballShowerDuration = 0;
    private float fireballReload;

    public SkeletonSummoner(float xPos, float yPos) {
        super(xPos, yPos);
        super.texture = ImageArchive.getSkeletonSummoner();
        super.bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.speed = 0;
        super.pathingX = 4;
        super.pathingY = 25;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 16, 11);
        setMaxLife(5000);
        name = "Skeleton Summoner";
        knockbackable = false;
        moveStrat = MoveRegister.getIdle(this);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        super.update(container, game, delta);
        if (castingDelay > 0) {
            castingDelay -= hundredPerSec * delta;
        }
        if (skill0CD > 0) {
            skill0CD -= hundredPerSec * delta;
        }
        if (shootingFireballs) {
            fireballReload -= hundredPerSec * delta;
            if (fireballShowerDuration > 0) {
                fireballShowerDuration -= hundredPerSec * delta;
            } else {
                shootingFireballs = false;
            }
        }
        if (shootingFireballs && fireballReload <= 0) {
            SkillHelper.skeletonFireballShower(xPosMiddle, yPosMiddle);
            fireballReload = 5;
            System.out.println("aa");
        }
    }

    @Override
    public void stoppedByStun(GameContainer container, int delta) {
        super.stoppedByStun(container, delta);
        if (!isCasting) {
            act(delta);
        }
    }

    @Override
    public void act(int delta) {

        if (castingDelay <= 0) {
            int skill;
            do {
                skill = RandomTool.getRandom().nextInt(1);
            } while (!canUseSkill(skill));
            useSkill(skill);
        }

    }

    public boolean canUseSkill(int id) {
        switch (id) {
            case 0:
                if (skill0CD <= 0) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    @Override
    public void useSkill(int id) {
        switch (id) {
            case 0:
                isCasting = true;
                setCastTime(300);
                castID = 0;
                skillName = "Fireball Shower";
                break;
        }
    }

    @Override
    public void cast() {
        switch (castID) {
            case 0:
                shootingFireballs = true;
                fireballReload = 0;
                fireballShowerDuration = 300;
                castingDelay = 500;
                skill0CD = 0;
                break;
        }
    }

    @Override
    public void stun(float duration) {
        super.stun(duration);
        shootingFireballs = false;
    }

}
