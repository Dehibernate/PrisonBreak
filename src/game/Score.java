/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.Serializable;

/**
 *COPYRIGHT
 * @author abpp582 (Aleks Ivanov)
 */
public class Score implements Serializable {
    private String name;
    private int score;
    
    public Score(String n,int s){
        name = n;
        score = s;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return name;
    }
    
    public void addScore(int s){
        score= score+s;
    }
    
    public void subtractScore(int s){
        score= score-s;
    }
    
    public void setScore(int s)
    {
        score = s;
    }
    
    public void setName(String n){
        
    }
    
}
