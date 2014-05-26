/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemyManagement;

import Player.Player;
import Player.PlayerHandler;
import extendables.Projectile;
import extendables.SlickClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import projectiles.WeakFireball;

/**
 *
 * @author PK
 */
public class EnemyProjectileManager implements SlickClass {

    private Player player;
    private static ArrayList<Projectile> projectiles;
    private Iterator<Projectile> projectileIterator;

    public EnemyProjectileManager() {
        projectiles = new ArrayList<>();
    }

    public void SpawnProjectile(float xPos, float yPos, float angle, int id) {
        switch (id) {
            case 0:
                projectiles.add(new WeakFireball(xPos, yPos, angle));
                break;
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player = PlayerHandler.getPlayer();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile p = projectileIterator.next();
            p.render(container, game, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile p = projectileIterator.next();
            p.update(container, game, delta);
            checkCollision(p);
            if (p.getYPos() < -100 || p.getYPos() > container.getHeight() + 100 || p.getXPos() < -100 || p.getXPos() > container.getWidth() + 100 || p.getLifeTime() <= 0) {
                projectileIterator.remove();
            }
        }
    }

    public void checkCollision(Projectile p) {
        if (p.checkCollision(player.getBounds())) {
            p.collision(player);
        }
    }

    public void reset() {
        projectileIterator = projectiles.iterator();
        while(projectileIterator.hasNext()){
            projectileIterator.next();
            projectileIterator.remove();
        }
    }

}
