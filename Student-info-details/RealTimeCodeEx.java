package com.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RealTimeCodeEx {

	public static void main(String[] args) {
		// Resource declarations
		Scanner scanner = null;
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loaded successfully....");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "nitish");
			System.out.println("connection created successfully.....");
			statement = connection.createStatement();
			scanner = new Scanner(System.in);
			int x = statement.executeUpdate("create table student(id number,name varchar2(20),rollno number)");
			System.out.println(x + " table crated successfully.....");
			while (true) {
				System.out.println("enter student id number");
				int id = scanner.nextInt();
				System.out.println("enter student name");
				String name = scanner.next();
				System.out.println("enter student rollno");
				int rollno = scanner.nextInt();
				int inst = statement
						.executeUpdate("insert into student values(" + id + ",'" + name + "'," + rollno + ")");
				System.out.println(inst + "record inserted successfully....." + "\n");
				System.out.println("Do you want to insert one more record [yes/no]");
				String y = scanner.next();
				if (y.equalsIgnoreCase("yes")) {
					continue;
				}
				break;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (scanner != null)
					scanner.close();
				System.out.println("Resources are released.....");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
