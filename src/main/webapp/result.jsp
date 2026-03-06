<%@ page import="com.resumeai.model.ResumeAnalysis" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Analysis Result - AI Resume Analyzer</title>
            <link rel="stylesheet" href="css/style.css">
            <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
                rel="stylesheet">
        </head>

        <body>
            <nav class="navbar">
                <div class="container nav-content">
                    <h1 class="logo">AI Resume Analyzer</h1>
                    <div>
                        <a href="index.jsp" class="nav-link">New Analysis</a>
                        <a href="history" class="nav-link">History</a>
                    </div>
                </div>
            </nav>

            <main class="container">
                <% ResumeAnalysis analysis=(ResumeAnalysis) request.getAttribute("analysis"); if (analysis==null) {
                    response.sendRedirect("index.jsp"); return; } int matchPercent=analysis.getMatchPercentage(); String
                    colorClass=matchPercent>= 80 ? "color-success" : (matchPercent >= 60 ? "color-warning" :
                    "color-danger");
                    %>

                    <div class="header-action">
                        <h2>Analysis Results</h2>
                        <a href="index.jsp" class="btn btn-outline">Start Over</a>
                    </div>

                    <div class="grid-layout">
                        <!-- Left Column: Match Score -->
                        <div class="card score-card">
                            <h3>Match Score</h3>
                            <p class="subtitle">For: <%= analysis.getCandidateName() %> applying to <%=
                                        analysis.getJobTitle() %>
                            </p>

                            <div class="circular-progress <%= colorClass %>">
                                <span class="progress-value">
                                    <%= matchPercent %>%
                                </span>
                            </div>

                            <div class="score-text">
                                <% if(matchPercent>= 80) { %>
                                    <p><strong>Excellent Match!</strong> You are highly qualified for this role.</p>
                                    <% } else if(matchPercent>= 60) { %>
                                        <p><strong>Good Match!</strong> You have most of the skills but could highlight
                                            a few more things.</p>
                                        <% } else { %>
                                            <p><strong>Low Match.</strong> Focus on adding the missing keywords to your
                                                resume.</p>
                                            <% } %>
                            </div>
                        </div>

                        <!-- Right Column: Missing Skills & Improvement -->
                        <div class="card feedback-card">
                            <h3>Missing Skills & Keywords</h3>
                            <p class="subtitle">The AI identified these skills from the Job Description that are missing
                                in your Resume:</p>

                            <ul class="skills-list">
                                <% String skills=analysis.getMissingSkills(); if (skills !=null &&
                                    !skills.trim().isEmpty() && !skills.equals("[]")) { String[]
                                    skillArray=skills.split(","); for (String skill : skillArray) {
                                    if(!skill.trim().isEmpty()) { %>
                                    <li><span class="skill-tag">
                                            <%= skill.trim() %>
                                        </span></li>
                                    <% } } } else { %>
                                        <li><span class="skill-tag success">No missing skills found!</span></li>
                                        <% } %>
                            </ul>

                            <div class="action-box">
                                <h4>Next Steps</h4>
                                <p>Integrate the highlighted skills organically into your work experience bullets or
                                    skills section before applying.</p>
                            </div>
                        </div>
                    </div>
            </main>
        </body>

        </html>