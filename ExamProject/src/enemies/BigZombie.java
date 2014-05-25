/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemies;

import Player.Player;
import Player.PlayerHandler;
import extendables.Boss;
import extendables.Enemy;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.MoveRegister;
import helpers.RandomTool;
import helpers.SkillHelper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class BigZombie extends Boss {

    private float damage = 30;
    private float attackReload = 0;
    private float slowAmount = 0.40f;
    private float slowDuration = 300;
    private boolean aggro = false;
    private float skill0CD;

    public BigZombie(float xPos, float yPos) {
        super(xPos, yPos);
        super.texture = ImageArchive.getBigZombie();
        super.bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.speed = 0.065f;
        super.pathingX = 4;
        super.pathingY = 25;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 16, 11);
        setMaxLife(2000);
        moveStrat = MoveRegister.getFollow(this);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {        
        super.update(container, game, delta);
        if(skill0CD > 0){
        skill0CD -= hundredPerSec * delta;
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
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        super.render(container, game, g);
    }

    @Override
    public void act(int delta) {
        if (aggro) {
            attackReload -= hundredPerSec * delta;
            if (MathTool.getDistanceToPlayer(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 40 && attackReload <= 0) {
                if (MathTool.getDistanceToPlayer(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 30 && skill0CD <= 0 && RandomTool.getRandom().nextInt(3) == 0) {
                    useSkill(0);
                } else {
                    attack(delta);
                }
            }
            if (!(MathTool.getDistanceToPlayer(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 25)) {
                moveStrat.move(this, delta);
            }
        } else {
            aggro();
        }
    }
    
    public void aggro(){
        if (life != maxLife || MathTool.getDistanceToPlayer(xPosMiddle, yPosMiddle) <200) {
                aggro = true;
            }
    }

    public void attack(int delta) {
        player.receiveDamage(damage);
        player.slow(slowAmount, slowDuration);
        attackReload = 60;
    }

    @Override
    public void useSkill(int id) {
        switch (id) {
            case 0:
                isCasting = true;
                castingTime = 100;
                castingTimeMax = 100;
                castID = 0;
                skillName = "Zombie Smash!";
                break;
        }
    }

    @Override
    public void cast() {
        switch (castID) {
            case 0:
                SkillHelper.bigZombieSmash(xPosMiddle, yPosMiddle, 100);
                skill0CD = 500;
                break;
        }
    }

}
