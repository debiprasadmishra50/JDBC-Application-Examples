import java.sql.*;
public class DBMD_EX 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		Connection con1 = Provider.getMysqlConnection();
		
		DatabaseMetaData dbmd = con.getMetaData();
		DatabaseMetaData dbmd1 = con1.getMetaData();
		
		System.out.println("For Oracle\n------------------");
		System.out.println(dbmd.getDatabaseMajorVersion());
		System.out.println(dbmd.getDatabaseMinorVersion());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.getDatabaseProductVersion());
		
		System.out.println(dbmd.getMaxColumnNameLength());
		System.out.println(dbmd.getMaxColumnsInGroupBy());
		System.out.println(dbmd.getMaxColumnsInOrderBy());
		System.out.println(dbmd.getMaxColumnsInSelect());
		System.out.println(dbmd.getMaxColumnsInIndex());
		System.out.println(dbmd.getMaxTableNameLength());
		System.out.println(dbmd.getMaxTablesInSelect());
		System.out.println(dbmd.getMaxColumnsInTable());
		
		System.out.println("\n\nFor MySql\n----------------");
		System.out.println(dbmd1.getDatabaseMajorVersion());
		System.out.println(dbmd1.getDatabaseMinorVersion());
		System.out.println(dbmd1.getDatabaseProductName());
		System.out.println(dbmd1.getDatabaseProductVersion());
		
		System.out.println(dbmd1.getMaxColumnNameLength());
		System.out.println(dbmd1.getMaxColumnsInGroupBy());
		System.out.println(dbmd1.getMaxColumnsInOrderBy());
		System.out.println(dbmd1.getMaxColumnsInSelect());
		System.out.println(dbmd1.getMaxColumnsInIndex());
		System.out.println(dbmd1.getMaxTableNameLength());
		System.out.println(dbmd1.getMaxTablesInSelect());
		System.out.println(dbmd1.getMaxColumnsInTable());
	}
}
