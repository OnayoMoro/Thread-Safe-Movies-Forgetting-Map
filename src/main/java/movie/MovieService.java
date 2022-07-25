package main.java.movie;

import main.java.ForgettingMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;

public class MovieService extends ForgettingMap {

    // read in file and create associations map
    public static ConcurrentHashMap<String, Movie> BuildAssociations(String filePath) {
        ConcurrentHashMap<String, Movie> FeaturedMoviesList = new ConcurrentHashMap<>(); // holds associations

        try {
            AtomicReference<BufferedReader> bufferedReader = new AtomicReference();
            bufferedReader.set(new BufferedReader(new FileReader(filePath)));

            String line;

            while ((line = bufferedReader.get().readLine()) != null) {
                if (FeaturedMoviesList.size() < mapSize) {
                    if (line.startsWith("title")) {
                        Movie movie = BuildMovieObject(line, bufferedReader.get());
                        FeaturedMoviesList.put(Objects.requireNonNull(movie).getTitle(), movie);
                    }
                } else {
                    break;
                }
            }

            bufferedReader.get().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FeaturedMoviesList;
    }

    // build movie object
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // create and return movie object
    public static Movie CreateMovie(String title, String director, AtomicInteger year,
                                    AtomicInteger rating, AtomicInteger searchCount) {
        return new Movie(title, director, year, rating, searchCount);
    }
}
