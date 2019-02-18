package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
class startScreen extends JComponent{
            Rectangle2D.Float r = new Rectangle2D.Float(0,0,m.dx,m.dy);
            JLabel start = new JLabel("Press ENTER to start the snake!");
            startScreen(){
                        start.setVerticalAlignment(JLabel.CENTER);
                        start.setHorizontalAlignment(JLabel.CENTER);
                        start.setSize(m.dx,m.dy);
                        add(start);
                        
            }
            public void paintComponent(Graphics co){Graphics2D g = (Graphics2D) co;g.setColor(Color.WHITE);g.fill(r);}
}