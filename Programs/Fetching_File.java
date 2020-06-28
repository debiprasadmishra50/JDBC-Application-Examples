import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
public class Fetching_File 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select multi_data from multi_table where slno=?";
		
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, 100);
		
		ResultSet rs = pst.executeQuery();
		if(rs.next())
		{
			FileOutputStream fos = new FileOutputStream("x.jpg");
			InputStream iis = rs.getBinaryStream(1);
			int ch;
			while((ch = iis.read() ) != -1)
				fos.write(ch);
			System.out.println("Fetched Sucessfully...");
			fos.close();
			iis.close();
		}
	}
}	
