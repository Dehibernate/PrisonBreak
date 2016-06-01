/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import city.soi.platform.*;
import game.Door;
import game.levels.GameLevel;
import game.LevelBuilder.GameBuilder;
import game.LevelBuilder.PlatformCore;
import game.LevelBuilder.PlatformDetails;
import game.abst.GeneralGame;
import java.awt.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jbox2d.common.Vec2;

/**
 * Template object for a custom level. It contains a list of platform details, 
 * which are used to create the platforms and the pickups on them.
 * 
 * @author Aleks Ivanov
 */
public class CustomLevel extends GameLevel {

    private ArrayList<PlatformDetails> pd;
    private GeneralGame game;

    
    public CustomLevel(GeneralGame g) {
        super(g);
        game = g;
           pd= new ArrayList<PlatformDetails>();
    }

    /**
     * Initialises the main parts of the level - door, player and top platform.
     * Used when building a new level in the level builder.
     */
    public void initLevel() {
        super.initLevel();
        World world = game.getWorld();
        Body topPlatform = new Body(world, PolygonShape.makeBox(100, 5), Body.Type.STATIC);
        topPlatform.setPosition(new Vec2(0, 1750));
        topPlatform.setFillColor(Color.GRAY);
        Body door = new Door(game);
        door.putOn(topPlatform);
     
    }

    /**
     * Creates each platform using the list of platform details.
     * 
     * @param pd - the list of platform details containing info for each platform.
     */
    public void initLevel(ArrayList<PlatformDetails> pd) {
        initLevel();
        for (PlatformDetails p : pd) {
            new PlatformCore(game, p);
        }
    }

    public void save(ArrayList<PlatformDetails> pd) {

        //FILE CHOOSER
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("PB level files (*.pbl)", "pbl"));
        fc.showSaveDialog(null);
        String fileName = null;
        if (fc.getSelectedFile() != null) {
            fileName = fc.getSelectedFile().toString();
        
        if (fileName != null && !fileName.contains(".pbl")) {

            fileName = fileName + ".pbl";
            fc.setSelectedFile(new File(fileName));
            System.out.println("NAME FIXED");
        } else {
            System.out.println("OK");
        }
        System.out.println(fc.getSelectedFile());
        //WRITING TO FILE
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(pd);
            System.out.println("FILE WRITTEN");
        } catch (IOException ex) {
            Logger.getLogger(GameBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{System.out.println("no file chosen");}
    }

    /**
     * Loads the platform details from a file.
     * 
     * @param fileName - the path to the file containing the platform details
     * @return - true or false, based on whether it was successful
     */
    public boolean loadFromFile(String fileName) {
        
            ObjectInputStream in;
            File file = new File(fileName);
            if (file.exists()) {
                try {
                    in = new ObjectInputStream(new FileInputStream(fileName));

                    ArrayList<PlatformDetails> tempPD = (ArrayList<PlatformDetails>) in.readObject();
                    if (pd.isEmpty()) {
                        pd = tempPD;
                    }
                    else{
                        for(PlatformDetails current: tempPD){
                            pd.add(current);
                        }
                    }
                    initLevel(pd);

                } catch (IOException exc) {
                    exc.printStackTrace();
                } catch (ClassNotFoundException exc) {
                    exc.printStackTrace();
                }

                return true;
            } else {
                System.out.println("File not found.");
                return false;
            }
    }
    
    public ArrayList<PlatformDetails> getPlatformList(){
        return pd;
    }
}
