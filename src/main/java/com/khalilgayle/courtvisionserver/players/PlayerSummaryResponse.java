package com.khalilgayle.courtvisionserver.players;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "players",
        "current_page",
        "next_page",
        "previous_page",
        "is_last_page",
        "total_players"
})

public class PlayerSummaryResponse {
    @JsonProperty("players")
    private List<PlayerSummary> players;
    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("next_page")
    private Integer nextPage;
    @JsonProperty("previous_page")
    private Integer previousPage;
    @JsonProperty("is_last_page")
    private Boolean isLastPage;
    @JsonProperty("total_players")
    private Integer totalPlayers;

    public PlayerSummaryResponse() {
    }

    public PlayerSummaryResponse(List<PlayerSummary> players, Integer currentPage, Integer nextPage, Integer previousPage, Boolean isLastPage, Integer totalPlayers) {
        this.players = players;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.isLastPage = isLastPage;
        this.totalPlayers = totalPlayers;
    }

    @JsonProperty("players")
    public List<PlayerSummary> getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(List<PlayerSummary> players) {
        this.players = players;
    }

    @JsonProperty("current_page")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("current_page")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("next_page")
    public Integer getNextPage() {
        return nextPage;
    }

    @JsonProperty("next_page")
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("previous_page")
    public Integer getPreviousPage() {
        return previousPage;
    }

    @JsonProperty("previous_page")
    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    @JsonProperty("is_last_page")
    public Boolean getIsLastPage() {
        return isLastPage;
    }

    @JsonProperty("is_last_page")
    public void setIsLastPage(Boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @JsonProperty("total_players")
    public Integer getTotalPlayers() {
        return totalPlayers;
    }

    @JsonProperty("total_players")
    public void setTotalPlayers(Integer totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerSummaryResponse{");
        sb.append("players=").append(players);
        sb.append(", currentPage=").append(currentPage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", previousPage=").append(previousPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", totalPlayers=").append(totalPlayers);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSummaryResponse that = (PlayerSummaryResponse) o;
        return Objects.equals(getPlayers(), that.getPlayers()) && Objects.equals(getCurrentPage(), that.getCurrentPage()) && Objects.equals(getNextPage(), that.getNextPage()) && Objects.equals(getPreviousPage(), that.getPreviousPage()) && Objects.equals(getIsLastPage(), that.getIsLastPage()) && Objects.equals(getTotalPlayers(), that.getTotalPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayers(), getCurrentPage(), getNextPage(), getPreviousPage(), getIsLastPage(), getTotalPlayers());
    }
}
