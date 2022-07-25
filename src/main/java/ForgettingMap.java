package main.java;

import main.java.association.Add;
import main.java.association.Find;
import main.java.movie.Movie;
import main.java.movie.MovieService;

import java.util.Map;
import java.util.Scanner;

public class ForgettingMap {
    public static void main(String[] args) {
        Map<String, Movie> FeaturedMoviesList; // holds associations

            System.out.println("Movie Featured List Manager");
            System.out.println("----------------------------");
            System.out.println("Reading in movie list...");
            System.out.println("Featured list can only contain 6 movies");

            FeaturedMoviesList = MovieService.BuildAssociations(
                    "C:\\Users\\onayo\\IdeaProjects\\Movies Forgetting Map\\src\\main\\java\\Movie\\Movies.txt");

            System.out.println("Movie list imported, associations created");

            Start(FeaturedMoviesList);
    }

    public static void Start(Map<String, Movie> FeaturedMoviesList) {
        Scanner scanner = new Scanner(System.in);

        String command;

        try {
            while (true) {
                System.out.println("Please issue any of the commands bellow...");
                System.out.println("[Add, Find, Print, Exit]...");

                String input = scanner.nextLine();

                if (input.length() < 3 || input.length() > 5) {
                    CommandError();
                } else {
                    Command(input, FeaturedMoviesList);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Movie> Command(String command, Map<String, Movie> FeaturedMoviesList) {
        command = command.toLowerCase();

        switch (command) {
            case "add" -> FeaturedMoviesList = Add.AddAssociation(FeaturedMoviesList);
            case "find" -> Find.FindMovie(FeaturedMoviesList);
            case "print" -> Find.PrintAll(FeaturedMoviesList);
            case "exit" -> System.exit(0);
            default -> CommandError();
        }

        return FeaturedMoviesList;
    }

    public static void CommandError() {
        System.out.println("Command not recognised");
    }
}
