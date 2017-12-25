import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

/**
 * author: Jingyu Wang ( JingyuWang1988@gmail.com), Date: 2017-12-25, Time: 3:22 AM
 * Description:
 */
public class Bricks {
    public int map[][];
    public int width,height;

    public Bricks(int row,int col){
        map = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j] = 1;
            }
        }
        width = 540/col;
        height = 150/row;
    }

    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if (map[i][j]>0){
                    //draw the white
                    g.setColor(Color.WHITE);
                    g.fillRect(j*width+80,i*height+50,width,height);
                    //draw the line
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*width+80,i*height+50,width,height);
                }
            }
        }
    }
    public void setBricks(int value,int row, int col){
        map[row][col] = value;
    }
}
