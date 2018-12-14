package com.ilensys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SetQuizOptions {
	
	public static void main(String a[]) {

		Random randomizer = new Random();

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/cpmechv6";

		// Database credentials
		final String USER = "ilensys";
		final String PASS = "ilensys";

		try {
			Connection conn = null;
			Statement stmt = null;

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			// System.out.println("Creating statement...");

			Statement stmt3 = conn.createStatement();
			Statement stmt4 = conn.createStatement();
			String sql;String sql1;

			sql = "SELECT * FROM quizquestions";
			

			System.out.println(sql);

			ResultSet rs = stmt3.executeQuery(sql);
			

		
			while (rs.next()) {
				
				String id = rs.getString(1);
				String ansopts = rs.getString(7);
				//String correctans = rs.getString(6);
			//	String ansid = rs.getString(12);

				 String opt[] = ansopts.split("@");
				 String ansoptions = "";
				 
				 for (int i =0;i<opt.length;i++) {
					 opt[i] = i+1+"!!"+opt[i];
					 ansoptions = ansoptions+opt[i]+"@";
				 }
				
				 sql1 = "update quizquestions set answer_options = '"+ansoptions+"' where id = "+id;
				 System.out.println(sql1);
				 
				 int i  = stmt4.executeUpdate(sql1);
				 

			}
			stmt3.close();
			rs.close();
			conn.close();

			

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
	}

}