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
    public void addMovie(Movie movie){
        movieDB.put(movie.getName(),movie);
    }
    public void addDirector(Director director){
        directorDB.put(director.getName(),director);
    }
    public void addMovieDirectorPair(String directorName,String movieName){
        if(directorMoviePairDB.containsKey(directorName)){
            directorMoviePairDB.get(directorName).add(movieName);
        }
        else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            directorMoviePairDB.put(directorName,list);
        }
//        if(movieDB.containsKey(movieName) && directorDB.containsKey(directorName)){
//            movieDB.put(movieName,movieDB.get(movieName));
//            directorDB.put(directorName,directorDB.get(directorName));
//            List<String> cruntMovie = new ArrayList<String>();
//            if(directorMoviePairDB.containsKey(directorName)){
//                cruntMovie = directorMoviePairDB.get(directorName);
//            }
//            cruntMovie.add(movieName);
//            directorMoviePairDB.put(directorName,cruntMovie);
//        }
    }
    public Movie getMovieByName(String name){
        return movieDB.get(name);
    }
    public Director getDirectorByName(String name){
        return directorDB.get(name);
    }
    public List<String> getMoviesByDirectorName(String director){
//        List<String> moviesByDirector = new ArrayList<>();
//        if(directorMoviePairDB.containsKey(director)){
//            moviesByDirector = directorMoviePairDB.get(director);
//        }
        return directorMoviePairDB.get(director);
    }
    public List<String> findAllMovies(){
        List<String> movies = new ArrayList<>();
        for(String m : movieDB.keySet()){
            movies.add(m);
        }
        return movies;
    }
    public void deleteDirectorByName(String director){
        List<String> list = directorMoviePairDB.get(director);
        for(String s : list){
            movieDB.remove(s);
        }
        directorDB.remove(director);
        directorMoviePairDB.remove(director);
//        List<String> movies = new ArrayList<>();
//        if(directorMoviePairDB.containsKey(director)){
//            movies = directorMoviePairDB.get(director);
//            for(String movie : movies){
//                if(movieDB.containsKey(movie)){
//                    movieDB.remove(movie);
//                }
//            }
//            directorMoviePairDB.remove(director);
//        }
//        if(directorDB.containsKey(director)){
//            directorDB.remove(director);
//        }
    }
    public void deleteAllDirectors(){
        for(String x : directorMoviePairDB.keySet()){
            deleteDirectorByName(x);
        }
//        HashSet<String> movieSet = new HashSet<>();
//        for(String director : directorMoviePairDB.keySet()){
//            for(String movie : directorMoviePairDB.get(director)){
//                movieSet.add(movie);
//            }
//        }
//        for(String movie : movieSet){
//            if(movieDB.containsKey(movie)){
//                movieDB.remove(movie);
//            }
//        }
    }
}
