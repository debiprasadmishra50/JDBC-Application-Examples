/*BDS - Basic Data Source
Add jar File
G:\Pendrive Copied Java\J2EE (SOFTWARE)\JAR\connectionpool
G:\Pendrive Copied Java\J2EE (SOFTWARE)\JAR\connectionpool\org.apache.commons.dbcp
*/
import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;
public class BDS_Faster 
{
	public static void main(String[] args) throws Exception 
	{
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String driver="oracle.jdbc.OracleDriver";
		String username="system";
		String password="sipusipu18";
		
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		
		for (int i = 1; i < 5; i++) 
		{
			Connection con = bds.getConnection();
			System.out.println(con.hashCode());
			con.close();
		}
		System.out.println("---------------------------------");
		for (int i = 1; i < 5; i++) 
		{
			Connection con = DriverManager.getConnection(url,username,password);
			System.out.println(con.hashCode());
			con.close();
		}
		
	}
}
