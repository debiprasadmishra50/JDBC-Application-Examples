import java.sql.*;
import java.util.Scanner;
public class Statement_Ex_from_keyboard 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		String sql = null;
		boolean result = false;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the table name you want to drop");
		String table_name = sc.next();
		
		sql = "drop table "+table_name; //here the space is mandatory
		try 
		{
			result = st.execute(sql);
			System.out.println("Table dropped Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Table doesnot exists ....");
		}
		finally // It is better to write object_name.close() the connection inside finally block... from the Industry point of view
		{
			con.close(); //here the object_name for connection is con so con.close();
		}
	}
}
/*
 * 1. Create a table
 * 			a. Name from keyboard
 * 			b. No of columns and column name from KB
 * 			c. Datatype from KB
 * 			d. Size from KB
 * 			e. Constraint from KB
 * 2. Add new column
 * 			a. No of columns from KB
 * 			b. Column name from KB
 * 			c. datatype from KB
 * 			d. Size from KB
 * 3. To Modify the column 
 * 			a. Change the datatype
 * 					i) Column name from KB
 * 					ii) new Datatype
 * 					iii) new size
 * 4. To rename the column
 * 			a. old column name from KB
 * 			b. new column name from KB
 * 5. Truncate the table
 * 			a. table from KB
 * 6. Drop the table
 * 			a. table name from KB
 * 			
 */

