package main.java;

import main.java.association.AddAssociation;
import main.java.association.FindAssociation;
import main.java.movie.Movie;
import main.java.movie.MovieService;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;

public class ForgettingMap {
    public static ConcurrentHashMap<String, Movie> FeaturedMoviesList = new ConcurrentHashMap<>(); // holds associations
    public static String filePath = "src\\main\\java\\Movie\\moviesList.txt"; // movies file path
    public static Scanner scanner = new Scanner(System.in); // console input object
    public static int mapSize; // map size parameter

    // start program
    public static void main(String[] args) {

        System.out.println("Featured Movies List");
        System.out.println("----------------------------");
        MapSize();
        System.out.println("Reading in movie list...");
        System.out.println("Featured list can only contain 6 movies");

        FeaturedMoviesList = MovieService.BuildAssociations(
                filePath);

        System.out.println("Movie list imported, associations created");

        Start();
    }

    // specify map size
    public static void MapSize() {
        System.out.println("Please specify size of list...");

        while (true) {
            try {
                mapSize = parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter valid number");
            }
        }
    }

    // get user command
    public static void Start() {
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
            case "add" -> FeaturedMoviesList = AddAssociation.Add(FeaturedMoviesList);
            case "find" -> FindAssociation.Find(FeaturedMoviesList);
            case "print" -> FindAssociation.PrintAll(FeaturedMoviesList);
            case "exit" -> System.exit(0);
            default -> CommandError();
        }
    }

    // print command error message
    public static void CommandError() {
        System.out.println("Command not recognised");
    }
}
