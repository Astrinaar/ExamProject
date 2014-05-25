/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiles;

import extendables.Enemy;
import extendables.Entity;
import extendables.Projectile;
import helpers.ImageArchive;
import helpers.SkillHelper;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author PK
 */
public class Push extends Projectile{
    
    private ArrayList<Entity> hitList;
    private float slowAmount = 0.5f;
    private float slowDuration = 350;

    public Push(float xPos, float yPos, float angle) {
        super(xPos, yPos, angle);
        super.texture = ImageArchive.getPush();
        bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.damage = 100;
        super.speed = 1f;
        super.texture.setRotation((float) Math.toDegrees(angle * -1));
        hitList = new ArrayList<>();
    }
    
    @Override
    public void collision(Entity e) {
        boolean isHit = false;
        for (Entity e2 : hitList) {

            if (e.equals(e2)) {
                isHit = true;
            }
        }
        if (!isHit) {
            SkillHelper.push(e, angle, damage, slowAmount, slowDuration);
            hitList.add(e);

        }
    }
}
