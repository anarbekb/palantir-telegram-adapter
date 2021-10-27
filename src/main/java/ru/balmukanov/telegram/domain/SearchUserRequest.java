package ru.balmukanov.telegram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SearchUserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private String correlationId;
    private boolean isComplete;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private SearchResult searchResult;
}