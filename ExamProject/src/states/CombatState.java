/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import Player.Player;
import Player.PlayerHandler;
import enemyManagement.EnemyManager;
import extendables.*;
import helpers.ImageArchive;
import helpers.SkillHelper;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.TutorialFirstCombat;
import ui.TutorialReachedLvl10;
import ui.TutorialReachedLvl15;
import ui.TutorialReachedLvl2;
import ui.TutorialReachedLvl5;
import wiki.Wiki;

/**
 *
 * @author PK
 */
public class CombatState extends BasicGameState {

    private int id;
    private StateHandler stateHandler;
    private Image background;
    private Image bottomBorder;
    private PlayerHandler playerHandler;
    private EnemyManager enemyManager;
    private SkillHelper skillHelper;
    private static extendables.Error error;
    private Wiki wiki;
    private boolean showWiki = false;
    private boolean showLifeTooltip = false;
    private boolean showManaTooltip = false;
    private boolean paused = false;
    private boolean showTutorialFirstCombat = false;
    public static boolean showTutorialReachedLvl2 = false;
    public static boolean showTutorialReachedLvl5 = false;
    public static boolean showTutorialReachedLvl10 = false;
    public static boolean showTutorialReachedLvl15 = false;
    private Image pausedDarkener;
    public static boolean firstCombat = true;
    private TutorialFirstCombat tutorialFirstCombat;
    private TutorialReachedLvl2 tutorialReachedLvl2;
    private TutorialReachedLvl5 tutorialReachedLvl5;
    private TutorialReachedLvl10 tutorialReachedLvl10;
    private TutorialReachedLvl15 tutorialReachedLvl15;
    private boolean stopped = false;
    private float deathDelay = 150;
    private boolean showDeath = false;
    private boolean resetReady = false;
    private boolean showPauseDarkener = true;
    private boolean showVictory = false;
    private float hundredPerSec = 0.1041667f;

    private Rectangle wikiBounds;
    private Rectangle lifeBounds;
    private Rectangle manaBounds;
    private Image lifeTooltip;
    private Image manaTooltip;

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
        bottomBorder = ImageArchive.getBackgroundBottomBorder();
        pausedDarkener = ImageArchive.getBackgroundPausedDarkener();
        ArrayList<Enemy> enemies = new ArrayList<>();
        playerHandler = new PlayerHandler(enemies);
        playerHandler.init(container, game);
        enemyManager = new EnemyManager(enemies);
        enemyManager.init(container, game);
        skillHelper.setEnemies(enemies);
        skillHelper.setPlayerProjectileManager(playerHandler.getPlayerProjectileManager());
        skillHelper.setPlayer();
        skillHelper.setEnemyProjectilemanager(enemyManager.getProjectileManager());
        wiki = new Wiki(this);
        wiki.init(container, game);
        tutorialFirstCombat = new TutorialFirstCombat(this);
        tutorialFirstCombat.init(container, game);
        tutorialReachedLvl2 = new TutorialReachedLvl2(this);
        tutorialReachedLvl2.init(container, game);
        tutorialReachedLvl5 = new TutorialReachedLvl5(this);
        tutorialReachedLvl5.init(container, game);
        tutorialReachedLvl10 = new TutorialReachedLvl10(this);
        tutorialReachedLvl10.init(container, game);
        tutorialReachedLvl15 = new TutorialReachedLvl15(this);
        tutorialReachedLvl15.init(container, game);
        wikiBounds = new Rectangle(762, 554, 28, 42);
        lifeBounds = new Rectangle(120, 555, 150, 16);
        manaBounds = new Rectangle(120, 580, 150, 16);
        lifeTooltip = ImageArchive.getTooltipLife();
        manaTooltip = ImageArchive.getTooltipMana();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0);
        bottomBorder.draw(0, 550);
        skillHelper.render(container, game, g);
        enemyManager.render(container, game, g);
        playerHandler.render(container, game, g);
        renderError(container, game, g);
        renderTooltips(g);
        if (stopped) {
            if (showPauseDarkener) {
                pausedDarkener.draw(0, 0);
            }
            if (paused && !playerHandler.isDead()) {
                g.setColor(Color.white);
                g.drawString("Paused", 375, 250);
                g.drawString("Press ", 295, 300);
                g.setColor(Color.orange);
                g.drawString("Escape", 350, 300);
                g.setColor(Color.white);
                g.drawString("to unpause", 410, 300);
            }
        }
        deathRender(g);

        if (showWiki) {
            wiki.render(container, game, g);
        }
        renderTutorials(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!stopped) {
            playerHandler.update(container, game, delta);
            enemyManager.update(container, game, delta);
            skillHelper.update(container, game, delta);
            updateError(container, game, delta);
        }
        deathUpdate(delta);
        updateTutorials(container, game, delta);
        if (showWiki) {
            wiki.update(container, game, delta);
        }
        reactToInput(container);

    }

    public void updateError(GameContainer container, StateBasedGame game, int delta) {
        if (error != null) {
            if (error.getLifeTime() > 0) {
                error.update(container, game, delta);
            } else {
                error = null;
            }
        }
    }

    public void renderError(GameContainer container, StateBasedGame game, Graphics g) {
        if (error != null) {
            error.render(container, game, g);
        }
    }

    public void renderTooltips(Graphics g) {
        if (showLifeTooltip) {
            lifeTooltip.draw(120, 555 - lifeTooltip.getHeight());
        } else {
            if (showManaTooltip) {
                manaTooltip.draw(120, 580 - manaTooltip.getHeight());
            }
        }
    }

    public void updateTutorials(GameContainer container, StateBasedGame game, int delta) {
        if (showTutorialFirstCombat) {
            paused = false;
            tutorialFirstCombat.update(container, game, delta);

        } else {
            if (showTutorialReachedLvl2) {
                paused = false;
                tutorialReachedLvl2.update(container, game, delta);

            } else {
                if (showTutorialReachedLvl5) {
                    paused = false;
                    tutorialReachedLvl5.update(container, game, delta);

                } else {
                    if (showTutorialReachedLvl10) {
                        paused = false;
                        tutorialReachedLvl10.update(container, game, delta);

                    } else {
                        if (showTutorialReachedLvl15) {
                            paused = false;
                            tutorialReachedLvl15.update(container, game, delta);

                        }
                    }
                }
            }
        }

    }

    public void renderTutorials(GameContainer container, StateBasedGame game, Graphics g) {
        if (showTutorialFirstCombat) {
            tutorialFirstCombat.render(container, game, g);
        } else {
            if (showTutorialReachedLvl2) {
                tutorialReachedLvl2.render(container, game, g);

            } else {
                if (showTutorialReachedLvl5) {
                    tutorialReachedLvl5.render(container, game, g);

                } else {
                    if (showTutorialReachedLvl10) {
                        tutorialReachedLvl10.render(container, game, g);

                    } else {
                        if (showTutorialReachedLvl15) {
                            tutorialReachedLvl15.render(container, game, g);

                        }
                    }
                }
            }
        }

    }

    public void load(Image background, ArrayList<Enemy> enemies) {
        this.background = background;
        enemyManager.setEnemies(enemies);
        if (firstCombat) {
            showTutorialFirstCombat = true;
        }
        stopped = true;
        paused = true;
    }

    public void deathUpdate(int delta) {
        if (playerHandler.isDead()) {
            if (!showDeath) {
                stopped = true;
                showPauseDarkener = false;
                deathDelay -= hundredPerSec * delta;
                if (deathDelay <= 0) {
                    showDeath = true;
                    deathDelay = 150;
                    showPauseDarkener = true;
                }
            } else {
                if (!resetReady) {
                    deathDelay -= hundredPerSec * delta;
                    if (deathDelay <= 0) {
                        resetReady = true;
                        deathDelay = 150;
                    }
                }
            }
        } else {
            if (enemyManager.isDead()) {
                if (!showVictory) {
                    stopped = true;
                    showPauseDarkener = false;
                    deathDelay -= hundredPerSec * delta;
                    if (deathDelay <= 0) {
                        showVictory = true;
                        deathDelay = 150;
                        showPauseDarkener = true;
                    }
                } else {
                    if (!resetReady) {
                        deathDelay -= hundredPerSec * delta;
                        if (deathDelay <= 0) {
                            resetReady = true;
                            deathDelay = 150;
                        }
                    }
                }
            }

        }
    }

    public void deathRender(Graphics g) {
        if (showDeath || showVictory) {
            if (showDeath) {
                g.setColor(Color.red);
                g.drawString("You are dead", 335, 250);
            } else {
                if (showVictory) {
                    g.setColor(Color.white);
                    g.drawString("You are victorious!", 335, 250);
                }
            }
            if (resetReady) {
                g.setColor(Color.white);
                g.drawString("Press ", 230, 310);
                g.setColor(Color.orange);
                g.drawString("escape", 280, 310);
                g.setColor(Color.white);
                g.drawString("to return to the main menu", 340, 310);
            }
        }
    }

    public void reactToInput(GameContainer container) {
        Input input = container.getInput();
        Point p = new Point(input.getMouseX(), input.getMouseY());
        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P)) {
            if (showWiki) {
                System.out.println(showWiki);
                closeUI(0);
                System.out.println(showWiki);
            } else {
                if (showTutorialFirstCombat) {
                    closeUI(1);
                } else {
                    if (resetReady) {
                        reset();
                        stateHandler.enterState(0);
                    } else {
                        if (!paused && !playerHandler.isDead()) {
                            paused = true;
                            stopped = true;
                        } else {
                            if (!playerHandler.isDead()) {
                                paused = false;
                                stopped = false;
                            }
                        }
                    }
                }
            }

        }

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

            if (wikiBounds.contains(p)) {
                showWiki = true;
                stopped = true;
            }
        }

        if (lifeBounds.contains(p)) {
            showLifeTooltip = true;
        } else {
            showLifeTooltip = false;
            if (manaBounds.contains(p)) {
                showManaTooltip = true;
            } else {
                showManaTooltip = false;
            }
        }

    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        enemyManager.setEnemies(enemies);
    }

    public static void setError(extendables.Error error) {
        CombatState.error = error;
    }

    public static void setShowTutorialReachedLvl2(boolean showTutorialReachedLvl2) {
        firstCombat = false;
        CombatState.showTutorialReachedLvl2 = showTutorialReachedLvl2;
    }

    public static void setShowTutorialReachedLvl5(boolean showTutorialReachedLvl5) {
        firstCombat = false;
        CombatState.showTutorialReachedLvl5 = showTutorialReachedLvl5;
    }

    public static void setShowTutorialReachedLvl10(boolean showTutorialReachedLvl10) {
        firstCombat = false;
        CombatState.showTutorialReachedLvl10 = showTutorialReachedLvl10;
    }

    public static void setShowTutorialReachedLvl15(boolean showTutorialReachedLvl15) {
        firstCombat = false;
        CombatState.showTutorialReachedLvl15 = showTutorialReachedLvl15;
    }

    public void closeUI(int id) {
        switch (id) {
            case 0:
                showWiki = false;
                break;
            case 1:
                showTutorialFirstCombat = false;
                firstCombat = false;
                break;
            case 2:
                showTutorialReachedLvl2 = false;
                break;
            case 3:
                showTutorialReachedLvl5 = false;
                break;
            case 4:
                showTutorialReachedLvl10 = false;
                break;
            case 5:
                showTutorialReachedLvl15 = false;
                break;
        }
        stopped = true;
        paused = true;
        this.paused = true;

    }

    public void reset() {
        stopped = false;
        deathDelay = 150;
        showDeath = false;
        resetReady = false;
        showPauseDarkener = true;
        showVictory = false;
        paused = false;
        playerHandler.reset();
        enemyManager.reset();
        skillHelper.reset();
    }

}
