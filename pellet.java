package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
class pellet extends JComponent{
            int x = 0, y = 0, si = m.sizeOfObjects;
            Rectangle2D.Float p = new Rectangle2D.Float(x,y,si,si);
            Random r = new Random();
            pellet(){
                        setSize(m.dx,m.dy);
                        newRandomLocation();
            }
            public void paintComponent(Graphics co){
                        Graphics2D g = (Graphics2D) co;
                        p.setFrame(x,y,si,si);
                        g.setColor(Color.RED);
                        g.fill(p);
            }
            void newRandomLocation(){
                        int newx = r.nextInt(m.dx);
                        int newy = r.nextInt(m.dy);
                        System.out.println(newx + " | " + newy);
                        if(newx % m.sizeOfObjects != 0){
                                    while(newx % 10 != 0){
                                                newx--;
                                    }
                        }
                        if(newy % m.sizeOfObjects != 0){
                                    while(newy % 10 != 0){
                                                newy--;
                                    }
                        }
                        if(newx > m.dx - (m.sizeOfObjects * 16)){
                                    newx-=(m.sizeOfObjects * 16);
                        }
                        if(newy > m.dy - (m.sizeOfObjects * 16)){
                                    newy-=(m.sizeOfObjects * 16);
                        }
                        x = newx;
                        y = newy;
                        System.out.println("After Proecessing: " + newx + " | " + newy);
                        repaint();
            }
}