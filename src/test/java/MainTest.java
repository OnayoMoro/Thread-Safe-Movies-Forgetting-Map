package test.java;

import main.java.ForgettingMap;
import main.java.association.Find;
import main.java.movie.Movie;
import main.java.movie.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    MovieService movieService = new MovieService();
    Find find = new Find();

    @Test
    void BuildAssociationsTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/java/testData/correctMovieMap/correctMapOutput.txt"));
        ConcurrentHashMap<String, Movie> FeaturedMoviesList;
        assertNotNull(FeaturedMoviesList = movieService.BuildAssociations(ForgettingMap.filePath));
        String line = "";

        for (Object o : FeaturedMoviesList.entrySet()) {
            Movie movie = find.ReturnMovieEntry((ConcurrentHashMap.Entry) o);
            line += movie.getTitle() + "\n" + movie.getDirector() + "\n" + movie.getYear() + "\n"
                    + movie.getRating().get() + "\n" + movie.getSearchCount() + "\n";
        }

        String output = "";
        String readOutput;

        while ((readOutput = bufferedReader.readLine()) != null) {
            output += readOutput + "\n";
        }

        assertEquals(output, line);
    }
}