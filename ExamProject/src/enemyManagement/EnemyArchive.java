/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemyManagement;

import enemies.Zombie;
import extendables.Enemy;
import helpers.SkillHelper;
import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class EnemyArchive {

    private ArrayList<Enemy> enemies;
    private int count;
    private SkillHelper skillHelper;

    public EnemyArchive(SkillHelper skillHelper) {
        this.skillHelper = skillHelper;
    }

    public void prepareArrayList() {
        enemies = new ArrayList<>();
        count = 0;
    }
    
    public ArrayList<Enemy> getArrayList(){
        return enemies;
    } 
    
    public float getNextXPos(){
        switch(count){
            case 0:
                return 400;
            case 1:
                return 475;
            case 2:
                return 325;
            case 3:
                return 550;
            case 4:
                return 250;
            default:
                return 0;
        }
    }
    
    private float getNextYPos(){
        if(count >= 0 && count <= 4){
        return 100;
    } 
        return 0;
    }

    public void addZombie() {
        enemies.add(new Zombie(getNextXPos(), getNextYPos()));
        count++;
    }

}
