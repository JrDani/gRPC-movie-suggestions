package com.github.jrdani.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
public class User {
    @Id
    private String login;
    private String name;
    private String genre;
}
