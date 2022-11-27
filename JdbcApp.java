package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcApp
{
	Integer sId;
	String name;
	Integer cId;
	String action;
	String query;

	public static void main(String[] args)
	{

		System.out.println("________Welcome to JDBC Application_________");
		
		System.out.println("1.Insert 2.Update 3.Read 4.Delete 5.Exit");
		System.out.println("Please enter the option number to perform corresponding operations.");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		JdbcApp jdbc = new JdbcApp();
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
		case 4:jdbc.getInputsFromUserToDelete();
			break;
		case 5:System.exit(0);
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
			// call jdbc
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
			// scan.close();
			System.out.println("Enter student name to update : ");
			Scanner scanName = new Scanner(System.in);
			this.name = scanName.nextLine();
			System.out.println("__________" + this.name);
			// scanName.close();
			// call jdbc
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
			// call jdbc
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
			// call jdbc
			jdbc();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void jdbc() throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String url = "jdbc:mysql://localhost:3306/javadb";
		String uname = "root";
		String pswd = "root";

		try
		{
			connection = DriverManager.getConnection(url, uname, pswd);
			if (connection != null)
				statement = connection.createStatement();
			if (this.action != null && this.action.equalsIgnoreCase("insert"))
			{
				query = String.format("insert into students values(%d,'%s',%d)", this.sId, this.name, this.cId);
				System.out.println(query);
				int noOfRows = statement.executeUpdate(query);
				// System.out.println(noOfRows + " effected Rows.");
				if (noOfRows == 1)
					switch (cId)
					{
					case 1:
						System.out.println("Succesfully registered for the JAVA course.");
						break;
					case 2:
						System.out.println("Succesfully registered for the JAVASCRIPT course.");
						break;
					case 3:
						System.out.println("Succesfully registered for the BLOCKCHAIN course.");
						break;
					case 4:
						System.out.println("Succesfully registered for the SPRINGBOOT course.");
						break;
					}
			} else if (this.action != null && this.action.equalsIgnoreCase("update"))
			{
				query = String.format("update students set s_name ='%s' where s_id= %d ", this.name, this.sId);
				System.out.println(query);
				int noOfRows = statement.executeUpdate(query);
				// System.out.println(noOfRows + " effected Rows.");
				if (noOfRows == 1)
					System.out.println("Succesfully modified name for the student_id " + this.sId);
			} else if (this.action != null && this.action.equalsIgnoreCase("read"))
			{
				query = String.format("select s_id,s_name,s_c_id from students where s_id= %d ", this.sId);
				System.out.println(query);
				resultSet = statement.executeQuery(query);
				// System.out.println(noOfRows + " effected Rows.");
				System.out.println("Id\tName\tCourseId");

				while (resultSet.next())
				{
					String sid = resultSet.getString("s_id");
					String name = resultSet.getString("s_name");
					String cid = resultSet.getString("s_c_id");
					System.out.println(sid + "\t" + name + "\t" + cid);
				}
			}else if (this.action != null && this.action.equalsIgnoreCase("delete"))
			{
				query = String.format("delete from students where s_id= %d ", this.sId);
				System.out.println(query);
				int noOfRows = statement.executeUpdate(query);
				 System.out.println(noOfRows + " effected Rows.");
				 System.out.println("Succesfully deleted record from student table for the id :"+this.sId);			
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
	}

}
