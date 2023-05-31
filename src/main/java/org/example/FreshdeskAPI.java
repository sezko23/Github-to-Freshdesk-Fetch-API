package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class FreshdeskAPI {
    private final String domain;
    private final String freshdeskToken;
    private final String apiBaseUrl;

    public FreshdeskAPI(String domain, String freshdeskToken) {
        this.domain = domain;
        this.freshdeskToken = freshdeskToken;
        this.apiBaseUrl = "https://" + domain + ".freshdesk.com/api/v2/";
    }

    public void createFreshdeskContact(GithubUser githubUser) throws Exception {
        URL url = new URL(apiBaseUrl + "contacts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((freshdeskToken + ":X").getBytes()));

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", githubUser.getLogin());
        requestBody.put("email", githubUser.getLogin() + "@example.com");
        requestBody.put("unique_external_id", githubUser.getId());

        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(requestBody.toString());
        writer.flush();
        writer.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 201) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject contactJson = new JSONObject(response.toString());
            System.out.println("Contact created successfully!");
            System.out.println("Contact Id: " + contactJson.getInt("id"));
        } else {
            throw new Exception("Failed to create Freshdesk contact");
        }
    }

    public int getFreshdeskContactId(String username) throws Exception {
        URL url = new URL(apiBaseUrl + "contacts?unique_external_id=" + username.toLowerCase());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((freshdeskToken + ":X").getBytes()));

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray contactsArray = new JSONArray(response.toString());
            if (contactsArray.length() > 0) {
                JSONObject contactJson = contactsArray.getJSONObject(0);
                return contactJson.getInt("id");
            }

            return -1;
        } else if (responseCode == 401) {
            throw new Exception("Invalid Freshdesk token");
        } else if (responseCode == 404) {
            return -1;
        } else {
            throw new Exception("Failed to retrieve Freshdesk contact");
        }
    }
}
