import java.sql.*;
import java.util.Scanner;
public class Scrollable_Example2 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select roll,name,cgpa from Student";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery(sql);
		rs.absolute(2);
		System.out.println(rs.getInt(1) +"\t"+ rs.getString(2)+"\t"+rs.getDouble(3)); 
		
		rs.absolute(2);
		rs.updateInt(1, 5);
		rs.updateString(2, "DEBA");
		rs.updateDouble(3, 9.9);
		rs.updateRow();
		System.out.println("Yes Updated");
		
		rs.moveToInsertRow();
		rs.updateInt(1, 222);
		rs.updateString(2, "DEEEBBBIII");
		rs.updateDouble(3, 6.5);
		rs.insertRow();
		System.out.println("Record Inserted..");
		
		rs.absolute(1); //or rs.first();
		
		while(rs.next())
		{
			System.out.println(rs.getInt(1) +"\t"+ rs.getString(2)+"\t"+rs.getDouble(3)); 
		}
		
		System.out.println("Enter row you want to delete");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		
		rs.absolute(row);
		rs.deleteRow();
		System.out.println("Record Deleted...");
	}
}

