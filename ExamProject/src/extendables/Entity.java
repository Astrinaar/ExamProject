/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendables;

import helpers.SkillHelper;
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

    protected float xPos;
    protected float yPos;
    protected float xPosMiddle;
    protected float yPosMiddle;
    protected Image texture;
    protected Rectangle bounds;
    protected float life;
    protected float maxLife;
    protected float speed;
    protected Rectangle pathing;
    protected float pathingX;
    protected float pathingY;
    protected boolean slowed = false;
    protected float slowDuration = 0;
    protected float slowAmount = 0;
    protected boolean knockback = false;
    protected boolean stunned = false;
    protected float stunDuration = 0;
    protected float hundredPerSec = 0.1041667f;
    protected boolean boss = false;

    public Entity(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        texture.draw(xPos, yPos);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (!stunned) {
            stoppedByStun(container, delta);
        } else {
            stunDuration -= hundredPerSec * delta;
            if (stunDuration <= 0) {
                stunned = false;
            }
        }
        if (slowed) {
            slowDuration -= hundredPerSec * delta;
            if (slowDuration <= 0) {
                slowAmount = 0;
                slowed = false;
            }
        }
        enforceBorders(container);
        updateBounds();
        pathing.setLocation(xPos + pathingX, yPos + pathingY);
    }

    public void pathing(Rectangle r, int delta) {
        if (pathing.intersects(r) && !pathing.equals(r)) {
            //If this entity is above the Rectangle
            if (pathing.getMaxY() - r.getMinY() < 7) {
                setyPos(getyPos() - ((speed / 2) * delta));
            }
            //If this entity is below the Rectangle
            if (r.getMaxY() - pathing.getMinY() < 7) {
                setyPos(getyPos() + ((speed / 2) * delta));
            }
            //If this entity is left of the Rectangle
            if (pathing.getMaxX() - r.getMinX() < 7) {
                setxPos(getxPos() - ((speed / 2) * delta));
            }
            //If this entity is right of the Rectangle
            if (r.getMaxX() - pathing.getMinX() < 7) {
                setxPos(getxPos() + ((speed / 2) * delta));
            }

        }
    }

    public void stoppedByStun(GameContainer container, int delta) {

    }

    public void receiveDamage(float damage) {
        setLife(life - damage);
    }

    public void slow(float amount, float duration) {
        slowed = true;
        slowAmount = amount;
        slowDuration = duration;
    }

    public void stun(float duration) {
        stunDuration = duration;
        stunned = true;
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

    public boolean isKnockback() {
        return knockback;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setKnockback(boolean knockback) {
        this.knockback = knockback;
    }

    public float getxPos() {
        return xPos;
    }

    public float getxPosMiddle() {
        return xPosMiddle;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
        updateBounds();
        pathing.setX(xPos + pathingX);
    }

    public float getyPos() {
        return yPos;
    }

    public float getyPosMiddle() {
        return yPosMiddle;
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
        xPosMiddle = xPos + texture.getWidth() / 2;
        yPosMiddle = yPos + texture.getHeight() / 2;
    }

    public Rectangle getPathing() {
        return pathing;
    }

    public void setPathing(Rectangle pathing) {
        this.pathing = pathing;
    }

    public float getSpeed() {
        return speed * (1 - slowAmount);
    }

    public Image getTexture() {
        return texture;
    }

    public float getMaxLife() {
        return maxLife;
    }

    public boolean isBoss() {
        return boss;
    }

    public void enforceBorders(GameContainer container) {
        if (xPos < 0) {
            setxPos(0);
            if (knockback) {
                stun(200);
                knockback = false;
            }
        } else {
            if (xPos + texture.getWidth() > container.getWidth()) {
                setxPos(container.getWidth() - texture.getWidth());
                if (knockback) {
                    stun(200);
                    knockback = false;
                }
            }
        }
        if (yPos < 0) {
            setyPos(0);
            if (knockback) {
                stun(200);
                knockback = false;
            }
        } else {
            if (yPos + texture.getHeight() + 50 > container.getHeight()) {
                setyPos(container.getHeight() - texture.getHeight() - 50);
                if (knockback) {
                    stun(200);
                    knockback = false;
                }
            }
        }
    }
}
