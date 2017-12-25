import javax.swing.JFrame;

/**
 * author: Jingyu Wang ( JingyuWang1988@gmail.com), Date: 2017-12-25, Time: 2:30 AM
 * Description:
 */
public class Breakout_Clone__DaZhuanKuai {
    public static void main (String args[]){
        JFrame jf = new JFrame();
        jf.setBounds(10,10,700,600);
        jf.setTitle("Breakout Clone");
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(new GameBoard());
    }
}
