/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemyManagement;

import comparators.ComparatorY;
import extendables.Boss;
import extendables.Enemy;
import extendables.SlickClass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class EnemyManager implements SlickClass {

    private ArrayList<Enemy> enemies;
    private Iterator<Enemy> enemyiterator;
    private ComparatorY comparatorY;
    private EnemyProjectileManager projectileManager;

    public EnemyManager(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        comparatorY = new ComparatorY();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        projectileManager = new EnemyProjectileManager();
        projectileManager.init(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        Collections.sort(enemies, comparatorY);
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Enemy e = enemyiterator.next();
            e.render(container, game, g);
            if (e.isBoss()) {
                drawBossBars((Boss) e, g);
            } else {
                drawHealthBars(e, g);
            }
//            g.draw(e.getPathing());
        }
        projectileManager.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Enemy e = enemyiterator.next();
            e.update(container, game, delta);
            if (e.getLife() <= 0) {
                enemyiterator.remove();
            }
        }
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Enemy e = enemyiterator.next();
            for (Enemy i : enemies) {
                e.pathing(i.getPathing(), delta);
            }
        }
        projectileManager.update(container, game, delta);
    }

    public void drawHealthBars(Enemy e, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(e.getxPos() + 2, e.getyPos() - 10, e.getTexture().getWidth() - 2, 5);
        g.setColor(Color.red);
        g.fillRect(e.getxPos() + 3, e.getyPos() - 9, (e.getTexture().getWidth() - 2) * (e.getLife() / e.getMaxLife()), 3);

    }

    public void drawBossBars(Boss e, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(250, 20, 300, 25);
        g.setColor(Color.red);
        g.fillRect(251, 21, 300 * (e.getLife() / e.getMaxLife()), 23);
        if (e.isCasting()) {
            drawCastBar(e, g);
        }
    }

    public void drawCastBar(Boss e, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(300, 52, 200, 20);
        g.setColor(Color.blue);
        g.fillRect(301, 53, 198 * (1 - e.getCastingTime() / e.getCastingTimeMax()), 18);
        g.setColor(Color.white);
        g.drawString(e.getSkillName(), 340, 53);
    }

    public void SpawnProjectile(float xPos, float yPos, float angle, int id) {
        projectileManager.SpawnProjectile(xPos, yPos, angle, id);
    }

    public void reset() {
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            enemyiterator.next();
            enemyiterator.remove();
        }
        projectileManager.reset();
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        enemyiterator = this.enemies.iterator();
        while (enemyiterator.hasNext()) {
            enemyiterator.next();
            enemyiterator.remove();
        }
        for (Enemy e : enemies) {
            this.enemies.add(e);
        }
    }

}
