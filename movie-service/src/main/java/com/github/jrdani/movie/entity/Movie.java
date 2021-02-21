package com.github.jrdani.movie.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
public class Movie {
    @Id
    private int id;
    private String title;
    private int year;
    private double rating;
    private String genre;
}