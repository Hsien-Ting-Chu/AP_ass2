package Model;

/**
 * @author Hsienting Chu
 *
 */
import java.util.*;
import View.Ozlympic;
import Model.*;

public abstract class Game {
	private String ID;
	private String type;
	private Official referee;
	private List<Athlete> athletes;
	private List<Athlete> result;

	public Game(String ID, String type, List<Athlete> athletes, Official referee) {
		this.ID = ID;
		this.type = type;
		this.referee = referee;
		this.athletes=athletes;
				
		
	}
	public void start(){
		
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
		return result;
	}
	
}