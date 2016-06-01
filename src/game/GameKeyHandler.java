/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.soi.platform.DebugSettings;
import city.soi.platform.DebugViewer;
import java.awt.event.KeyAdapter;
import org.jbox2d.common.Vec2;

/**
 *
 * Key handler for player management
 */
public class GameKeyHandler extends KeyAdapter {
    
    /** The game */
    private Game game;
    /** The player */
    private Player player;
    /** The debug view */
    private DebugViewer debugViewer;
    
    /**
     * Constructs a key handler to manage the player
     * @param g A copy of the game
     */
    public GameKeyHandler(Game g){
        game = g;
        player = game.getPlayer();
    }
    
    /** Handle key press events for walking and jumping. */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (game.isOver()) {
            return;
        }
        int code = e.getKeyCode();
        // SPACE = jump
        if (code == java.awt.event.KeyEvent.VK_SPACE) {
            // only jump if player is not already jumping
            if (!player.isJumping()) {
                player.jump(player.getJump());
                player.jumpShape();
                game.getPlayer().startTimer();
            }
            player.jumpShape();
            
            
            // LEFT ARROW = walk left
        } else if (code == java.awt.event.KeyEvent.VK_LEFT) {
            player.walkLeft(150);
            game.getPlayer().startTimer();
            
            // RIGHT ARROW = walk right
        } 
        
        else if (code == java.awt.event.KeyEvent.VK_T) {
            player.setPosition(new Vec2(-50, 1750+5+26));
            
            
            // RIGHT ARROW = walk right
        } 
        
        else if (code == java.awt.event.KeyEvent.VK_V) {
           game.getPlayer().useLife();
            
           
        }
        
       
        
        else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
            player.walkRight(150);
            game.getPlayer().startTimer();
            
            // F1 key toggles display of debug view
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

    /** Handle key release events (stop walking). */
    public void keyReleased(java.awt.event.KeyEvent e) {
        if (game.isOver()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == java.awt.event.KeyEvent.VK_LEFT) {
            player.stopWalking();
        } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
            player.stopWalking();
        }
        
        else if (code == java.awt.event.KeyEvent.VK_SPACE) {
            player.groundShape();
        }
    }
}


