import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * author: Jingyu Wang ( JingyuWang1988@gmail.com), Date: 2017-12-25, Time: 2:54 AM
 * Description:
 */
public class GameBoard extends JPanel implements KeyListener,ActionListener{
    private int score = 0;
    private boolean play = false;

    private int bricks = 21;

    private Timer timer;
    private int delay = 8;//game speed for timer

    private int ballposX = 100;
    private int ballposY = 300;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private int playerX = 300;

    public GameBoard(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        // the background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        // the border
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);
        // ball
        g.setColor(Color.yellow);
        g.fillRect(ballposX,ballposY,20,20);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerX+=20;
    }
    public void moveLeft(){
        play = true;
        playerX-=20;
    }
}
