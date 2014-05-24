/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

import Player.Player;
import Player.PlayerHandler;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Boss extends Enemy {

    protected boolean isCasting = false;
    protected float castingTime = 0;
    protected float castingTimeMax;
    protected int castID;
    protected float castCooldown = 50;
    protected Player player;
    protected String name;
    protected String skillName;

    public Boss(float xPos, float yPos) {
        super(xPos, yPos);
        boss = true;
        player = PlayerHandler.getPlayer();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        super.update(container, game, delta);
        if (isCasting) {
            castingTime -= hundredPerSec * delta;
            if (castingTime <= 0) {
                cast();
                isCasting = false;
            }
        }
    }

    
    public void useSkill(int id) {

    }

    public void cast() {

    }

    public boolean isCasting() {
        return isCasting;
    }

    public float getCastingTime() {
        return castingTime;
    }

    public float getCastingTimeMax() {
        return castingTimeMax;
    }

    public String getName() {
        return name;
    }

    public String getSkillName() {
        return skillName;
    }
    
    

}
