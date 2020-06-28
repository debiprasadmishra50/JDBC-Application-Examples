import java.io.FileInputStream;
import java.sql.*;
public class MultiMedia_Store 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		String sql = "Insert into multi_table values (?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		
		FileInputStream fis = new FileInputStream("a.jpg");
		
		pst.setInt(1, 100);
		pst.setBinaryStream(2, fis,fis.available());
		int status = pst.executeUpdate();
		
		if(status > 0)
			System.out.println("Multimedia information stored successfully");
		else
			System.out.println("Error in Insertion....");
	}
}
