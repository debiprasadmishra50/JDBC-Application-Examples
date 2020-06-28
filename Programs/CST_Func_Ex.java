import java.sql.*;
import java.util.Scanner;
public class CST_Func_Ex 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		
		Connection con = Provider.getOracleConnection();
		CallableStatement cst = con.prepareCall("{?=call fun_insert (?,?,?)}");
		
			System.out.println("Enter Roll,Name,Cgpa");
			int roll = sc.nextInt();
			String name = sc.next();
			double cgpa = sc.nextDouble();
			cst.setInt(2, roll);
			cst.setString(3, name);
			cst.setDouble(4, cgpa);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			System.out.println("Record Inserted By Function...");
			System.out.println("No of records present in student table : "+cst.getInt(1));
			
		
	}
}

/* 1.Create a Pl/sql procedure which will delete the record based on roll
 * 2. Create a Pl/Sql procedure which will update the record based on roll
 * 3. Create a pl/sql function which return the details of Student based on roll
 * 4. Create a pl/sql function which will return the maximum & minimum cgpa of student
 * 
 */
