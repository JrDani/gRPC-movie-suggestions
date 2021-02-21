package com.github.jrdani.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String title;
    private Integer year;
    private double rating;
}
