/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import extendables.Entity;
import helpers.ImageArchive;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author PK
 */
public class Player extends Entity {

    private Input input;
    private Image slowedTint;
    private float reloadTime;
    private final float doubleDirectionMultiplier = 0.707114f;
    private float originalSpeed;
    private boolean slowed = false;
    private float slowDuration;
    private float slowAmount = 0;
    private PlayerHandler playerHandler;

    public Player(float xPos, float yPos, PlayerHandler playerHandler) {
        super(xPos, yPos);
        this.playerHandler = playerHandler;
    }
    

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        texture = ImageArchive.getPlayer();
        slowedTint = ImageArchive.getPlayerSlowedTint();
        super.bounds = new Rectangle(xPos + 3, yPos + 3, texture.getWidth() - 6, texture.getHeight() - 6);
        slowedTint.setAlpha(0.5f);
        super.speed = 0.13f;
        super.pathingX = 2;
        super.pathingY = 10;
        super.pathing = new Rectangle(xPos + pathingX, yPos + pathingY, 8, 10);
        originalSpeed = speed;
        setMaxLife(500);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        super.render(container, game, g);
        if (slowed) {
            slowedTint.draw(xPos, yPos);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (slowed) {
            slowDuration -= 0.5f * delta;
            if (slowDuration <= 0) {
                slowAmount = 0;
                slowed = false;
            }
        }
        if (reloadTime >= -9) {
            reloadTime -= 0.5f * delta;
        }
        reactToInput(container, delta);
        super.update(container, game, delta);
    }

    private void reactToInput(GameContainer container, int delta) {
        input = container.getInput();
        if ((input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)) && (!(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_W)) && (!(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_D))))) {
            speed = doubleDirectionMultiplier * originalSpeed * (1 - slowAmount);
        } else {
            speed = originalSpeed * (1 - slowAmount);
        }
        if (input.isKeyDown(Input.KEY_S)) {
            yPos += speed * delta;
        }
        if (input.isKeyDown(Input.KEY_W)) {
            yPos -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_D)) {
            xPos += speed * delta;
        }
        if (input.isKeyDown(Input.KEY_A)) {
            xPos -= speed * delta;
        }
    }
    
    @Override
    public void updateBounds() {
        bounds.setLocation(xPos + 3, yPos + 3);
    }

    public void slow(float amount, float duration) {
        slowed = true;
        slowAmount = amount;
        slowDuration = duration;
    }

    public void reset() {
        setxPos(387);
        setyPos(500);
        setLife(500);
        reloadTime = 400;
        slowAmount = 0;
        slowed = false;
        slowDuration = 0;
    }
}
