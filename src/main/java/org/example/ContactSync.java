package org.example;

public class ContactSync {
    private final GithubAPI gitHubAPI;
    private final FreshdeskAPI freshdeskAPI;

    public ContactSync(GithubAPI gitHubAPI, FreshdeskAPI freshdeskAPI) {
        this.gitHubAPI = gitHubAPI;
        this.freshdeskAPI = freshdeskAPI;
    }

    public void getUserAndSyncContact(String username) {
        try {
            GithubUser githubUser = gitHubAPI.getUser(username);

            int freshdeskContactId = freshdeskAPI.getFreshdeskContactId(username);

            if (freshdeskContactId == -1) {
                freshdeskAPI.createFreshdeskContact(githubUser);
                System.out.println("Contact created successfully!");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

