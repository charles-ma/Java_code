package snake;

import java.util.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observer;
import java.util.Observable;

public class SnakeView extends JPanel implements Observer {

    private SnakeModel model = null;
    private Cell[][] cells; 
    private int pixels;

    public SnakeView(Cell[][] cells, int pixels, int width, int length){
	this.cells = cells;
	this.pixels = pixels;
	setSize(width, length);
    }

    @Override
    public void paint(Graphics g){
	for(int i = 0; i < cells.length; ++i){
	    for(Cell cell : cells[i]){
		if(cell.isEmpty()) g.setColor(Color.white);
		if(cell.isFly()) g.setColor(Color.green);
		if(cell.isSnake()) g.setColor(Color.red);
		g.fillRect(cell.getY() * this.pixels, cell.getX() * this.pixels, this.pixels, this.pixels);
	    }
	}
    }


    public void update(Observable obs, Object arg){
	this.repaint();
    }
}
