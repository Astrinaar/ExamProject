/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extendables;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public abstract class Projectile {

    protected float xPos;
    protected float yPos;
    protected Image texture;
    protected float speed;
    protected float angle;
    protected float lifeTime = 1;
    protected Rectangle bounds;
    protected float damage;

    public Projectile(float xPos, float yPos, float angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        texture.draw(xPos, yPos);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        yPos += speed * (float) Math.cos(angle) * delta;
        xPos += speed * (float) Math.sin(angle) * delta;
        bounds.setLocation(xPos, yPos);
    }

    public boolean checkCollision(Shape shape) {
        if(lifeTime == 0){
            return false;
        }
        return bounds.intersects(shape);
    }

    public void collision(Entity e) {
        if (lifeTime != 0) {
            e.receiveDamage(damage);
            lifeTime = 0;
        }
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
        bounds.setX(xPos);

    }

    public float getXPos() {
        return xPos;
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
        bounds.setY(yPos);

    }

    public float getYPos() {
        return yPos;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
