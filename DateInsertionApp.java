package Assignment2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class DateInsertionApp
{

	public static void main(String[] args)
	{
		System.out.println("___Welcome to Date Insertion Application___");
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Employee name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Employee address : ");
		String address = scanner.nextLine();

		System.out.print("Enter Employee DOB(dd-MM-yyyy) : ");
		String userDob = scanner.nextLine();

		System.out.print("Enter Employee DOJ(MM-dd-yyyy) : ");
		String userDoj = scanner.nextLine();

		System.out.print("Enter Employee DOM(yyyy-MM-dd) : ");
		String userDom = scanner.nextLine();
		try
		{
			SimpleDateFormat dfDob = new SimpleDateFormat("dd-MM-yyyy");
			// converting string to util.Date type using SimpleDateFormat class
			java.util.Date utilDob = dfDob.parse(userDob);
			// getting date in the form of milliseconds
			long lDob = utilDob.getTime();
			// getting sql.Date by passing long type (date)
			java.sql.Date sDob = new Date(lDob);

			SimpleDateFormat dfDoj = new SimpleDateFormat("MM-dd-yyyy");
			// converting string to util.Date type using SimpleDateFormat class
			java.util.Date utilDoj = dfDoj.parse(userDoj);
			// getting date in the form of milliseconds
			long lDoj = utilDoj.getTime();
			// getting sql.Date by passing long type (date)
			java.sql.Date sDoj = new Date(lDoj);

			
			//Direct Conversion from string if it is same as the which it should be saved in db.
			java.sql.Date sDom = java.sql.Date.valueOf(userDom);
			DateInsertionApp dia = new DateInsertionApp();
			dia.jdbc(name, address, sDob, sDoj, sDom);
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally {
			if(scanner != null)
				scanner.close();
		}
	}

	public void jdbc(String name, String address, java.sql.Date dob, java.sql.Date doj, java.sql.Date dom)
	{
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/javadb");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		Connection connection = null;
		PreparedStatement pstmt = null;
		try
		{
			connection = dataSource.getConnection();
			String sqlInsertQuery = "insert into employee(`name`,`address`,`dob`,`doj`,`dom`) values(?,?,?,?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			 if(pstmt != null) { 
				 pstmt.setString(1,name);
				 pstmt.setString(2,address);
				 pstmt.setDate(3,dob);
				 pstmt.setDate(4,doj);
				 pstmt.setDate(5,dom);
				 int RowsEffected = pstmt.executeUpdate();
				 if(RowsEffected == 1)
					 System.out.println("Successfully Record inserted.");
				 else
					 System.out.println("Record not inserted.");
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
