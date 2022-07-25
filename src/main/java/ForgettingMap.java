package main.java;

import main.java.association.Add;
import main.java.association.Find;
import main.java.movie.Movie;
import main.java.movie.MovieService;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ForgettingMap {
    public static ConcurrentHashMap<String, Movie> FeaturedMoviesList = new ConcurrentHashMap<>(); // holds associations
    public static String filePath = "src\\main\\java\\Movie\\moviesList.txt"; // movies file path

    // start program
    public static void main(String[] args) {

        System.out.println("Movie Featured List Manager");
        System.out.println("----------------------------");
        System.out.println("Reading in movie list...");
        System.out.println("Featured list can only contain 6 movies");

        FeaturedMoviesList = MovieService.BuildAssociations(
                filePath);

        System.out.println("Movie list imported, associations created");

        Start();
    }

    // get user command
    public static void Start() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Please issue any of the commands bellow...");
                System.out.println("[Add, Find, Print, Exit]...");

                AtomicReference<String> input = new AtomicReference();
                input.set(scanner.nextLine());

                if (input.get().length() < 3 || input.get().length() > 5) {
                    CommandError();
                } else {
                    Command(input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // determine user command
    public static void Command(AtomicReference<String> command) {
        command.set(command.get().toLowerCase());

        switch (command.get()) {
            case "add" -> FeaturedMoviesList = Add.AddAssociation(FeaturedMoviesList);
            case "find" -> Find.FindMovie(FeaturedMoviesList);
            case "print" -> Find.PrintAll(FeaturedMoviesList);
            case "exit" -> System.exit(0);
            default -> CommandError();
        }
    }

    // print command error message
    public static void CommandError() {
        System.out.println("Command not recognised");
    }
}
