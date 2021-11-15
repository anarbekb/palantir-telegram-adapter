package ru.balmukanov.telegram.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SearchUserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private String correlationId;
    private boolean isComplete;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private SearchResult searchResult;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SearchUserRequest(User user, String query) {
        this.user = user;
        this.query = query;
    }
}