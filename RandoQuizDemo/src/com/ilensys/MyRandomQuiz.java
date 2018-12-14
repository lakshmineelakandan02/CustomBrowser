package com.ilensys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

public class MyRandomQuiz {

	public static void main(String a[]) {

		Random randomizer = new Random();

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://192.168.1.120/cpversion5";

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
			String sql;

			sql = "select * from quizquestions";

			System.out.println(sql);

			ResultSet rs = stmt3.executeQuery(sql);

			List<String[]> quizList = new ArrayList<String[]>();
			System.out.println("\n\nBefore Shuffling");
			while (rs.next()) {
				String[] obj = new String[4];
				String question = rs.getString(2);
				String ansopts = rs.getString(7);
				String correctans = rs.getString(6);
				String ansid = rs.getString(12);

				obj[0] = question;
				obj[1] = ansopts;
				obj[2] = correctans;
				obj[3] = ansid;

				System.out.println(question + " >> Ans >> " + correctans);
				quizList.add(obj);

			}
			stmt3.close();
			rs.close();
			conn.close();

			if (quizList.size() > 0) {
				Collections.shuffle(quizList);
				System.out.println("\n\nAfter Shuffling");
				for (String[] quiz : quizList) {
					System.out.println(quiz[0]);
					String[] ansopts = quiz[1].split("@");
					List<String> ansList = Arrays.asList(ansopts);
					Collections.shuffle(ansList);
					for (String ansoption : ansList) {
						System.out.println(ansoption);
					}
					System.out.println("\n------------------------------");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
	}

}
