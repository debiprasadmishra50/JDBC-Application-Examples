/*BDS - Basic Data Source
Add jar File
G:\Pendrive Copied Java\J2EE (SOFTWARE)\JAR\connectionpool
G:\Pendrive Copied Java\J2EE (SOFTWARE)\JAR\connectionpool\org.apache.commons.dbcp
*/
import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;
public class BDS 
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
		
		Connection con = bds.getConnection();
		if (con != null)
		{
			System.out.println("Connected with ORACLE using BDS");
		}
	}
}
