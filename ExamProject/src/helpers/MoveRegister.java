/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import MoveStrategy.MoveCaster1;
import MoveStrategy.MoveCasterTowards;
import MoveStrategy.MoveFlee;
import MoveStrategy.MoveFollow;
import MoveStrategy.MoveIdle;
import extendables.Enemy;
import extendables.Entity;
import extendables.MoveStrategy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class MoveRegister {
    
    public static final String CASTER1_ID = "caster1";
    public static final String CASTERTOWARDS_ID = "casterTowards";
    public static final String FLEE_ID = "flee";
    public static final String IDLE_ID = "idle";
    public static final String FOLLOW_ID = "follow";


    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    public static MoveStrategy getCaster1(Enemy e) {
        e.setCurrentMoveStrat(CASTER1_ID);
        return new MoveCaster1();
    }

    public static MoveStrategy getFlee(Enemy e) {
        e.setCurrentMoveStrat(FLEE_ID);
        return new MoveFlee();
    }

    public static MoveStrategy getIdle(Enemy e) {
        e.setCurrentMoveStrat(IDLE_ID);
        return new MoveIdle();
    }

    public static MoveStrategy getCasterTowards(Enemy e) {
        e.setCurrentMoveStrat(CASTERTOWARDS_ID);
        return new MoveCasterTowards();
    }

    public static MoveStrategy getFollow(Enemy e) {
        e.setCurrentMoveStrat(FOLLOW_ID);
        return new MoveFollow();
    }
    
}
