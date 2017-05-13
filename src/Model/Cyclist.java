package Model;
/**
 * @author Haifan Wang 
 *
 */

import java.util.Random;

public class Cyclist extends Athlete {

	public Cyclist(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
		// TODO Auto-generated constructor stub
	}

	// Define range 500~800
	final int range = 301;
	final int start = 500;

	// implement compete
	public int compete(String gameType) {
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}

}
