package ru.balmukanov.telegram.adapter.rest.search;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.exception.AdapterMappingException;
import ru.balmukanov.telegram.domain.SearchUserRequest;

@Component
public class Mapper {
    private final ModelMapper mapper;
    public Mapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.mapper.typeMap(RequestDto.class, SearchUserRequest.class)
            .addMapping(RequestDto::getUsername, SearchUserRequest::setQuery);
    }

    public SearchUserRequest mapToDomain(RequestDto request) {
        try {
            return mapper.map(request, SearchUserRequest.class);
        } catch (Exception e) {
            throw new AdapterMappingException(e);
        }
    }
}