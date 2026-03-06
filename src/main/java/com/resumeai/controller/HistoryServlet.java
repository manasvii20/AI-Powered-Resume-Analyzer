package com.resumeai.controller;

import com.resumeai.dao.ResumeAnalysisDAO;
import com.resumeai.model.ResumeAnalysis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private ResumeAnalysisDAO analysisDAO;

    public void init() {
        analysisDAO = new ResumeAnalysisDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<ResumeAnalysis> analysisList = analysisDAO.getAllAnalyses();
        
        request.setAttribute("historyList", analysisList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("history.jsp");
        dispatcher.forward(request, response);
    }
}
