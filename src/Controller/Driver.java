package Controller;

/**
 * @author Hsienting Chu
 *
 */
import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Model.*;

public class Driver {
	private ArrayList<Athlete> athleteList = new ArrayList<>();
	private ArrayList<Official> officialList = new ArrayList<>();
	private ArrayList<String> gamesHistory = new ArrayList<>();
	private List<String> gameResult;
	private List<String> printResult;
	private List<Integer> scoreList;
	public static final String SWIM = "Swimming";
	public static final String CYCLE = "Cycling";
	public static final String RUN = "Running";
	private int gameNum = 0;
	private Game game = null;
	private Connection participants = null;
	private Connection gameResults = null;

	public boolean dbconnection() {

		try {
			Class.forName("org.sqlite.JDBC");
			participants = DriverManager.getConnection("jdbc:sqlite:participants.db");
			gameResults = DriverManager.getConnection("jdbc:sqlite:participants.db");
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
			FileOutputStream writer = new FileOutputStream("gameResults.txt");
			writer.write(("").getBytes());
			writer.close();
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

		printGameResult(game, official);
		printgameHistory();
		gameNum++;
	}

	private void printGameResult(Game game, Official official) {
		String gameid = game.getID();
		Calendar cal = Calendar.getInstance();
		DateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		gameResult = game.getPrintResult();
		scoreList = official.getscoreList();

		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			bw = new BufferedWriter(new FileWriter("gameResults.txt", true));
			out = new PrintWriter(bw);
			for (int i = 0; i < gameResult.size(); i++) {
				if (i == 0) {
					System.out.println(gameid + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
					out.println(gameid + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
				} else if (i == 1) {
					System.out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 5");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 5");
				} else if (i == 2) {
					System.out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 2");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 2");
				} else if (i == 3) {
					System.out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 1");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 1");
				}
			}

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
			try {
				if (out != null)
					out.close();
			} catch (Exception e4) {
				throw new RuntimeException("Fail to Close File ");
			}
		}

	}

	private List<Athlete> sortAthletes(List<Athlete> athelets) {
		List<Athlete> sortList = new ArrayList<Athlete>(athelets);
		Collections.sort(sortList, new Comparator<Athlete>() {
			@Override
			public int compare(Athlete a1, Athlete a2) {
				return a2.getPoints() - a1.getPoints();
			}
		});
		return sortList;
	}

	private void printgameHistory() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("gameResults.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				gamesHistory.add(line);
			}
		} catch (IOException e) {

		} finally {
			try {
				if (reader != null)
					reader.close();

			} catch (IOException e) {

			}
		}
	}

	public ArrayList<Athlete> getAthleteList() {
		return athleteList;
	}

	public ArrayList<Official> getOfficialList() {
		return officialList;
	}

	public ArrayList<String> getgamesHistory() {
		return gamesHistory;
	}

}
