package Assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class DateRetrievalApp
{

	public static void main(String[] args)
	{
		System.out.println("___Welcome to Date Retrival Application___");
		Scanner scanner = new Scanner(System.in);
		try
		{
			System.out.print("Enter Employee name : ");
			String name = scanner.nextLine();
			DateRetrievalApp dra = new DateRetrievalApp();
			dra.jdbc(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			
			if(scanner != null)
				scanner.close();
		}
		
	}
	public void jdbc(String name)
	{
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/javadb");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try
		{
			connection = dataSource.getConnection();
			String sqlSelectQuery = "select name,address,dob,doj,dom from employee where name = ?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			 if(pstmt != null) { 
				 pstmt.setString(1,name);
				 
				 resultSet = pstmt.executeQuery();
				 if(resultSet != null && resultSet.next()) {
						 String ename = resultSet.getString(1);
						 String eaddress = resultSet.getString(2);
						 java.sql.Date edob = resultSet.getDate(3);
						 java.sql.Date edoj = resultSet.getDate(4);
						 java.sql.Date edom = resultSet.getDate(5);//default (yyyy-MM-dd)
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
						String sDob = sdf1.format(edob);
						String sDoj = sdf2.format(edoj);
						 System.out.print("\nName : "+ename+"\nAddress : "+eaddress+"\nDOB : "+sDob+"\nDOJ : "+sDoj+"\nDOM : "+edom);
				 }
				 else
					 System.out.print("\nNo Records Found for the name :"+name);
			 }
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try
			{
				if(pstmt != null)
					pstmt.close();
				if(connection != null)
					connection.close();
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
}
