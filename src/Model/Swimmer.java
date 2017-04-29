package Model;
/**
 * @author Haifan Wang 
 *
 */

import java.util.Random;

public class Swimmer extends Athlete {

	public Swimmer(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
		// TODO Auto-generated constructor stub
	}

	// Define range 100~200
	final int range = 101;
	final int start = 100;

	// implement compete
	public int compete() {
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}

}
