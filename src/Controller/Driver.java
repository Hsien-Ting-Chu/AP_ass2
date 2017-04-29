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
	ArrayList<Athlete> athletesList;
	ArrayList<Official> officialList;

	
	public void dbconnection(){
		Connection c = null;
		  try {
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:participants.db");
		  } catch (Exception e) {
		   System.err.println(e.getClass().getName() + ": " + e.getMessage());
		   System.exit(0);
		  }
		  System.out.println("Opened database successfully");
	}
	
	public void readFile() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("participants.txt"));
			String line;
			Set<String> itemSet = new TreeSet<>();
			while ((line = br.readLine()) != null) {
				itemSet.add(line);
			}
			athletesList = new ArrayList<>();
			officialList = new ArrayList<>();

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
				if (type.equals("Cyclist"))
					athletesList.add(new Cyclist(ID, type, name, age, state));
				else if (type.equals("Swimmer"))
					athletesList.add(new Swimmer(ID, type, name, age, state));
				else if (type.equals("Sprinter"))
					athletesList.add(new Sprinter(ID, type, name, age, state));
				else if (type.equals("Super"))
					athletesList.add(new SuperAthlete(ID, type, name, age, state));
				else if (type.equals("Officer"))
					officialList.add(new Official(ID, type, name, age, state));
			}
			// for (int i = 0; i < athletesList.size(); i++)
			// System.out.println(athletesList.get(i));
			// for (int i = 0; i < officialList.size(); i++)
			// System.out.println(officialList.get(i));
		} catch (FileNotFoundException e1) {
			e1.getMessage();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e3) {
				throw new RuntimeException("Fail to Close File ");
			}
		}
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

	public void gamevildation(String gameType, ArrayList<Athlete> athletesList, ArrayList<Official> officialList){
			
		
		
		
	}
	

}
