package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * @author Charles(Chao) Ma
 * @author Karan Srivastava
 * @version Dec 5, 2012
 * 
 * View class
 */
@SuppressWarnings("serial")
public class SnakeView extends JPanel implements Observer{

   SnakeModel model;
   Snake snake;
   
   int score = 0;
   int j = 0;
    
    /**
     * @param snake
     *           the SnakeController
     */
    public SnakeView(Snake snake) {
        this.snake = snake;
    }
    
    /**
     * @param model
     *            the SnakeModel
     */
    public void setModel(SnakeModel model) {
        this.model = model;
    }
    
    

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     * 
     * The graphics to draw
     */
    public void paint(Graphics g) {
        if(model == null) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 800, 800);             
            g.setColor(Color.black);
            g.fillRect(0, 800, 800, 30);
            g.setColor(Color.white);
            g.drawString("Score: 0" , 375, 815);
            return;
        }
            int rows = model.getMaxRows();
            int cols = model.getMaxCols();
            
            int cellSizeRows = 800 / rows;
            int cellSizeCols = 800 / cols;
    
            int bodyCount = 0;
            
            for(int i = 0; i < cols; i++) {
                for(int j = 0; j < rows; j++) {
                    if(model.grid[i][j].isSnake()) {
                        bodyCount ++;
                        if(bodyCount % 3 == 0) {
                            g.setColor(Color.green);
                        }else {
                            g.setColor(Color.red);
                        }
                    }else if(model.grid[i][j].isFly()) {
                        g.setColor(Color.black);
                    }else {
                        g.setColor(Color.white);
                    }
                    g.fillRect(j * cellSizeRows, i * cellSizeCols, cellSizeRows, cellSizeCols);
                }
            }
            //g.setColor(Color.black);
            //g.fillRect(0, 800, 800, 30);
            g.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 15));
            g.setColor(Color.blue);
            g.drawString("Score: " + model.getScore(), 10, 20);

    }
    
    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     * 
     * Called when the model posts a change
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if(model.isOver()) {
            snake.gameOver();
            return;
        }
        //System.out.println("repaint");
        repaint();
    }   
}
