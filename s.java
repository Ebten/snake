package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
class s extends JComponent implements Runnable, KeyListener{
            // This class mainly maintains the tails that are the snake
            // The snake head that was originally imagined to be a seperate object from the tails
            // was changed into being a part of the tail, the first one, and so this class is just a thread
            // that checks to see if the x and y are equal to the pellet's x and y,
            // adds a tail in such case, and updates the chain of tails every cycle of the loop in the thread.
            Thread t;
            // Change this for a faster moving snake! the smaller the time, the quicker he moves
            int threadTime = 64;
            boolean hold = true;
            // The amount of how many tails can exist, change at your own risk! 6400 is the max
            // in an environment where the size of the objects is 10 pixels by 10 pixels and the dimensions
            // of the window are 800 x 800
            int possibleTails = 6400;
            // Setting up the coordinates for the tails to look at for their position
            int[] xtc = new int[possibleTails];
            int[] ytc = new int[possibleTails];
            // Creating the tails
            // The snake head is considered tail[0]
            tail[] tail = new tail[possibleTails];
            // Indicator for when a tail is created of which number tail it should be
            int tailNumber = 0;
            int x = 40, y = 40, sisp = m.sizeOfObjects, spx = 1, spy = 0;
            Graphics2D g;
            Rectangle2D.Float bkgd = new Rectangle2D.Float(0,0,m.scx,m.scy);
            JLabel score = new JLabel("|" + tailNumber + "|");
            Font f = new Font("Verdana",Font.BOLD,16);
            pellet tar;
            s(pellet t){
                        setSize(m.dx, m.dy);
                        for(int i = 0;i<possibleTails;i++){
                                    xtc[i] = 0;
                                    ytc[i] = 0;
                        }
                        xtc[0] = x;
                        ytc[0] = y;
                        addKeyListener(this);
                        setFocusable(true);
                        addTail();
                        setupScoreDisplay();
                        tar = t;
                        start();
            }
            void setupScoreDisplay(){
                        score.setForeground(Color.WHITE);
                        score.setSize(m.dx,m.dy);
                        score.setVerticalAlignment(JLabel.TOP);
                        score.setFont(f);
                        score.setVisible(true);
                        add(score);
            }
            void cycleTailValues(int newSnakex, int newSnakey){
                        int nsx = newSnakex, nsy = newSnakey;
                        int t1x, t1y;
                        // Updating the coordinates in the coordinate arrays
                        
                        // These seperate statements get the new snake x and y added into the array at the [0] position
                        // and set in motion the swapping of values of the array by sending the values of the old
                        // [0] position coordinates to the primary temprary variable
                        t1x = xtc[0];
                        xtc[0] = nsx;
                        t1y = ytc[0];
                        ytc[0] = nsy;
                        // updating the coordinates of all the other values in the arrays by swapping:
                        
                        // the primary temporary value from the previous switch is stored in a secondary temporaray variable
                        // the value of the array position one down from the current position is sent to the primary temporary variable
                        // The array value at the position one down is set to the secondary temporary value
                        for(int i = 0;i<possibleTails-1;i++){
                                    int t2x = t1x;
                                    t1x = xtc[i+1];
                                    xtc[i+1] = t2x;
                                    int t2y = t1y;
                                    t1y = ytc[i+1];
                                    ytc[i+1] = t2y;
                        }
                        // Attaching the coordinates from the arrays of coordinates to their respective tail, including the snake head
                        for(int i = 0;i<possibleTails;i++){
                                    if(tail[i] != null){
                                                tail[i].updateLoc(xtc[i],ytc[i]);
                                    }else{
                                                break;
                                    }
                        }
            }
            void addTail(){
                        tail[tailNumber] = new tail(xtc[tailNumber],ytc[tailNumber]);
                        add(tail[tailNumber]);
                        tailNumber++;
                        score.setText("|" + tailNumber + "|");
            }
            void start(){if(t == null){t = new Thread(this);t.start();}}
            public void run(){
                        while(hold == true){
                                    x+= (spx * sisp);
                                    y+= (spy * sisp);
                                    // if the snake head touches a pellet
                                    if(x == tar.x && y == tar.y){
                                                tar.newRandomLocation();
                                                addTail();
                                    }
                                    // Checking to see if the snake head, or tail[0], is touching any other tails
                                    // The loop variable i is initiated with 1 because it must start at tail[1]
                                    for(int i = 1;i<possibleTails;i++){
                                                if(tail[i] != null){
                                                            if(tail[0].x == tail[i].x && tail[0].y == tail[i].y){
                                                                        hold = false;
                                                                        score.setText("Snake is dead! Press ENTER to restart.");
                                                                        break;
                                                            }
                                                }else{
                                                            break;
                                                }
                                    }
                                    if(x < 0 || x >= (m.dx - (m.sizeOfObjects * 2)) || y < 0 || y >= (m.dy - (m.sizeOfObjects * 4))){
                                                hold = false;
                                                score.setText("Snake is dead! Press ENTER to restart.");
                                                break;
                                    }
                                    // cycling the tail values
                                    cycleTailValues(x,y);
                                    repaint();
                                    try{t.sleep(threadTime);}catch(Exception e){}
                        }
            }
            public void keyTyped(KeyEvent k){}
            public void keyReleased(KeyEvent k){}
            public void keyPressed(KeyEvent k){
                        if(k.getKeyChar() == KeyEvent.VK_ENTER){
                                    pellet p = new pellet();
                                    s s = new s(p);
                                    m.jf.add(p);
                                    m.jf.add(s);
                                    m.jf.remove(this);
                                    m.jf.remove(tar);
                        }else if(k.getKeyChar() == 'w' && spy != 1){
                                   spx = 0;
                                    spy = -1;
                        }
                        if(k.getKeyChar() == 'a' && spx != 1){
                                    spx = -1;
                                    spy = 0;
                        }
                        if(k.getKeyChar() == 's' && spy != -1){
                                    spx = 0;
                                    spy = 1;
                        }
                        if(k.getKeyChar() == 'd' && spx != -1){
                                    spx = 1;
                                    spy = 0;
                        }
            }
}