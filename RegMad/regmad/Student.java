/**
 * 
 */
package regmad;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simulate students
 * @author machao
 * @version 1.0
 */
public class Student implements Runnable{
	private int id = 0;
	private RegSys sys = null;
	private Random ran = new Random();
	private boolean waiting = false;
	
	/**
	 * Constructor for Student
	 * @param id student id
	 * @param sys registration system
	 */
	public Student(int id, RegSys sys) {
		this.id = id;
		this.sys = sys;
	}

	@Override
	public void run() {
		ArrayList<Integer> wish = sys.getWish(id);
		for(int i = 0; i < wish.size(); i++) {
			if(!sys.register(id, wish.get(i))) waiting = true;
		}
		while(true) {
			boolean sat = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			if(sys.getRes(id).size() < 3) {
				sat = false;
				if(!sat) {
					int rate = ran.nextInt(100);
					if(rate < 20) {
						ArrayList<Integer> re = sys.getRes(id);
						for(int i = 0; i < re.size(); i++) {
							if(!sys.getWish(id).contains(re.get(i))) sys.drop(id, re.get(i));
						}
					}
				}
				int ranNum = ran.nextInt(24) + 1;
				if(!sys.getWish(id).contains(ranNum)) {
					if(sys.isAvailable(id, ranNum)) {
						sys.register(id, ranNum);
					}
				}
			} else if(sys.getRes(id).size() > 3) {
				ArrayList<Integer> re = sys.getRes(id);
				for(int i = 0; i < re.size(); i++) {
					if(!sys.getWish(id).contains(re.get(i))) sys.drop(id, re.get(i));
				}
			} else {
				if(!waiting) break;
			}
		}
	}
}
