package org.example;

public class GithubUser {
    private final String login;
    private final String name;
    private final String email;
    private final int id;

    public GithubUser(String login, String name, String email, int id) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}

