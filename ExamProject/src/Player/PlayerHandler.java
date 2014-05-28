/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import extendables.Enemy;
import extendables.Icon;
import extendables.Projectile;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.SkillHelper;
import icons.IconFireball;
import icons.IconPush;
import icons.IconSentry;
import icons.IconWiki;
import java.util.ArrayList;
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

    private static Player player;
    private Image leenFace;
    private boolean dead = false;
    private final PlayerProjectileManager projectileManager;
    private final ArrayList<Icon> icons;

    public PlayerHandler(ArrayList<Enemy> enemies) {
        player = new Player(387, 500, this);
        projectileManager = new PlayerProjectileManager(enemies);
        icons = new ArrayList<>();
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player.init(container, game);
        projectileManager.init(container, game);
        MathTool.setPlayer(player);
        leenFace = ImageArchive.getLeenFace();
        icons.add(new IconFireball());
        icons.add(new IconSentry());
        icons.add(new IconPush());
        icons.add(new IconWiki());
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        player.render(container, game, g);
        projectileManager.render(container, game, g);
        drawHealthBar(g);
        drawManaBar(g);
        drawLeenFace(g);
        drawIcons(container, game, g);
        if (player.isCasting()) {
            drawCastBar(g);
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (player.getLife() <= 0) {
            dead = true;
        }
        player.update(container, game, delta);
        projectileManager.update(container, game, delta);
        updateIcons(container, game, delta);
    }

    public void SpawnProjectileFromPlayer(float angle, int id) {
        projectileManager.SpawnProjectileFromPlayer(player.getxPos(), player.getyPos(), angle, id);
    }

    public void drawHealthBar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(120, 555, 150, 16);
        if (player.getLife() > 0) {
            g.setColor(Color.red);
            g.fillRect(121, 556, (148) * (player.getLife() / player.getMaxLife()), 14);
        }
        g.setColor(Color.white);
        if (player.getLife() > 0) {
            g.drawString((int) player.getLife() + " / " + (int) player.getMaxLife(), 150, 554);
        } else {
            g.drawString(0 + " / " + (int) player.getMaxLife(), 150, 554);
        }
    }

    public void drawManaBar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(120, 580, 150, 16);
        if (player.getMana() > 0) {
            g.setColor(Color.blue);
            g.fillRect(121, 581, (148) * (player.getMana() / player.getMaxMana()), 14);
        }
        g.setColor(Color.white);
        if (player.getMana() > 0) {
            g.drawString((int) player.getMana() + " / " + (int) player.getMaxMana(), 150, 579);
        } else {
            g.drawString(0 + " / " + (int) player.getMaxMana(), 150, 579);
        }
    }

    public void drawCastBar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(350, 537, 100, 10);
        g.setColor(Color.cyan);
        g.fillRect(351, 538, (98) * (1 - player.getCastingTime() / player.getCastingTimeMax()), 8);
//        g.setColor(Color.white);
//        g.drawString("" + player.getCastingTime(), 85, 66);
    }

    public void drawIcons(GameContainer container, StateBasedGame game, Graphics g) {
        for (Icon i : icons) {
            i.render(container, game, g);
        }

//        g.setColor(Color.white);
//        ImageArchive.getIconFireball().draw(330, 554);
//        g.drawString("1", 330, 554);
//        ImageArchive.getIconSentry().draw(372, 554);
//        g.drawString("2", 372, 554);
//        if (player.getSkill1CD() > 0) {
//            ImageArchive.getIconCDDark().draw(372, 554);
//            g.drawString("" + (int) player.getSkill1CD() / 100, 387, 565);
//        }
//        ImageArchive.getIconPush().draw(414, 554);
//        g.drawString("3", 414, 554);
//        if (player.getSkill2CD() > 0) {
//            ImageArchive.getIconCDDark().draw(414, 554);
//            g.drawString("" + (int) player.getSkill2CD() / 100, 429, 565);
//        }
    }

    public void drawLeenFace(Graphics g) {
        leenFace.draw(60, 550);
    }
    
    public void updateIcons(GameContainer container, StateBasedGame game, int delta){
        for(Icon i : icons){
            i.update(container, game, delta);
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public PlayerProjectileManager getPlayerProjectileManager() {
        return projectileManager;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void reset() {
        dead = false;
        player.reset();
        projectileManager.reset();
    }
}
