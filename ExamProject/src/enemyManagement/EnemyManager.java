/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enemyManagement;

import comparators.ComparatorY;
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
public class EnemyManager implements SlickClass{
    
    private ArrayList<Enemy> enemies;
    private Iterator<Enemy> enemyiterator;
    private ComparatorY comparatorY;


    public EnemyManager(ArrayList<Enemy> enemies) throws SlickException {
        this.enemies = enemies;
        comparatorY = new ComparatorY();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }


    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        Collections.sort(enemies, comparatorY);
        enemyiterator = enemies.iterator();
        while (enemyiterator.hasNext()) {
            Enemy e = enemyiterator.next();
            e.render(container, game, g);
            drawHealthBars(e, g);
//            g.draw(e.getPathing());
        }
    }

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
    }

    public void drawHealthBars(Enemy e, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(e.getxPos() + 2, e.getyPos() - 10, e.getTexture().getWidth() - 2, 5);
        g.setColor(Color.red);
        g.fillRect(e.getxPos() + 3, e.getyPos() - 9, (e.getTexture().getWidth() - 2) * (e.getLife() / e.getMaxLife()), 3);
    }
    
    

    public void reset(ArrayList<Enemy> enemies){
        setEnemies(enemies);
    }
    
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    
}
