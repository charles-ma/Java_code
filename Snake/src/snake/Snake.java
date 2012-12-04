package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Snake extends JFrame{
    
    public static void main(String[] args) {
        new Snake().run();
    }
    
    
    //TODO: Implement!
    private void run() {
        JPanel panel = new JPanel();
        add(panel);
        pack();
        setSize(100, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(new KeyAdapter() {  // adds to this JFrame
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Up arrow pressed.");
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("Down arrow pressed.");
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Left arrow pressed.");
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Right arrow pressed.");
                }
            }
        });
    }
}