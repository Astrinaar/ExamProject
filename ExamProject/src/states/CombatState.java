/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import Player.Player;
import Player.PlayerHandler;
import enemyManagement.EnemyManager;
import extendables.Enemy;
import helpers.SkillHelper;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class CombatState extends BasicGameState {

    private int id;
    private StateHandler stateHandler;
    private Image background;
    private PlayerHandler playerHandler;
    private EnemyManager enemyManager;
    private SkillHelper skillHelper;

    public CombatState(int id, StateHandler stateHandler, SkillHelper skillHelper) {
        this.id = id;
        this.stateHandler = stateHandler;
        this.skillHelper = skillHelper;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ArrayList<Enemy> enemies = new ArrayList<>();        
        playerHandler = new PlayerHandler(enemies);
        playerHandler.init(container, game);
        enemyManager = new EnemyManager(enemies);
        enemyManager.init(container, game);
        skillHelper.setEnemies(enemies);
        skillHelper.setPlayerProjectileManager(playerHandler.getPlayerProjectileManager());
        skillHelper.setPlayer();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
        skillHelper.render(container, game, g);        
        enemyManager.render(container, game, g);
        playerHandler.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        playerHandler.update(container, game, delta);
        enemyManager.update(container, game, delta);
        skillHelper.update(container, game, delta);
    }

    public void load(Image background, ArrayList<Enemy> enemies){
        this.background = background;
        enemyManager.setEnemies(enemies);
    }
    
    public void setBackground(Image background) {
        this.background = background;
    }
    
    public void setEnemies(ArrayList<Enemy> enemies){
        enemyManager.setEnemies(enemies);
    }

}
