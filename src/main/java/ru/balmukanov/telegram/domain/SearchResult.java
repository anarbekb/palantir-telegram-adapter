package ru.balmukanov.telegram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class SearchResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean isFind;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "searchResult")
	private SearchUserRequest request;

	@ElementCollection
	@MapKeyColumn(name = "source")
	@Column(name = "link")
	@CollectionTable(name = "search_result_finds", joinColumns = @JoinColumn(name = "search_result_id"))
	private Map<String, String> finds = new HashMap<>();
}