package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid input. Please provide all required parameters correctly.");
            return;
        }

        String username = args[0];
        String domain = args[1];
        String githubToken = System.getenv("GITHUB_TOKEN");
        String freshdeskToken = System.getenv("FRESHDESK_TOKEN");

        GithubAPI gitHubAPI = new GithubAPI(githubToken);
        FreshdeskAPI freshdeskAPI = new FreshdeskAPI(domain, freshdeskToken);
        ContactSync contactSync = new ContactSync(gitHubAPI, freshdeskAPI);
        contactSync.getUserAndSyncContact(username);

//        If you have difficulties running the program you can try this way:
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//
//        System.out.print("Enter domain: ");
//        String domain = scanner.nextLine();

//        String githubToken = System.getenv("GITHUB_TOKEN");
//        String freshdeskToken = System.getenv("FRESHDESK_TOKEN");

//        scanner.close();
//
//        GithubAPI gitHubAPI = new GithubAPI(githubToken);
//        FreshdeskAPI freshdeskAPI = new FreshdeskAPI(domain, freshdeskToken);
//        ContactSync contactSync = new ContactSync(gitHubAPI, freshdeskAPI);
//        contactSync.getUserAndSyncContact(username);

    }
}
