import java.sql.*;
public class Non_Scrollable_RS_EX 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select name , cgpa , roll from Student";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		System.out.println(rs.getString(1)+"\t"+rs.getDouble("cgpa")+"\t"+rs.getInt(3));
		
	}
}

/*
 * Display all the table name present in Oracle server
 * 			Select table_name from user_tables; - It will show all the tables
 * 
 * Check a paraticular table existance from RS
 */

