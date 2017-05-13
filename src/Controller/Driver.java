package Controller;

/**
 * @author Hsienting Chu
 *
 */
import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

import Model.*;

public class Driver {
	private ArrayList<Athlete> athleteList = new ArrayList<>();
	private ArrayList<Official> officialList = new ArrayList<>();
	private ArrayList<Game> historyGames = new ArrayList<>();
	private List<String> gameResult;
	public static final String SWIM = "Swimming";
	public static final String CYCLE = "Cycling";
	public static final String RUN = "Running";
	private int gameNum = 0;
	private Game game = null;
	private Connection c = null;

	public boolean dbconnection() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:participants.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			return false;
		}
		return true;
	}

	public boolean readFile() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("participants.txt"));
			String line;
			Set<String> itemSet = new TreeSet<>();
			while ((line = br.readLine()) != null) {
				itemSet.add(line);
			}
			for (String s : itemSet) {
				String[] items = s.split(",\\s*");
				if (!validData(items)) {
					continue;
				}
				String ID = items[0];
				String type = items[1];
				String name = items[2];
				int age = Integer.parseInt(items[3]);
				String state = items[4];
				if (type.equals("Cyclist")) {
					athleteList.add(new Cyclist(ID, type, name, age, state));
				} else if (type.equals("Swimmer")) {
					athleteList.add(new Swimmer(ID, type, name, age, state));
				} else if (type.equals("Sprinter")) {
					athleteList.add(new Sprinter(ID, type, name, age, state));
				} else if (type.equals("Super")) {
					athleteList.add(new SuperAthlete(ID, type, name, age, state));
				} else if (type.equals("Officer")) {
					officialList.add(new Official(ID, type, name, age, state));
				}
			}
		} catch (FileNotFoundException e1) {
			e1.getMessage();
			return false;
		} catch (IOException e2) {
			e2.printStackTrace();
			return false;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e3) {
				throw new RuntimeException("Fail to Close File ");
			}
		}
		return true;
	}

	public void writeFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("gameResult.txt"));

		} catch (FileNotFoundException e1) {
			e1.getMessage();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e3) {
				throw new RuntimeException("Fail to Close File ");
			}
		}

	}

	public boolean validData(String[] data) {
		for (String s : data) {
			if ("".equals(s))
				return false;
		}
		return true;
	}

	public void startgame(String gameType, ArrayList<Athlete> athletes, Official official) {
		String gameID = gameType.charAt(0) + (gameNum < 10 ? "0" : "") + gameNum;
		switch (gameType) {
		case SWIM:
			game = new Swimming(gameID, gameType, athletes, official);
		case CYCLE:
			game = new Cycling(gameID, gameType, athletes, official);
		case RUN:
			game = new Running(gameID, gameType, athletes, official);
		}
		game.start();
		historyGames.add(game);
		printGameResult(game);
		gameNum++;
	}

	public ArrayList<Athlete> getAthleteList() {
		return athleteList;
	}

	public ArrayList<Official> getOfficialList() {
		return officialList;
	}

	private void printGameResult(Game game) {
		String gameid = game.getID();
		System.out.println(gameid);
		gameResult = game.getPrintResult();
		for (int i = 0; i < gameResult.size(); i++) {
			System.out.println(gameResult.get(i));
		}
	}

}
