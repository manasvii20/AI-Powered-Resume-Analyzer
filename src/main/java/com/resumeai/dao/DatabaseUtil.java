package com.resumeai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    // Relative path to SQLite DB file inside the project directory
    private static final String URL = "jdbc:sqlite:resume_analyzer.db";

    static {
        // Automatically Initialize the database table when the class is loaded
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
             
            String sql = "CREATE TABLE IF NOT EXISTS resume_analysis (" +
                         " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         " candidate_name TEXT NOT NULL," +
                         " job_title TEXT NOT NULL," +
                         " match_percentage INTEGER NOT NULL," +
                         " missing_skills TEXT NOT NULL," +
                         " timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
                         ");";
            stmt.execute(sql);
            System.out.println("SQLite Database initialized with table 'resume_analysis'.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Register JDBC driver explicitly for slightly older environments
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC Driver not found", e);
        }
        return DriverManager.getConnection(URL);
    }
}
