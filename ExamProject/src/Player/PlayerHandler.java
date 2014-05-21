/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import helpers.MathTool;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class PlayerHandler {

    public static Player player;
    private Image leenFace;
    private boolean dead = false;

    public PlayerHandler() {
        player = new Player(387, 500);
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player.init(container, game);
        MathTool.setPlayer(player);
        leenFace = new Image("res/LeenFace.png");
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        player.render(container, game, g);
        drawHealthBars(g);
        drawLeenFace(g);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (player.getLife() <= 0) {
            dead = true;
        }
        player.update(container, game, delta);
    }

    public void drawHealthBars(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(200, 550, 400, 30);
        if (player.getLife() > 0) {
            g.setColor(Color.red);
            g.fillRect(201, 551, (398) * (player.getLife() / player.getMaxLife()), 28);
        }
        g.setColor(Color.white);
        if (player.getLife() > 0) {
            g.drawString((int) player.getLife() + " / " + (int) player.getMaxLife(), 370, 555);
        } else {
            g.drawString(0 + " / " + (int) player.getMaxLife(), 370, 555);
        }
    }

    public void drawLeenFace(Graphics g) {
        leenFace.draw(110, 527);
    }

    public static Player getPlayer() {
        return player;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void reset() {
        dead = false;
    }
}
