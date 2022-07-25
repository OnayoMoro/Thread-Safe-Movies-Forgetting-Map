package main.java.association;

import main.java.movie.Movie;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class FindAssociation extends AddAssociation {

    // loop through and print all associations
    public static void PrintAll(ConcurrentHashMap<String, Movie> FeaturedMoviesList) {
        for (Object o : FeaturedMoviesList.entrySet()) {
            Movie movie = ReturnMovieEntry((ConcurrentHashMap.Entry) o);
            Print(movie);
        }
    }

    // find association print movie and increment search counter
    public static void Find(ConcurrentHashMap<String, Movie> FeaturedMoviesList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter movie title:");

        AtomicReference<String> movieTitle = new AtomicReference<>();
        movieTitle.set(scanner.nextLine());

        try {
            AtomicReference<Movie> movie = new AtomicReference<>();
            movie.set(FeaturedMoviesList.get(movieTitle.get()));
            movie.get().incrementSearchCount();
            FeaturedMoviesList.put(movie.get().getTitle(), movie.get());
            Print(movie.get());
        } catch (Exception e) {
            System.out.println("Movie enrty does not exist");
        }
    }

    // print movie to console
    public static void Print(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Year: " + movie.getYear());
        System.out.println("Rating: " + movie.getRating() + "%");
        System.out.println("Search Count: " + movie.getSearchCount() + "\n");
    }
}
