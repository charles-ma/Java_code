/**
 * 
 */
package regmad;

import java.util.ArrayList;

/**
 * Simulate the course
 * @author machao
 * @version 1.0
 */
public class Course {
	
	private int id = 0;
	private ArrayList<Integer> rosterM = new ArrayList<Integer>();
	private ArrayList<Integer> rosterE = new ArrayList<Integer>();
	private ArrayList<Integer> waitingList = new ArrayList<Integer>();
	
	/**
	 * Constructor for Course
	 * @param id course id
	 */
	public Course(int id) {
		this.id = id;
	}	
	
	/**
	 * Register this course
	 * @param sId student id
	 * @param courseId course id
	 * @return true if succeeded
	 */
	public boolean register(int sId, int courseId) {
		if(courseId % 2 == 0) {
			if(rosterE.size() < 25) rosterE.add(sId);
			else if(rosterM.size() < 25) rosterM.add(sId);
			else {
				waitingList.add(sId);
				return false;
			}
		} else {
			if(rosterM.size() < 25) rosterM.add(sId);
			else if(rosterE.size() < 25) rosterE.add(sId);
			else {
				waitingList.add(sId);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Drop a course
	 * @param sId student id
	 * @param courseId course id
	 * @return id of the student moved from waiting list to registered
	 */
	public int drop(int sId, int courseId) {
		int newId = -1;
		if(waitingList.size() != 0) {
			newId = waitingList.get(0);
			waitingList.remove(0);
		}
		for(int i = 0; i < rosterM.size(); i++) {
			if(rosterM.get(i) == sId) {
				rosterM.remove(i);
				rosterM.add(newId);
				break;
			}
		}
		for(int i = 0; i < rosterE.size(); i++) {
			if(rosterE.get(i) == sId) {
				rosterE.remove(i);
				rosterE.add(newId);
				break;
			}
		}
		return newId;
	}
	
	/**
	 * Check if a course is still available
	 * @param sId student id
	 * @param courseId course id
	 * @return true if it is available
	 */
	public boolean isAvailable(int sId, int courseId) {
		if(waitingList.size() != 0) return false;
		if(rosterE.contains(sId) || rosterM.contains(sId) || waitingList.contains(sId)) return false;
		return true;
	}
	
	/**
	 * Get total number of students tried to register for the course
	 * @return total number
	 */
	public int getRegisterNum() {
		return rosterM.size() + rosterE.size() + waitingList.size();
	}

	/**
	 * Get course id
	 * @return course id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Print out course registration detail
	 */
	public void printStat() {
		System.out.println("Course ID: " + id + " / Morning Session: " + String.format("%2d", rosterM.size()) + " / Afternoon Session: " +
				String.format("%2d", rosterE.size()));
	}
}
