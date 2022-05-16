package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {
	
	//A common method to connect to the DB
	private Connection connect(){
		
		 Connection con = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName,username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbitems", "root", "");
		 }
		 catch (Exception e){
			 e.printStackTrace();
		 	}
		 	
		 	return con;
		 }
		
	//Method to insert Billing to the system
	public String insertBilling(String CustomerID, String BillNumber, String PrevReading, String CurReading, String DueAmount, String BillDate){
		
		 String output = "";
		 
		 try{
			 
			 Connection con = connect();
		
			 if (con == null){
				 return "Error while connecting to the database for inserting."; 
				}
		 
			 // create a prepared statement
			 String query = "insert into Billing(`BID`,`CID`,`BN`,`PR`,`CR`,`DA`,`BD`)" + " values (?, ?, ?, ?, ?,?,?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, CustomerID);
			 preparedStmt.setString(3, BillNumber);
			 preparedStmt.setString(4, PrevReading);
			 preparedStmt.setString(5, CurReading);
			 preparedStmt.setString(4, DueAmount);
			 preparedStmt.setString(5, BillDate);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			  
			 String newBilling = readBilling() ;
			 output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}"; 
		 }
		 	catch (Exception e){	
		 		output = "{\"status\":\"error\", \"data\": \"Error while inserting the Billing.\"}";
		 				 System.err.println(e.getMessage()); 
		 				 e.printStackTrace();
		 		}
		 	
		 return output;
	}
		

	//---------------method to view all the Billing------------------------
	public String readBilling(){
		
		 String output = "";
		
		 try{
			 Connection con = connect(); 
			 if (con == null){
				 return "Error while connecting to the database for reading."; }
		
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Customer ID</th><th>Bill Number</th>" +
					 "<th>Previous Reading</th>" +
					 "<th>Current Reading</th>" + 
					 "<th>Due Amount</th>" +
					 "<th>Bill Date</th>" +
					 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from Billing";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next()){			 
				 String BillID = Integer.toString(rs.getInt("BID"));
				 String CustomerID = rs.getString("CID");
				 String BillNumber = rs.getString("BN");
				 String PrevReading = rs.getString("PR");
				 String CurReading = rs.getString("CR");
				 String DueAmount = rs.getString("DA");
				 String BillDate = rs.getString("BD");
				 	
				 // Add into the html table
				 output += "<tr><td>" + CustomerID + "</td>";
				 output += "<td>" + BillNumber + "</td>";
				 output += "<td>" + PrevReading + "</td>";
				 output += "<td>" + CurReading + "</td>";
				 output += "<td>" + DueAmount + "</td>";
				 output += "<td>" + BillDate + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-Billingid='" + BillID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-Billingid='" + BillID + "'></td></tr>"; 
			 }
			 	
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 	catch (Exception e){
		 		output = "Error while reading the Billing.";
		 		System.err.println(e.getMessage());
		 	}
		 
		 	return output;
	}	
	//-------------------------method to update Billing informations by id---------------------
	public String updateBilling(String CustomerID, String BillNumber, String PrevReading, String CurReading, String DueAmount, String BillDate){
		
			 String output = "";
			
			 try{
				  Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for updating."; }
			
				  // create a prepared statement
				  String query = "UPDATE Billing SET CustomerID=?,BillNumber=?,PrevReading=?,CurReading=?,DueAmount=?,BillDate=? WHERE BillID=?";
			 	  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 	  // binding values
			 	  preparedStmt.setString(1, CustomerID);
			 	  preparedStmt.setString(2, BillNumber);
			 	  preparedStmt.setString(3, PrevReading);
			 	  preparedStmt.setString(4, CurReading);
			 	 preparedStmt.setString(5, DueAmount);
			 	preparedStmt.setString(6, BillDate);
			 
			 	  // execute the statement
			 	  preparedStmt.execute();
			 	  con.close();
			 	  String newBilling = readBilling();
				 	 output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
				 }
				 	catch (Exception e){
				 		output = "{\"status\":\"error\", \"data\":\"Error while updating the Billing.\"}";
				 				 System.err.println(e.getMessage());
				 	}
			 return output;
	}
			

	//--------------------method to delete by id ----------------------------------
	public String deleteBilling(String ID){
		
			 String output = "";
			 
			 try{	 
				 Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for deleting."; }
			 
				  // create a prepared statement
				  String query = "delete from Billing where ID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
				  // binding values
				  preparedStmt.setInt(1, Integer.parseInt(ID));
			 
				  // execute the statement
				  preparedStmt.execute();
				  con.close();
				  
				  String newBilling = readBilling();
				  output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}"; 
			 }
			 	catch (Exception e){
			 		output = "{\"status\":\"error\", \"data\": \"Error while deleting the Billing.\"}";
			 				 System.err.println(e.getMessage());
			 	  }
			 return output;
	  } 
	//-----------------------------------------------------------------------------------
}

