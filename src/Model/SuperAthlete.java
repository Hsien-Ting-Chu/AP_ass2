package Model;

/**
 * @author Haifan Wang
 *
 */
import java.util.Random;
import Controller.Driver;

public class SuperAthlete extends Athlete {

	public SuperAthlete(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
		// TODO Auto-generated constructor stub
	}

	private int range = 0;
	private int start = 0;
	
	private double range_d = 0.0;
	private double start_d = 0.0;
	
	public double compete(String gameType) {
		switch (gameType) {
		case Driver.RUN:
			range_d = 11.0;
			start_d = 10.0;
			break;
		case Driver.SWIM:
			range = 101;
			start = 100;
			break;
		case Driver.CYCLE:
			range = 301;
			start = 500;
			break;
		}
		if (gameType.equals(Driver.RUN)) {
			double seconds;
			Random r = new Random();
			seconds = range_d + r.nextDouble() * start_d;
			return seconds;
		} else {
			double seconds;
			Random r = new Random();
			seconds = r.nextInt(range) + start;
			return seconds;
		}
	}

}
