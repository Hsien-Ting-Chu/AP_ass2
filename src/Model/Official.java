package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haifan Wang
 *
 */

public class Official extends Participants {

	ArrayList<Athlete> resultList = new ArrayList<>();
	ArrayList<Integer> scoreList = new ArrayList<>();

	public Official(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
		// TODO Auto-generated constructor stub
	}

	public void rank(Athlete athlete, int result) {
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i) > result) {
				continue;
			} else {
				resultList.add(i, athlete);
				scoreList.add(i, result);
				break;
			}
		}
		if (!resultList.contains(athlete)) {
			resultList.add(athlete);
			scoreList.add(result);
		}
	}

	public void summarise() {
		for (int rank = 1; rank <= 3; rank++) {
			Athlete athlete = resultList.get(rank);
			switch (rank) {
			case 1:
				athlete.addPoints(5);
				break;
			case 2:
				athlete.addPoints(2);
				break;
			case 3:
				athlete.addPoints(1);
				break;
			default:
				break;
			}
		}
	}

	public List<Athlete> getResult() {
		return resultList;
	}
}
