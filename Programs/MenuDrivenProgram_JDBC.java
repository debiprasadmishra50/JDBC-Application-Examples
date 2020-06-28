import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuDrivenProgram_JDBC
{
	static String Query=null;
	static boolean result=false;
	static Connection con=null;
	static Statement stm=null;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static String tableName=null;
	static int noOfColumn;
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException 
	{
		
		int choice=0;
		try
		{
		con=Provider.getOracleConnection();
		stm=con.createStatement();
		}
		catch( SQLException e)
		{
			System.out.println("Connection Failed");
		}
		
		while(true)
		{
			System.out.printf("\n\n     *********Main Menu*********     \n");
			System.out.printf("\nChoose one option from the following list ...\n");  
		     System.out.printf("\n===============================================\n");
		     System.out.printf("\n1.Create a table\n2.Add new Column\n3.To Modify Columns\n4.To Rename the Column"
		     		+ " \n5.Truncate the table\n6.Drop the table\n7.Insert the table\n8.Update the table"
		     		+ "\n9.Delete the table\n10.Select the table\n11.Decribe the table\n12.Drop Column\n0.Exit\n");   
		     System.out.printf("\nEnter your choice?\n"); 
		     choice=Integer.parseInt(br.readLine());
		     switch(choice)  
		
		     {  
		            case 1:  
		            createTable();      
		            break;  
		            case 2:  
		            addNewColumn();         
		            break;  
		            case 3:  
		            modifyColumn();       
		            break;  
		            case 4:  
		            renameColumn();       
		            break;  
		            case 5:  
		            truncateTable();        
		            break;  
		            case 6:  
		            dropTable();          
		            break;  
		            case 7:  
		            insertTable();         
		            break;  
		            case 8:  
		            updateTable();        
		            break;  
		            case 9:  
		            deleteTable();  
		            break; 
		            case 10:
		            selectTable();
		            break;
		            case 11:
		            describeTable();
		            break;
		            case 12:
		            	dropColumn();
		            	break;
		            
		            case 0:
		            	System.exit(0);
		            default:  
		            System.out.printf("Please enter valid choice..");  
		        }  	       
		}
		
	}
	
	private static void dropColumn() throws IOException 
	{
		System.out.println("enter the table ");
		tableName=br.readLine();
		System.out.println("enter the column name");
		String columnName=br.readLine();
		Query="alter table "+tableName+" drop column "+columnName;
		try {
			stm.execute(Query);
			System.out.println("drop the Column");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("not drop column");
		}
		
		
	}
	private static void describeTable() throws IOException, SQLException 
	{
		System.out.println("enter the table ");
		tableName=br.readLine();
		try
		{
	
		PreparedStatement ps=con.prepareStatement("select * from  "+tableName);
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData rsmd=rs.getMetaData();
		int columnCount=rsmd.getColumnCount();
		
		System.out.println("ColumnName    ColumnTypeName     ColumnSize");
		System.out.println("-----------------------------------------------");
		for(int i=1;i<=columnCount;i++)
		{
			System.out.println(rsmd.getColumnName(i)+"           "+rsmd.getColumnTypeName(i)+"           "+rsmd.getColumnDisplaySize(i));
		}
	}
		catch(Exception e)
		{
			System.out.println("object "+tableName+" does not exist");
		}
		
	}
	private static void selectTable() throws IOException 
	{
		System.out.println("enter the table name");
		tableName=br.readLine();
		Query="select * from "+tableName;
		PreparedStatement ps=null;
		try 
		{
			ps = con.prepareStatement("select * from "+tableName);
			//ps.setString(1, tableName);
			ps.executeQuery();
		   
		   ResultSetMetaData rsmd=ps.getMetaData();
		   int columnCount=rsmd.getColumnCount();
			ResultSet rs=stm.executeQuery(Query);
			
			while(rs.next())
			{
			   for(int i=1;i<=columnCount;i++)
			   {
			
				   	System.out.print(rs.getObject(i)+"    ");
			    }
			   System.out.println();
			}
		
		}
		catch (SQLException e)
		{
			
			System.out.println("table doesnot Exist...");
		}
	}
	private static void deleteTable() throws IOException
	{
		System.out.println("enter the table name");
		tableName=br.readLine();
		
		Query="delete from "+tableName;
		try
		{
			stm.executeUpdate(Query);
			System.out.println("sucess the delete table");
		} catch (SQLException e)
		{
			System.out.println(" not delete table");
		
		}
		
	}
	private static void updateTable() throws IOException 
	{

		System.out.println("enter the table name");
		tableName=br.readLine();
		System.out.println("select the without Condition update(press 1) or with condition update(press 2)");
		int value=Integer.parseInt(br.readLine());
		
		if(value==1)
		{
			System.out.println("u want update value integervalue(press 1) or String value(press 2)");
			int value1=Integer.parseInt(br.readLine());
			if(value1==1)
			{
				System.out.println("enter the column Name");
				String ColumnName=br.readLine();
				System.out.println("enter the store value");
				int storeValueInteger=Integer.parseInt(br.readLine());
			      Query="update "+tableName+" set "+ColumnName+"="+storeValueInteger;
			}
			else if(value1==2)
			{
				System.out.println("enter the column Name");
				String ColumnName=br.readLine();
				System.out.println("enter the store value");
				String storeValueString=br.readLine();
			      Query="update "+tableName+" set "+ColumnName+"="+"'"+storeValueString+"'";
			}
			
		}
		else
		{
			System.out.println("u want update value integervalue(press 1) or String value(press 2)");
			int value2=Integer.parseInt(br.readLine());
			if(value2==1)
			{
				System.out.println("enter the column Name");
				String ColumnName=br.readLine();
				System.out.println("enter the store value");
				int storeValueInteger=Integer.parseInt(br.readLine());
				System.out.println("u want to choice condition value integer press(1) or String(press 2)");
				int value3=Integer.parseInt(br.readLine());
				if(value3==1)
				{
				    System.out.println("enter the condition Column Name");
				
				     String conditionColumnName=br.readLine();
				    System.out.println("enter the condition value");
				    String conditionValue=br.readLine();
				
			          Query="update "+tableName+" set "+ColumnName+"="+storeValueInteger+" where "+conditionColumnName+"="+conditionValue;
				}
				else if(value3==2)
				{
					System.out.println("enter the condition Column Name");
					
				     String conditionColumnName=br.readLine();
				    System.out.println("enter the condition value");
				    String conditionValue=br.readLine();
				
			          Query="update "+tableName+" set "+ColumnName+"="+storeValueInteger+" where "+conditionColumnName+"='"+conditionValue+"'";
					
				}
			}
			else if(value2==2)
			{
				System.out.println("enter the column Name");
				String ColumnName=br.readLine();
				System.out.println("enter the store value");
				String storeValueString=br.readLine();
				System.out.println("u want to choice condition value integer press(1) or String(press 2)");
				int value3=Integer.parseInt(br.readLine());
				if(value3==1)
				{
				    System.out.println("enter the condition Column Name");
				
				     String conditionColumnName=br.readLine();
				    System.out.println("enter the condition value");
				    String conditionValue=br.readLine();
				
			          Query="update "+tableName+" set "+ColumnName+"='"+storeValueString+"' where "+conditionColumnName+"="+conditionValue;
				}
				else if(value3==2)
				{
					System.out.println("enter the condition Column Name");
					
				     String conditionColumnName=br.readLine();
				    System.out.println("enter the condition value");
				    String conditionValue=br.readLine();
				
			          Query="update "+tableName+" set "+ColumnName+"='"+storeValueString+"' where "+conditionColumnName+"='"+conditionValue+"'";
					
				}
			}
				
			
		}
		try
		{
			
			stm.executeUpdate(Query);
			System.out.println("sucess the update");
		} catch (SQLException e) 
		{
			System.out.println("not updated");
			
			e.printStackTrace();
		}
		
	}
	private static void insertTable() throws NumberFormatException, IOException
	{
		System.out.println("how many row data entry");
		int noOfRowDataEntry=Integer.parseInt(br.readLine());
		System.out.println("enter the table name");
		tableName=br.readLine();
	PreparedStatement ps=null;
		int value=0;
		
		try {
			ps = con.prepareStatement("select * from "+tableName);
			//ps.setString(1, tableName);
			ps.executeQuery();
		   
		   ResultSetMetaData rsmd=ps.getMetaData();
		   int columnCount=rsmd.getColumnCount();
		   
		   
		  for(int j=0;j<noOfRowDataEntry;j++)
		  {  
			  Query="insert into "+tableName+" values(";
		   String data[]=new String[columnCount];
		   for(int i=0;i<columnCount;i++)
		      {
			 
			   System.out.println("Column Name:"+rsmd.getColumnName(i+1));
			   System.out.println("enter the  Column "+(i+1)+" data");
			   data[i]=br.readLine();
			   if(rsmd.getColumnTypeName(i+1).equals("NUMBER"))
			   {
		            if(i==columnCount-1)
		    	           Query=Query.concat(data[i]+")");
		             else   
		                 Query=Query.concat(data[i]+",");
			   }
			   else if(rsmd.getColumnTypeName(i+1).equals("VARCHAR2"))
			   {
				   if(i==columnCount-1)
	    	           Query=Query.concat("'"+data[i]+"')");
	             else   
	                 Query=Query.concat("'"+data[i]+"',");
			   }
		      }

		   		value=stm.executeUpdate(Query);
		   	
		   System.out.println("sucess data entry...");
		     
		  }  
		     
	     	
		}
		catch (SQLException e)
		{
			
			
		System.out.println("data not entry....");
		}
		
		
	}
	private static void dropTable()  
	{
		System.out.println("enter the  table name drop");
		 
		try {
			tableName=br.readLine();
			stm=con.createStatement();
			stm.execute("drop table "+tableName);
			System.out.println("table dropped sucesssfully");
			
		} catch (IOException | SQLException e)
		{
		
			System.out.println("table not dropped");
		}
		
		
	}
	private static void truncateTable()
	{
		System.out.println("enter the table name truncate");
		
		try {
			tableName=br.readLine();
			stm=con.createStatement();
			stm.execute("truncate table "+tableName);
			System.out.println(" table truncate  sucesssfully");
			
		} catch (IOException | SQLException e)
		{
		
			System.out.println("table not truncate");
		}
	}
	private static void renameColumn() throws IOException
	{
		System.out.println("enter the table name");
		tableName=br.readLine();
		System.out.println("enter the old Column Name");
		String oldColumnName=br.readLine();
		System.out.println("enter the new Column Name");
		String newColumnName=br.readLine();
		Query="alter table "+tableName+" rename column "+oldColumnName+" to "+newColumnName;
		try
		{
			System.out.println(Query);
			stm.execute(Query);
			System.out.println("sucess the Rename Column name");
		} catch (SQLException e)
		{
				e.printStackTrace();
			System.out.println("does not add new Column table");
		}
		
	
		
	}
	private static void modifyColumn() throws IOException 
	{
		System.out.println("enter the table name");
		tableName=br.readLine();
		System.out.println("enter the how many Column modify");
		 noOfColumn=Integer.parseInt(br.readLine());
		 String columnName[]=new String[noOfColumn];
			
		String dataType[]=new String[noOfColumn];
		String sizeofDatatype[]=new String[noOfColumn];
		//String constraintsName[]=new String[noOfColumn];
		for(int i=0;i<noOfColumn;i++)
			{
				System.out.println("enter the  "+(i+1)+" Column Data");
				
				System.out.println("enter the Column name");
						columnName[i]=br.readLine();
				System.out.println("enter the datatype");
							dataType[i]=br.readLine();
				System.out.println("enter the datatype size");
						sizeofDatatype[i]=br.readLine();
				
				       
						
			}
		 Query="alter table "+tableName+" modify ";
		
		for(int i=0;i<noOfColumn;i++)
		{
				if(noOfColumn==1)
				{
					Query= Query.concat(""+columnName[i]+"  "+dataType[i]+"("+sizeofDatatype[i]+")");
					           
					
				}
				
				else
				if(i==(noOfColumn-1))
				
				{
					Query= Query.concat(columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+"))");
				
				}
				else
					Query= Query.concat("("+columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+"),");
					
				
		
			   
		}
		
		try
		{
			stm.execute(Query);
			System.out.println("sucess the modify Column ");
		} catch (SQLException e)
		{
			
			System.out.println("does not modify Column ");
		}

		
		
	}
	private static void addNewColumn() throws NumberFormatException, IOException 
	{
		System.out.println("enter the table name");
		tableName=br.readLine();
		System.out.println("enter the how many Column add");
		 noOfColumn=Integer.parseInt(br.readLine());
		 String columnName[]=new String[noOfColumn];
			
		String dataType[]=new String[noOfColumn];
		String sizeofDatatype[]=new String[noOfColumn];
		//String constraintsName[]=new String[noOfColumn];
		for(int i=0;i<noOfColumn;i++)
			{
				System.out.println("enter the  "+(i+1)+" Column data modify");
				
				System.out.println("enter the Column name");
						columnName[i]=br.readLine();
				System.out.println("enter the datatype");
							dataType[i]=br.readLine();
				System.out.println("enter the datatype size");
						sizeofDatatype[i]=br.readLine();
				
				       
						
			}
		 Query="alter table "+tableName+" add ";
		
		for(int i=0;i<noOfColumn;i++)
		{
				if(noOfColumn==1)
				{
					Query= Query.concat(""+columnName[i]+"  "+dataType[i]+"("+sizeofDatatype[i]+")");
					           
					
				}
				
				else
				if(i==(noOfColumn-1))
				
				{
					Query= Query.concat(columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+"))");
				
				}
				else
					Query= Query.concat("("+columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+"),");
					
				
		
			   
		}

		
		try
		{
			stm.execute(Query);
			System.out.println("sucess the add new Column table");
		} catch (SQLException e)
		{
			
			System.out.println("does not add new Column table");
		}
		
		
	}

		
		
	
	private static void createTable() throws IOException 
	{
		
		String Query=null;
		System.out.println("enter the table name");
		 tableName=br.readLine();
		System.out.println("enter the how many column add in table");
		 noOfColumn=Integer.parseInt(br.readLine());
		String columnName[]=new String[noOfColumn];
		
		String dataType[]=new String[noOfColumn];
		String sizeofDatatype[]=new String[noOfColumn];
		String constraintsName[]=new String[noOfColumn];
		for(int i=0;i<noOfColumn;i++)
		{
			System.out.println("enter the  "+(i+1)+" Column Data");
			
			System.out.println("enter the Column name");
					columnName[i]=br.readLine();
			System.out.println("enter the datatype");
						dataType[i]=br.readLine();
			System.out.println("enter the datatype size");
					sizeofDatatype[i]=br.readLine();
			System.out.println("enter the Constraints of the column");
					
			        constraintsName[i]=br.readLine();
			       
					
		}
		Query="create table "+tableName;
		for(int i=0;i<noOfColumn;i++)
		{
				if(noOfColumn==1)
				{
					Query= Query.concat("("+columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+") "+constraintsName[i]+")");
					
				}
				else
				if(i==(noOfColumn-1))
				{
					Query= Query.concat(""+columnName[i]+" "+
					           dataType[i]+"("+sizeofDatatype[i]+") "+constraintsName[i]+")");
				}
				else
				{
					if(i==0)
						Query= Query.concat("("+columnName[i]+" "+
								dataType[i]+"("+sizeofDatatype[i]+") "+constraintsName[i]+",");
					else
							Query= Query.concat(""+columnName[i]+" "+
									dataType[i]+"("+sizeofDatatype[i]+") "+constraintsName[i]+",");
				 
						
				}
		
			   System.out.println(Query);
		}
		
		try
		{
		    stm.execute(Query);
			System.out.println("sucess the create table");
		} catch (SQLException e)
		{
				e.printStackTrace();
			System.out.println("does not create table");
		}
		
		
	}

}
