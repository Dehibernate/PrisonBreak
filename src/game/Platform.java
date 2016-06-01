/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.pickups.Muffin;
import game.pickups.Levitron;
import game.pickups.ShockTrap;
import game.pickups.TimeWarper;
import game.abst.GeneralGame;
import city.soi.platform.*;
import java.awt.Color;
import java.util.Random;
import org.jbox2d.common.Vec2;

/**
 *
 * @author abpp582
 */
public class Platform extends Body {

    private Game game;
    private Body pickup;
    private int width;
    private float probability;
    private int draw;

    public Platform(GeneralGame g, int w) {
        super(g.getWorld(), PolygonShape.makeBox(w / 2, 5), Body.Type.STATIC);
        game = (Game) g;
        width = w;

        //RANDOMLY GENERATES A PICKUP AND PUTS IN ON THE PLATFORM BASED ON PROBABILITY         

        probability = 50 / ((game.getLevel() + 2.5f) / 2.5f);

        Random rn = new Random();

        draw = rn.nextInt(100);

        this.setFillColor(Color.GRAY);

        if (draw <= probability) {
            draw = rn.nextInt(100);
            if (draw <= 20) {
                pickup = new Muffin(g);
                this.setFillColor(Color.CYAN);
            } else if (draw > 20 && draw <= 25) {
                pickup = new Levitron(g);
                this.setFillColor(Color.GREEN);
            } else if (draw > 25 && draw <= 44) {
                pickup = new ShockTrap(g);
                this.setFillColor(Color.RED);
            } else {
                pickup = new TimeWarper(g);
                this.setFillColor(Color.BLUE);
            }
        }
        this.setPos(new Vec2(0, 0));
    }

    public void setPos(Vec2 position) {
        int worldWidth = 500;
        int worldHeight = 500;

        position.x = (-worldWidth / 2) + 50 + 40 + 80 * position.x;
        position.y = (-worldHeight / 2) + 100 + (100 * position.y);
        this.setPosition(position);
        if (pickup != null) {
            pickup.putOn(this);
        }
    }
}
