/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.*;

/**
 *
 * @author Aleks Ivanov
 */
public class StatePanel extends JPanel implements PlayerStateListener {

    private Game game;
    private JLabel score;
    private JLabel levels;
    private JLabel levitrons;
    private JLabel jumpHeight;

    public StatePanel(Game g) {
        game = g;
        score = new JLabel("Score: " + g.getPlayer().returnScore());
        levels = new JLabel("Level: "+g.getLevel());
        levitrons = new JLabel("Levitrons: "+g.getPlayer().getLevitrons());
        jumpHeight = new JLabel("Jump Height: "+g.getPlayer().getJump());
        this.add(score);
        this.add(levels);
        this.add(levitrons);
        this.add(jumpHeight);
        g.getPlayer().addPlayerStateListener(this);
    }

    @Override
    public void stateChanged(Player player) {
        score.setText("Score: " + player.returnScore());
        jumpHeight.setText("Jump Height: "+game.getPlayer().getJump());
        levitrons.setText("Levitrons: "+game.getPlayer().getLevitrons());
        
    }

    public void levelChanged(Boolean gameOver) {
        
            levels.setText(("Level: " + (game.getLevel() + 1)));
        
    }
}
