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
import View.Ozlympic;

public class Driver {
	private ArrayList<Athlete> athletesList = new ArrayList<>();
	private ArrayList<Official> officialList = new ArrayList<>();
	private ArrayList<Participants> participantsList = new ArrayList<>();

	public static final String SWIM = "Swimming";
	public static final String CYCLE = "Cycling";
	public static final String RUN = "Running";
	public int gameNum = 0;

	public boolean dbconnection() {
		Connection c = null;
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
					athletesList.add(new Cyclist(ID, type, name, age, state));
					participantsList.add(new Cyclist(ID, type, name, age, state));
				} else if (type.equals("Swimmer")) {
					athletesList.add(new Swimmer(ID, type, name, age, state));
					participantsList.add(new Swimmer(ID, type, name, age, state));
				} else if (type.equals("Sprinter")) {
					athletesList.add(new Sprinter(ID, type, name, age, state));
					participantsList.add(new Sprinter(ID, type, name, age, state));
				} else if (type.equals("Super")) {
					athletesList.add(new SuperAthlete(ID, type, name, age, state));
					participantsList.add(new SuperAthlete(ID, type, name, age, state));
				} else if (type.equals("Officer")) {
					officialList.add(new Official(ID, type, name, age, state));
					participantsList.add(new Official(ID, type, name, age, state));
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
		Game game = null;
		String gameID = gameType.charAt(0) + (gameNum < 10 ? "0" : "") + gameNum;
		switch(gameType){
		case SWIM:
			game = new Swimming(gameID, gameType, athletes, official);
		case CYCLE:
			game = new Cycling(gameID, gameType, athletes, official);
		case RUN:
			game = new Running(gameID, gameType, athletes, official);
		}
		
		

		
		gameNum++;
	}

	public ArrayList<Athlete> getAthleteList() {
		return athletesList;
	}

	public ArrayList<Official> getOfficialList() {
		return officialList;
	}

	public ArrayList<Participants> getParticipantsList() {
		return participantsList;
	}

}
