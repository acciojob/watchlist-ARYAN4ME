package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
@Repository

public class MovieRepository {
    HashMap<String,Movie> movieDB = new HashMap<>();
    HashMap<String,Director> directorDB = new HashMap<>();
    HashMap<String,List<String>> directorMoviePairDB = new HashMap<>();
    public String addMovie(Movie movie){
        String key = movie.getName();
        movieDB.put(key,movie);
        return "Movie added Successfully";
    }
    public String addDirector(Director director){
        String key = director.getName();
        directorDB.put(key,director);
        return "Director added Successfully";
    }
    public void addMovieDirectorPair(String directorName,String movieName){
        if(movieDB.containsKey(movieName) && directorDB.containsKey(directorName)){
            movieDB.put(movieName,movieDB.get(movieName));
            directorDB.put(directorName,directorDB.get(directorName));
            List<String> cruntMovie = new ArrayList<String>();
            if(directorMoviePairDB.containsKey(directorName)){
                cruntMovie = directorMoviePairDB.get(directorName);
            }
            cruntMovie.add(movieName);
            directorMoviePairDB.put(directorName,cruntMovie);
        }
    }
    public Movie getMovieByName(String name){
        return movieDB.get(name);
    }
    public Director getDirectorByName(String name){
        return directorDB.get(name);
    }
    public List<String> getMoviesByDirectorName(String director){
        List<String> moviesByDirector = new ArrayList<>();
        if(directorMoviePairDB.containsKey(director)){
            moviesByDirector = directorMoviePairDB.get(director);
        }
        return moviesByDirector;
    }
    public List<String> findAllMovies(){
        List<String> movies = new ArrayList<>();
        for(String m : movieDB.keySet()){
            movies.add(m);
        }
        return movies;
    }
    public void deleteDirectorByName(String director){
        List<String> movies = new ArrayList<>();
        if(directorMoviePairDB.containsKey(director)){
            movies = directorMoviePairDB.get(director);
            for(String movie : movies){
                if(movieDB.containsKey(movie)){
                    movieDB.remove(movie);
                }
            }
            directorMoviePairDB.remove(director);
        }
        if(directorDB.containsKey(director)){
            directorDB.remove(director);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>();
        for(String director : directorMoviePairDB.keySet()){
            for(String movie : directorMoviePairDB.get(director)){
                movieSet.add(movie);
            }
        }
        for(String movie : movieSet){
            if(movieDB.containsKey(movie)){
                movieDB.remove(movie);
            }
        }
    }
}
