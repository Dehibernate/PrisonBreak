/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.soi.platform.*;
import game.EnergyField;
import game.Game;
import game.Platform;
import game.Player;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Aleks Ivanov
 */
public class LevelThree extends GameLevel {

    private Game game;

    public LevelThree(Game g) {
        super(g);
        game = g;
    }

    @Override
    public void initLevel() {
        super.initLevel();
        
        //Initialise the top platform, mothership and energy field, since it's last level.
        World world = game.getWorld();
        Player player = game.getPlayer();
        
        Body topPlatform = new Body(world, PolygonShape.makeBox(100, 5), Body.Type.STATIC);
        topPlatform.setPosition(new Vec2(0, 1750));
        topPlatform.setFillColor(Color.GRAY);

        Body mothership = new Body(world, new PolygonShape(-72.0f, -11.0f, -22.0f, 20.0f, -10.0f, 27.0f, 9.0f, 27.0f, 18.0f, 22.0f, 72.0f, -10.0f, 55.0f, -25.0f, -54.0f, -25.0f), Body.Type.STATIC);
        mothership.putOn(topPlatform);
        mothership.move(new Vec2(0, 50));
        mothership.setImage(new BodyImage("images/Mothership.png"));

        EnergyField energyField = new EnergyField(game);
        energyField.putOn(topPlatform);
        energyField.move(new Vec2(0, 10));

        //Generate a list of positions where platforms should be created.
        //The numbers vary from 0-4 and determine where platform will be created.
        int[] row1 = {-1, 5};
        int[] row2 = {2, 3};
        int[] row3 = {0, 5};
        int[] row4 = {2};
        int[] row5 = {4};
        int[] row6 = {3, 0};
        int[] row7 = {1};
        int[] row8 = {-1, 4};
        int[] row9 = {2};
        int[] row10 = {4};
        int[] row11 = {0, 5};
        int[] row12 = {4};
        int[] row13 = {3};
        int[] row14 = {1};
        int[] row15 = {4};
        int[] row16 = {2};
        int[] row17 = {0};
        int[] row18 = {3};
        int[] row19 = {0, 4};

        int[][] platformSet = {row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19};

        //Add all rows to a platform set and create platforms at the specified positions.
        for (int j = 0; j < platformSet.length; j++) {
            for (int i = 0; i < platformSet[j].length; i++) {
                Platform gp1 = new Platform(game, 20);
                gp1.setPos(new Vec2(platformSet[j][i], j));
            }
        }

    }
}
