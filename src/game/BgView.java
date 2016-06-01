/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.abst.GeneralGame;
import city.soi.platform.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.ImageIcon;
import org.jbox2d.common.Vec2;

/**
 *
 * @author abpp582
 */
public class BgView extends WorldView implements StepListener {

    private Game game;

    public BgView(GeneralGame g, int width, int height) {
        super(g.getWorld(), width, height);
        
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addStepListener(this);
        }
    }

    //Set game window background
    @Override
    public void paintBackground(Graphics2D g) {
        g.drawImage(new ImageIcon("images/ConcreteBG.jpg").getImage(), 0, 0, this);
    }

    //Setup vertical scrolling based on player position
    @Override
    public void postStep(StepEvent e) {
        Vec2 player = game.getPlayer().getPosition();
        Vec2 centre = getCameraCentre();

        float distance = player.y - centre.y;
        float boundary = 25;

        if (distance > boundary && centre.y <= 1750) {
            setCamera(new Vec2(0, player.y - boundary), 1f);

        } else if (distance < -240) {
            if (game.getPlayer().getLevitrons() <= 0) {
                game.setWinState(false);
                game.gameOver();
            } else {
                game.getPlayer().useLife();
            }
        }
    }

    @Override
    public void preStep(StepEvent e) {
    }
}
