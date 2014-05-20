/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class Entity implements SlickClass {

    float xPos;
    float yPos;
    Image texture;
    Rectangle bounds;
    float life;
    float maxLife;
    float speed;
    Rectangle pathing;
    float pathingX;
    float pathingY;

    

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        texture.draw(xPos, yPos);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        enforceBorders(container);
        updateBounds();
        pathing.setLocation(xPos + pathingX, yPos + pathingY);
    }

    public void pathing(Rectangle r) {
        if (pathing.intersects(r) && !pathing.equals(r)) {
            if (pathing.getMaxY() - r.getMinY() < 4) {
                setyPos(getyPos() - (pathing.getMaxY() - r.getMinY()));
            }
            if (pathing.getMaxY() - r.getMinY() > 18) {
                setyPos(getyPos() + (r.getMaxY() - pathing.getMinY()));
            }
            if (pathing.getMaxX() - r.getMinX() < 4) {
                setxPos(getxPos() - (pathing.getMaxX() - r.getMinX()));
            }
            if (pathing.getMaxX() - r.getMinX() > 28) {
                setxPos(getxPos() + (r.getMaxX() - pathing.getMinX()));
            }

        }
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public void setMaxLife(float life) {
        this.life = life;
        maxLife = life;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
        updateBounds();
        pathing.setX(xPos + pathingX);
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
        updateBounds();
        pathing.setY(yPos + pathingY);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void updateBounds() {
        bounds.setLocation(xPos, yPos);
    }

    public Rectangle getPathing() {
        return pathing;
    }

    public void setPathing(Rectangle pathing) {
        this.pathing = pathing;
    }

    public float getSpeed() {
        return speed;
    }

    public Image getTexture() {
        return texture;
    }

    public float getMaxLife() {
        return maxLife;
    }

    public void enforceBorders(GameContainer container) {
        if (xPos < 0) {
            setxPos(0);
        } else {
            if (xPos + texture.getWidth() > container.getWidth()) {
                setxPos(container.getWidth() - texture.getWidth());
            }
        }
        if (yPos < 0) {
            setyPos(0);
        } else {
            if (yPos + texture.getHeight() > container.getHeight()) {
                setyPos(container.getHeight() - texture.getHeight());
            }
        }
    }
}
