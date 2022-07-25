package main.java.movie;

import java.util.concurrent.atomic.AtomicInteger;

public class Movie {
    private final String title;
    private final String director;
    private final AtomicInteger year;
    private final AtomicInteger rating;
    private final AtomicInteger searchCount;

    // movie object
    public Movie(String title, String director,
                 AtomicInteger year, AtomicInteger rating, AtomicInteger searchCount) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.searchCount = searchCount;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public AtomicInteger getYear() {
        return year;
    }

    public AtomicInteger getRating() {
        return rating;
    }

    public AtomicInteger getSearchCount() {
        return searchCount;
    }

    public void incrementSearchCount() {
        searchCount.incrementAndGet();
    }

}
