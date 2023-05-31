package org.example;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GithubAPI {
    private final String token;

    public GithubAPI(String token) {
        this.token = token;
    }

    public GithubUser getUser(String username) throws Exception {
        URL url = new URL("https://api.github.com/users/" + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
                System.out.println("Response: " + response);

            }
            reader.close();

            JSONObject userJson = new JSONObject(response.toString());
            String login = userJson.getString("login");
            String name = userJson.getString("name");
            String email = userJson.isNull("email") ? "N/A" : userJson.getString("email");

            int id = userJson.getInt("id");

            return new GithubUser(login, name, email, id);
        } else if (responseCode == 404) {
            throw new Exception("Invalid GitHub user");
        } else if (responseCode == 401) {
            throw new Exception("Invalid GitHub token");
        } else {
            throw new Exception("Failed to fetch GitHub user");
        }
    }
}
