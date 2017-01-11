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
    private static final int DRAW = 1, WIN = 3, LOSE = 0;
    private boolean unplayed = true;

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

    public void setWinner(int hScore, int aScore) {
        if (unplayed == true) {
            homeScore = hScore;
            awayScore = aScore;
            if (homeScore == awayScore) {
                homeTeam.addPoint(DRAW);
                awayTeam.addPoint(DRAW);
            } else if (homeScore < awayScore) {
                homeTeam.addPoint(LOSE);
                awayTeam.addPoint(WIN);
                matchGDiff(homeScore, awayScore);
            } else if (homeScore > awayScore) {
                homeTeam.addPoint(WIN);
                awayTeam.addPoint(LOSE);
                matchGDiff(homeScore, awayScore);
            }

            unplayed = false;
        }
    }

    private void matchGDiff(int hScore, int aScore) {

        homeTeam.changeDiff(hScore - aScore);
        homeTeam.addgScore(hScore);
        awayTeam.changeDiff(aScore - hScore);
        awayTeam.addgScore(aScore);
    }

    public boolean isUnplayed() {
        return unplayed;
    }

}
