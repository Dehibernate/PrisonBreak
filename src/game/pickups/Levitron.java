/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.pickups;

import city.soi.platform.*;
import game.Game;
import game.abst.GeneralGame;

/**
 *
 * @author abpp582
 */
public class Levitron extends Body implements CollisionListener {

    private Game game;

    public Levitron(GeneralGame g) {
        super(g.getWorld(), new PolygonShape(12.0f, -11.5f, -13.0f, -11.5f, -10.0f, 4.5f, -3.0f, 12.5f, 2.0f, 12.5f, 9.0f, 4.5f), Body.Type.DYNAMIC);
        setImage(new BodyImage("images/Levitron.png"));
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addCollisionListener(this);
        }
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == game.getPlayer()) {
            game.getPlayer().addPoints(500);
            game.getPlayer().addLife();

            this.destroy();
        }
    }
}
