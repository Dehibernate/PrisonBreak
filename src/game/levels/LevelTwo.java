/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.soi.platform.World;
import game.Game;
import game.Platform;
import game.Player;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Aleks Ivanov
 */
public class LevelTwo extends GameLevel {

    private Game game;

    public LevelTwo(Game g) {
        super(g);
        game = g;
    }

    @Override
    public void initLevel() {
        super.initLevel();
        World world = game.getWorld();
        Player player = game.getPlayer();

        super.addTopPlatform();


        //Generate a list of positions where platforms should be created.
        //The numbers vary from 0-4 and determine where platform will be created.
        int[] row1 = {2};
        int[] row2 = {0, 4};
        int[] row3 = {2};
        int[] row4 = {3};
        int[] row5 = {1, 5};
        int[] row6 = {3};
        int[] row7 = {1, 5};
        int[] row8 = {-1, 5};
        int[] row9 = {2};
        int[] row10 = {3};
        int[] row11 = {1};
        int[] row12 = {3};
        int[] row13 = {2};
        int[] row14 = {1};
        int[] row15 = {1};
        int[] row16 = {4};
        int[] row17 = {1};
        int[] row18 = {2};
        int[] row19 = {0, 4};


        int[][] platformSet = {row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19};

        //Add all rows to a platform set and create platforms at the specified positions.
        for (int j = 0; j < platformSet.length; j++) {
            for (int i = 0; i < platformSet[j].length; i++) {
                Platform gp1 = new Platform(game, 40);
                gp1.setPos(new Vec2(platformSet[j][i], j));
            }
        }


    }
}
