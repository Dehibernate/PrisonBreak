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
public class ShockTrap extends Body  implements CollisionListener {
    
    private Game game;
    
    public ShockTrap(GeneralGame g){
        super(g.getWorld(),new PolygonShape(11.5f,-4.0f, -12.5f,-4.0f, -6.5f,5.0f, 5.5f,5.0f), Body.Type.DYNAMIC);
        setImage(new BodyImage("images/ShockTrap.png"));
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addCollisionListener(this);
        }
    }
    
    @Override public void collide(CollisionEvent e){
        if(e.getOtherBody()==game.getPlayer()){
            game.getPlayer().subtractPoints(100);
            game.getPlayer().nerfJump();
            game.getPlayer().shockPlayer();
            this.destroy();
        }
    }
}
