import java.sql.*;
public class PreparedStatement_Ex 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		String sql = "Insert into Student (cgpa,roll,name) values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setDouble(1, 7.8);
		pst.setInt(2, 100);
		pst.setString(3, "Raja");
		
		int status = pst.executeUpdate();
		System.out.println(status);
		if (status > 0)
			System.out.println("Successfully inserted");
		else
			System.out.println("failed to Insert");
		
	}
}

