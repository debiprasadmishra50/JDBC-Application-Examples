import java.sql.*;
public class Transaction_SetPoint 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		con.setAutoCommit(false);
		String sql1 = "Insert into Student values (10,'a',6)";
		String sql2 = "Insert into Student values (20,'b',7)";
		String sql3 = "Insert into Student values (21,'c',7)";
		String sql4 = "Insert into Student values (22,'d',7)";
		String sql5 = "Insert into Student values (23,'e',7)";
		
		Statement st = con.createStatement();
		
		int status1 = st.executeUpdate(sql1);
		int status2 = st.executeUpdate(sql2);
		Savepoint sp1 = con.setSavepoint();
		int status3 = st.executeUpdate(sql3);
		Savepoint sp2 = con.setSavepoint();
		int status4 = st.executeUpdate(sql4);
		int status5 = st.executeUpdate(sql5);
		
		System.out.println(status1);
		System.out.println(status2);
		System.out.println(status3);
		System.out.println(status4);
		System.out.println(status5);
		
		con.rollback(sp2);
		System.out.println("Rollbacked upto sp2");
		con.rollback(sp1);
		System.out.println("Rollbacked upto sp2");
		con.commit();
		System.out.println("Committed..");
		
		
		
	}
}
