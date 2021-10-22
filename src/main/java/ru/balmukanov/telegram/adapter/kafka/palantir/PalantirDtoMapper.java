package ru.balmukanov.telegram.adapter.kafka.palantir;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.exception.AdapterMappingException;
import ru.balmukanov.telegram.domain.SearchUserRequest;

@Component
public class PalantirDtoMapper {
	private final ModelMapper mapper;
	public PalantirDtoMapper() {
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		this.mapper.typeMap(SearchUserRequest.class, SearchUserRequestDto.class)
			.addMapping(SearchUserRequest::getQuery, SearchUserRequestDto::setQuery)
			.addMapping(SearchUserRequest::getCorrelationId, SearchUserRequestDto::setRequestId);
	}

	public SearchUserRequestDto mapToDto(SearchUserRequest request) {
		try {
			return mapper.map(request, SearchUserRequestDto.class);
		} catch (Exception e) {
			throw new AdapterMappingException(e);
		}
	}
}