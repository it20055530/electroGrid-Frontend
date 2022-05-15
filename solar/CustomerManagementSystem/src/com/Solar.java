package com;
import java.sql.*;

public class Solar {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3111/electrogrid", "root", "");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertSolar(String customername, String paneltype, String generatepower, String noOfPanels)
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
			String query = " insert into solar (`ID`,`customerName`,`panelType`,`generatePower`,`noOfPanels`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customername);
			preparedStmt.setString(3, paneltype);
			preparedStmt.setString(4, generatepower);
			preparedStmt.setString(5, noOfPanels);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newSolar = readSolar();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the solar.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readSolar()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Customer Name</th>"
							+"<th>Panel Type</th>"
							+ "<th>Generate Power</th>"
							+ "<th>No Of Panels</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from solar";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String ID  = Integer.toString(rs.getInt("ID"));
						String customername  = rs.getString("customername");
						String paneltype  = rs.getString("paneltype");
						String generatepower =rs.getString("generatepower");
						String noOfPanels  = rs.getString("noOfPanels");



						// Add a row into the html table
						output += "<tr><td><input id='hidSolarIDUpdate'name='hidSolarIDUpdate'type='hidden' value='" + ID  + "'>"+ customername  + "</td>";
						output += "<td>" + paneltype + "</td>";
						output += "<td>" + generatepower + "</td>";
						output += "<td>" + noOfPanels + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-solarid='" + ID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-solarid='" + ID + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the solar.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deleteSolar(String ID)
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
			String query = "delete from solar where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newSolar = readSolar();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the solar.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updateSolar(String ID, String customername, String paneltype,String generatepower,String noOfPanels)
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
			String query = "UPDATE solar SET customerName=?,panelType=?,generatePower=?,noOfPanels=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, customername);
			preparedStmt.setString(2, paneltype);
			preparedStmt.setString(3, generatepower);
			preparedStmt.setString(4, noOfPanels);
			preparedStmt.setInt(6, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated Solar details successfully";
			String newSolar = readSolar();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Solar Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the solar.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

