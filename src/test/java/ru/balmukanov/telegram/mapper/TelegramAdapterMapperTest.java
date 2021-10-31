package ru.balmukanov.telegram.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.balmukanov.telegram.CustomDefaultPrettyPrinter;
import ru.balmukanov.telegram.adapter.rest.search.Mapper;
import ru.balmukanov.telegram.adapter.rest.search.RequestDto;
import ru.balmukanov.telegram.domain.SearchUserRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramAdapterMapperTest {
	private Mapper mapper;
	private ObjectMapper testObjectMapper;

	@BeforeEach
	public void setUp() {
		mapper = new Mapper();
		testObjectMapper = new ObjectMapper();
		testObjectMapper.registerModule(new JavaTimeModule());
		testObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		testObjectMapper.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
		testObjectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
		testObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		testObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		testObjectMapper.setDefaultPrettyPrinter(new CustomDefaultPrettyPrinter());
	}

	@Test
	public void happyPath() {
		RequestDto dto = readFromFile("/TelegramAdapterMapperTest/requestDto.json", RequestDto.class);
		SearchUserRequest result = mapper.mapToDomain(dto);

		assertEqualsToFile(result, "/TelegramAdapterMapperTest/searchUserRequest.json");
	}

	private <T> T readFromFile(String fileName, Class<T> type) {
		String content = readFile(fileName);
		try {
			return testObjectMapper.readValue(content, type);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void assertEqualsToFile(Object value, String fileName) {
		String expected = readFile(fileName);
		try {
			String actual = testObjectMapper.writeValueAsString(value);
			assertEquals(expected, actual);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}


	private String readFile(String fileName) {
		try {
			URL resource = getClass().getResource(fileName);
			assert resource != null;
			return Files.readString(Paths.get(resource.toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}
}