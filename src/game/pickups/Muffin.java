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
public class Muffin extends Body implements CollisionListener{
    
    private Game game;
    
    public Muffin(GeneralGame g){
        super(g.getWorld(),new PolygonShape(8.0f,-10.5f, 12.0f,2.5f, 12.0f,5.5f, 0.0f,12.5f, -12.0f,6.5f, -12.0f,3.5f, -8.0f,-10.5f), Body.Type.DYNAMIC);
        setImage(new BodyImage("images/Muffin.png"));
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addCollisionListener(this);
        }
    }
    
    @Override public void collide(CollisionEvent e){
        if(e.getOtherBody()==game.getPlayer()){
            game.getPlayer().addPoints(100);
            game.getPlayer().buffJump();
            
            this.destroy();
        }
    }
}
