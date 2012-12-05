package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.lang.Thread;

public class Snake extends JFrame{
    
    private int row = 50;
    private int col = 50;
    private int pixels = 10;
    private int snakeLength = 10;

    private SnakeModel model = new SnakeModel(this.row, this.col, this.snakeLength);

    public static void main(String[] args) {
        new Snake().run();
    }
    
    
    //TODO: Implement!
    private void run() {
        SnakeView panel = new SnakeView(this.model.getGrid(), this.pixels, this.col * this.pixels, this.row * this.pixels);
        pack();
	this.setSize(500, 550);
	add(panel, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.model.addObserver(panel);
	SnakeThread st = new SnakeThread();
	st.start();

        addKeyListener(new KeyAdapter() {  // adds to this JFrame
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println(model.getSnakeL());
		    model.turnAbUp();
		}
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println(model.getSnakeL());
		    model.turnAbDown();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println(model.getSnakeL());
		    model.turnAbLeft();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println(model.getSnakeL());
		    model.turnAbRight();
                }
            }
        });
    }

    private class SnakeThread extends Thread {

	public SnakeThread(){
	    super();
	}
	
	@Override
	public void run(){
	    model.start();
	}
    }
}