package com.github.jrdani.service;

import com.github.jrdani.common.*;
import com.github.jrdani.dto.*;
import com.github.jrdani.movie.*;
import com.github.jrdani.user.*;
import net.devh.boot.grpc.client.inject.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class UserMovierService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<Movie> getUserMovieSuggestions(String loginId) {
        UserSearchRequest user = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userGenre = this.userStub.getUserGenre(user);
        MovieSearchRequest movieRequest = MovieSearchRequest.newBuilder().setGenre(userGenre.getGenre()).build();
        return this.movieStub.getMovies(movieRequest).getMovieList()
                .stream()
                .map(movieDto -> new Movie(movieDto.getTitle(), movieDto.getYear(), movieDto.getRating()))
                .collect(Collectors.toList());
    }

    public void setUserGenre(UserGenre userGenre) {
        UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = this.userStub.updateUserGenre(userGenreUpdateRequest);
    }
}
