package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserResponseDto {
    private boolean isFind;
    private String query;
    protected Map<String, String> finds;
}