/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aleks Ivanov
 */
public class TimePanel extends JPanel  {
     private Game game;
    private JLabel time;
    

    public TimePanel(Game g) {
        game = g;
        time = new JLabel("Ready when you are!");
        this.add(time);
    }
    
    public void reset(){
     time.setText("Ready when you are!");   
    }
    
    public void update(){
        int minutes = game.getPlayer().getTimeLeft()/60;
        int seconds = game.getPlayer().getTimeLeft()-(minutes*60);
        
        String format="";
        
        if(seconds<10){
             format= "0";
        }
        
       time.setText("Time left: "+minutes+":"+format+seconds);
    }
}
