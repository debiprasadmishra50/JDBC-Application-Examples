import java.sql.*;
public class Statement_Example 
{
	public static void main(String[] args) throws Exception 
	{
		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		String sql = null;
		boolean result = false;
		
	//	sql = "drop table Java_Course";
		//result = st.execute(sql);
	//	System.out.println(result);
		
		sql = "Create table Java_Course (cid number(4) Primary key , cname varchar2(20) , cfees number(6,1) )";
		result = st.execute(sql);
		System.out.println(result);
		
		//Add a new column i.e. EmailID & phone no
		
		sql = "Alter table Java_Course add (EmailID varchar(40) Not null, phone_no number(10) Not null Unique)";
		result = st.execute(sql);
		System.out.println(result);
		
		/*
		sql = "Rename Java_Course to Java_Course";
		result = st.execute(sql);
		System.out.println(result);
		*/
		
		sql = "Alter table Java_Course modify (phone_no  varchar2(10))";
		result = st.execute(sql);
		System.out.println(result);
		
		sql = "Alter table Java_Course rename column EmailID to email";
		result = st.execute(sql);
		System.out.println(result);
		
		sql = "Alter table Java_Course drop (phone_no)";
		result = st.execute(sql);
		System.out.println(result);
		
		sql = "Truncate table Java_Course";
		result = st.execute(sql);
		System.out.println(result);
	}
}

/* H:W
 * 1. Rename the table from Course to Java_Course. (done)
 * 2. Delete the datatype of phone_no from number to varchar2 (done)
 * 3. Change the column name from EmailID to email (done)
 * 4. Delete phone_no column (done)
 * 5. Execute the Truncate statement (done)
 */
