/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiles;

import extendables.Projectile;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.RandomTool;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author PK
 */
public class WeakFireball extends Projectile{

    public WeakFireball(float xPos, float yPos, float angle) {
        super(xPos, yPos, angle);
        super.angle += (RandomTool.getRandom().nextFloat()) - 0.5f;
        super.texture = ImageArchive.getWeakFireball();
        bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.damage = 20;
        super.texture.setCenterOfRotation(7, 7);
        super.speed = 0.3f;
        super.texture.setRotation((float) Math.toDegrees(this.angle * -1 + 3.1416));
    }
    
}
