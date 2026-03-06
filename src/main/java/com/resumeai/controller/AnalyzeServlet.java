package com.resumeai.controller;

import com.resumeai.dao.ResumeAnalysisDAO;
import com.resumeai.model.ResumeAnalysis;
import com.resumeai.service.OpenAIService;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/analyze")
public class AnalyzeServlet extends HttpServlet {
    private ResumeAnalysisDAO analysisDAO;

    public void init() {
        analysisDAO = new ResumeAnalysisDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String candidateName = request.getParameter("candidateName");
        String jobTitle = request.getParameter("jobTitle");
        String resumeText = request.getParameter("resumeText");
        String jobDescription = request.getParameter("jobDescription");

        // Validate basic input
        if (candidateName == null || resumeText == null || jobDescription == null) {
            response.sendRedirect("index.jsp?error=Missing+Fields");
            return;
        }

        // Call the OpenAI Service
        String aiResponseJson = OpenAIService.analyzeResume(resumeText, jobDescription);
        
        int matchPercentage = 0;
        String missingSkills = "";

        // Parse AI response safely
        try {
            JSONObject json = new JSONObject(aiResponseJson);
            matchPercentage = json.optInt("match_percentage", 0);
            
            // Convert JSON Array of missing skills to a comma separated string for easy storage/display
            if (json.has("missing_skills")) {
                missingSkills = json.getJSONArray("missing_skills").join(", ").replace("\"", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            missingSkills = "Error parsing AI response";
        }

        // Save to Database
        ResumeAnalysis analysis = new ResumeAnalysis();
        analysis.setCandidateName(candidateName);
        analysis.setJobTitle(jobTitle);
        analysis.setMatchPercentage(matchPercentage);
        analysis.setMissingSkills(missingSkills);
        
        analysisDAO.addAnalysis(analysis);

        // Forward to the Results Page
        request.setAttribute("analysis", analysis);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
}
