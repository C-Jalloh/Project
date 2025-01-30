package com.example.Backend.Entities.Movies;

import com.example.Backend.Entities.Reviews.ContentType;
import com.example.Backend.Entities.Reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewService reviewService;

    public MovieService(MovieRepository movieRepository, ReviewService reviewService) {
        this.movieRepository = movieRepository;
        this.reviewService = reviewService;
    }

    public List<MovieModel> getAllMovies() {
        List<MovieModel> movies =  movieRepository.findAll();
        return  movies.stream().map(movie -> {
            Double averageRating = reviewService.getAverageRating(movie.getId(), ContentType.MOVIE);
            movie.setRating(averageRating);
            return movie;
        }).collect(java.util.stream.Collectors.toList());

    }

    public Optional<MovieModel> getMovieById(Long id) {
        return movieRepository.findById(id).map(movie -> {
            Double averageRating = reviewService.getAverageRating(movie.getId(), ContentType.MOVIE);
            movie.setRating(averageRating);
            return movie;
        });
    }

    public MovieModel addMovie(MovieModel movie) {
        return movieRepository.save(movie);
    }

    public MovieModel updateMovie(Long id, MovieModel updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setDuration(updatedMovie.getDuration());
            movie.setDescription(updatedMovie.getDescription());
            if (updatedMovie.getRating() != null) {
                movie.setRating(updatedMovie.getRating());
            } else {
                Double averageRating = reviewService.getAverageRating(movie.getId(), ContentType.MOVIE);
                movie.setRating(averageRating);
            }
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}