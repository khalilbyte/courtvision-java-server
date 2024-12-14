package com.khalilgayle.courtvisionserver;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlayerDTO {
    @NonNull private Long playerId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private LocalDate birthDate;
    @NonNull private String height;
    @NonNull private Short weight;
    @NonNull private Byte seasonExperience;
    @NonNull private Byte jersey;
    @NonNull private String position;
    @NonNull private String playerImageUrl;
    @NonNull private Long teamId;
    @NonNull private String teamName;
    @NonNull private String conference;
    @NonNull private String division;
    @NonNull private String teamImageUrl;
}
