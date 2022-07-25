package main.java.movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class MovieService {

    public static Map<String, Movie> BuildAssociations(String filePath) {
        Map<String, Movie> FeaturedMoviesList = new HashMap<>(); // holds associations

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("title")) {
                    Movie movie = BuildMovieObject(line, bufferedReader);
                    FeaturedMoviesList.putIfAbsent(Objects.requireNonNull(movie).getTitle(), movie);
                }
            }

            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return FeaturedMoviesList;
    }

    public static Movie BuildMovieObject(String line, BufferedReader bufferedReader) {

        try {
            line = line.substring(7);
            String title = line;
            String director = bufferedReader.readLine().substring(10);
            AtomicInteger year = new AtomicInteger();
            year.set(parseInt(bufferedReader.readLine().substring(6)));
            AtomicInteger rating = new AtomicInteger();
            rating.set(parseInt(bufferedReader.readLine().substring(8)));
            AtomicInteger searchCount = new AtomicInteger();
            searchCount.set(parseInt(bufferedReader.readLine().substring(14)));

            return CreateMovie(title, director, year, rating, searchCount);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Movie CreateMovie(String title, String director, AtomicInteger year,
                                    AtomicInteger rating, AtomicInteger searchCount) {
        return new Movie(title, director, year, rating, searchCount);
    }
}
