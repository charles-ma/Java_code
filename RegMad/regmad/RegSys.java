/**
 * 
 */
package regmad;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simulate registration system
 * @author machao
 * @version 1.0
 */
public class RegSys {
	
	private ArrayList<ArrayList<Integer>> wishList = new ArrayList<ArrayList<Integer>>(); 
	private ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>(); 
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	/**
	 * Constructor for RegSys
	 */
	public RegSys() {
		for(int i = 0; i <RegMad.sNum; i++) {
			ArrayList<Integer> wish = new ArrayList<Integer>();
			ArrayList<Integer> result = new ArrayList<Integer>();
			while(wish.size() != 3){
				Random ran = new Random();
				int courseId = ran.nextInt(12) + 1;
				int rate = ran.nextInt(10) + 1;
				if(rate < 5) courseId = courseId * 2 - 1; 
				else courseId = courseId * 2;
				boolean flag = false;
				for(int j = 0; j < wish.size(); j++) {
					if(courseId / 2 == wish.get(j) / 2) {
						flag = true;
						break;
					}
				}
				if(flag) continue;
				wish.add(courseId);
			}
			wishList.add(wish);
			resultList.add(result);
		}
		
		for(int i = 1; i <= 12; i++) {
			courses.add(new Course(100 + i));
		}
	}
	
	/**
	 * Judge whether the madness is over
	 * @return true if it is over
	 */
	public synchronized boolean isOver() {
		for(int i = 0; i < resultList.size(); i++) {
			if(resultList.get(i).size() != 3) return false;
		}
		return true;
	}
	
	/**
	 * Get the wish list of a student
	 * @param id student id
	 * @return wish list
	 */
	public ArrayList<Integer> getWish(int id) {
		return wishList.get(id - 1);
	}
	
	/**
	 * Get the final registration result of a student
	 * @param id student id
	 * @return result list
	 */
	public synchronized ArrayList<Integer> getRes(int id) {
		return resultList.get(id - 1);
	}
	
	/**
	 * Register for a course
	 * @param id student id
	 * @param courseId course id
	 * @return true if register succeeded
	 */
	public synchronized boolean register(int id, int courseId) {
		if(courses.get((courseId - 1) / 2).register(id, courseId)) {
			resultList.get(id - 1).add(courseId);
		}
		return true;
	}

	/**
	 * Drop a course
	 * @param id student id
	 * @param courseId course id
	 */
	public synchronized void drop(int id, int courseId) {
		int newId = courses.get((courseId - 1) / 2).drop(id, courseId);
		if(newId != -1) resultList.get(newId - 1).add(courseId);
	}
	
	/**
	 * Check whether a course is still available
	 * @param sId student id
	 * @param courseId course id
	 * @return true if it is available
	 */
	public synchronized boolean isAvailable(int sId, int courseId) {
		return courses.get((courseId - 1) / 2).isAvailable(sId, courseId);
	}
	
	/**
	 * Print out detailed result
	 */
	public void printRes() {
		for(int i = 0; i < resultList.size(); i++) {
			for(int j = 0; j < resultList.get(i).size(); j++) {
				System.out.print(resultList.get(i).get(j) + "/");
			}
			System.out.print("--");
		}
		System.out.println();
	}
	
	/**
	 * Print our statistical result
	 */
	public void printResult() {
		int count = 0;
		for(int i = 0; i < wishList.size(); i++) {
			int j = 0;
			for(j = 0; j < wishList.get(i).size(); j++) {
				if(wishList.get(i).get(j) != resultList.get(i).get(j)) break; 
			}
			if(j == wishList.get(i).size()) count++;
		}
		int popCourse = 0;
		int popId = 101;
		for(int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			if(c.getRegisterNum() > popCourse) {
				popId = c.getID();
				popCourse = c.getRegisterNum();
			}
			c.printStat();
		}
		
		System.out.println("\nStudent with desired three courses: " + count);
		System.out.println("Most popular course: " + popId + "(" + popCourse + " tried to register)");
	}
}

