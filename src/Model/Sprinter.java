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
	final double range = 11.0;
	final double start = 10.0;

	public double compete(String gameType) {
		double seconds;
		Random r = new Random();
		seconds = range+r.nextDouble() * start;
		return seconds;
	}
}