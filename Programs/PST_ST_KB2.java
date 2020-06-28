import java.sql.*;
import java.util.Scanner;
public class PST_ST_KB2 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		
		Connection con = Provider.getOracleConnection();
		
		Statement st = con.createStatement();
		
		for (int i = 0; i < 3; i++) 
		{
			System.out.println("Enter Cgpa,Roll,Name");
			double cgpa = sc.nextDouble();
			int roll = sc.nextInt();
			String name = sc.next();
			String sql = "Insert into Student (cgpa,roll,name) values("+cgpa+","+roll+",'"+name+"')";
			
			boolean status = st.execute(sql);
			if (status == false)
				System.out.println("Successfully inserted");
			else
				System.out.println("failed to Insert");
		}
		
		
	}
}

