import java.sql.*;
import java.util.Scanner;
public class Statement_Ex_from_KB_DML 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		String sql = null;
		int status = 0;
		
		Scanner sc = new Scanner(System.in);
		
		sql = "Insert into Course values (100,'raja',5000,'r@gmail.com',9090909090)";
		status = st.executeUpdate(sql);
		if(status > 0)
			System.out.println("Record Inserted Successfully");
		else
			System.out.println("Error in Insertion....");
		
		
		int roll = sc.nextInt();
		String name = sc.next();
		sql = "Insert into Course (roll,name) values ("+roll+",'"+name+"')";
		status = st.executeUpdate(sql);
		if(status > 0)
			System.out.println("Record Inserted Successfully");
		else
			System.out.println("Error in Insertion....");
		
		
		sc.close();
	}
}

//In SQL
// Select * from Course;
// Set lines 3000