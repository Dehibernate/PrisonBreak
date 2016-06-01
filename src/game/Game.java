package game;

import city.soi.platform.*;
import game.*;
import game.abst.GeneralGame;
import game.levels.GameLevel;
import game.levels.LevelOne;
import game.levels.LevelThree;
import game.levels.LevelTwo;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import org.jbox2d.common.Vec2;

/**
 * A very basic platform game.
 */
public class Game extends GeneralGame {

    /**
     * The player (a specialised Actor).
     */
    private Player player;
    /**
     * Game over flag.
     */
    private boolean isOver;
    //Level Stuff
    private int levelcounter;
    private ArrayList<GameLevel> gameLevels;
    // private GameLevel currentLevel;
    private StatePanel statePanel;
    private TimePanel timePanel;
    private ScoreSet highScores;
    private Boolean winState;
    private ObjectivesWindow Objectives;
    
    private Vec2 startPosition;
    private boolean custom;
    private String fileName;

    /**
     * Initialise a new Game.
     */
    public Game(JFrame f, String fileName) {
        super(f);
        this.fileName = fileName;
        custom = fileName != null;

        initLevels();
        initFrame();
        initSound();

        world.start();
        world.pause();
    }

    public final void initLevels() {
        isOver = false;
        highScores = new ScoreSet();
        winState = false;

        //Setup all the levels and initialise the current level if
        levelcounter = 0;
        gameLevels = new ArrayList<GameLevel>();
        gameLevels.add(new LevelOne(this));
        gameLevels.add(new LevelTwo(this));
        gameLevels.add(new LevelThree(this));
        currentLevel = gameLevels.get(levelcounter);
        currentLevel.initLevel();
        
        //If playing custom mode, only initialise the current level.
        if (custom) {
            resetWorld();
            boolean successful = loadCustomLevel(fileName);
            //boolean successful = loadCustomLevel();
            if (!successful) {
                currentLevel.initLevel();
                this.custom = false;
            }
        }

        //add the cell (must be created before player, so it would stand behind him therefore in this class)
        Body cell = new Body(world, PolygonShape.makeBox(86 / 2, 109 / 2), Body.Type.STATIC);
        cell.setGhostly(true);
        cell.setPosition(new Vec2(0, -240 + (109 / 2) + 15));
        cell.move(new Vec2(-90, 0));
        cell.setImage(new BodyImage("images/hole.png"));

        // make a player
        player = new Player(this);
        persistentBodies.add(player);
        startPosition = new Vec2(-90, -240 + 15 + 26);
        player.setPosition(startPosition);
    }

    //Initialise the sound
    public final void initSound() {

                //Sound clip music.mp3 was downloaded from the link shown below and converted to WAV format. I don't own this piece of music.
                //http://downloads.khinsider.com/game-soundtracks/album/need-for-speed-4-high-stakes-pc-rip-/03-paradigm-shifter-long-version-.mp3

                new Thread() {
                    public void run() {
                        File musicFile = new File("sound/music.mp3");
                        
                        //Loop mp3 audio file
                        while (musicFile.exists()) {
                            try {
                                javazoom.jl.player.advanced.AdvancedPlayer p = new javazoom.jl.player.advanced.AdvancedPlayer(new FileInputStream("sound/music.mp3"));
                                p.play();
                                musicFile = new File("sound/music.mp3");
                            } catch (JavaLayerException ex) {
                                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }.start();
    }

    public final void initFrame() {
        //Show the objectives and setup the state and time panels that show the score and instructions
        Objectives = new ObjectivesWindow(this);
        Objectives.setVisible(false);

        statePanel = new StatePanel(this);
        timePanel = new TimePanel(this);
    }

    //Start the game if paused
    public void start() {
        if (world.isPaused()) {
            world.unpause();
            
            frame.add(statePanel, BorderLayout.SOUTH);
            frame.add(timePanel, BorderLayout.NORTH);
            if (Objectives != null) {
                Objectives.setVisible(true);
            }
        }

    }
    
    //Remove the objectives window displayed in the beginning
    public void closeObjectives() {
        if (Objectives != null) {
            Objectives.dispose();
            Objectives = null;
        }
    }

    public void updateTimePanel() {
        timePanel.update();
    }

    /**
     * Is the game over?
     */
    public boolean isOver() {
        return isOver;
    }

    public void setWinState(boolean ws) {
        winState = ws;
    }

    /**
     * End the game.
     */
    public void gameOver() {
        /*read the previous high score form a specific file (score.dat)
         * display the high score
         * if the current score is higher than the high score then you ask the user to 
         * enter his name and use that name save the score to the same file (score.dat)
         * 
         */
        player.stopTimer();

        int lowestHS = highScores.getLowestScore();

        if (player.returnScore() >= lowestHS && !custom) {
            //Ask User for his name
            String name = (String) JOptionPane.showInputDialog(
                    frame,
                    "You have reached a new high score. Please enter your name: ",
                    "Save Score",
                    JOptionPane.PLAIN_MESSAGE);

            if (name != null && !"".equals(name)) {
                highScores.addScore(new Score(name, player.returnScore()));
                highScores.saveScoreToFile();
            }
        }

        HighScoreWindow winlose = new HighScoreWindow(this, winState);

        //Pause game at the end
        world.pause();
        isOver = true;

    }

    public ScoreSet getHighScores() {
        return highScores;
    }

    public int getLevel() {
        return levelcounter;
    }

    /**
     * The player.
     */
    public Player getPlayer() {
        return player;
    }

    //Increment level when playing standard mode
    public void changeLevel() {
        player.updateScore();

        if (levelcounter < gameLevels.size() - 1 && !custom) {
            //Increment the counter to indicate that level has finished
            levelcounter++;
            statePanel.levelChanged(false);

            //Destrtoy all bodies in the world except for the player
            for (Body b : world.getBodies()) {
                if (b != player) {
                    world.destroyBody(b);
                }
            }
            
            //Initialise the next level
            currentLevel = gameLevels.get(levelcounter);
            currentLevel.initLevel();
            timePanel.reset();
            
            
            //Put the player in the start position
            view.setCamera(new Vec2(0, 0), 1f);
            player.setPosition(startPosition);
        } else {
            //If there are no more levels, the game is won
            statePanel.levelChanged(true);
            winState = true;
            gameOver();
        }
    }
}
