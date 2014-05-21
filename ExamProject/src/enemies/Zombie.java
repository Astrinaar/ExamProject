/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemies;

import Player.Player;
import Player.PlayerHandler;
import extendables.Enemy;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.MoveRegister;
import helpers.RandomTool;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Zombie extends Enemy {

    private float damage = 2;
    private Player player;
    private float attackReload = 0;
    private float slowAmount = 0.25f;
    private float slowDuration = 750;

    public Zombie(float xPos, float yPos) {
        super(xPos, yPos);
        super.texture = ImageArchive.getZombie();
        super.bounds = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        super.speed = 0.08f;
        super.pathingX = 4;
        super.pathingY = 25;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 16, 11);
        player = PlayerHandler.getPlayer();
        setMaxLife(100 + RandomTool.getRandom().nextInt(76));
        moveStrat = MoveRegister.getFollow(this);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        act(delta);
        super.update(container, game, delta);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        super.render(container, game, g);
    }

    @Override
    public void act(int delta) {
        attackReload -= 0.5f * delta;
        if (MathTool.getDistanceToPlayer(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 30 && attackReload <= 0) {
            attack();
        }
        if(!(MathTool.getDistanceToPlayer(xPos + texture.getWidth() / 2, yPos + texture.getHeight() / 2) < 25)) {
        moveStrat.move(this, delta);
        }
    }

    public void attack() {
        player.receiveDamage(damage);
        player.slow(slowAmount, slowDuration);
        attackReload = 100;
    }

}
