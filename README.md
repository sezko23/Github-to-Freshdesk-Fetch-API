# Github-to-Freshdesk-Fetch-API

This program retrieves information about a GitHub user and creates a contact in Freshdesk using their respective APIs.

## Prerequisites
### Before running the program and tests, make sure you have the following:

1.Java Development Kit (JDK) installed on your machine.

2.IntelliJ IDEA or any other Java IDE of your choice.

3.GitHub token and Freshdesk token. Set these as environment variables:

4.GITHUB_TOKEN: Your GitHub personal access token.

5.FRESHDESK_TOKEN: Your Freshdesk API key.

6.GitHub username and Freshdesk domain. These will be provided as program arguments(or user input) when running the code.

## Running the Program
### Follow these steps to run the program:

1.Open the project in IntelliJ IDEA.

2.Update the Main class to retrieve the GitHub token and Freshdesk token from the respective environment variables (GITHUB_TOKEN and FRESHDESK_TOKEN). You can access these environment variables using the System.getenv() method.

3.Build the project to ensure that all dependencies are resolved correctly.

4.Run the Main class. 

5.The program will prompt you to enter the GitHub username and Freshdesk domain. Provide the required information as prompted.

6.The program will fetch the GitHub user information, create the contact in Freshdesk, and display the result in the console.

## Running the Unit Tests
### To run the unit tests for the GithubAPI class, follow these steps:

1.Open the project in IntelliJ IDEA.

2.Make sure you have the necessary testing dependencies in your project. You can check them in the pom.xml file.

3.Locate the GithubAPITest class in your project's test folder.

4.Run the GithubAPITest class.

5.IntelliJ IDEA will execute the unit tests and display the results in the test runner window.

6.Review the test results to ensure that all tests pass successfully.

## Additional Notes
The program assumes that the required environment variables (GITHUB_TOKEN and FRESHDESK_TOKEN) are set correctly before running the program. Make sure to set these variables with the appropriate values.
