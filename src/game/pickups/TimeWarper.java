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
public class TimeWarper extends Body implements CollisionListener{
    
    private Game game;
    
    public TimeWarper(GeneralGame g){
        super(g.getWorld(),new PolygonShape(9.0f,-13.5f, 9.0f,-9.5f, 2.0f,1.5f, -3.0f,1.5f, -10.0f,-10.5f, -10.0f,-13.5f), Body.Type.DYNAMIC);
        this.addShape(new PolygonShape(9.0f,14.5f, 9.0f,11.5f, 2.0f,1.5f, -3.0f,1.5f, -10.0f,11.5f, -10.0f,14.5f));
        setImage(new BodyImage("images/TimeWarper.png"));
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addCollisionListener(this);
        }
    }
    
    @Override public void collide(CollisionEvent e){
        if(e.getOtherBody()==game.getPlayer()){
            game.getPlayer().addPoints(50);
            game.getPlayer().addTime(3);
            this.destroy();
        }
    }
}
