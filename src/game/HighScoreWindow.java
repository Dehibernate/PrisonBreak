/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aleks Ivanov
 */
public class HighScoreWindow extends JFrame {
    public HighScoreWindow(Game g, boolean win){
        super("Congratulations! You Win!");
        
        if(!win){
            super.setTitle("You lose. Better luck next time.");
        }
        
         String highscores = g.getHighScores().getListHTML();
         
         
        //CREATE A NEW JFRAME WHICH DISPLAYS THE WIN/LOSE STATE AND THE SCORE/HIGHSCORE
           
            //ADD A PANEL TO THAT WINDOW
            JPanel panel1= new JPanel();
            JPanel panel2= new JPanel();
            //ADD A LABEL TO THE PANEL (TEXT CAN DISPLAY HIGH SCORE, CURRENT SCORE, LIVES WHATEVER...)
            
            if(win){
                panel1.add(new JLabel("<html><center>Welcome back, Captain! You have safely reached the spaceship!<br>"
                         + "Your Score is: "+g.getPlayer().returnScore()+"</center></html>"));
                
            }
            else if(g.getPlayer().getTimeLeft()==0) 
            {
                 panel1.add(new JLabel("<HTML><center>You ran out of time.<br> You need to be quicker!<br>"
                         + "Your Score is: "+g.getPlayer().returnScore()+"</center></html>"));
            }
            else 
            {
                 panel1.add(new JLabel("<HTML>You fell to your death. <br> Better luck next time!<br>"
                         + "Your Score is: "+g.getPlayer().returnScore()+"</center></html>"));
            }
            
            panel2.add(new JLabel(highscores));
            
            //ADD THE PANEL TO THE FRAME
            this.add(panel1, BorderLayout.NORTH);
            this.add(panel2, BorderLayout.SOUTH);
            //MAKE THE FRAME VISIBLE
            this.setVisible(true);
            //PACK THE FRAME SO IT FITS PERFECTLY
            this.pack();
            //MAKE IT NON RESIZABLE
            this.setResizable(false);
            
            //CALCULATE THE CENTER OF THE FIRST WINDOW (WHERE THE GAME IS RUNNING)
            //TO GET THE SCREEN RESOLUTION OF THE MONITOR         
            
            int x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2- this.size().width/2;
            int y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-this.size().height/2;
            
            //MOVE THE POSITION OF THE NEW WINDOW TO THE CENTRE OF THE FIRST WINDOW
            this.setLocation(x, y);
            //CLOSE GAME WHEN THE SMALL WINDOW IS CLOSED
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //MAKE THE NEW WINDOW APPEAR ALWAYS ON TOP OF ALL OTHER WINDOWS
            this.setAlwaysOnTop(true);
    }
}
