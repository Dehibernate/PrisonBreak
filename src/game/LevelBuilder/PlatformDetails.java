/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.LevelBuilder;

import city.soi.platform.Body;
import java.io.Serializable;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Aleks Ivanov
 */
public class PlatformDetails implements Serializable {

    private float width;
    private float x;
    private float y;
    private int pickupType;

    public PlatformDetails(float w, Vec2 pos, int puT) {
       width = w;
       x = pos.x;
       y=pos.y;
       pickupType = puT;
    }



    public float getWidth() {
        return width;
    }

    public Vec2 getPosition() {
        return new Vec2(x,y);
    }

    public int getPickupType() {
        return pickupType;
    }
    
    public void setWidth(float w) {
         width=w;
    }

    public void setPosition(Vec2 pos) {
        x=pos.x;
        y=pos.y;
    }

    public void setPickupType(int puT) {
        pickupType=puT;
    }
}
