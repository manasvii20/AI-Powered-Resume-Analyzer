<%@ page import="com.resumeai.model.ResumeAnalysis" %>
    <%@ page import="java.util.List" %>
        <%@ page import="java.time.format.DateTimeFormatter" %>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Analysis History - AI Resume Analyzer</title>
                    <link rel="stylesheet" href="css/style.css">
                    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
                        rel="stylesheet">
                </head>

                <body>
                    <nav class="navbar">
                        <div class="container nav-content">
                            <h1 class="logo">AI Resume Analyzer</h1>
                            <div>
                                <a href="index.jsp" class="nav-link">New Analysis</a>
                                <a href="history" class="nav-link active">History</a>
                            </div>
                        </div>
                    </nav>

                    <main class="container">
                        <div class="header-action">
                            <h2>Your Analysis History</h2>
                            <p>Review past resume evaluations to track your improvement.</p>
                        </div>

                        <% List<ResumeAnalysis> historyList = (List<ResumeAnalysis>)
                                request.getAttribute("historyList");
                                if (historyList == null || historyList.isEmpty()) {
                                %>
                                <div class="empty-state">
                                    <p>You haven't analyzed any resumes yet.</p>
                                    <a href="index.jsp" class="btn btn-primary mt-3">Start First Analysis</a>
                                </div>
                                <% } else { %>

                                    <div class="table-container">
                                        <table class="history-table">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Candidate Name</th>
                                                    <th>Target Role</th>
                                                    <th>Match Score</th>
                                                    <th>Missing Skills</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% DateTimeFormatter dtf=DateTimeFormatter.ofPattern("MMM dd, yyyy
                                                    HH:mm"); for (ResumeAnalysis analysis : historyList) { int
                                                    matchPercent=analysis.getMatchPercentage(); String
                                                    scoreBadgeClass=matchPercent>= 80 ? "badge-success" : (matchPercent
                                                    >= 60 ? "badge-warning" : "badge-danger");
                                                    %>
                                                    <tr>
                                                        <td class="text-sm color-muted">
                                                            <%= analysis.getTimestamp() !=null ?
                                                                dtf.format(analysis.getTimestamp()) : "N/A" %>
                                                        </td>
                                                        <td class="font-medium">
                                                            <%= analysis.getCandidateName() %>
                                                        </td>
                                                        <td>
                                                            <%= analysis.getJobTitle() %>
                                                        </td>
                                                        <td>
                                                            <span class="badge <%= scoreBadgeClass %>">
                                                                <%= matchPercent %>%
                                                            </span>
                                                        </td>
                                                        <td class="missing-skills-cell">
                                                            <% String skills=analysis.getMissingSkills(); if(skills
                                                                !=null && !skills.trim().isEmpty() &&
                                                                !skills.equals("[]")) { String[]
                                                                skillArray=skills.split(","); int count=0; for(String s
                                                                : skillArray) { if(!s.trim().isEmpty() && count < 3) {
                                                                %>
                                                                <span class="skill-tag small">
                                                                    <%= s.trim() %>
                                                                </span>
                                                                <% count++; } } if(skillArray.length> 3) {
                                                                    %>
                                                                    <span class="skill-tag small more">+<%=
                                                                            skillArray.length - 3 %></span>
                                                                    <% } } else { %>
                                                                        <span class="text-muted text-sm">None</span>
                                                                        <% } %>
                                                        </td>
                                                    </tr>
                                                    <% } %>
                                            </tbody>
                                        </table>
                                    </div>

                                    <% } %>
                    </main>
                </body>

                </html>