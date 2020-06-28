import java.sql.*;
public class Transaction_Ex 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		con.setAutoCommit(false);
		String sql1 = "Insert into Student values (10,'a',6)";
		String sql2 = "Insert into Student values (20,'b',7)";
//		String sql3 = "Update Student set name='xyz' where roll=900"; //will be 1,1,0 and will not be auto committed
		String sql3 = "Update Student set name='xyz' where roll=10"; //will be 1,1,1 and will be committed
		
		Statement st = con.createStatement();
		
		int status1 = st.executeUpdate(sql1);
		int status2 = st.executeUpdate(sql2);
		int status3 = st.executeUpdate(sql3);
		
		System.out.println(status1);
		System.out.println(status2);
		System.out.println(status3);
		
		if(status1 > 0 && status2 > 0 && status3 > 0)
		{
			con.commit();
			System.out.println("Yes Records Committed...");
		}
		else
		{
			con.rollback();
			System.out.println("Records are Rollbacked...");
		}
		
		con.close();
		
		
	}
}
