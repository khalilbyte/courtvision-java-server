package com.khalilgayle.courtvisionserver.players;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "players",
        "currentPage",
        "nextPage",
        "previousPage",
        "isLastPage",
        "totalPlayers"
})

public class PlayerListDTOResponse {
    @JsonProperty("players")
    private List<PlayerListDTO> players;
    @JsonProperty("currentPage")
    private Integer currentPage;
    @JsonProperty("nextPage")
    private Integer nextPage;
    @JsonProperty("previousPage")
    private Integer previousPage;
    @JsonProperty("isLastPage")
    private Boolean isLastPage;
    @JsonProperty("totalPlayers")
    private Integer totalPlayers;

    public PlayerListDTOResponse() {
    }

    public PlayerListDTOResponse(List<PlayerListDTO> players, Integer currentPage, Integer nextPage, Integer previousPage, Boolean isLastPage, Integer totalPlayers) {
        this.players = players;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.isLastPage = isLastPage;
        this.totalPlayers = totalPlayers;
    }

    @JsonProperty("players")
    public List<PlayerListDTO> getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(List<PlayerListDTO> players) {
        this.players = players;
    }

    @JsonProperty("currentPage")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("currentPage")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("nextPage")
    public Integer getNextPage() {
        return nextPage;
    }

    @JsonProperty("nextPage")
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("previousPage")
    public Integer getPreviousPage() {
        return previousPage;
    }

    @JsonProperty("previousPage")
    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    @JsonProperty("isLastPage")
    public Boolean getIsLastPage() {
        return isLastPage;
    }

    @JsonProperty("isLastPage")
    public void setIsLastPage(Boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @JsonProperty("totalPlayers")
    public Integer getTotalPlayers() {
        return totalPlayers;
    }

    @JsonProperty("totalPlayers")
    public void setTotalPlayers(Integer totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerListDTOResponse{");
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
        PlayerListDTOResponse that = (PlayerListDTOResponse) o;
        return Objects.equals(getPlayers(), that.getPlayers()) && Objects.equals(getCurrentPage(), that.getCurrentPage()) && Objects.equals(getNextPage(), that.getNextPage()) && Objects.equals(getPreviousPage(), that.getPreviousPage()) && Objects.equals(getIsLastPage(), that.getIsLastPage()) && Objects.equals(getTotalPlayers(), that.getTotalPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayers(), getCurrentPage(), getNextPage(), getPreviousPage(), getIsLastPage(), getTotalPlayers());
    }
}
