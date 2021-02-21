package com.github.jrdani.controller;

import com.github.jrdani.dto.*;
import com.github.jrdani.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AggregatorController {

    @Autowired
    private UserMovierService userMovierService;

    @GetMapping("/user/{loginId}")
    public List<Movie> getRecommendedMovies(@PathVariable String loginId) {
        return this.userMovierService.getUserMovieSuggestions(loginId);
    }

    @PutMapping("/user")
    public void setUserGenre(@RequestBody UserGenre userGenre) {
        this.userMovierService.setUserGenre(userGenre);
    }
}
