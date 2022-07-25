package main.java.association;

import main.java.movie.Movie;
import main.java.movie.MovieService;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Add extends MovieService {

    // method for user input
    public static ConcurrentHashMap<String, Movie> AddAssociation(ConcurrentHashMap<String, Movie> FeaturedMoviesList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add the name of the film: ");
        String title = scanner.nextLine();

        System.out.println("Add the films director: ");
        String director = scanner.nextLine();

        AtomicInteger year;
        System.out.println("Add the films release year: ");

        while (true) {
            String yearString = scanner.nextLine();
            try {
                if (yearString.length() == 4) {
                    if (parseInt(yearString) >= 1888
                            && parseInt(yearString) <= 2022) {

                        year = new AtomicInteger(parseInt(yearString));
                        break;

                    } else {
                        ValidYearErrorMsg();
                    }
                } else {
                    ValidYearErrorMsg();
                }
            } catch (Exception e) {
                ValidYearErrorMsg();
            }
        }

        AtomicInteger rating;
        System.out.println("Add the films rating: ");

        while (true) {
            String ratingString = scanner.nextLine();
            try {
                if (parseInt(ratingString) <= 100 && parseInt(ratingString) >= 0) {
                    rating = new AtomicInteger(parseInt(ratingString));
                    break;
                } else {
                    ValidRatingErrorMsg();
                }
            } catch (Exception e) {
                ValidRatingErrorMsg();
            }
        }

        return ManageAssociations(FeaturedMoviesList, CreateMovie(title, director, year, rating, new AtomicInteger(0)));
    }

    // determine which association to remove from the map
    public static ConcurrentHashMap<String, Movie> ManageAssociations(ConcurrentHashMap<String, Movie> FeaturedMoviesList, Movie movie) {
        Iterator<ConcurrentHashMap.Entry<String, Movie>> iterator = FeaturedMoviesList.entrySet().iterator();

        Movie leastSearched = ReturnMovieEntry(iterator.next());

        while (iterator.hasNext()) {
            Movie compare = ReturnMovieEntry(iterator.next());

            // delete entry with the lowest amount of searches
            if (parseInt(String.valueOf(leastSearched.getSearchCount())) > parseInt(String.valueOf(movie.getSearchCount()))) {
                leastSearched = compare;
            } // if entry search numbers are the same, delete entry with the lowest rating
            else if (parseInt(String.valueOf(leastSearched.getSearchCount())) == parseInt(String.valueOf(movie.getSearchCount()))) {
                if (parseInt(String.valueOf(leastSearched.getRating())) > parseInt(String.valueOf(compare.getRating()))) {
                    leastSearched = compare;
                }
            }
            // if ratings and search numbers are the same, delete current entry
        }

        // delete entry and add new movie
        FeaturedMoviesList.remove(leastSearched.getTitle());
        FeaturedMoviesList.put(movie.getTitle(), movie);

        return FeaturedMoviesList;
    }

    // return movie object from iterator
    public static Movie ReturnMovieEntry(ConcurrentHashMap.Entry iterator) {
        return (Movie) iterator.getValue();
    }

    // valid year error message
    public static void ValidYearErrorMsg() {
        System.out.println("Please enter valid year");
    }

    public static void ValidRatingErrorMsg() {
        System.out.println("Please enter valid rating");
    }
}
