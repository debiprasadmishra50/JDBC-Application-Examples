import java.sql.*;
public class Scrollable_Ex 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select roll,name,cgpa from Student";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = st.executeQuery(sql);
		
		System.out.println("Forward Traversing...");
		while(rs.next())
		{
			System.out.println(rs.getInt(1) +"\t"+ rs.getString(2)+"\t"+rs.getDouble(3)); //Requires minimun 5 records to be present in table else will show error
		}
		
		System.out.println("Backward Traversing...");
		while(rs.previous())
		{
			System.out.println(rs.getInt(1) +"\t"+ rs.getString(2)+"\t"+rs.getDouble(3)); //Requires minimun 5 records to be present in table else will show error
		}
		
		rs.absolute(2);
		System.out.println("Record present in 2nd row");
		System.out.println(rs.getInt(1) +"\t"+ rs.getString(2)+"\t"+rs.getDouble(3));
		
		System.out.println("---------------------------------------------------------------");
		
		System.out.println(rs.getRow());
		System.out.println(rs.isFirst());
		System.out.println(rs.isLast());
		System.out.println(rs.isBeforeFirst());
		System.out.println(rs.isAfterLast());
		
		System.out.println("----------------------------------------------------------------");
		
		rs.first();
		System.out.println(rs.getRow());
		rs.last();
		System.out.println(rs.getRow());
		rs.beforeFirst();
		System.out.println(rs.getRow());
		rs.afterLast();
		System.out.println(rs.getRow());
		
		rs.absolute(3);
		//rs.deleteRow(); //Invalid operation for read only resultset: deleteRow //asResultSet is readonly type
	}
}

