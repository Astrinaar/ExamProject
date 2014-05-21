/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

/**
 *
 * @author PK
 */
public abstract class Enemy extends Entity {

    protected MoveStrategy moveStrat;
    protected String currentMoveStrat;

    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

    public void act(int delta) {

    }

    public String getCurrentMoveStrat() {
        return currentMoveStrat;
    }

    public void setCurrentMoveStrat(String currentMoveStrat) {
        this.currentMoveStrat = currentMoveStrat;
    }


}
