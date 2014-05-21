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
import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class PlayerProjectileManager implements SlickClass {

    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Iterator<Projectile> playeriterator;
    private Iterator<Enemy> enemyiterator;

    public PlayerProjectileManager(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        projectiles = new ArrayList<>();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        playeriterator = projectiles.iterator();
        while (playeriterator.hasNext()) {
            Projectile p = playeriterator.next();
            p.render(container, game, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        playeriterator = projectiles.iterator();
        while (playeriterator.hasNext()) {
            Projectile p = playeriterator.next();
            p.update(container, game, delta);
            checkCollision(p);
            if (p.getYPos() < -100 || p.getYPos() > container.getHeight() + 100 || p.getXPos() < -100 || p.getXPos() > container.getWidth() + 100 || p.getLifeTime() <= 0) {
                playeriterator.remove();
            }
        }
    }

    public void SpawnProjectile(float xPos, float yPos, float angle, int id) {
        switch (id) {
            case 0:
                break;
        }
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

    public void reset(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        projectiles = new ArrayList<>();
    }

}
