/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MoveStrategy;

import extendables.Enemy;
import extendables.MoveStrategy;
import helpers.MathTool;
import helpers.RandomTool;

/**
 *
 * @author Patrick
 */
public class MoveCasterTowards implements MoveStrategy{

    private float xMultiplier = 0;
    private float yMultiplier = 0;

    @Override
     public void move(Enemy e, int delta) {
        float xPos = e.getxPos();
        float yPos = e.getyPos();
        float speed = e.getSpeed();
        if(xMultiplier == 0 && yMultiplier == 0){
            float angle = MathTool.getAngleToPlayer(xPos, yPos) + RandomTool.getRandom().nextFloat()*2-1;
            xMultiplier = (float) Math.sin(angle);
            yMultiplier = (float) Math.cos(angle);
        }
        
        e.setyPos(yPos += (speed * yMultiplier * delta));
        e.setxPos(xPos += (speed * xMultiplier * delta));
    }

    
}
