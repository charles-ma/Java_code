/**
 * 
 */
package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @author machao
 * @version 1.0
 */
public class Pathfinder {

	/**
	 * Main method of the project
	 * @param args system parameters
	 */
	public static void main(String[] args) {
		/*int[][] puzzle = {{7, 3, 6, 3, 2, 4, 4, 2, 2, 1}, {5, 4, 6, 3, 1, 7, 2, 5, 8 ,5}, {6, 4, 2, 1, 4, 3, 5, 
			4, 8, 1}, {1, 5, 1, 6, 7, 5, 5, 5, 3, 3}, {1, 3, 1, 3, 0, 2, 1, 5, 1, 3}, {4, 3, 4, 1, 3, 2, 4, 6, 
				1, 4}, {2, 5, 2, 2, 5, 1, 2, 5, 3, 4}, {3, 2, 2, 4, 4, 2, 1, 4, 3, 6}, {3, 1, 2, 8, 5, 5, 2, 7, 
					5, 2}, {5, 5, 4, 1, 3, 3, 4, 4, 8, 3}};*/
		int[][] puzzle = {{2, 2, 1}, {3, 1, 0}, {5, 1, 1}};
		Stack<Direction> result = null;
		result = solve(puzzle);
		if(result == null) System.out.println("Didn't find an answer!");
		else {
			while(result.size() != 0) System.out.println(result.pop());
		}
	}

	/**
	 * Method to solve the puzzle
	 * @param puzzle 2D array representing the puzzle
	 * @return a Stack<Direction> representing operations to solve the puzzle
	 */
	public static Stack<Direction> solve(int[][] puzzle) {
		Stack<Direction> result = new Stack<Direction>();
		int[][] hash = new int[puzzle.length][puzzle.length];
		for(int i = 0; i < hash.length; i++) {
			for(int j = 0; j < hash.length; j++) {
				hash[i][j] = 0;
			}
		}
		for(int i = 0; i < 4; i++) {
			if(makeMove(0, 0, result, puzzle, i, hash)) {
				Stack<Direction> modRes = new Stack<Direction>();
				while(result.size() != 0) {
					modRes.push(result.pop());
				}
				return modRes;
			}
		}
 		return null;
	}
	
	/**
	 * Recursive method to solve the puzzle. This method will 
	 * guarantee a solution to the puzzle if you have enough memory
	 * @param x the x position where the method is called
	 * @param y the y position where the method is called
	 * @param result the data structure to store all the moves
	 * @param puzzle 2D array representing the puzzle
	 * @param dir integer indicating the directions to move
	 * @param hash array to change the starting recursion direction when one position visited more than once(to avoid loop)
	 * @return whether can reach the target position
	 */
	public static boolean makeMove(int x, int y, Stack<Direction> result, int[][] puzzle, int dir, int[][] hash) {
		int step = puzzle[x][y]; 
		if(dir == 0) {
			result.push(Direction.RIGHT);
			y = y + step;
		}
		if(dir == 1) {
			result.push(Direction.DOWN);
			x = x + step;
		}
		if(dir == 2) {
			result.push(Direction.LEFT);
			y = y - step;
		}
		if(dir == 3) {
			result.push(Direction.UP);
			x = x - step;
		}
		if(x == puzzle.length - 1 && y == puzzle.length - 1) return true;
		if(x < 0 || y < 0 || x >= puzzle.length || y >= puzzle.length || puzzle[x][y] == 0) {
			result.pop();
			return false;
		}
		
		int i = hash[x][y];
		int v = i;
		v++;
		if(v >= 4) v = v - 4;
		hash[x][y] = v;
		for(int j = 0; j < 4; j++) {
			if(makeMove(x, y, result, puzzle, (i + j) % 4, hash)) return true;
		}
		result.pop();
		return false;
	}
}
