/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import city.soi.platform.*;
import game.abst.GeneralGame;
/**
 *
 * @author Aleks Ivanov
 */
public class Door extends Body implements CollisionListener{
    
    private Game game;
    
    public Door(GeneralGame g){
        super(g.getWorld(),PolygonShape.makeBox(46/2, 87/2), Body.Type.STATIC);
        this.setImage(new BodyImage("images/door.png"));
        if (g.getClass() == Game.class) {
            game = (Game) g;
            g.getWorld().addCollisionListener(this);
        }
    }
    
   
    @Override
    public void collide(CollisionEvent e){
        if(game.getClass()==Game.class){
            Game g = (Game)game;
        if(e.getOtherBody()==game.getPlayer()){
           
            g.changeLevel();
        }
        }
    }
    
}
