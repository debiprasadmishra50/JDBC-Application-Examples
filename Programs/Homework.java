import java.sql.*;
import java.util.Scanner;
public class Homework 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		String sql = null;
		boolean result = false;
		
		System.out.println("Hello User \nPlease enter the name of the table that you want to create");
		String table_name = sc.next();
		
		
		System.out.println("Please enter how many columns you want to add (Take Maximum 2)"); 
		int col_no = sc.nextInt();
		
		String col_name [] = new String [col_no];
		String d_type = null;
		int size [] = new int [col_no];
		String col_dtype [] = {"number" , "Varchar2"};
		
		String a = "Primary Key";
		String b = "Not null";
		String c = "Check";
		String d = "Unique";
		String e = "Foreign Key";
		String y = null;
		System.out.println("Please Enter the column names :");
		for (int i = 0; i < col_no; i++) 
		{
			System.out.println("Name For column "+(i+1));
			col_name[i] = sc.next();
			System.out.println("Please enter datatype for column "+col_name[i]+"\nPlease choose the option\nd1.number  \nd2.varchar2 ");
			String input = sc.next();
			switch(input)
			{
				case "d1":
					d_type = col_dtype[0];
					break;
				case "d2":
					d_type = col_dtype[1];
					break;
			}

			
			System.out.println("Please enter the size for the datatype "+col_dtype[i]);
			size[i] = sc.nextInt();
			System.out.println("Please choose the suitable constraint for the column "+col_name[i]);
			System.out.println("Please choose the option \na.Primary Key \nb.Not null\nc.Check\nd.Unique\ne.Foreign key ");
			String x = sc.next();
			switch (x)
			{
				case "a": 
					y = a;
					break;
				case "b":
					y = b;
					break;
				case "c":
					y = c;
					break;
				case "d":
					y = d;
					break;
				case "e":
					y = e;
					break;
			}
			
		//	System.out.println("Now for next column");
		}
		
		sql = "Create table "+table_name+"("+col_name[0]+" "+d_type+"("+size[0]+") "+y+" , "+col_name[1]+" "+d_type+"("+size[1]+") "+y+")";
		
		String s = "hello";
		
		s = s+" hiii";
		System.out.println();
		try 
		{
			result = st.execute(sql);
			System.out.println("Your Table is successfully Created");
		} 
		catch (Exception e2) 
		{
			System.out.println("I am sorry, Something is Wrong \nI can't create the table\nPlease check and come again");
		}
		
		
		//Add New Columns
		
		System.out.println("Do you want to add more columns, Press Y/N");
		char c1 = sc.next().charAt(0);
		if (c1 == 'Y')
		{
			System.out.println("Tell me how many no of columnns you want to add (Please take it maximum 2)");
			int c_n = sc.nextInt();
			String col_nam [] = new String [c_n];
			System.out.println("Please Enter the column names :");
			for (int j = 0; j < c_n; j++) 
			{
				System.out.println("Name For column "+(j+1));
				col_nam[j] = sc.next();
				System.out.println("Please enter datatype for column "+col_nam[j]+"\nPlease choose the option\nd1.number  \nd2.varchar2 ");
				String input = sc.next();
				switch(input)
				{
					case "d1":
						d_type = col_dtype[0];
						break;
					case "d2":
						d_type = col_dtype[1];
						break;
				}
				
				System.out.println("Please enter the size for the datatype "+d_type);
				size[j] = sc.nextInt();
			}
			sql = "Alter table "+table_name+" add ("+col_nam[0]+" "+d_type+"("+size[0]+") , "+col_nam[1]+" "+d_type+"("+size[1]+"))";
			
			
			try 
			{
				result = st.execute(sql);
				System.out.println("Columns added successfully to Table ");
			} 
			catch (Exception e2) 
			{
				System.out.println("I am sorry, Something is Wrong \nI can't add the columns\nPlease check and come again");
			}
		}
		else
			System.out.println("Okay , Bye User :)");
		
		
		
		//Modify the Columns
		
		System.out.println("Hey do you wanna modify the columns , Press Y/N");
		char c2 = sc.next().charAt(0);
		if (c2 == 'Y')
		{
			System.out.println("Which column you want to modify, just gimme a name");
			String m_name = sc.next();
			System.out.println("which datatype you want to have in this column \nPlease choose the option\nd1.number \nd2.varchar2");
			String input = sc.next();
			switch(input)
			{
				case "d1":
					d_type = col_dtype[0];
					break;
				case "d2":
					d_type = col_dtype[1];
					break;
			}
			
			System.out.println("Please enter the size for the datatype "+d_type);
			int size_new_d_type = sc.nextInt(); 
			
			sql = "Alter table "+table_name+" modify ("+m_name+" "+d_type+"("+size_new_d_type+"))";
			
			try 
			{
				result = st.execute(sql);
				System.out.println("Columns datatype changed Successfully");
			} 
			catch (Exception e2) 
			{
				System.out.println("I am sorry, Something is Wrong \nI can't change the datatype of columns\nPlease check and come again");
			}
			
		}
		else
			System.out.println("Bye User");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
