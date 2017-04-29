package Model;

import java.util.Random;

/**
 * @author Haifan Wang
 *
 */

public class SuperAthlete extends Athlete {

	public SuperAthlete(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
		// TODO Auto-generated constructor stub
	}

	private int range = 0;
	private int start = 0;

	public void getgameType(String gameType) {
		switch (gameType) {
		case "Running":
			range = 11;
			start = 10;
			break;
		case "Swinmming":
			range = 101;
			start = 100;
			break;
		case "Cycling":
			range = 301;
			start = 500;
			break;
		}

	}

	public int compete() {
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}

}
