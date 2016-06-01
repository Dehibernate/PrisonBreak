/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleks Ivanov
 */
public class ScoreSet implements Serializable {

    private Score[] scoreSet;

    public ScoreSet() {
        loadScoreFromFile();
    }

    public Score[] getHighScoreList() {
        return scoreSet;
    }

    public int getLowestScore() {

        for (int j = scoreSet.length - 1; j > 0; j--) {
           
            //IF ALL SCORE SPACES ARE ALREADY USED
            if (scoreSet[j] != null) {
                return scoreSet[j].getScore();
            }
            //IF THERE IS AN EMPTY SCORE SPACE
            else
            {
                return 0;
            }
        }
        return 0;
    }

    public String getListHTML() {
        String list = null;
        if (scoreSet[0] != null) {

            list = "<html><center>Top 5 Highscores: </center><br>";
            for (int i = 0; i < scoreSet.length; i++) {
                if (scoreSet[i] != null) {
                    list = list + (i + 1) + ". " + scoreSet[i].getName() + ":     " + scoreSet[i].getScore() + "<br>";
                }
            }

            list = list + "</html>";

        }
        return list;

    }

    public void addScore(Score score) {

        //ADD THE SCORES
        for (int i = 0; i < scoreSet.length - 1; i++) {
            if (scoreSet[i] == null || score.getScore() >= scoreSet[i].getScore()) {

                //Shift the lower scores down by one position
                for (int j = scoreSet.length - 1; j > i; j--) {
                    scoreSet[j] = scoreSet[j - 1];
                }
                scoreSet[i] = score;
                return;
            }
        }

    }

    public void printScores() {
        for (int i = 0; i < scoreSet.length; i++) {
            if (scoreSet[i] != null) {
                System.out.println(scoreSet[i].getName() + ": " + scoreSet[i].getScore());
            }
        }
    }

    public void saveScoreToFile() {
        
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream("scoreset.dat"));
            out.writeObject(scoreSet);
        } catch (IOException exc) {
            exc.printStackTrace();
        }



    }

    public void loadScoreFromFile() {

        ObjectInputStream in;
        File scoreFile = new File("scoreset.dat");

        if (scoreFile.exists()) {
            try {
                in = new ObjectInputStream(new FileInputStream("scoreset.dat"));
                scoreSet = (Score[]) in.readObject();

            } catch (IOException exc) {
                exc.printStackTrace();
            } catch (ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        } else {
            System.out.println("Score file not found. Creating a new one...");
            scoreSet = new Score[5];
        }
    }
}
