import java.sql.*;
public class Statement_Ex 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		
		
		String sql1 = "Create table Course (cid number(4) Primary key , cname varchar2(20) , cfees number(6,1) )";
		boolean result = st.execute(sql1);
		System.out.println(result);
	}
}

