# AI Resume Analyzer

AI Resume Analyzer is a full-stack Java web application that analyzes resumes against job descriptions using AI. The system evaluates the match percentage and identifies missing skills, helping users optimize their resumes for specific roles.

## Features

- Upload resume and job description for AI-based analysis
- Calculates match percentage between resume and job description
- Identifies missing or weak skills
- Stores analysis history for future reference
- Clean and responsive web interface

## Tech Stack

**Backend**
- Java
- Servlets
- JDBC
- REST APIs

**Frontend**
- JSP
- HTML
- CSS

**Database**
- SQLite

**AI Integration**
- OpenAI API

## Architecture

The application follows the **MVC (Model-View-Controller)** architecture.

- **Controller:** Java Servlets handle HTTP requests
- **Model:** DAO layer manages database operations
- **View:** JSP pages display results and UI

## Project Structure

```
ResumeAnalyzer
│
├── src
│   ├── servlet
│   │   ├── AnalyzeServlet.java
│   │   └── HistoryServlet.java
│   │
│   ├── dao
│   │   └── ResumeAnalysisDAO.java
│   │
│   ├── service
│   │   └── OpenAIService.java
│   │
│   └── util
│       └── DatabaseUtil.java
│
├── webapp
│   ├── index.jsp
│   ├── result.jsp
│   └── history.jsp
│
└── pom.xml
```

## How It Works

1. User submits resume text and job description.
2. The backend sends the data to the OpenAI API.
3. AI analyzes the content and returns:
   - Match percentage
   - Missing skills
4. Results are displayed on the UI.
5. Analysis is stored in the SQLite database for history tracking.

## Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ResumeAnalyzer.git
cd ResumeAnalyzer
```

### 2. Set OpenAI API Key

#### Windows (PowerShell)

```powershell
$env:OPENAI_API_KEY="your-api-key"
```

#### Linux / Mac

```bash
export OPENAI_API_KEY="your-api-key"
```

### 3. Run the application

```bash
mvn tomcat7:run
```

### 4. Open in Browser

```
http://localhost:8080/ResumeAnalyzer
```

## Screenshots

Add screenshots of:
- Resume input page
- Analysis results
- History page

## Future Improvements

- Resume PDF parsing
- Skill gap recommendations
- Resume scoring visualization
- Authentication system

