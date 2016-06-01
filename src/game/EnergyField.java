/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import game.abst.GeneralGame;
import city.soi.platform.*;
import java.io.IOException;
import org.jbox2d.common.Vec2;
/**
 *
 * @author Aleks Ivanov
 */
public class EnergyField extends Body implements CollisionListener{
    
    private Game game;
    
    public EnergyField(GeneralGame g){
        super(g.getWorld(),new PolygonShape(-26.5f,-11.0f, -15.5f,-18.0f, -1.5f,-19.0f, 13.5f,-18.0f, 25.5f,-12.0f, 16.5f,20.0f, -18.5f,20.0f), Body.Type.STATIC);
        this.setImage(new BodyImage("images/energyfield.png"));
        game=(Game)g;
        if(g.getClass()==Game.class){
        g.getWorld().addCollisionListener(this);}
    }
    
   
    @Override
    public void collide(CollisionEvent e){
        if(e.getOtherBody()==game.getPlayer()){
            game.getView().setCamera(new Vec2(0,0),1f);
            game.changeLevel();
            game.getPlayer().addPoints(game.getPlayer().getLevitrons()*1000);

        }
    }
    
}
