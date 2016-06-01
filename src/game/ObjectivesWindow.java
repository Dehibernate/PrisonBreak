/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aleks Ivanov
 */
public class ObjectivesWindow extends JFrame {

    public ObjectivesWindow(Game g){
        super("Objectives");
        
        
         
         
         
        //CREATE A NEW JFRAME WHICH DISPLAYS THE WIN/LOSE STATE AND THE SCORE/HIGHSCORE
            //CREATE TWO PANELS
            JPanel brief= new JPanel();
            JPanel pickups= new JPanel();
            JPanel hotkeys= new JPanel();
          
            
            //ADD A LABEL TO THE PANEL (TEXT CAN DISPLAY HIGH SCORE, CURRENT SCORE, LIVES WHATEVER...)
                
            brief.add(new JLabel("<HTML>Greetings, Captain!<br> <left> Your spaceship has crashed on a hostile planet!<br> "
                        + "You have been captured by its inhabitants and placed in an underground dungeon.<br>"
                        + "Escape as fast as you can and reach the mothership before they set off the alarm and they capture you again.<br>"
                        + "This is your only chance! Good luck!</left></HTML"));
                    
                
               
               pickups.add(new JLabel("Space snacks allow you to jump higher.", new ImageIcon("images/Muffin.png"), JLabel.CENTER));
               
               pickups.add(new JLabel("Shock traps hinder your jumps.", new ImageIcon("images/ShockTrap.png"), JLabel.CENTER));
               pickups.add(new JLabel("Time warpers give you more time.", new ImageIcon("images/TimeWarper.png"), JLabel.CENTER));
               pickups.add(new JLabel("<html>Levitrons give you a thrust when you need it (press V).<br>"
                       + "They are triggered automatically if you fall as a safety mechanism.", new ImageIcon("images/Levitron.png"), JLabel.CENTER));
               
               hotkeys.add(new JLabel("<html><center>(CONTROLS: Use the Left/Right arrow keys to move sideways, spacebar to jump and V to trigger a Levitron.)</center></html>"));
               
            //ADD THE PANEL TO THE FRAME
            this.add(brief, BorderLayout.NORTH);
            this.add(hotkeys,BorderLayout.CENTER);
            this.add(pickups,BorderLayout.SOUTH);
           
            //MAKE THE FRAME VISIBLE
            this.setVisible(true);
            //PACK THE FRAME SO IT FITS PERFECTLY
            this.pack();
            //MAKE IT NON RESIZABLE
            this.setResizable(false);
            //CALCULATE THE CENTER OF THE FIRST WINDOW (WHERE THE GAME IS RUNNING)
            //TO GET THE SCREEN RESOLUTION OF THE MONITOR

            int x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2-this.size().width/2;
            int y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-this.size().height/2;

            //MOVE THE POSITION OF THE NEW WINDOW TO THE CENTRE OF THE FIRST WINDOW
            this.setLocation(x, y);
            
            
            //MAKE THE NEW WINDOW APPEAR ALWAYS ON TOP OF ALL OTHER WINDOWS
            this.setAlwaysOnTop(true);
    }

}
