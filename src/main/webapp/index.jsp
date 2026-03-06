<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI Resume Analyzer</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <nav class="navbar">
        <div class="container nav-content">
            <h1 class="logo">AI Resume Analyzer</h1>
            <div>
                <a href="index.jsp" class="nav-link active">New Analysis</a>
                <a href="history" class="nav-link">History</a>
            </div>
        </div>
    </nav>

    <main class="container">
        <div class="hero">
            <h2>Optimize Your Resume for the Job</h2>
            <p>Paste your resume and the job description below to get instant, AI-powered feedback.</p>
        </div>

        <% 
            String error = request.getParameter("error");
            if(error != null) { 
        %>
            <div class="alert alert-error">
                <%= error %>
            </div>
        <% } %>

        <div class="card form-card">
            <form action="analyze" method="post" id="analyzeForm">
                
                <div class="form-row">
                    <div class="form-group half-width">
                        <label for="candidateName">Candidate Name</label>
                        <input type="text" id="candidateName" name="candidateName" required placeholder="John Doe">
                    </div>
                    
                    <div class="form-group half-width">
                        <label for="jobTitle">Target Job Title</label>
                        <input type="text" id="jobTitle" name="jobTitle" required placeholder="Software Engineer">
                    </div>
                </div>

                <div class="form-group">
                    <label for="jobDescription">Job Description</label>
                    <textarea id="jobDescription" name="jobDescription" rows="6" required placeholder="Paste the job requirements here..."></textarea>
                </div>

                <div class="form-group">
                    <label for="resumeText">Resume Text</label>
                    <textarea id="resumeText" name="resumeText" rows="10" required placeholder="Paste your entire resume text here..."></textarea>
                    <small class="help-text">Tip: Copy from your PDF and paste directly.</small>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary btn-large" id="submitBtn">
                        <span id="btnText">Analyze Resume</span>
                        <div class="spinner" id="spinner" style="display: none;"></div>
                    </button>
                </div>
            </form>
        </div>
    </main>

    <script>
        document.getElementById('analyzeForm').addEventListener('submit', function() {
            document.getElementById('btnText').textContent = 'Analyzing with AI...';
            document.getElementById('spinner').style.display = 'inline-block';
            document.getElementById('submitBtn').classList.add('loading-btn');
        });
    </script>
</body>
</html>
