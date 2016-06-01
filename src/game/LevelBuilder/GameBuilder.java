package game.LevelBuilder;

import game.levels.CustomLevel;
import city.soi.platform.*;
import game.abst.GeneralGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import org.jbox2d.common.Vec2;

//import sound
/**
 * A very basic platform game.
 */
public class GameBuilder extends GeneralGame {

    private ArrayList<PlatformDetails> platformList;
    private CustomLevel currentLevel;
    private PlatformCore cursor;
    private int pickupType;

    /**
     * Initialise a new Game.
     */
    public GameBuilder(JFrame f) {
        //Set level time limit
        super(f);

        currentLevel = new CustomLevel(this);
        currentLevel.initLevel();
        pickupType = 0;

        cursor = new PlatformCore(this, new PlatformDetails(25, new Vec2(0, -150), 0));
        cursor.setFillColor(Color.yellow);
        persistentBodies.add(cursor);
        
        platformList = currentLevel.getPlatformList();
        printArray();
        view.setCamera(view.getCameraCentre().add(new Vec2(0, 875)), 0.24f);

        // start!
        world.start();
        // world.pause();

        JPanel botPanel = new JPanel();
        botPanel.add(new JLabel("Change Platform Size: A,S;    Add platform + pickups: SPACE;   Delete platform: DELETE", JLabel.CENTER));
        f.add(botPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Toggle zoom: Z;    Save/Load level: CTRL+S/CTRL+L", JLabel.CENTER));
        f.add(topPanel, BorderLayout.NORTH);
    }

    public void triggerZoom() {
        if (view.getZoom() == 1f) {
            view.setCamera((new Vec2(0, 875)), 0.24f);
        } else {
            view.setCamera(new Vec2(0, cursor.getPosition().y + 150), 1f);
        }

    }

    public void triggerCursorSize(boolean grow) {
        float w = cursor.getPlatformDetails().getWidth();


        if (grow) {
            if (w < 65) {
                w += 10f;
            } else {
                //   w = 25;
            }
        } else if (w == 25) {
            //  w = 65;
        } else {
            w -= 10f;
        }

        cursor.getPlatformDetails().setWidth(w);
        refreshCursor(cursor.getPlatformDetails());
    }

    public void refreshCursor(PlatformDetails pd) {
        persistentBodies.remove(cursor);
        cursor.destroy();
        cursor = new PlatformCore(this, pd);
        cursor.setFillColor(Color.yellow);
        persistentBodies.add(cursor);
    }

    public void addPlatform() {
        //TEMPORARY VARIABLES STORING THE CURSOR DETAILS
        float width = cursor.getPlatformDetails().getWidth();
        Vec2 position = cursor.getPosition();
        int pickupType = cursor.getPlatformDetails().getPickupType();
        PlatformDetails newPD = new PlatformDetails(width, position, pickupType);
        //
        //PlatformDetails temp = new PlatformDetails(width, position, pickupType);

        boolean exists = false;
        for (PlatformDetails p : platformList) {
            if (p.getPosition().x == position.x && p.getPosition().y == position.y) {
                exists = true;
            }
        }

        if (!exists) {
            pickupType = 0;
            platformList.add(new PlatformDetails(width, position, pickupType));
            PlatformCore platform = new PlatformCore(this, newPD);
        } else {
            System.out.println("platform already exists");
            PlatformCore temp = getPlatformAtPos(cursor.getPosition());

            System.out.println(temp.getPlatformDetails().getWidth());

            pickupType = temp.getPlatformDetails().getPickupType() + 1;
            if (pickupType > 4) {
                pickupType = 0;
            }
            newPD.setPickupType(pickupType);
            removePlatform();
            PlatformCore platform = new PlatformCore(this, newPD);
            platformList.add(newPD);
        }
        System.out.println();
        System.out.println("PLATFORM ADDED");
        printArray();
        refreshCursor(cursor.getPlatformDetails());
    }

    public void printArray() {
        System.out.println("-------------");
        for (PlatformDetails p : platformList) {
            System.out.println("w " + p.getWidth() + " pos: " + p.getPosition() + " pickup:" + p.getPickupType());
        }
    }

    public PlatformCore getPlatformAtPos(Vec2 position) {
        for (Body b : world.getBodies()) {
            if (b != cursor && b.getPosition().x == cursor.getPosition().x && b.getPosition().y == cursor.getPosition().y) {
                return (PlatformCore) b;
            }
        }
        return null;
    }

    public void removePlatform() {

        //declare and initialise a new array to help with deleting platforms from the list
        boolean[] temp = new boolean[platformList.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = false;
        }

        printArray();

        //identify platforms to be removed
        for (int i = 0; i < platformList.size(); i++) {
            PlatformDetails pd = platformList.get(i);
            if (pd.getPosition().x == cursor.getPosition().x && pd.getPosition().y == cursor.getPosition().y) {
                System.out.println(platformList.indexOf(pd));
                temp[i] = true;
            }
        }

        //remove from platform list
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == true) {
                platformList.remove(i);
                System.out.println("removed");
            }
        }


        //physically remove from world
        for (Body b : getWorld().getBodies()) {
            if (b.getPosition().x == cursor.getPosition().x && b.getPosition().y == cursor.getPosition().y && b != cursor) {
                b.destroy();
            }
        }

        System.out.println();
        System.out.println("PLATFORM DELETED");
        printArray();

    }

    public void cursorLeft() {
        if (cursor.getPosition().x > -225) {
            cursor.move(new Vec2(-75, 0));
            cursor.updatePosition();
        }
    }

    public void cursorRight() {
        if (cursor.getPosition().x < 225) {
            cursor.move(new Vec2(75, 0));
            cursor.updatePosition();
        }
    }

    public void cursorUp() {
        if (cursor.getPosition().y < 1650) {
            cursor.move(new Vec2(0, 100));
            cursor.updatePosition();

            if (cursor.getPosition().y - view.getCameraCentre().y >= 100 && view.getZoom() == 1) {
                view.setCamera(new Vec2(0, cursor.getPosition().y - 150), 1f);
            }
        }
    }

    public void cursorDown() {
        if (cursor.getPosition().y > -150) {
            cursor.move(new Vec2(0, -100));
            cursor.updatePosition();

            if (cursor.getPosition().y - view.getCameraCentre().y < -100 && view.getZoom() == 1) {
                view.setCamera(new Vec2(0, cursor.getPosition().y + 150), 1f);
            }

        }
    }

    public void saveLevel() {
        CustomLevel cl = (CustomLevel)currentLevel;
        cl.save(platformList);
    }

    public void start() {
        world.unpause();
    }
   }
