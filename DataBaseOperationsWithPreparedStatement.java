package Assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class DataBaseOperationsWithPreparedStatement
{
	Integer sId;
	String name;
	Integer cId;
	String action;
	String query;

	public static void main(String[] args)
	{

		System.out.println("________Welcome to JDBC Application With PreparedStatement_________");

		System.out.println("1.Insert 2.Update 3.Read 4.Delete 5.Exit");
		System.out.println("Please enter the option number to perform corresponding operations.");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		DataBaseOperationsWithPreparedStatement jdbc = new DataBaseOperationsWithPreparedStatement();
		switch (option)
		{
		case 1:
			jdbc.getInputsFromUserToInsert();
			break;
		case 2:
			jdbc.getInputsFromUserToUpdate();
			break;
		case 3:
			jdbc.getInputsFromUserToRead();
			break;
		case 4:
			jdbc.getInputsFromUserToDelete();
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input.Please select valid input.");
		}

	}

	public void getInputsFromUserToInsert()
	{
		try
		{
			this.action = "insert";
			System.out.print("Enter student id: ");
			Scanner scan = new Scanner(System.in);
			this.sId = scan.nextInt();
			System.out.print("\nEnter student name: ");
			this.name = scan.next();
			do
			{
				System.out.println("\nEnter course id : 1.JAVA 2.JAVASCRIPT 3.BLOCKCHAIN 4.SPRINGBOOT.");
				this.cId = scan.nextInt();
			} while (this.cId > 4 || this.cId < 1);
			scan.close();
			jdbc();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void getInputsFromUserToUpdate()
	{
		try
		{
			this.action = "update";
			System.out.print("Enter student id: ");
			Scanner scan = new Scanner(System.in);
			this.sId = scan.nextInt();
			System.out.println("Enter student name to update : ");
			Scanner scanName = new Scanner(System.in);
			this.name = scanName.nextLine();
			jdbc();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void getInputsFromUserToRead()
	{
		try
		{
			this.action = "read";
			System.out.print("Enter student id: ");
			Scanner scan = new Scanner(System.in);
			this.sId = scan.nextInt();
			scan.close();
			jdbc();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void getInputsFromUserToDelete()
	{
		try
		{
			this.action = "delete";
			System.out.print("Enter student id: ");
			Scanner scan = new Scanner(System.in);
			this.sId = scan.nextInt();
			scan.close();
			jdbc();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void jdbc() throws SQLException
	{
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setURL("jdbc:mysql://localhost:3306/javadb");
		ds.setUser("root");
		ds.setPassword("root");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			connection = ds.getConnection();
			if (this.action != null && this.action.equalsIgnoreCase("insert"))
			{
				
				query = "insert into students values(?,?,?)";
				if (connection != null)
					pstmt = connection.prepareStatement(query);
				if (pstmt != null)
				{
					pstmt.setInt(1, sId);
					pstmt.setString(2, name);
					pstmt.setInt(3, cId);
					int noOfRows = pstmt.executeUpdate();
					if (noOfRows == 1)
						switch (cId)
						{
						case 1:
							System.out.println(sId+" Succesfully registered for the JAVA course.");
							break;
						case 2:
							System.out.println(sId+" Succesfully registered for the JAVASCRIPT course.");
							break;
						case 3:
							System.out.println(sId+" Succesfully registered for the BLOCKCHAIN course.");
							break;
						case 4:
							System.out.println(sId+" Succesfully registered for the SPRINGBOOT course.");
							break;
						}
				}
			}
			else if (this.action != null && this.action.equalsIgnoreCase("update"))
			{
				query = "update students set s_name = ? where s_id = ?";
				if (connection != null)
					pstmt = connection.prepareStatement(query);
				if (pstmt != null)
				{
					pstmt.setString(1, name);
					pstmt.setInt(2, sId);
					int noOfRows = pstmt.executeUpdate();
					if(noOfRows != 0)
					System.out.println("Succesfully modified name for the student_id " + this.sId);
					else
					System.out.println("No records found for the student id :" + this.sId);
				}
			} else if (this.action != null && this.action.equalsIgnoreCase("read"))
			{
				query = "select s_id,s_name,s_c_id from students where s_id= ? ";
				if (connection != null)
					pstmt = connection.prepareStatement(query);
				if (pstmt != null) {
					pstmt.setInt(1, sId);
					resultSet = pstmt.executeQuery();
				}
				
				if (resultSet.next())
				{
					do{
						String sid = resultSet.getString("s_id");
						String name = resultSet.getString("s_name");
						String cid = resultSet.getString("s_c_id");
						System.out.println("Student Id : "+sid + "\nStudent Name : " + name + "\nStudent Course Id : " + cid);
					}while(resultSet.next());
					
				} 
				else 
					System.out.println("No records found for the student id :" + this.sId);

			} else if (this.action != null && this.action.equalsIgnoreCase("delete"))
			{
				query = "delete from students where s_id = ? ";
				if (connection != null)
					pstmt = connection.prepareStatement(query);
				if (pstmt != null) {
					pstmt.setInt(1, sId);
					int noOfRows = pstmt.executeUpdate();
					if(noOfRows != 0)
						System.out.println("Succesfully deleted record from student table for the id :" + this.sId);
					else
						System.out.println("No records found for the student id :" + this.sId);
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (resultSet != null)
				resultSet.close();
			if (pstmt != null)
				pstmt.close();
			if (connection != null)
				connection.close();
		}
	}

}
	
