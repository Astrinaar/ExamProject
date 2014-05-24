/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiles;

import extendables.Projectile;
import helpers.ImageArchive;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author PK
 */
public class SentryFire extends Projectile{

    public SentryFire(float xPos, float yPos, float angle, float angleInvY) {
        super(xPos, yPos, angle);
        super.texture = ImageArchive.getArrow();
        bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.damage = 10;
        super.speed = 0.5f;
        super.texture.setRotation((float) Math.toDegrees(angleInvY));
    }
    
}
