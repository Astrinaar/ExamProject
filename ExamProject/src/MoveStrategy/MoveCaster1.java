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
public class MoveCaster1 implements MoveStrategy {

    private float xMultiplier = 0;
    private float yMultiplier = 0;
    private float direction = 1.57f;

    @Override
    public void move(Enemy e, int delta) {
        float xPos = e.getxPos();
        float yPos = e.getyPos();
        float speed = e.getSpeed();
        if (xMultiplier == 0 && yMultiplier == 0) {
            int i = RandomTool.getRandom().nextInt(2);
            switch (i) {
                case 0:
                    break;
                case 1:
                    direction *= -1;
            }
            float angle = (MathTool.getAngleToPlayer(xPos, yPos) + RandomTool.getRandom().nextFloat() - 0.5f + direction);
            xMultiplier = (float) Math.sin(angle);
            yMultiplier = (float) Math.cos(angle);

        }

        e.setyPos(yPos += (speed * yMultiplier * delta));
        e.setxPos(xPos += (speed * xMultiplier * delta));
    }

}
