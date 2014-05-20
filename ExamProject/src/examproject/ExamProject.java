/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examproject;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

/**
 *
 * @author PK
 */
public class ExamProject extends StateBasedGame{
    
    public static final int MAINMENUSTATE = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        // TODO code application logic here
        AppGameContainer app = new AppGameContainer(new ExamProject("Namae"));
        app.setDisplayMode(800, 600, false);
        app.start();
    }
    
    public ExamProject (String name){
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainMenu(MAINMENUSTATE));
        getState(MAINMENUSTATE).init(container, this);
        enterState(MAINMENUSTATE);
    }
}