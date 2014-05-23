/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import Player.PlayerProjectileManager;
import effects.fireballGroundEffect;
import extendables.Effect;
import extendables.Enemy;
import extendables.Entity;
import extendables.Projectile;
import extendables.SlickClass;
import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import skills.Sentry;

/**
 *
 * @author PK
 */
public class SkillHelper implements SlickClass {

    private static ArrayList<Enemy> enemies;
    private static Iterator<Enemy> enemyiterator;
    private static ArrayList<Effect> effects;
    private Iterator<Effect> effectsIterator;
    private static ArrayList<Entity> entities;
    private Iterator<Entity> entitiesIterator;
    private static PlayerProjectileManager playerProjectileManager;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        effects = new ArrayList<>();
        entities = new ArrayList<>();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        effectsIterator = effects.iterator();
        while (effectsIterator.hasNext()) {
            Effect e = effectsIterator.next();
            e.render(container, game, g);
        }
        entitiesIterator = entities.iterator();
        while (entitiesIterator.hasNext()) {
            Entity e = entitiesIterator.next();
            e.render(container, game, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        effectsIterator = effects.iterator();
        while (effectsIterator.hasNext()) {
            Effect e = effectsIterator.next();
            e.update(container, game, delta);
            if (e.getLifeTime() <= 0) {
                effectsIterator.remove();
            }
        }
        entitiesIterator = entities.iterator();
        while (entitiesIterator.hasNext()) {
            Entity e = entitiesIterator.next();
            e.update(container, game, delta);
            if (e.getLife() <= 0) {
                entitiesIterator.remove();
            }
        }
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setPlayerProjectileManager(PlayerProjectileManager playerProjectileManager) {
        this.playerProjectileManager = playerProjectileManager;
    }

    public static void areaDamageExclusive(float xPos, float yPos, float radius, float damage) {
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Entity e = enemyiterator.next();
            if (MathTool.getDistanceBetweenPoints(xPos, yPos, e.getxPosMiddle(), e.getyPosMiddle()) < radius && xPos != e.getxPosMiddle() && yPos != e.getyPosMiddle()) {
                e.receiveDamage(damage);
            }
        }
    }

    public static void areaDamageInclusive(float xPos, float yPos, float radius, float damage) {
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Entity e = enemyiterator.next();
            if (MathTool.getDistanceBetweenPoints(xPos, yPos, e.getxPosMiddle(), e.getyPosMiddle()) < radius) {
                e.receiveDamage(damage);
            }
        }
    }

    public static void fireball(Entity target, float radius, float damage) {
        areaDamageExclusive(target.getxPosMiddle(), target.getyPosMiddle(), radius, damage);
        effects.add(new fireballGroundEffect(target.getxPosMiddle(), target.getyPosMiddle(), ImageArchive.getEffectScorchedGround()));
    }

    public static void sentry(float xPos, float yPos) {
        entities.add(new Sentry(xPos, yPos));
    }

    public static void sentryFire(Entity e) {
        Entity target = findTarget(e);
        if (target != null) {
            playerProjectileManager.SpawnProjectile(e.getxPosMiddle(), e.getyPosMiddle(), MathTool.getAngleBetweenTwoPoints(e.getxPosMiddle(), e.getyPosMiddle(), target.getxPosMiddle(), target.getyPosMiddle(), PlayerProjectileManager.weakFireballXOffset), 1);
        }
    }

    public static Entity findTarget(Entity e) {
        float minDistance = 9999;
        Entity closestTarget = null;
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Entity e1 = enemyiterator.next();
            float distance = MathTool.getDistanceBetweenEntities(e, e1);
            if (distance < minDistance) {
                minDistance = distance;
                closestTarget = e1;
            }
        }
        return closestTarget;
    }

}
