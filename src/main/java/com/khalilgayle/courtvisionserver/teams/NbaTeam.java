package com.khalilgayle.courtvisionserver.teams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({
        "team_id",
        "full_name",
        "abbreviation",
        "nickname",
        "city",
        "team_image_url",
        "conference",
        "division"
})

public class NbaTeam {
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("full_name")
    private String fullName;
    private String abbreviation;
    private String nickname;
    private String city;
    @JsonProperty("team_image_url")
    private String teamImageUrl;
    private String conference;
    private String division;

    public NbaTeam() {
    }

    public NbaTeam(
            Long teamId,
            String fullName,
            String abbreviation,
            String nickname,
            String city,
            String teamImageUrl,
            String conference,
            String division
    ) {
        this.teamId = teamId;
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.nickname = nickname;
        this.city = city;
        this.teamImageUrl = teamImageUrl;
        this.conference = conference;
        this.division = division;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NbaTeam{");
        sb.append("teamId=").append(teamId);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", abbreviation='").append(abbreviation).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", teamImageUrl='").append(teamImageUrl).append('\'');
        sb.append(", conference='").append(conference).append('\'');
        sb.append(", division='").append(division).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NbaTeam nbaTeam = (NbaTeam) o;
        return Objects.equals(getTeamId(), nbaTeam.getTeamId()) && Objects.equals(getFullName(), nbaTeam.getFullName()) && Objects.equals(getAbbreviation(), nbaTeam.getAbbreviation()) && Objects.equals(getNickname(), nbaTeam.getNickname()) && Objects.equals(getCity(), nbaTeam.getCity()) && Objects.equals(getTeamImageUrl(), nbaTeam.getTeamImageUrl()) && Objects.equals(getConference(), nbaTeam.getConference()) && Objects.equals(getDivision(), nbaTeam.getDivision());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamId(), getFullName(), getAbbreviation(), getNickname(), getCity(), getTeamImageUrl(), getConference(), getDivision());
    }
}
