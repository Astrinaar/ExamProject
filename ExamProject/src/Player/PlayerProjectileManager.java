/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import extendables.Enemy;
import extendables.Entity;
import extendables.Projectile;
import extendables.SlickClass;
import helpers.ImageArchive;
import helpers.MathTool;
import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import projectiles.Push;
import projectiles.SentryFire;
import projectiles.WeakFireball;

/**
 *
 * @author PK
 */
public class PlayerProjectileManager implements SlickClass {

    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Iterator<Projectile> projectileiterator;
    private Iterator<Enemy> enemyiterator;
    private Input input;
    private Player player;

    public static float weakFireballXOffset;
    public static float sentryFireXOffset;
    public static float sentryFireYOffset;
    public static float pushXOffset;

    public PlayerProjectileManager(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        projectiles = new ArrayList<>();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        weakFireballXOffset = ImageArchive.getWeakFireball().getWidth() / 2;
        sentryFireXOffset = ImageArchive.getArrow().getWidth() / 2;
        sentryFireYOffset = ImageArchive.getArrow().getHeight() / 2;
        pushXOffset = ImageArchive.getPush().getWidth() / 2;
        player = PlayerHandler.getPlayer();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        projectileiterator = projectiles.iterator();
        while (projectileiterator.hasNext()) {
            Projectile p = projectileiterator.next();
            p.render(container, game, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        input = container.getInput();
        projectileiterator = projectiles.iterator();
        while (projectileiterator.hasNext()) {
            Projectile p = projectileiterator.next();
            p.update(container, game, delta);
            checkCollision(p);
            if (p.getYPos() < -100 || p.getYPos() > container.getHeight() + 100 || p.getXPos() < -100 || p.getXPos() > container.getWidth() + 100 || p.getLifeTime() <= 0) {
                projectileiterator.remove();
            }
        }
    }

    public void SpawnProjectileFromPlayer(float xPos, float yPos, float angle, int id) {

        switch (id) {
            case 0:
                projectiles.add(new WeakFireball(xPos, yPos, angle));
                break;
            case 1:
                break;
            case 2:
                projectiles.add(new Push(xPos, yPos, angle));
        }
    }

    public void SpawnProjectileFromPoint(float xPosOrigin, float yPosOrigin, float xPosTarget, float yPosTarget, float angle, int id) {

        switch (id) {
            case 0:

                break;
            case 1:
                projectiles.add(new SentryFire(xPosOrigin - sentryFireXOffset, yPosOrigin - sentryFireYOffset, angle, MathTool.getAngleBetweenTwoPointsInvY(xPosOrigin, yPosOrigin, xPosTarget, yPosTarget, sentryFireXOffset), player.isPhysicalManipulationSentry(), player.isManaManipulationSentry()));
                if(player.isPhysicalManipulationSentry()){
                    projectiles.add(new SentryFire(xPosOrigin - sentryFireXOffset, yPosOrigin - sentryFireYOffset, angle - 0.25f, MathTool.getAngleBetweenTwoPointsInvY(xPosOrigin, yPosOrigin, xPosTarget, yPosTarget, sentryFireXOffset), player.isPhysicalManipulationSentry(), player.isManaManipulationSentry()));
                    projectiles.add(new SentryFire(xPosOrigin - sentryFireXOffset, yPosOrigin - sentryFireYOffset, angle + 0.25f, MathTool.getAngleBetweenTwoPointsInvY(xPosOrigin, yPosOrigin, xPosTarget, yPosTarget, sentryFireXOffset), player.isPhysicalManipulationSentry(), player.isManaManipulationSentry()));
                }
                break;
            case 2:
                break;
        }
    }
    
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    public void checkCollision(Projectile p) {
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Enemy e = enemyiterator.next();
            if (p.checkCollision(e.getBounds())) {
                p.collision(e);
            }
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void reset() {
        projectileiterator = projectiles.iterator();
        while (projectileiterator.hasNext()) {
            projectileiterator.next();
            projectileiterator.remove();
        }
    }

}
