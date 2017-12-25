import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;

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
    private int delay = 4;//game speed for timer

    private int ballposX = 100;
    private int ballposY = 300;
    private int ballXdir = -1;//ball moving direction
    private int ballYdir = -2;//ball moving direction

    private int playerX = 300;

    private Bricks b;

    public GameBoard(){
        b = new Bricks(3,7);
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
        g.fillOval(ballposX,ballposY,20,20);
        // the bricks
        b.draw((Graphics2D) g);
        //score board
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri",Font.BOLD,30));
        g.drawString(""+ score,590,30);

        if(ballposY>570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Calibri",Font.BOLD,30));
            g.drawString("Game Over, your score is " + score,190,300);

            g.setFont(new Font("Calibri",Font.BOLD,30));
            g.drawString("Press Enter to Restart",230,350);
        }
        if(bricks <=0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Calibri",Font.BOLD,30));
            g.drawString("You won!! Score is " + score,190,300);

            g.setFont(new Font("Calibri",Font.BOLD,30));
            g.drawString("Press Enter to Restart",230,350);
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            // A part is to deal with the ball hitting bricks
            A: for(int i=0;i<b.map.length;i++){
                for(int j=0;j<b.map[i].length;j++){
                    if(b.map[i][j]>0){
                        int brickX = j*b.width +80;
                        int brickY = i*b.height + 50;
                        int width = b.width;
                        int height = b.height;
                        Rectangle brick = new Rectangle(brickX,brickY,width,height);
                        Rectangle ball = new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect = brick;
                        // intersects
                        if(ball.intersects(brickRect)){
                            b.setBricks(0,i,j);//kill the bricks
                            bricks--;
                            score += 100;
                            //hit the bricks then flip
                            if(ballposX + 19 <= brickRect.x || ballposX +1 >=brickRect.x +brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }// A part ended
            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX<0){
                ballXdir = -ballXdir;
            }
            if(ballposY<0){
                ballYdir = -ballYdir;
            }
            if(ballposX>670){
                ballXdir = -ballXdir;
            }
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;
            }

        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {

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
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 100;
                ballposY = 300;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                bricks = 21;
                b = new Bricks(3,7);

                repaint();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerX+=30;
    }
    public void moveLeft(){
        play = true;
        playerX-=30;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
