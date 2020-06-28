import java.sql.*;
import java.util.Scanner;
public class JDBCApplication
{
	static Scanner sc = new Scanner(System.in);
	static Statement st;
	static PreparedStatement pst;
	static Connection con;
	
	static void backOrExit() throws Exception
	{
		System.out.println("Do you want to go back or exit \n1 --> Go back\n0 --> Exit");
		String ch = sc.next();
		if (ch.equalsIgnoreCase("1"))
			JDBCApplication.mainMenu();
		else if(ch.equalsIgnoreCase("0"))
			System.exit(0);
		else
			System.out.println("Please Enter a Valid Choice");
	}
	
	static String datatype()
	{
		String input = sc.next();
		switch(input)
		{
		case "1":
			input = "Number";
			break;
		case "2":
			input = "Varchar2";
			break;
		default :
			System.out.println("Please Enter a valid option");
			datatype();
		}
		return input;
	}
	
	static String Constraint()
	{
		String input = sc.next();
		switch(input)
		{
		case "1" :
			input = "Primary Key";
			break;
		case "2" :
			input = "Not null";
			break;
		case "3" :
			input = "check";
			break;
		case "4" :
			input = "Unique";
			break;
		case "5" :
			input = "Foreign Key";
			break;
		case "6" :
			input = "";
			break;
		default :
			System.out.println("Please Enter a valid option");
			Constraint();
		}
		return input;
	}
	
	public static void main(String[] args) throws Exception 
	{
		JDBCApplication.mainMenu();
	}
	
	static void mainMenu() throws Exception
	{
		System.out.println("                Hello User\n       Please choose what you want to do\n=================================================");
		System.out.println("1. Create a Table\n2. Adding new coumn\n3. Modify Column\n4. Rename Column\n5. Truncate Table\n6. Drop Table\n7. Rename Table\n8. Describe Table\n9. Select Table\n10. Insert into Table\n11. Delete all data from Table\n0. Exit from here");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1 :
			JDBCApplication.createTable();
			break;
		case 2 :
			JDBCApplication.addNewColumn();
			break;
		case 3 :
			JDBCApplication.modifyColumn();
			break;
		case 4 :
			JDBCApplication.renameColumn();
			break;
		case 5 :
			JDBCApplication.truncateTable();
			break;
		case 6 :
			JDBCApplication.dropTable();
			break;
		case 7 :
			JDBCApplication.renameTable();
			break;
		case 8 :
			JDBCApplication.descTable();
			break;
		case 9 :
			JDBCApplication.selectTable();
			break;
		case 10 :
			JDBCApplication.insertTable();
			break;
		case 11 :
			JDBCApplication.deleteTable();
			break;
		case 0 :
			System.exit(0);
			break;
		default :
			System.out.println("Terminated for wrong insertion, please try again");
			System.exit(0);
			
		}
	}
	
	private static void deleteTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		System.out.println("Enter the table name that you want to delete");
		String table_name = sc.next();
		String sql = "Delete "+table_name;
		pst = con.prepareStatement(sql);
		try 
		{
			pst.executeUpdate();
			System.out.println(table_name+" Table data Deleted Successfully");
		}
		catch (Exception e) 
		{
			System.out.println("Table doesnot exist");
		}
		System.out.println("Do you want to delete more Tables (Y/N)");
		String select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			deleteTable();
		else
			con.close();
		JDBCApplication.backOrExit();
		
	}
	
	private static void insertTable() throws Exception
	{
		con = Provider.getOracleConnection();
		System.out.println("Enter the table name in which you want to insert");
		String table_name = sc.next();
		
		try
		{	
			pst = con.prepareStatement("Select * from "+table_name);
			pst.executeQuery();
			ResultSetMetaData rsmd = pst.getMetaData();
			int no_cols = rsmd.getColumnCount();
			String sql = "Insert into "+table_name+" (";
			String value[] = new String[rsmd.getColumnCount()];
			for (int i = 0; i < no_cols; i++) 
			{
				if(i == no_cols-1)
				{
					sql = sql.concat(rsmd.getColumnName((i+1))+") values (");
					while(no_cols != 0)
					{
						if(no_cols == 1)
							sql = sql.concat("?)");
						else
							sql = sql.concat("?,");
							no_cols--;
					}
				}
				else
					sql = sql.concat(rsmd.getColumnName((i+1))+",");
			}
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			int col_no = rsmd.getColumnCount();
			for (int j = 0; j < col_no; j++) 
			{
				System.out.println("Column name "+rsmd.getColumnName((j+1)));
				System.out.println("Enter values for colume "+rsmd.getColumnName((j+1))+" with datatype "+rsmd.getColumnTypeName((j+1)));
				String col_type = rsmd.getColumnTypeName((j+1));
				if (col_type.equalsIgnoreCase("Number"))
				{
					value[j] = sc.next();
					pst.setInt((j+1), Integer.parseInt(value[j]));
				}
				else if (col_type.equalsIgnoreCase("Varchar2"))
				{
					value[j] = sc.next();
					pst.setString((j+1), value[j]);
				}	
			}
			int status = pst.executeUpdate();
			if (status > 0)
				System.out.println("Successfully inserted");
		}	
		catch (Exception e) 
		{
			System.out.println("Values already exists, Error in insertion");
		}
		System.out.println("Do you want to Insert more columns (Y/N)");
		String select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			insertTable();
		else
			con.close();
		JDBCApplication.backOrExit();
	}
	
	private static void selectTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		System.out.println("Enter the table name you want to see");
		String table_name = sc.next();
		String sql = "Select * from "+table_name;
		String select = null;
		try
		{
			pst = con.prepareStatement(sql);
			pst.executeQuery();
			ResultSetMetaData rsmd=pst.getMetaData();
			int col_Count=rsmd.getColumnCount();
			ResultSet rs=pst.executeQuery(sql);
			while(rs.next())
			{
				for (int i = 1; i <= col_Count; i++) 
				{
					System.out.print(rs.getObject(i)+"\t");
				}
				System.out.println();
			}
		}
		catch (Exception e) 
		{
			System.out.println("Table doesnot exist");
		}
		System.out.println("Do you want to Show more Tables (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			selectTable();
		else
			con.close();
			JDBCApplication.backOrExit();
	}
	
	private static void descTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		System.out.println("Tell me which table you want to Show");
		String table_name = sc.next();
		String select = null;
		try 
		{
			String sql = "Select * from "+table_name;
			ResultSet rs = st.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int no_cols = rsmd.getColumnCount();
			System.out.println("ColName    ColTypeName(ColSize)\n---------------------------------------------");
			for(int i=1 ; i<=no_cols ; i++)
			{
				System.out.println(rsmd.getColumnLabel(i) +"\t"+ rsmd.getColumnTypeName(i) +"("+ rsmd.getColumnDisplaySize(i)+")");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Table or view does not exist");
		}
		
		System.out.println("Do you want to Describe more Tables (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			descTable();
		else
			con.close();
			JDBCApplication.backOrExit();
	}
	
	private static void renameTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		String select = null;
		System.out.println("Tell me which table you want to Rename");
		String table_name = sc.next();
		System.out.println("Enter the new name for table "+table_name);
		String new_table_name = sc.next();
		
		sql = "rename "+table_name+" to "+new_table_name;
		
		try 
		{
			st.execute(sql);
			System.out.println("Table renamed Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Table or view does not exist");
		}
		
		System.out.println("Do you want to rename more Tables (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			renameTable();
		else
			con.close();
			JDBCApplication.backOrExit();
	}
	
	private static void dropTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		String select = null;
		System.out.println("Tell me which table you want to drop");
		String table_name = sc.next();
		sql = "drop table "+table_name;
		try 
		{
			st.execute(sql);
			System.out.println(table_name+" Table Dropped Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Table or view does not exist");
		}

		System.out.println("Do you want to drop more Tables (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			dropTable();
		else
			con.close();
			JDBCApplication.backOrExit();
		
	}
	
	private static void truncateTable() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		String select = null;
		System.out.println("Tell me which table you want to Truncate");
		String table_name = sc.next();
		sql = "Truncate table "+table_name;
		try 
		{
			st.execute(sql);
			System.out.println(table_name+" Table Truncated Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Missing Cluster or Table doesnot exist");
		}
		System.out.println("Do you want to truncate more Tables (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			truncateTable();
		else
			con.close();
			JDBCApplication.backOrExit();
	}
	
	private static void renameColumn() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		String select = null;
		System.out.println("Tell me which table Where you want to Rename");
		String table_name = sc.next();
		
			sql = "Alter table "+table_name+" rename column ";
			System.out.println("Name which column you want to rename");
			String col_name = sc.next();
			System.out.println("Enter the new Column name for "+col_name);
			String new_col_name = sc.next();
			
			sql = sql.concat(col_name+" to "+new_col_name);
			try 
			{
				st.execute(sql);
				System.out.println(col_name+" Changed to "+new_col_name+" Successfully");
			} 
			catch (Exception e) 
			{
				System.out.println("Column doesnot exist");
			}
			
			System.out.println("Do you want to rename more columns (Y/N)");
			select = sc.next();
			if(select.equalsIgnoreCase("Y"))
				renameColumn();
			else
				con.close();
				JDBCApplication.backOrExit();
	}
	
	private static void modifyColumn() throws Exception 
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		String select = null;
		System.out.println("Tell me which table Where you want to Modify");
		String table_name = sc.next();
		System.out.println("Enter the column name that you want to modify");
		String col_name = sc.next();
		System.out.println("Enter new Datatype for column "+col_name);
		String col_datatype = JDBCApplication.datatype();
		System.out.println("Enter the size for the datatype "+col_datatype);
		int size = sc.nextInt();

		sql = "Alter table "+table_name+" modify ( "+col_name+" "+col_datatype+"("+size+"))";
		
		try 
		{
			st.execute(sql);
			System.out.println(col_name+" Mofified Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Column doesnot exist");
		}
		
		System.out.println("Do you want to rename more columns (Y/N)");
		select = sc.next();
		if(select.equalsIgnoreCase("Y"))
			modifyColumn();
		else
			con.close();
			JDBCApplication.backOrExit();
	}
	
	private static void addNewColumn() throws Exception
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		System.out.println("\nPlease tell me in which table you want to add columns");
		String table_name = sc.next();
		System.out.println("\nPlease tell me how many columns you want to add");
		int noofaddcol = sc.nextInt();
		String col_name[] = new String[noofaddcol];
		sql = "Alter table "+table_name+" add (";
		for (int i = 0; i < noofaddcol; i++) 
		{
			System.out.println("Lets enter Column "+(i+1)+" details");
			System.out.println("\nPlease enter column name "+(i+1));
			col_name[i] = sc.next();
			System.out.println("\nEnter datatype for column "+col_name[i]+"\n\nChoose the option\n1.Number\n2.Varchar2\n");
			String col_datatype = JDBCApplication.datatype();
			System.out.println("\nYou choose datatype "+col_datatype);
			System.out.println("\nEnter size for datatype "+col_datatype);
			int size = sc.nextInt();
			
			if (noofaddcol == 1)
				sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+"))");
			else
			{
				if (i == noofaddcol-1)
					sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+"))");
				else
					sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+"),");
			}
		}
		System.out.println(sql);
		
		try 
		{
			st.execute(sql);
			System.out.println("Columns added successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Sorry, Unable to add");
		}
		finally
		{
			con.close();
			JDBCApplication.backOrExit();
		}
	}
	
	private static void createTable() throws Exception
	{
		con = Provider.getOracleConnection();
		st = con.createStatement();
		String sql = null;
		System.out.println("Hello User \nPlease enter the name of the table that you want to create");
		String table_name = sc.next();
		System.out.println("Please enter how many columns you want in here"); 
		int col_no = sc.nextInt();
		String col_name[] = new String[col_no];
		sql = "Create table "+table_name+"(";
		for (int i = 0; i < col_no; i++) 
		{
			System.out.println("Lets enter Column "+(i+1)+" details");
			System.out.println("Please Enter column name for column "+(i+1));
			col_name[i] = sc.next();
			System.out.println("Enter datatype for column "+col_name[i]+"\n\nChoose the option\n1.Number\n2.Varchar2");
			String col_datatype = JDBCApplication.datatype();
			System.out.println("You choose datatype "+col_datatype);
			System.out.println("Enter size for datatype "+col_datatype);
			int size = sc.nextInt();
			System.out.println("\nEnter Constraint for column "+col_name[i]+"\n\nChoose the option\n1. Primary Key\n2. Not null\n3. Check\n4. Unique\n5. Foreign Key\n6. No constraint");
			String col_constraint = JDBCApplication.Constraint();
			System.out.println("You choose Constraint "+col_constraint);
					
			if (col_no == 1)
			{
				sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+") "+col_constraint+")");
				break;
			}		
			else
			{	
				if ( i == (col_no-1) )
					sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+") "+col_constraint+")");
				else
					sql = sql.concat(col_name[i]+" "+col_datatype+"("+size+") "+col_constraint+",");
			}	
		}
		System.out.println(sql);
		try 
		{
			st.execute(sql);
			System.out.println("Table created successfully");
		} 
		catch (Exception e) 
		{
			System.out.println(table_name+" Table already exists");
		}
		finally
		{
			con.close();
			JDBCApplication.backOrExit();
		}
		
	}
}