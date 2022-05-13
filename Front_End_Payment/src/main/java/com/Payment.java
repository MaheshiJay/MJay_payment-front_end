package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	//db connection
		public Connection connect(){
			   
			 Connection con = null;
		   
			 try{
		        Class.forName("com.mysql.jdbc.Driver");
		        con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg_db","PAF_user1", "paf_user1");
		
		        //For testing
		        System.out.print("Successfully connected");
		
			 }catch(Exception e){
		         e.printStackTrace();
		     }
			 
		     return con;
	   }
		
		//insert method
		public String insertPayment(String billID, String paymentAmount, String creditCardType, String cardNo,String expirationalMonth,String expirationalYear,String cvn){
		
			String output = "";
		
			try{
		      Connection con = connect();
		      
		      if (con == null){
		         return "Error while connecting to the database";
	     	  }
		
		      // create a prepared statement
		      String query = " insert into payment(`paymentID`,`billID`,`paymentAmount`,`creditCardType`,`cardNo`,`expirationalMonth`,`expirationalYear`,`cvn`)"
		                                        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      // binding values
		   
		      preparedStmt.setInt(1, 0);
		      preparedStmt.setInt(2, Integer.parseInt(billID));
		      preparedStmt.setDouble(3, Double.parseDouble(paymentAmount));
		      preparedStmt.setString(4, creditCardType);
		      preparedStmt.setString(5, cardNo);
		      preparedStmt.setString(6, expirationalMonth);
		      preparedStmt.setString(7, expirationalYear);
		      preparedStmt.setString(8, cvn);
				
		      //execute the statement
		      preparedStmt.execute();
		      con.close();
		      
		      String newUPayments = readPaymentDetails();
			  output = "{\"status\":\"success\", \"data\": \"" + newUPayments + "\"}"; 
			 
		      
		      }
		      catch (Exception e){
		    	  output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";
		    	  System.err.println(e.getMessage());
		      }
		      return output;
		  }
		
		//read data
		public String readPaymentDetails(){
		
			String output = "";
		    
			//connect data base
			try{
		       Connection con = connect();
		
		       if (con == null){
		          return "Error while connecting to the database for reading.";
		       }
		       
		   // Prepare the html table to be displayed
		   output = "<table border='2'><tr>"
		            +"<th>Bill ID</th><th>Payment Amount</th>"
		            + "<th>Card Type</th>"
		            + "<th>Card No</th>"
		            + "<th>Expire Month</th>"
		            + "<th>Expire Year</th><th>cvn</th>"
		            + "<th>Update</th><th>Remove</th></tr>";
		   
		   //read data
		   String query = "select * from payment";
	       Statement stmt = con.createStatement();
	       //Assign read values 
		   ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		   while (rs.next()){
		      String paymentID = Integer.toString(rs.getInt("paymentID"));
		      String billID = Integer.toString(rs.getInt("billID"));
			  String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
		      String creditCardType = rs.getString("creditCardType");
		      String cardNo = rs.getString("cardNo");
		      String expirationalMonth = rs.getString("expirationalMonth");
		      String expirationalYear = rs.getString("expirationalYear");
		      String cvn = rs.getString("cvn");
				
		      // Add a row into the html table
		      output += "<td>" + billID + "</td>";
		      output += "<td>" + paymentAmount + "</td>";
		      output += "<td>" + creditCardType + "</td>";
		      output += "<td>" + cardNo + "</td>";
		      output += "<td>" + expirationalMonth + "</td>";
		      output += "<td>" + expirationalYear + "</td>";
		      output += "<td>" + cvn + "</td>";
			  
		      // buttons
		      output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-paymentid='" + paymentID + "'></td>"
		    		  + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'data-paymentid='"+ paymentID + "'>" + "</td></tr>";
		 }
		 con.close();
		
		 // Complete the html table
		 output += "</table>";
		}
		
		catch (Exception e){
		   output = "Error While Reading Payment Details";
		   System.err.println(e.getMessage());
		}
		return output;
	}
		
		
		//delete
		public String deletePaymentDetails(String paymentID){
		     
			  String output = "";
		
			  try{
		         Connection con = connect();
		
		         if (con == null){
		             return "Error while connecting to the database for deleting.";
		         }
		         
		         // create a prepared statement
		         String query = "delete from payment where paymentID=?";
		         PreparedStatement preparedStmt = con.prepareStatement(query);
		
		         // binding values
		         preparedStmt.setInt(1, Integer.parseInt(paymentID));
		
		         // execute the statement
		         preparedStmt.execute();
		         con.close();
		        
		         String newPayments = readPaymentDetails();
				 output = "{\"status\":\"success\", \"data\": \"" +
						 newPayments + "\"}"; 

		    }
		    catch (Exception e){
		        output = "Error while deleting the item.";
		        System.err.println(e.getMessage());
		    }
		    return output;
		}
		
		//update
		public String updatePayment(String paymentID, String billID, String paymentAmount, String creditCardType, String cardNo,String expirationalMonth,String expirationalYear,String cvn) {
					String output = "";
					
					try{
					   Connection con = connect();
					  
					   if (con == null){
						   return "Error while connecting to the database for updating."; 
					   }
					 
					   // create a prepared statement
					   String query = "UPDATE payment SET billID=?,paymentAmount=?,creditCardType=?,cardNo=?,expirationalMonth=?,expirationalYear=?,cvn=? WHERE paymentID=?";
					   PreparedStatement preparedStmt = con.prepareStatement(query);
					
					   // binding values
					   preparedStmt.setInt(1, Integer.parseInt(billID));
					   preparedStmt.setDouble(2, Double.parseDouble(paymentAmount));
					   preparedStmt.setString(3, creditCardType);
					   preparedStmt.setString(4, cardNo);
					   preparedStmt.setString(5, expirationalMonth);
					   preparedStmt.setString(6, expirationalYear);
					   preparedStmt.setString(7, cvn);
					   preparedStmt.setInt(8, Integer.parseInt(paymentID));
						 	  
					   
					   // execute the statement
					   preparedStmt.execute();
					   con.close();
					  
					   String newPayments = readPaymentDetails();
					   output = "{\"status\":\"success\", \"data\": \"" +
	                  newPayments + "\"}";
					   
				  }catch (Exception e){
					  output = "Error While Updating the Payment Details.";
					  System.err.println(e.getMessage());
				  }
					return output;
			}
}
