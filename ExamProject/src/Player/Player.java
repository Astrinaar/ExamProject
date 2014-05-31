/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import errors.NotEnoughMana;
import extendables.Entity;
import helpers.ImageArchive;
import helpers.MathTool;
import helpers.SkillHelper;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import states.CombatState;

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
    private final PlayerHandler playerHandler;
    private float mana;
    private float maxMana;

    private boolean sorceryFireball = true;
    private boolean physicalManipulationSentry = false;
    private boolean manaManipulationSentry = false;

    private boolean isCasting = false;
    private float castingLockCount = 0;
    private float castingTime = 0;
    private float castingTimeMax;
    private int castID;
    private float castCooldown = 50;

    private float skill1CD = 0;
    private float skill2CD = 0;

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
        setMaxMana(100);
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
        input = container.getInput();
        if (castingLockCount > 0) {
            castingLockCount -= hundredPerSec * delta;
        }
        if (reloadTime >= -9) {
            reloadTime -= 0.5f * delta;
        }
        if (castCooldown > 0) {
            castCooldown -= hundredPerSec * delta;
        }
        reduceCooldowns(delta);
        super.update(container, game, delta);
    }

    @Override
    public void stoppedByStun(GameContainer container, int delta) {
        super.stoppedByStun(container, delta);
        if (isCasting) {
            castingTime -= hundredPerSec * delta;
            if (castingTime <= 0) {
                cast();
                isCasting = false;
            }
        }
        reactToInput(container, delta);
    }

    private void reactToInput(GameContainer container, int delta) {
        if ((input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)) && (!(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_W)) && (!(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_D))))) {
            speed = doubleDirectionMultiplier * originalSpeed * (1 - slowAmount);
        } else {
            speed = originalSpeed * (1 - slowAmount);
        }
        if (input.isKeyDown(Input.KEY_S) && castingLockCount <= 0) {
            yPos += speed * delta;
            isCasting = false;
        }
        if (input.isKeyDown(Input.KEY_W) && castingLockCount <= 0) {
            yPos -= speed * delta;
            isCasting = false;
        }
        if (input.isKeyDown(Input.KEY_D) && castingLockCount <= 0) {
            xPos += speed * delta;
            isCasting = false;
        }
        if (input.isKeyDown(Input.KEY_A) && castingLockCount <= 0) {
            xPos -= speed * delta;
            isCasting = false;
        }
        if (input.isKeyDown(Input.KEY_1) && !isCasting && castCooldown <= 0) {
            useSkill(0);
        }
        if (input.isKeyDown(Input.KEY_2) && !isCasting && castCooldown <= 0 && skill1CD <= 0) {
            useSkill(1);
        }
        if (input.isKeyDown(Input.KEY_3) && !isCasting && castCooldown <= 0 && skill2CD <= 0) {
            useSkill(2);
        }
    }

    public void reduceCooldowns(int delta) {
        if (skill1CD > 0) {
            skill1CD -= hundredPerSec * delta;
        }
        if (skill2CD > 0) {
            skill2CD -= hundredPerSec * delta;
        }
    }

    public void useSkill(int id) {
        switch (id) {
            case 0:
                //Fireball
                isCasting = true;
                castingLockCount = 50;
                castingTime = 100;
                castingTimeMax = 100;
                castID = 0;
                castCooldown = 50;
                break;
            case 1:
                //Sentry
                SkillHelper.sentry(input.getMouseX(), input.getMouseY());
                castCooldown = 50;
                skill1CD = 600;
                break;
            case 2:
                //Push
                if (useMana(50)) {
                    playerHandler.SpawnProjectileFromPlayer(MathTool.getAngle(input, PlayerProjectileManager.pushXOffset), id);
                    castCooldown = 50;
                    skill2CD = 500;
                    break;
                }
        }
    }

    public void cast() {
        playerHandler.SpawnProjectileFromPlayer(MathTool.getAngle(input, PlayerProjectileManager.weakFireballXOffset), castID);
    }

    @Override
    public void stun(float duration) {
        super.stun(duration);
        isCasting = false;
        castingTime = 0;
        castingTimeMax = 0;
    }

    @Override
    public void updateBounds() {
        bounds.setLocation(xPos + 3, yPos + 3);
    }

    public void setMaxMana(float mana) {
        this.mana = mana;
        maxMana = mana;
    }

    public void reset() {
        setxPos(387);
        setyPos(500);
        setLife(500);
        setMaxMana(100);
        reloadTime = 400;
        slowAmount = 0;
        slowed = false;
        slowDuration = 0;
        isCasting = false;
        castingLockCount = 0;
        castingTime = 0;
        stunned = false;
        knockback = false;
        skill1CD = 0;
        skill2CD = 0;
    }

    public boolean useMana(float mana) {
        if (mana <= this.mana) {
            this.mana -= mana;
            return true;
        } else {
            CombatState.setError(new NotEnoughMana());
            return false;
        }
    }

    public boolean isCasting() {
        return isCasting;
    }

    public float getCastingTime() {
        return castingTime;
    }

    public float getCastingTimeMax() {
        return castingTimeMax;
    }

    public float getMana() {
        return mana;
    }

    public float getMaxMana() {
        return maxMana;
    }

    public float getSkill1CD() {
        return skill1CD;
    }

    public float getSkill2CD() {
        return skill2CD;
    }

    public boolean isPhysicalManipulationSentry() {
        return physicalManipulationSentry;
    }

    public boolean isManaManipulationSentry() {
        return manaManipulationSentry;
    }

    public boolean isSorceryFireball() {
        return sorceryFireball;
    }

}
