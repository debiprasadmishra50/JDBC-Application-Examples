import java.sql.*;
public class RSMD_EX 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		
		String sql = "Select * from student ";
		ResultSet rs = st.executeQuery(sql);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int no_cols = rsmd.getColumnCount();
		
		System.out.println("No of Columns : "+no_cols);
		for(int i=1 ; i<=no_cols ; i++)
		{
			System.out.println(rsmd.getColumnLabel(i) +"\t"+ rsmd.getColumnTypeName(i) +"\t"+ rsmd.getColumnClassName(i));
		}
	}
}
