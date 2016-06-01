package game;

import city.soi.platform.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 * Basic players in a game.
 */
public class Player extends Actor implements ActionListener {

    /**
     * The game.
     */
    private Game game;
    private int score;
    private int jumpHeight;
    private int levitrons;
    ArrayList<PlayerStateListener> listeners;
    //TIMER METHODS
    private Timer timer;
    private int seconds;
    private int timeLimit;

    /**
     * Initialise a new player.
     *
     * @param game The game in which the player will be playing.
     */
    public Player(Game game) {
        super(game.getWorld(), PolygonShape.makeBox(1, 1));
        
        groundShape();


        this.game = game;
        setFillColor(java.awt.Color.MAGENTA);
        setLineColor(java.awt.Color.BLACK);
        setImage(new BodyImage("images/Alien.png"));

        listeners = new ArrayList<PlayerStateListener>();
        jumpHeight = 210;
        levitrons = 0;


        //Set level time limit and initialize timer
        timeLimit = 60;
        seconds = 0;
        timer = new Timer(1000, this);


    }

    public void jumpShape() {
        for (Shape s : this.getShapeList()) {
            this.removeShape(s);
        }

        this.addShape(new PolygonShape(4.0f, 19.0f, -4.0f, 19.0f, -7.0f, 14.0f, -4.0f, -9.0f, 4.0f, -9.0f, 6.0f, 15.0f));
        this.addShape(new PolygonShape(-11.0f, 19.0f, -14.0f, 18.0f, -4.0f, -4.0f, 0.0f, -3.0f, -9.0f, 19.0f));
        this.addShape(new PolygonShape(10.0f, 19.0f, 7.0f, 18.0f, 0.0f, -4.0f, 4.0f, -4.0f, 13.0f, 17.0f));
        this.addShape(new PolygonShape(8.0f, -9.0f, 4.0f, -4.0f, 0.0f, -4.0f, 0.0f, -9.0f, 6.0f, -18.0f, 11.0f, -18.0f));
        this.addShape(new PolygonShape(-10.0f, -17.0f, -5.0f, -4.0f, 0.0f, -4.0f, 0.0f, -9.0f, -5.0f, -18.0f, -10.0f, -18.0f));

        this.setImage(new BodyImage("images/alien jump.png"));
    }

    public void groundShape() {
        for (Shape s : this.getShapeList()) {
            this.removeShape(s);
        }

        //head
        this.addShape(new PolygonShape(3.0f, -8.0f, 5.0f, 21.0f, 4.0f, 25.0f, -6.0f, 25.0f, -7.0f, 21.0f, -5.0f, -8.0f));
        //left leg
        this.addShape(new PolygonShape(-5.0f, -25.0f, 0.0f, -3.0f, -5.0f, -3.0f, -10.0f, -25.0f));
        //right leg
        this.addShape(new PolygonShape(10.0f, -25.0f, 4.0f, -3.0f, -1.0f, -3.0f, 5.0f, -25.0f));
        //left arm
        this.addShape(new PolygonShape(-9.0f, -7.0f, -6.0f, 7.0f, -6.0f, 15.0f, -12.0f, 10.0f, -14.0f, -6.0f));
        //right arm
        this.addShape(new PolygonShape(9.0f, -8.0f, 4.0f, 8.0f, 4.0f, 15.0f, 9.0f, 11.0f, 13.0f, -5.0f));

        this.setImage(new BodyImage("images/alien.png"));
    }

    public void addLife() {
        levitrons++;
        update();
    }

    public int getLevitrons() {
        return levitrons;

    }

    public void useLife() {
        if (levitrons > 0) {
            levitrons--;
            this.jump(500);
            update();
        }
    }

    public void addPoints(int amount) {
        score = score + amount;
        update();
    }

    public void buffJump() {

        jumpHeight = jumpHeight + 25;


        update();
    }

    public void nerfJump() {
        if (jumpHeight > 210) {
            //cancel buff (regular)
            jumpHeight = 210;
        }

        update();
    }

    public void shockPlayer() {
        Random rand = new Random();

        int x = 100 + rand.nextInt(75);
        int y = 100 + rand.nextInt(75);

        int draw = rand.nextInt(2);

        if (draw == 0) {
            game.getPlayer().applyImpulse(new Vec2(-x, y));
        } else {
            game.getPlayer().applyImpulse(new Vec2(x, y));
            System.out.println(draw);
        }
    }

    public void setJump(int jump) {
        jumpHeight = jump;
        update();
    }

    public int getJump() {
        return jumpHeight;
    }

    public void subtractPoints(int amount) {
        if ((score - amount) < 0) {
            score = 0;
        } else {
            score = score - amount;
        }
        update();
    }

    public int returnScore() {
        return score;
    }

    public void setScore(int sc) {
        score = sc;
        update();
    }

    public void addPlayerStateListener(PlayerStateListener psl) {
        listeners.add(psl);
    }

    private void update() {
        for (PlayerStateListener psl : listeners) {
            psl.stateChanged(this);
        }
    }

    public void updateScore() {
        float multiplier = 1;
        float tl = this.timeLimit;
        float sec = this.seconds;

        if (seconds != 0) {
            multiplier = tl / (sec);
        }

        setScore(Math.round(returnScore() * multiplier));
        System.out.println("mult " + multiplier + " tl " + tl + " sec " + sec);

        timer.stop();
        timeLimit = 60;
        seconds = 0;
    }

    //TIMER METHOD
    public void addTime(int time) {
        timeLimit = timeLimit + time;
        game.updateTimePanel();
    }

    //TIMER METHOD
    @Override
    public void actionPerformed(ActionEvent e) {
        if (seconds < timeLimit) {
            seconds++;
            game.updateTimePanel();
        } else {
            game.gameOver();
            timer.stop();
        }
    }

    //TIMER METHOD
    public int getTimeLeft() {
        return (timeLimit - seconds);
    }

    //    //TIMER METHOD
    public void startTimer() {
        if (!game.getWorld().isPaused()) {
            game.closeObjectives();
            timer.start();
            game.start();
        }
    }
    
    public void stopTimer(){
        timer.stop();
    }
}
