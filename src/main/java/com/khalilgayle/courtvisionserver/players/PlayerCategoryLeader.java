package com.khalilgayle.courtvisionserver.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({
        "player_id",
        "player_name",
        "rank",
        "team_id",
        "games_played",
        "minutes_played",
        "fgm",
        "fga",
        "fgpct",
        "fg3m",
        "fg3a",
        "fg3_pct",
        "ftm",
        "fta",
        "ftpct",
        "oreb",
        "dreb",
        "reb",
        "ast",
        "stl",
        "blk",
        "tov",
        "pts",
        "eff",
})

public class PlayerCategoryLeader {
    @JsonProperty("player_id")
    private Long playerId;
    @JsonProperty("player_name")
    private String playerName;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("games_played")
    private String gamesPlayed;
    @JsonProperty("minutes_played")
    private String fgm;
    private String fga;
    private String fgpct;
    private String fg3m;
    private String fg3a;
    private String fg3_pct;
    private String ftm;
    private String fta;
    private String ftpct;
    private String oreb;
    private String dreb;
    private String reb;
    private String ast;
    private String stl;
    private String blk;
    private String tov;
    private String pts;
    private String eff;

    public PlayerCategoryLeader() {
    }

    public PlayerCategoryLeader(Long playerId, String playerName, Integer rank, Long teamId, String gamesPlayed, String fgm, String fga, String fgpct, String fg3m, String fg3a, String fg3_pct, String ftm, String fta, String ftpct, String oreb, String dreb, String reb, String ast, String stl, String blk, String tov, String pts, String eff) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.rank = rank;
        this.teamId = teamId;
        this.gamesPlayed = gamesPlayed;
        this.fgm = fgm;
        this.fga = fga;
        this.fgpct = fgpct;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.fg3_pct = fg3_pct;
        this.ftm = ftm;
        this.fta = fta;
        this.ftpct = ftpct;
        this.oreb = oreb;
        this.dreb = dreb;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pts = pts;
        this.eff = eff;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getEff() {
        return eff;
    }

    public void setEff(String eff) {
        this.eff = eff;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getTov() {
        return tov;
    }

    public void setTov(String tov) {
        this.tov = tov;
    }

    public String getBlk() {
        return blk;
    }

    public void setBlk(String blk) {
        this.blk = blk;
    }

    public String getStl() {
        return stl;
    }

    public void setStl(String stl) {
        this.stl = stl;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public String getReb() {
        return reb;
    }

    public void setReb(String reb) {
        this.reb = reb;
    }

    public String getDreb() {
        return dreb;
    }

    public void setDreb(String dreb) {
        this.dreb = dreb;
    }

    public String getOreb() {
        return oreb;
    }

    public void setOreb(String oreb) {
        this.oreb = oreb;
    }

    public String getFtpct() {
        return ftpct;
    }

    public void setFtpct(String ftpct) {
        this.ftpct = ftpct;
    }

    public String getFta() {
        return fta;
    }

    public void setFta(String fta) {
        this.fta = fta;
    }

    public String getFtm() {
        return ftm;
    }

    public void setFtm(String ftm) {
        this.ftm = ftm;
    }

    public String getFg3_pct() {
        return fg3_pct;
    }

    public void setFg3_pct(String fg3_pct) {
        this.fg3_pct = fg3_pct;
    }

    public String getFg3a() {
        return fg3a;
    }

    public void setFg3a(String fg3a) {
        this.fg3a = fg3a;
    }

    public String getFg3m() {
        return fg3m;
    }

    public void setFg3m(String fg3m) {
        this.fg3m = fg3m;
    }

    public String getFgpct() {
        return fgpct;
    }

    public void setFgpct(String fgpct) {
        this.fgpct = fgpct;
    }

    public String getFga() {
        return fga;
    }

    public void setFga(String fga) {
        this.fga = fga;
    }

    public String getFgm() {
        return fgm;
    }

    public void setFgm(String fgm) {
        this.fgm = fgm;
    }

    public String getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerCategoryLeader{");
        sb.append("playerId=").append(playerId);
        sb.append(", playerName='").append(playerName).append('\'');
        sb.append(", rank=").append(rank);
        sb.append(", teamId=").append(teamId);
        sb.append(", gamesPlayed='").append(gamesPlayed).append('\'');
        sb.append(", fgm='").append(fgm).append('\'');
        sb.append(", fga='").append(fga).append('\'');
        sb.append(", fgpct='").append(fgpct).append('\'');
        sb.append(", fg3m='").append(fg3m).append('\'');
        sb.append(", fg3a='").append(fg3a).append('\'');
        sb.append(", fg3_pct='").append(fg3_pct).append('\'');
        sb.append(", ftm='").append(ftm).append('\'');
        sb.append(", fta='").append(fta).append('\'');
        sb.append(", ftpct='").append(ftpct).append('\'');
        sb.append(", oreb='").append(oreb).append('\'');
        sb.append(", dreb='").append(dreb).append('\'');
        sb.append(", reb='").append(reb).append('\'');
        sb.append(", ast='").append(ast).append('\'');
        sb.append(", stl='").append(stl).append('\'');
        sb.append(", blk='").append(blk).append('\'');
        sb.append(", tov='").append(tov).append('\'');
        sb.append(", pts='").append(pts).append('\'');
        sb.append(", eff='").append(eff).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerCategoryLeader that = (PlayerCategoryLeader) o;
        return Objects.equals(getPlayerId(), that.getPlayerId()) && Objects.equals(getPlayerName(), that.getPlayerName()) && Objects.equals(getRank(), that.getRank()) && Objects.equals(getTeamId(), that.getTeamId()) && Objects.equals(getGamesPlayed(), that.getGamesPlayed()) && Objects.equals(getFgm(), that.getFgm()) && Objects.equals(getFga(), that.getFga()) && Objects.equals(getFgpct(), that.getFgpct()) && Objects.equals(getFg3m(), that.getFg3m()) && Objects.equals(getFg3a(), that.getFg3a()) && Objects.equals(getFg3_pct(), that.getFg3_pct()) && Objects.equals(getFtm(), that.getFtm()) && Objects.equals(getFta(), that.getFta()) && Objects.equals(getFtpct(), that.getFtpct()) && Objects.equals(getOreb(), that.getOreb()) && Objects.equals(getDreb(), that.getDreb()) && Objects.equals(getReb(), that.getReb()) && Objects.equals(getAst(), that.getAst()) && Objects.equals(getStl(), that.getStl()) && Objects.equals(getBlk(), that.getBlk()) && Objects.equals(getTov(), that.getTov()) && Objects.equals(getPts(), that.getPts()) && Objects.equals(getEff(), that.getEff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getPlayerName(), getRank(), getTeamId(), getGamesPlayed(), getFgm(), getFga(), getFgpct(), getFg3m(), getFg3a(), getFg3_pct(), getFtm(), getFta(), getFtpct(), getOreb(), getDreb(), getReb(), getAst(), getStl(), getBlk(), getTov(), getPts(), getEff());
    }
}

