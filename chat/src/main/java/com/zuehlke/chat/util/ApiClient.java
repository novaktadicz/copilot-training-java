package com.zuehlke.chat.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private final String baseUrl;
    private final String apiKey;

    public ApiClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public JSONObject callApi(String requestMethod, String endpoint, String requestBody) {
        JSONObject responseJson = null;
        try {
            URL url = new URL(this.baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod(requestMethod);

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            if (this.apiKey != null) {
                connection.setRequestProperty("Authorization", "Bearer " + this.apiKey);
            }

            // Enable output for the connection
            connection.setDoOutput(true);

            // Write request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            responseJson = new JSONObject(response.toString());
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return responseJson;
    }
}
