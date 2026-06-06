package com.jobs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Jobs extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/Jobs";
	static final String USER = "root";
	static final String PASS = "root";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Open a connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				Statement stmt = conn.createStatement();) {
			String sql = "SELECT * FROM jobs";
			ResultSet rs = stmt.executeQuery(sql);
			String res = "[";

			while (rs.next()) {
				String companyName = rs.getString("company_name");
				res += "{ \"companyName\" :\"" + companyName + "\",";

				String position = rs.getString("position");
				res += "\"position\" :\"" + position + "\",";

				String location = rs.getString("location");
				res += "\"location\":\"" + location + "\",";

				String experience = rs.getString("experience");
				res += "\"experience\":\"" + experience + "\",";

				String skills = rs.getString("skills");
				res += "\"skills\" :\"" + skills + "\"}";
				if (!rs.isLast()) {
					res += ",";
				}
			}
			res += "]";
			res = res.replace(",$", "");
			out.print(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String company_name = request.getParameter("companyName");
		String position = request.getParameter("position");
		String experience = request.getParameter("experience");
		String location = request.getParameter("location");
		String skills = request.getParameter("skills");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Jobs", "root", "root");

			PreparedStatement ps = con.prepareStatement("insert into jobs values(?,?,?,?,?)");

			ps.setString(1, company_name);
			ps.setString(2, position);
			ps.setString(3, experience);
			ps.setString(4, location);
			ps.setString(5, skills);

			int i = ps.executeUpdate();
			if (i > 0)
				out.print(true);

		} catch (Exception e2) {
			System.out.println(false);
		}

		out.close();
	}

}
