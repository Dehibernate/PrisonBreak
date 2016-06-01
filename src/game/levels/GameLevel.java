/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import game.abst.GeneralGame;
import city.soi.platform.*;
import game.Door;
import java.awt.*;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Aleks Ivanov
 */



public abstract class GameLevel {
    
    protected GeneralGame game;
    protected World world;
    
    public GameLevel(GeneralGame g){
        game=g;
        world=g.getWorld();
    }
    
    /**
     * Initialises the bare-bones of each level and adds the main platforms.
     */
    public void initLevel(){
        Body leftPillar = new Body(world, PolygonShape.makeBox(20, 1150), Body.Type.STATIC);
        leftPillar.setPosition(new Vec2(-275, 900));
        
        Body rightPillar = new Body(world, PolygonShape.makeBox(20, 1150), Body.Type.STATIC);
        rightPillar.setPosition(new Vec2(275, 900));
        
        Body ground = new Body(world, PolygonShape.makeBox(300, 15), Body.Type.STATIC);
        ground.setPosition(new Vec2(0, -240));
        ground.setFillColor(Color.GRAY);
        
        Body ceiling = new Body(world, PolygonShape.makeBox(300, 15), Body.Type.STATIC);
        ceiling.setPosition(new Vec2(0, 2000));
        
        leftPillar.setFillColor(Color.GRAY);
        rightPillar.setFillColor(Color.GRAY);
        ground.setFillColor(Color.GRAY);
        ceiling.setFillColor(Color.GRAY);
    }
    
    public void addTopPlatform(){
        Body topPlatform = new Body(world, PolygonShape.makeBox(100, 5), Body.Type.STATIC);
        topPlatform.setPosition(new Vec2(0, 1750));
        topPlatform.setFillColor(Color.GRAY);
        
        Door door = new Door(game);
        door.putOn(topPlatform);
    }
    
    
    
}
