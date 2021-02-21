package com.github.jrdani.movie.service;

import com.github.jrdani.movie.*;
import com.github.jrdani.movie.repository.*;
import io.grpc.stub.*;
import net.devh.boot.grpc.server.service.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;
import java.util.stream.*;

@GrpcService
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {
        List<MovieDto> movies = this.movieRepository.getMovieByGenreOrderByYearDesc(request.getGenre().toString())
                .stream()
                .map(movie -> MovieDto.newBuilder()
                        .setTitle(movie.getTitle())
                        .setYear(movie.getYear())
                        .setRating(movie.getRating())
                        .build())
                .collect(Collectors.toList());
        responseObserver.onNext(MovieSearchResponse.newBuilder().addAllMovie(movies).build());
        responseObserver.onCompleted();
    }
}
