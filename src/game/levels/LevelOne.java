/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.soi.platform.*;
import game.Game;
import game.Platform;
import game.Player;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Aleks Ivanov
 */
public class LevelOne extends GameLevel {

    private Game game;
    
    public LevelOne(Game g) {
        super(g);
        game=g;
    }

    @Override
    public void initLevel() {
        super.initLevel();
        World world = game.getWorld();
        
       
        super.addTopPlatform();
       
        //Generate a list of positions where platforms should be created.
        //The numbers vary from 0-4 and determine where platform will be created.
        int[] row1 = {0,3};
        int[] row2 = {2, 4};
        int[] row3 = {3};
        int[] row4 = {1, 4};
        int[] row5 = {0,3};
        int[] row6 = {4, 2};
        int[] row7 = {3};
        int[] row8 = {0, 4};
        int[] row9 = {1};
        int[] row10 = {4};
        int[] row11 = {0};
        int[] row12 = {2};
        int[] row13 = {4};
        int[] row14 = {1};
        int[] row15 = {4};
        int[] row16 = {2};
        int[] row17 = {0};
        int[] row18 = {2};
        int[] row19 = {0, 4};

        //Add all rows to a platform set and create platforms at the specified positions.
        int[][] platformSet = {row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19};
        for (int j = 0; j < platformSet.length; j++) {
            for (int i = 0; i < platformSet[j].length; i++) {
                Platform gp1 = new Platform(game, 80);
                gp1.setPos(new Vec2(platformSet[j][i], j));
            }
        }
    }
}
