import java.sql.*;
import java.util.Scanner;
public class PST_ST_KB 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		
		Connection con = Provider.getOracleConnection();
		String sql = "Insert into Student (cgpa,roll,name) values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		
		for (int i = 0; i < 3; i++) 
		{
			System.out.println("Enter Cgpa,Roll,Name");
			double cgpa = sc.nextDouble();
			int roll = sc.nextInt();
			String name = sc.next();
			
			pst.setDouble(1, cgpa);
			pst.setInt(2, roll);
			pst.setString(3, name);
			int status = pst.executeUpdate();
			if (status > 0)
				System.out.println("Successfully inserted");
			else
				System.out.println("failed to Insert");
		}
		
		
	}
}
/*
 * HW:
 * 1. All the DDL operation using PreparedStatement
 * 2. Using PST , do update and delete operation with and without KB input
 */

