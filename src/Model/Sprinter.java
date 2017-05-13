package Model;
/**
 * @author Haifan Wang 
 *
 */

import java.util.Random;

public class Sprinter extends Athlete {

	private Game game;
	public Sprinter(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);

	}

	// Define range 10~20
	final int range = 11;
	final int start = 10;

	public int compete(String gameType) {
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}
}