package Model;
/**
 * @author Hsienting Chu
 *
 */
import java.util.List;


public abstract class Game {
	private String ID;
	private String type;
	private Official referee;
	private boolean cancelled = false;
	private List<Athlete> athletes;
	private List<Athlete> result;

	abstract protected boolean checkIsMatch(Athlete athlete);

	public Game(String ID, String type, List<Athlete> athletes, Official referee) {
		this.ID = ID;
		this.type = type;
		this.referee = referee;
	
	}
}
