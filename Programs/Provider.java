import java.sql.*;

public class Provider 
{
	public static Connection getOracleConnection()
	{
		Connection con = null;
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","sipusipu18");	
		} 
		catch (Exception e) 
		{
			e.printStackTrace(); //It will show the details about Exception
		}
		return con;
	}
	public static Connection getMysqlConnection()
		{
			Connection con = null;
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/db1","root","sipusipu18");
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace(); //It will show the details about Exception
			}
			return con;
		}
	public static void main(String[] args) 
	{
		Connection con_ora = Provider.getOracleConnection();
		Connection con_mysql = Provider.getMysqlConnection();
		
		System.out.println(con_ora);
		System.out.println(con_mysql);
	}
}
//First set the classpath then run the program
/*
 * 	How to set the classpath of .jar file in Eclipse
 * ============================================================
 * 			JRE System Library (in Project) --> Right click --> Build Path --> Configure Build Path --> Libraries --> Select classpath --> Add External Jar File --> Browse the Jar file --> apply and close
 * 
 * Note : 
 * 		Check in Referenced Libraries
 *
 */
