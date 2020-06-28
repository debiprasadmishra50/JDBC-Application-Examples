import java.sql.*;
public class Batch_Ex 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		String sql1 = "Insert into Student values (10,'a',6)";
		String sql2 = "Insert into Student values (20,'b',7)";
		String sql3 = "Update Student set name='xyz' where roll=900";
		
		Statement st = con.createStatement();
		
		st.addBatch(sql1);
		st.addBatch(sql2);
		st.addBatch(sql3);
		
		int status[] = st.executeBatch();
		for (int s : status) 
			System.out.println(s);
		
	}
}
