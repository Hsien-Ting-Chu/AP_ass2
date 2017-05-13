package Model;

/**
 * @author Hsienting Chu
 *
 */
import java.util.ArrayList;
import java.util.List;
import Controller.Driver;

public abstract class Game {
	private String ID;
	private String type;
	private Official referee;
	private List<Athlete> athletes;
	private List<Athlete> resultList;

	public Game(String ID, String type, List<Athlete> athletes, Official referee) {
		this.ID = ID;
		this.type = type;
		this.referee = referee;
		this.athletes=athletes;
		
	}
	public void start(){		
		// Call each athlete in athletes list to start the game
		for(Athlete athlete : athletes){
			double result = athlete.compete(type);
			referee.rank(athlete, result);
		}
		// To summarise score of top3 winner
		referee.summarise();
		resultList = referee.getResult();
	}
	
	public List<String> getPrintResult() {
		List<String> pList = new ArrayList<String>();
		pList.add(referee.getID());
		pList.add(resultList.get(0).getID());
		pList.add(resultList.get(1).getID());
		pList.add(resultList.get(2).getID());
		return pList;
	}

	
	public String getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public Official getReferee() {
		return referee;
	}

	public List<Athlete> getAthletes() {
		return athletes;
	}

	public List<Athlete> getResult() {
		return resultList;
	}
	
}