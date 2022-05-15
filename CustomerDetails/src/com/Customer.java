package com; 
import java.sql.*;

public class Customer 
{ 	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertCus(String wiringMethod, String fullName, String phoneNo, String electricID) 
			 { 
			 String output = ""; 
			 try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database";
					}
					// create a prepared statement
					String query = " insert into customer(`PID`,`wiringMethod`,`fullName`,`phoneNo`,`electricID`)"
							+ " values (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, wiringMethod);
					preparedStmt.setString(3, fullName);
					preparedStmt.setDouble(4, Double.parseDouble(phoneNo));
					preparedStmt.setString(5, electricID);

					// execute the statement
					preparedStmt.execute();
					con.close();
					String newCus = readCone(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newCus + "\"}";
					output = "Inserted successfully";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the items.\"}"; 
					 System.err.println(e.getMessage());
				}
				return output;
			} 
			
	public String readCone()
	{ 
	 String output = ""; 
	 try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>wiringMethod</th>" + "<th>fullName</th><th>phoneNo</th>"
					+ "<th>electricID</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String PID = Integer.toString(rs.getInt("PID"));
				String wiringMethod = rs.getString("wiringMethod");
				String fullName = rs.getString("fullName");
				String phoneNo = Double.toString(rs.getDouble("phoneNo"));
				String electricID = rs.getString("electricID");
				// Add a row into the html table
				output += "<tr><td>" + wiringMethod + "</td>";
				output += "<td>" + fullName + "</td>";
				output += "<td>" + phoneNo + "</td>";

				output += "<td>" +electricID + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-pid='" + PID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-pid='" + PID + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
		
	public String updateCus(String ID, String wiringMethod, String fullName, String phoneNo, String electricID) 
			 { 
			 String output = ""; 
			 try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE customer SET wiringMethod=?,fullName=?,phoneNo=?,electricID=? WHERE PID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, wiringMethod);
					preparedStmt.setString(2, fullName);
					preparedStmt.setDouble(3, Double.parseDouble(phoneNo));
					preparedStmt.setString(4, electricID);
					preparedStmt.setInt(5, Integer.parseInt(ID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					String newPay = readCone(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPay + "\"}"; 
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while updating the items.\"}"; 
					 System.err.println(e.getMessage()); 
				}
				return output;
			}
			 
	public String deleteCus(String PID) 
	 { 
	 String output = ""; 
	 try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where PID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCus = readCone(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newCus + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the items.\"}"; 
			 System.err.println(e.getMessage());
		}
		return output;
	}
} 