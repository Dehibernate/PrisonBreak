/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.LevelBuilder;

import game.pickups.Muffin;
import game.pickups.Levitron;
import game.pickups.ShockTrap;
import game.pickups.TimeWarper;
import game.abst.GeneralGame;
import city.soi.platform.*;
import java.awt.Color;

/**
 *
 * @author abpp582
 */
public class PlatformCore extends Body {

    private GeneralGame game;
//    private GameBuilder gameB;
    private PlatformDetails pDetails;
    private Body pickup;

//    public PlatformCore(GeneralGame g, float width, Vec2 pos, int pickupType) {
//        super(g.getWorld(), PolygonShape.makeBox(width / 2, 5), Body.Type.STATIC);
//        game = g;
//        pickup = null;
//        pDetails = new PlatformDetails(width, pos, pickupType);
//        addPickup();
//    }

    public PlatformCore(GeneralGame g, PlatformDetails pd) {
        super(g.getWorld(), PolygonShape.makeBox(pd.getWidth() / 2, 5), Body.Type.STATIC);
        game = g;
        pickup = null;
        pDetails = pd;
        addPickup();
    }
//
//    public PlatformCore(GameBuilder g, float width, Vec2 pos, int pickupType) {
//        super(g.getWorld(), PolygonShape.makeBox(width / 2, 5), Body.Type.STATIC);
//        gameB = g;
//        pDetails = new PlatformDetails(width, pos, pickupType);
//
//        addPickup();
//    }
//
//    public PlatformCore(GameBuilder g, PlatformDetails pd) {
//        super(g.getWorld(), PolygonShape.makeBox(pd.getWidth() / 2, 5), Body.Type.STATIC);
//        gameB = g;
//        pickup = null;
//        pDetails = pd;
//        addPickup();
//    }
    private void addPickup() {
        int pickupType = pDetails.getPickupType();
        if (pickupType == 1) {
            pickup = new TimeWarper(game);
            
          //  pickup = new Body(game.getWorld(), PolygonShape.makeBox(10, 29 / 2), Body.Type.STATIC);
           // pickup.setImage(new BodyImage("images/TimeWarper.png"));
            this.setFillColor(Color.BLUE);
        } else if (pickupType == 2) {
            pickup = new Muffin(game);
            
          //  pickup = new Body(game.getWorld(), PolygonShape.makeBox(13, 12.5f), Body.Type.STATIC);
          //  pickup.setImage(new BodyImage("images/Muffin.png"));
            this.setFillColor(Color.CYAN);
        } else if (pickupType == 3) {
            pickup=new Levitron(game);
            
          //  pickup = new Body(game.getWorld(), PolygonShape.makeBox(13, 12.5f), Body.Type.STATIC);
           // pickup.setImage(new BodyImage("images/Levitron.png"));
            this.setFillColor(Color.GREEN);
        } else if (pickupType == 4) {
           pickup = new ShockTrap(game);
            
           // pickup = new Body(game.getWorld(), PolygonShape.makeBox(12.5f, 5), Body.Type.STATIC);
          //  pickup.setImage(new BodyImage("images/ShockTrap.png"));
            this.setFillColor(Color.RED);
        } else {
            this.setFillColor(Color.GRAY);
        }

        this.setPosition(pDetails.getPosition());
        if (pickup != null) {
            pickup.putOn(this);
        }
    }

    public PlatformDetails getPlatformDetails() {
        return pDetails;
    }

    public void updatePosition() {
        pDetails.setPosition(this.getPosition());
    }

    @Override
    public void destroy() {
        super.destroy();
        if (pickup != null) {
            pickup.destroy();
        }
    }
}
