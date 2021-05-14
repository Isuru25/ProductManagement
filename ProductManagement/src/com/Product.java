package com;

import java.sql.*; 
public class Product 
{ //Connection to the DB

private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //DB access denied 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/products", "root", "sahana123"); 
 } 
    catch (Exception e) 
    {e.printStackTrace();} 
 
    return con; 
 } 


public String insertProduct (String name, String category, String description, String price) 
{ 
  String output = ""; 

try
{ 
	 
   Connection con = connect(); 
   
   if (con == null) 
   {return "Error while connecting to the database for inserting."; } 

   // create a prepared statement
   String query = " INSERT INTO products VALUES (?, ?, ?, ?, ?)"; 

   PreparedStatement preparedStmt = con.prepareStatement(query); 

   // binding values
   preparedStmt.setInt(1, 0); 
   preparedStmt.setString(2, name); 
   preparedStmt.setString(3, category); 
   preparedStmt.setString(4, description);
   preparedStmt.setString(5, price);
  
   // execute the statement
   preparedStmt.execute(); 
   con.close(); 
    String newProducts = readProducts();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newProducts + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the product.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

public String readProducts() 
{ 
   String output = ""; 
   
   try
  { 
     Connection con = connect(); 
 
     if (con == null) 
     {return "Error while connecting to the database for reading."; } 
 
     // Prepare the HTML table to be displayed
     output = "<table border='1'><tr><th>Product Name</th><th>Category</th>" +
              "<th>Description</th>" + 
              "<th>Price</th>" +
              "<th>Update</th><th>Remove</th></tr>"; 
 
   String query = "select * from products"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
 
   // iterate through the rows in the result set
  while (rs.next()) 
 { 
      String id = Integer.toString(rs.getInt("id")); 
      String name = rs.getString("name"); 
      String category = rs.getString("category"); 
      String description = rs.getString("description");
      String price = rs.getString("price");
       
   // Add a row into the HTML table
		 output += "<tr><td><input id='hidIdUpdate' name='hidIdUpdate' type='hidden' value='" + id + "'>"
				 + name + "</td>";
		 output += "<td>" + category + "</td>"; 
		 output += "<td>" + description + "</td>"; 
		 output += "<td>" + price + "</td>";
		
		
   // buttons
		 output += "<td><input name='btnUpdate' " 
		 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-pid='" + id + "'></td>"
		 + "<td><form method='post' action='users.jsp'>"
		 + "<input name='btnRemove' " 
		 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-pid='" + id + "'>"
		 + "<input name='hidIdDelete' type='hidden' " 
		 + " value='" + id + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the HTML table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the products."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

public String updateProduct(String ID, String name, String category, String description, String price)
 { 
   
	String output = ""; 
 
	try
   { 
      Connection con = connect(); 
 
      if (con == null) 
      {return "Error while connecting to the database for updating."; } 
 
     // create a prepared statement
     String query = "UPDATE products SET name=?,category=?,description=?,price=? WHERE id=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 
     // binding values
    preparedStmt.setString(1, name); 
    preparedStmt.setString(2, category); 
    preparedStmt.setString(3, description);
    preparedStmt.setString(4, price);
    preparedStmt.setInt(5, Integer.parseInt(ID)); 
 
    // execute the statement
    preparedStmt.execute(); 
    con.close();
	String newProducts = readProducts();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newProducts + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the product.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


public String deleteProduct(String id) 
 { 
 String output = ""; 
 
 try
 { 
    Connection con = connect(); 
    if (con == null) 
    {return "Error while connecting to the database for deleting."; } 
 
    // create a prepared statement
    String query = "delete from products where id=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
 
    // binding values
    preparedStmt.setInt(1, Integer.parseInt(id)); 
 
 // execute the statement
 	 preparedStmt.execute(); 
 	 con.close(); 
 	 String newProducts = readProducts();
 	 output =  "{\"status\":\"success\", \"data\": \"" + 
 			 newProducts + "\"}"; 
 	 } 

 	catch (Exception e) 
 	 { 
 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the product.\"}";  
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
 		}








} 