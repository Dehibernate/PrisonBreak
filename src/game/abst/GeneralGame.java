/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.abst;

import city.soi.platform.Body;
import city.soi.platform.DebugViewer;
import city.soi.platform.World;
import game.BgView;
import game.levels.GameLevel;
import game.levels.CustomLevel;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Aleks Ivanov
 */
public abstract class GeneralGame {

    /**
     * The World in which the game bodies move and interact.
     */
    protected World world;
    /**
     * A graphical display of the world (a specialised JPanel).
     */
    protected BgView view;
    protected JFrame frame;
    protected ArrayList<Body> persistentBodies;
    protected GameLevel currentLevel;
    /**
     * A debug display.
     */
    protected DebugViewer debugViewer;

    public GeneralGame(JFrame f) {

        // make the world
        world = new World();
        view = new BgView(this, 500, 500);
        // display the view in a frame
        persistentBodies = new ArrayList<Body>();
        frame = f;

    }

    public boolean loadCustomLevel(String fileName) {
        resetWorld();
        CustomLevel temp = new CustomLevel(this);
        return temp.loadFromFile(fileName);
    }

    // Destroy all but the persistent bodies
    public void resetWorld() {
        for (Body b : world.getBodies()) {
            boolean match = false;
            for (Body i : persistentBodies) {
                if (b == i) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                b.destroy();
            }

        }
    }

    public World getWorld() {
        return world;
    }

    public BgView getView() {
        return view;
    }
}
