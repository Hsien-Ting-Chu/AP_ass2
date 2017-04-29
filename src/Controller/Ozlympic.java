package Controller;

/**
 * @author Hsienting Chu
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class Ozlympic {

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.dbconnection();
		driver.readFile();
		

	}
}