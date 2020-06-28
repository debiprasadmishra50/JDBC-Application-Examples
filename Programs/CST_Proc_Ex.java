import java.sql.*;
import java.util.Scanner;
public class CST_Proc_Ex 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		
		Connection con = Provider.getOracleConnection();
		CallableStatement cst = con.prepareCall("{call proc_insert(?,?,?)}");
		
		for (int i = 0; i < 3; i++) 
		{
			System.out.println("Enter Roll,Name,Cgpa");
			int roll = sc.nextInt();
			String name = sc.next();
			double cgpa = sc.nextDouble();
			
			
			cst.setInt(1, roll);
			cst.setString(2, name);
			cst.setDouble(3, cgpa);
			
			cst.execute();
			System.out.println("Record Inserted By Procedure");
		}
		sc.close();
	}
}
