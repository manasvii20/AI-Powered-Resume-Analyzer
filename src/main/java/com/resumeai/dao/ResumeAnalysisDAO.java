package com.resumeai.dao;

import com.resumeai.model.ResumeAnalysis;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResumeAnalysisDAO {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean addAnalysis(ResumeAnalysis analysis) {
        String sql = "INSERT INTO resume_analysis(candidate_name, job_title, match_percentage, missing_skills, timestamp) " +
                     "VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, analysis.getCandidateName());
            pstmt.setString(2, analysis.getJobTitle());
            pstmt.setInt(3, analysis.getMatchPercentage());
            pstmt.setString(4, analysis.getMissingSkills());
            
            // SQLite datetime format
            String formattedDate = LocalDateTime.now().format(formatter);
            pstmt.setString(5, formattedDate);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ResumeAnalysis> getAllAnalyses() {
        String sql = "SELECT id, candidate_name, job_title, match_percentage, missing_skills, timestamp " +
                     "FROM resume_analysis ORDER BY id DESC";
        List<ResumeAnalysis> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ResumeAnalysis analysis = new ResumeAnalysis();
                analysis.setId(rs.getInt("id"));
                analysis.setCandidateName(rs.getString("candidate_name"));
                analysis.setJobTitle(rs.getString("job_title"));
                analysis.setMatchPercentage(rs.getInt("match_percentage"));
                analysis.setMissingSkills(rs.getString("missing_skills"));
                
                String timestampStr = rs.getString("timestamp");
                if (timestampStr != null) {
                    analysis.setTimestamp(LocalDateTime.parse(timestampStr, formatter));
                }
                
                list.add(analysis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
