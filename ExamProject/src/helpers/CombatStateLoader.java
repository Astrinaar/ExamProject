/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import enemyManagement.EnemyArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.CombatState;

/**
 *
 * @author PK
 */
public class CombatStateLoader {
    
    private CombatState combatState;
    private EnemyArchive enemyArchive;

    public CombatStateLoader(CombatState combatState) {
        this.combatState = combatState;
    }
        
    public void init(GameContainer container, StateBasedGame game) throws SlickException{
        enemyArchive = new EnemyArchive();
    }
    
    
    public void loadCombatState(String level){
        switch(level){
            case "Rohan Grass":
                rohanGrass();
                break;
        }
    }
    
    public void rohanGrass(){
        enemyArchive.prepareArrayList();
        enemyArchive.addZombie();
        enemyArchive.addZombie();
        enemyArchive.addZombie();
        combatState.load(ImageArchive.getBackgroundGrass(), enemyArchive.getArrayList());
    }
    
}
