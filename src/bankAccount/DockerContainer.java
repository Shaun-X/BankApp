package bankAccount;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
public class DockerContainer {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

    final static String DB_URL = "jdbc:mysql://192.168.99.100:3304/bankDB";

    final static String USER = "root";

    final static String PASS = "mypassword";

	public static void createTable() {
       
        Connection conn = null;
        try {

           Class.forName("com.mysql.jdbc.Driver");

           System.out.println("Connecting to database...");
                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
                 System.out.println("Creating table in given database...");
                 Statement stmt = conn.createStatement();
                 String sql = "CREATE TABLE AccountBalance " +
                             "(AccountId INTEGER not NULL, " +
                             "Balance DOUBLE(10,2)," +
                             " PRIMARY KEY ( AccountId ))"; 
                 System.out.println(sql);
                 stmt.executeUpdate(sql);
                 System.out.println("Created table in given database...");
           } catch (SQLException | ClassNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
           }
	}
	public static int createUser(String firstName, String lastName, int age, double balance) {
		 Connection conn = null;
		 int accountId = 0;
	        try {

	           Class.forName("com.mysql.jdbc.Driver");

	           System.out.println("Adding user into database...");
	                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	                 Statement stmt = conn.createStatement();
	                 String sql1 = "INSERT INTO BankAccountData  (firstName,lastName,age) " +
	                 "VALUES (" + "\"" + firstName + "\",\"" + lastName + "\"," + age + ")" + ";";
	                 stmt.executeUpdate(sql1);
	                 String sql3 = "SELECT*FROM BankAccountData ORDER BY AccountId DESC LIMIT 1;";
	                 ResultSet rs = stmt.executeQuery(sql3);
	                 while(rs.next()) {
	                	 accountId = rs.getInt("AccountId");
	                	 }
	                 System.out.println(accountId);
	                 String sql2 = "INSERT INTO AccountBalance VALUES (" + accountId + "," + balance + ");";
	                 String sql4 = "INSERT INTO Transaction (action, amount, AccountId) VALUES (\"New\", 50.0, " + accountId +");";
	                 System.out.println(sql1);
	                 System.out.println(sql2);
	                 
	                 stmt.executeUpdate(sql2);
	                 stmt.executeUpdate(sql4);
	                 System.out.println("User added in the database...");
	           } catch (SQLException | ClassNotFoundException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	           }
			return accountId;
	}
	public static void deleteUser(int accountId) {
		 Connection conn = null;
	        try {

	           Class.forName("com.mysql.jdbc.Driver");

	           System.out.println("Deleting user from database...");
	                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	                 Statement stmt = conn.createStatement();
	                 String sql = "DELETE FROM BankAccountData where AccountId = " + accountId + ";";
	                 String sql2 = "DELETE FROM AccountBalance where AccountId = " + accountId + ";";
	                 //System.out.println(sql);
	                 stmt.executeUpdate(sql);
	                 stmt.executeUpdate(sql2);
	                 System.out.println("User added in the database...");
	           } catch (SQLException | ClassNotFoundException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	           }
	}
	public static BankAccount lookUpUser(int accountId) {
		 Connection conn = null;
		 BankAccount customer = new BankAccount();
	        try {

	           Class.forName("com.mysql.jdbc.Driver");

	                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	                 Statement stmt = conn.createStatement();
	                 String sql = "SELECT*FROM BankAccountData where AccountId = " + accountId +";";
	                 //System.out.println(sql);
	                 String sql2 = "SELECT*FROM AccountBalance where AccountId = " + accountId +";";
	                 ResultSet rs = stmt.executeQuery(sql);
	                 while(rs.next()) {
	                	 String firstName = rs.getString("firstName");	
	                	 String lastName = rs.getString("lastName");	
	                	 int age = rs.getInt("age");
	               // 	 double balance = rs.getDouble("Balance");
	                	 //System.out.println(firstName + " " + lastName + " "+ age + " "+ balance);
	            		 customer.setAcc_num(accountId);
	            		 customer.setFirstname(firstName);
	            		 customer.setLastname(lastName);
	            		 customer.setAge(age);
	            	//	 customer.setBalance(balance);
	                 }
	                 ResultSet rs2 = stmt.executeQuery(sql2);
	                 while(rs2.next()) {
	                	 double balance = rs2.getDouble("Balance");
	                	 customer.setBalance(balance);
	                 }
	                 
	           } catch (SQLException | ClassNotFoundException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	           }
			return customer;
	}
	public static void updateUserBalance(BankAccount customer, String action, double amount) {
		 Connection conn = null;
	        try {

	           Class.forName("com.mysql.jdbc.Driver");
	           System.out.println("Updating user's balance to database...");
	                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
	                 Statement stmt = conn.createStatement();
	                 String sql = "UPDATE AccountBalance SET balance = " +
	                 customer.getBalance() + " where AccountId = " + customer.getAcc_num()
	                 + ";";
	                 String sql2 = "INSERT INTO Transaction (action, amount, AccountId) VALUES ("+ "\""  + action+"\", "+ amount+" , " + customer.getAcc_num() + ");";

	                 //System.out.println(sql2);
	                 stmt.executeUpdate(sql);
	                 stmt.executeUpdate(sql2);

	                 System.out.println("User's balance updated  the database...");
	           } catch (SQLException | ClassNotFoundException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	           }
	}
}
