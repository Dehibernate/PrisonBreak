/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.LevelBuilder;

import city.soi.platform.DebugSettings;
import city.soi.platform.DebugViewer;
import game.Player;
import game.Game;
import java.awt.event.KeyAdapter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jbox2d.common.Vec2;

/**
 *
 * Key handler for player management
 */
public class LBKeyHandler extends KeyAdapter {

    /**
     * The game
     */
    private GameBuilder game;
    /**
     * The player
     */
    private Player player;
    /**
     * The debug view
     */
    private DebugViewer debugViewer;
    private boolean ctrl;

    /**
     * Constructs a key handler to manage the player
     *
     * @param g A copy of the game
     */
    public LBKeyHandler(GameBuilder g) {
        game = g;
        ctrl = false;
    }

    /**
     * Handle key press events for walking and jumping.
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {


        int code = e.getKeyCode();
        // SPACE = jump

        if (code == java.awt.event.KeyEvent.VK_CONTROL) {
            ctrl = true;
            System.out.println(ctrl);
        }
        if (code == java.awt.event.KeyEvent.VK_SPACE) {
            game.addPlatform();
        }
        if (code == java.awt.event.KeyEvent.VK_DELETE) {
            game.removePlatform();
        } else if (code == java.awt.event.KeyEvent.VK_LEFT) {
            game.cursorLeft();
        } else if (code == java.awt.event.KeyEvent.VK_UP) {
            game.cursorUp();
        } else if (code == java.awt.event.KeyEvent.VK_DOWN) {
            game.cursorDown();
        } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
            game.cursorRight();
            // F1 key toggles display of debug view
        } else if (code == java.awt.event.KeyEvent.VK_Z) {
            game.triggerZoom();
        } else if (code == java.awt.event.KeyEvent.VK_A) {
            game.triggerCursorSize(false);
        } else if (code == java.awt.event.KeyEvent.VK_S) {
            if (ctrl) {
                System.out.println("SAVE THE LEVEL");
                game.saveLevel();
            } else {
                game.triggerCursorSize(true);
            }
        } else if (code == java.awt.event.KeyEvent.VK_L) {
            String fileName=null;
            
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("PB level files (*.pbl)", "pbl"));
            fc.showOpenDialog(null);

            if (fc.getSelectedFile() == null) {
                System.out.println("No file selected");
                return;
            } else {
                fileName = fc.getSelectedFile().toString();
                System.out.println(fc.getSelectedFile());
                
            }
            game.loadCustomLevel(fileName);
            
        } else if (code == java.awt.event.KeyEvent.VK_F1) {
            if (debugViewer == null) {
                debugViewer = new DebugViewer(new DebugSettings(game.getWorld()));
            }
            if (debugViewer.isRunning()) {
                debugViewer.stopViewer();
            } else {
                debugViewer.startViewer();
            }
        }
    }

    /**
     * Handle key release events (stop walking).
     */
    public void keyReleased(java.awt.event.KeyEvent e) {

        int code = e.getKeyCode();
        if (code == java.awt.event.KeyEvent.VK_CONTROL) {
            ctrl = false;
            System.out.println(ctrl);
        }
    }
}
