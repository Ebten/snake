package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class m extends JFrame{
            static int scx = 1920, scy = 1080, dx = 800, dy = 800;
            static int sizeOfObjects = 10;
            static boolean started = false;
            static JFrame jf = new JFrame("Snake!");
            static JLayeredPane jl = new JLayeredPane();
            static pellet p = new pellet();
            static s s = new s(p);
            public static void main(String[] arguments){
                        jf.setSize(dx, dy);
                        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        jf.setContentPane(jl);
                        jf.setBackground(Color.BLACK);
                        jf.add(s);
                        jf.add(p);
                        jf.setVisible(true);
            }
}