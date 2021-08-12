package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserRequestDto {
    private String query;
}
