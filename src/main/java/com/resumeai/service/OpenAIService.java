package com.resumeai.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAIService {

    // You will need to set this environment variable or paste your key here for testing
    private static final String API_KEY = System.getenv("OPENAI_API_KEY"); 
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static String analyzeResume(String resumeText, String jobDescription) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            return "{\"match_percentage\": 0, \"missing_skills\": [\"Error: OPENAI_API_KEY not set\"]}";
        }

        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String prompt = "You are an expert technical recruiter." +
                            "Compare the following Resume with the Job Description." +
                            "Return ONLY a JSON response in this exact format, with no markdown formatting or backticks:" +
                            "{ \"match_percentage\": 85, \"missing_skills\": [\"Skill 1\", \"Skill 2\"] } \n\n" +
                            "Job Description:\n" + jobDescription + "\n\n" +
                            "Resume:\n" + resumeText;

            // Build request JSON
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", prompt);

            JSONArray messages = new JSONArray();
            messages.put(message);

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "gpt-3.5-turbo"); // Use a fast and cheap model
            requestBody.put("messages", messages);
            requestBody.put("temperature", 0.3); // Low temperature for consistent JSON
            
            // Send request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Extract the content from the OpenAI JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                String aiMessage = jsonResponse.getJSONArray("choices")
                                               .getJSONObject(0)
                                               .getJSONObject("message")
                                               .getString("content");
                
                return aiMessage; // This should be our formatted JSON string
            } else {
                return "{\"match_percentage\": 0, \"missing_skills\": [\"Error HTTP Code: " + responseCode + "\"]}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"match_percentage\": 0, \"missing_skills\": [\"Exception: " + e.getMessage() + "\"]}";
        }
    }
}
