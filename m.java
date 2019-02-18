package snake;
import javax.swing.*;
import java.awt.event.*;
class m extends JFrame implements KeyListener{
            static int scx = 1920, scy = 1080, dx = 800, dy = 800;
            static int sizeOfObjects = 10;
            boolean started = false;
            startScreen sc = new startScreen();
            static JLayeredPane j;
            static pellet p;
            static s snake;
            m(){
                        super("Snake!");
                        setSize(dx, dy);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        j = getLayeredPane();
                        addKeyListener(this);
                        setFocusable(true);
                        add(sc);
                        setVisible(true);
            }
            public static void main(String[] arguments){m m = new m();}
            public void keyTyped(KeyEvent k){}
            public void keyReleased(KeyEvent k){}
            public void keyPressed(KeyEvent k){
                        if(k.getKeyChar() == KeyEvent.VK_ENTER && started == false){
                                    started = true;
                                    startSnake();
                                    remove(sc);
                        }
                        if(k.getKeyChar() == 'w' && snake.spy != 1){
                                    snake.spx = 0;
                                    snake.spy = -1;
                        }
                        if(k.getKeyChar() == 'a' && snake.spx != 1){
                                    snake.spx = -1;
                                    snake.spy = 0;
                        }
                        if(k.getKeyChar() == 's' && snake.spy != -1){
                                    snake.spx = 0;
                                    snake.spy = 1;
                        }
                        if(k.getKeyChar() == 'd' && snake.spx != -1){
                                    snake.spx = 1;
                                    snake.spy = 0;
                        }
            }
            void startSnake(){
                        p = new pellet();
                        j.add(p);
                        snake = new s();
                        j.add(snake);
            }
}