package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class employee {

		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
				con = 
						DriverManager.getConnection( 
								"jdbc:mysql://127.0.0.1:3306/employee", "root", "9909"); 
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace(); 
			} 
			return con; 
		} 
		public String readEmployee() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for reading."; 
				} 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Employee Code</th> <th>Employee Name</th><th>Employee Phone</th>"+ "<th>Employee Description</th> <th>Update</th><th>Remove</th></tr>"; 
				String query = "select * from employee"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String employeeID = Integer.toString(rs.getInt("employeeID")); 
					String employeeCode = rs.getString("employeeCode"); 
	 
					String employeeName = rs.getString("employeeName"); 
					String employeePhone = rs.getString("employeePhone"); 
					String employeeDesc = rs.getString("employeeDesc"); 
					// Add into the html table
					output += "<tr><td><input id='hidEmployeeIDUpdate' name='hidEmployeeIDUpdate' type='hidden' value='" + employeeID+ "'>" + employeeCode + "</td>"; output += "<td>" + employeeName + "</td>"; output += "<td>" + employeePhone + "</td>"; output += "<td>" + employeeDesc + "</td>"; 
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger'  data-employeeid='" + employeeID + "'>" + "</td></tr>"; 
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the employee."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String insertEmployee(String code, String name, String phone, String desc) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for inserting."; 
				} 
				// create a prepared statement
				String query = " insert into employee (`employeeID`,`employeeCode`,`employeeName`,`employeePhone`,`employeeDesc`)" + " values (?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, code); 
				preparedStmt.setString(3, name); 
				preparedStmt.setString(4, phone); 
				preparedStmt.setString(5, desc); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newEmployee = readEmployee(); 
				output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the employee.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String updateEmployee(String ID, String code, String name, String phone, String desc) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for updating."; 
				} 
				// create a prepared statement
				String query = "UPDATE employee SET employeeCode=?,employeeName=?,employeePhone=?,employeeDesc=? WHERE employeeID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, code); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, phone); 
				preparedStmt.setString(4, desc); 
				preparedStmt.setInt(5, Integer.parseInt(ID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newEmployee = readEmployee(); 
				output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while updating the employee.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String deleteEmployee(String employeeID) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for deleting."; 
				} 
				// create a prepared statement
				String query = "delete from employee where employeeID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(employeeID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newEmployee = readEmployee(); 
				output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the employee.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
	}










	
	
	


