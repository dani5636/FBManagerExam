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

    private String homeTeam, awayTeam;
    private int homeScore, awayScore, MatchId;

    public Match(String homeTeam, String awayTeam, int MatchId) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.MatchId = MatchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
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
        return MatchId;
    }

    public void setMatchId(int MatchId) {
        this.MatchId = MatchId;
    }

}
