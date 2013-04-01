package backtracking;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class PathfinderTest {

	@Test
	public void testSolve() {
		int[][] puzzle1 = {{1, 0}, {1, 2}}; 
		int[][] puzzle2 = {{2, 2, 1}, {3, 1, 0}, {5, 1, 1}};
		int[][] puzzle3 = {{2, 2, 1, 0 ,3}, {2, 5, 3, 1, 0}, {5, 1, 1, 3, 5}, {2, 0, 1, 2, 6}, {9, 3, 2, 6, 5}};
		Stack<Direction> result1 = null, result2 = null, result3 = null;
		result1 = Pathfinder.solve(puzzle1);
		result2 = Pathfinder.solve(puzzle2);
		result3 = Pathfinder.solve(puzzle3);
		System.out.println("Result 1:");
		while(result1.size() != 0) System.out.println(result1.pop());
		System.out.println("Result 2:");
		while(result2.size() != 0) System.out.println(result2.pop());
		System.out.println("Result 3:");
		while(result3.size() != 0) System.out.println(result3.pop());
	}
}
