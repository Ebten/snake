package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
class tail extends JComponent{
            // This class is simply a rectangle that is managed by the thread in the snake class
            
            // The snake class updates each existing / visible tail's coordinates as it runs through its loop
            
            int x, y, sisp = m.sizeOfObjects;
            Rectangle2D.Float s = new Rectangle2D.Float(x,y,sisp,sisp);
            tail(int inpx, int inpy){
                        x = inpx;
                        y = inpy;
                        setSize(m.dx,m.dy);
            }
            public void paintComponent(Graphics co){
                        Graphics2D g = (Graphics2D) co;
                        s.setFrame(x,y,sisp,sisp);
                        g.setColor(Color.WHITE);
                        g.fill(s);
            }
            void updateLoc(int inpx, int inpy){
                        x = inpx;
                        y = inpy;
                        repaint();
            }
}