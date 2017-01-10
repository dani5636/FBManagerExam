/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.BE;

import java.util.List;

/**
 *
 * @author Mecaa
 */
public class Match {

    private Team homeTeam, awayTeam;
    private int homeScore, awayScore, matchId, round;
    private String homeTeamName, awayTeamName;
    public Match(Team homeTeam, Team awayTeam, int matchId, int round) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchId = matchId;
        this.round = round;
        homeTeamName = this.homeTeam.getName();
        awayTeamName = this.awayTeam.getName();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getGroup() {
        return homeTeam.getGroup();
    }

    public int getRound() {
        return round;
    }
}
