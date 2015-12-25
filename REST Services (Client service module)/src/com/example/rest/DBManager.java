package com.example.rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

public class DBManager {
	private  static DBManager database=null;
	private static Connection conn;
	private static String url;
	private static String dbusername;
	private static String dbpassword;
	
	private DBManager()
	{
		conn=null;
		url="jdbc:mysql://mydatabases.crvtlcumhkcd.us-west-2.rds.amazonaws.com:3306/cloudlogin";
		dbusername="shailesh";
		dbpassword="shailesh";
	}
	public static DBManager getInstance()
	{
		if(database==null)
			database=new DBManager();
		return database;
	}
	
	
	public void insert(String email,String password,String  username)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			String query="insert into userdetails values (?, ?, ?, ?)";
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, email);
		      preparedStmt.setString (2, password);
		      preparedStmt.setString(3,username);
		      preparedStmt.setBoolean(4, false);
		      preparedStmt.execute();
		      conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		
	}
	
	
	public int checkUserExistence(String email)
	{
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			String query=null;
			query="select count(*) as usercount from userdetails where email='"+email+"'";
			 Statement stmt = conn.createStatement();
		     ResultSet rs=stmt.executeQuery(query);
		     while(rs.next())
		     {
		    	 count=rs.getInt("usercount");
		     }
		     
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
//		System.out.println(tweets.size());
		return count;
	}
	

	public boolean authenticateFacebookUser(String email)
	{
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			String query=null;
			query="select count(*) as usercount from userdetails where email='"+email+"'  and isfacebook=true";
			 Statement stmt = conn.createStatement();
		     ResultSet rs=stmt.executeQuery(query);
		     while(rs.next())
		     {
		    	 count=rs.getInt("usercount");
		     }
		     
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(count==0)return false;
		return true;
	}
	
	
	
	public boolean authenticateWebsiteUser(String email,String password)
	{
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			String query=null;
			query="select count(*) as usercount from userdetails where email='"+email+"' and password='"+password+"' and isfacebook=false";
			 Statement stmt = conn.createStatement();
		     ResultSet rs=stmt.executeQuery(query);
		     while(rs.next())
		     {
		    	 count=rs.getInt("usercount");
		     }
		     
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(count==0)return false;
		return true;
	}
	


}
