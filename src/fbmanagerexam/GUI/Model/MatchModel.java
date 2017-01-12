/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.*;
import fbmanagerexam.BLL.TournamentManager;
import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public class MatchModel {

    /*Part of the SingleTon Pattern 1/3*/
    private static final MatchModel matchModel = new MatchModel();
    private ObservableList<Match> matches
            = FXCollections.observableArrayList();
    private ObservableList<Match> gMatches
            = FXCollections.observableArrayList();

    private ObservableList<Match> tMatches
            = FXCollections.observableArrayList();
    private int matchId = 0;
    private int roundId = 0;
    private TeamModel teamModel;

    TournamentManager tManager = new TournamentManager();

    /*Part of the SingleTon Pattern 2/3*/
    private MatchModel() {
        this.teamModel = TeamModel.getTeamModel();
    }

    /*Part of the SingleTon Pattern 3/3*/
    public static MatchModel getMatchModel() {
        return matchModel;
    }

    public ObservableList<Match> getMatches() {
        return matches;
    }

    public ObservableList<Match> getGroupMatches() {
        return gMatches;
    }

    public ObservableList<Match> getTeamMatches() {
        return tMatches;
    }

    /*Using enhanced for loops to generate our matches using the groups that 
    * have been given*/
    public void generateMatches(ArrayList<ObservableList<Team>> allGroups) {
        for (ObservableList<Team> group : allGroups) {
            if (group.size() == 3) {

                for (Team team : group) {

                    for (Team aTeam : group) {

                        if (team.getId() != aTeam.getId()) {
                            matchId++;
                            roundId++;
                            matches.add(new Match(team, aTeam, matchId, roundId));
                        }
                    }
                }
                roundId = 0;
            }

            if (group.size() == 4) {
                matchId++;
                roundId++;
                matches.add(new Match(group.get(0), group.get(1), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(2), group.get(3), matchId, roundId));
                matchId++;
                roundId++;
                matches.add(new Match(group.get(1), group.get(2), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(3), group.get(0), matchId, roundId));
                matchId++;
                roundId++;
                matches.add(new Match(group.get(0), group.get(2), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(1), group.get(3), matchId, roundId));
                matchId++;
                roundId++;
                matches.add(new Match(group.get(2), group.get(1), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(0), group.get(3), matchId, roundId));
                matchId++;
                roundId++;
                matches.add(new Match(group.get(1), group.get(0), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(3), group.get(2), matchId, roundId));
                matchId++;
                roundId++;
                matches.add(new Match(group.get(2), group.get(0), matchId, roundId));
                matchId++;
                matches.add(new Match(group.get(3), group.get(1), matchId, roundId));
                roundId = 0;
            }

        }
    }

    /*looks through all the matches where the home team contains the matching 
    *group
     */
    public void setGroupMatches(String group) {

        if (group != null) {
            gMatches.clear();
            String groupLetter = group.charAt(group.length() - 1) + "";
            for (Match match : matches) {
                if (match.getHomeTeam().getGroup().matches(groupLetter)) {
                    gMatches.add(match);
                }
            }
        }
    }

    public void setTeamMatches(int teamId) {
        tMatches.clear();
        for (Match match : matches) {
            if (match.getHomeTeam().getId() == teamId
                    || match.getAwayTeam().getId() == teamId) {
                tMatches.add(match);
            }

        }
    }

    public void SaveTournament(File file) {
        ArrayList<Team> teamsTBS = new ArrayList<>();
        teamsTBS.addAll(teamModel.getTeams());
        ArrayList<Match> matchesTBS = new ArrayList<>();
        matchesTBS.addAll(matches);
        tManager.saveTournament(teamsTBS, matchesTBS, file);
    }

    public void loadTournament(File file) {
        ArrayList<ArrayList<?>> allData = tManager.loadTournament(file);
        ObservableList teamsLoaded = FXCollections.observableArrayList(allData.get(0));
        ObservableList matchLoaded = FXCollections.observableArrayList(allData.get(1));
        teamModel.setTeams(teamsLoaded);
        setMatches(matchLoaded);

        teamModel.loadIntoGroups();
        teamModel.updateGroupRanking();

    }

    public Match getMatch(int matchId) {
        for (Match match : matches) {
            if (match.getMatchId() == matchId) {
                return match;
            }

        }
        return null;
    }

    public void setMatches(ObservableList<Match> matches) {
        this.matches.clear();
        for (Match match : matches) {
            this.matches.add(match);
        }
    }

    public boolean doMatchExist(int matchId) {
        for (Match match : matches) {
            if (match.getMatchId() == matchId) {
                return true;
            }
        }
        return false;
    }

    public Team mutualMatchResult(Team aTeam, Team bTeam) {
        int aTeamWins = 0;
        int bTeamWins = 0;
        for (Match match : matches) {
            if ((aTeam.getId() == match.getAwayTeam().getId()
                    || aTeam.getId() == match.getHomeTeam().getId())
                    && (bTeam.getId() == match.getAwayTeam().getId()
                    || bTeam.getId() == match.getHomeTeam().getId())) {
                if (aTeam.getPoint() > bTeam.getPoint()) {
                    aTeamWins++;
                } else if (aTeam.getPoint() < bTeam.getPoint()) {
                    bTeamWins++;
                }
            }
        }
        if (aTeamWins > bTeamWins) {
            return aTeam;
        } else if (aTeamWins > bTeamWins) {
            return bTeam;
        } else {
            return null;
        }
    }

    public void updateQuarterFinals() {
        ArrayList<ObservableList<Team>> allGroups = new ArrayList<>();
        allGroups.addAll(teamModel.getAllGroups());

        ArrayList<Team> ranked = new ArrayList<>();
        if (!(doMatchExist(49) && doMatchExist(50) && doMatchExist(51) && doMatchExist(52))) //0:winner of Group A, 1: runner-up of group A, 2:winner of Group B ....
        {
            for (ObservableList<Team> allGroup : allGroups) {
                Team winningTeam = null, runnerUp = null;

                for (Team team : allGroup) {
                    if ((team.getMatchPlayed() == 4 && allGroup.size() == 3)
                            || (team.getMatchPlayed() == 6 && allGroup.size() == 4)) {
                        if (winningTeam != null) {
                            if (winningTeam.getRank() > team.getRank()) {
                                runnerUp = winningTeam;
                                winningTeam = team;
                                ranked.add(winningTeam);
                            } else if (runnerUp != null) {
                                if (runnerUp.getRank() > team.getRank()) {
                                    runnerUp = team;
                                    ranked.add(runnerUp);
                                }
                            } else {
                                runnerUp = team;
                                ranked.add(runnerUp);
                            }
                        } else {
                            winningTeam = team;
                            ranked.add(winningTeam);
                        }

                    }
                }
            }
        }
        int quarterMatchNumber = 49;
        for (int i = 0; i < ranked.size(); i = i + 2) {
            if (ranked.get(i) != null && (i + 5) < ranked.size()) {
                Match match = new Match(ranked.get(i), ranked.get(i + 5), quarterMatchNumber, 1);
                quarterMatchNumber++;
                if (!doMatchExist(quarterMatchNumber)) {
                    matches.add(match);
                    System.out.println("Match: " + match.getHomeTeamName() + " vs " + match.getAwayTeamName());
                }
            } else if (ranked.get(i) != null && ranked.get(i - 3) != null) {
                Match match = new Match(ranked.get(i), ranked.get(i - 3), quarterMatchNumber, 1);
                quarterMatchNumber++;
                if (!doMatchExist(quarterMatchNumber)) {
                    matches.add(match);
                    System.out.println("Match: " + match.getHomeTeamName() + " vs " + match.getAwayTeamName());
                }
            }
        }
    }

    public void updateSemiFinals() {
        //53   54
        if (matchModel.doMatchExist(49) && matchModel.doMatchExist(50)) {
            if (!matchModel.getMatch(49).isUnplayed()
                    && !matchModel.getMatch(50).isUnplayed()
                    && !doMatchExist(53)) {
                Match semi1 = new Match(matchModel.getMatch(49).getWinner(),
                        matchModel.getMatch(50).getWinner(), 53, 2);
                matches.add(semi1);

                System.out.println("Made semi2" + matchModel.getMatch(49).getWinner().getName() + " vs " + matchModel.getMatch(50).getWinner().getName());
            }
        }
        if (matchModel.doMatchExist(51) && matchModel.doMatchExist(52)) {
            if (!matchModel.getMatch(51).isUnplayed()
                    && !matchModel.getMatch(52).isUnplayed()
                    && !doMatchExist(54)) {
                Match semi2 = new Match(matchModel.getMatch(51).getWinner(),
                        matchModel.getMatch(52).getWinner(), 54, 2);
                matches.add(semi2);
                System.out.println("Made semi2" + matchModel.getMatch(51).getWinner().getName() + " vs " + matchModel.getMatch(52).getWinner().getName());
            }
        }
    }

    public void updateFinals() {

        if (matchModel.doMatchExist(53) && matchModel.doMatchExist(54)) {
            if (!matchModel.getMatch(53).isUnplayed()
                    && !matchModel.getMatch(54).isUnplayed()
                    && !doMatchExist(55)) {
                Match fMatch = new Match(matchModel.getMatch(53).getWinner(),
                        matchModel.getMatch(54).getWinner(), 55, 2);
                matches.add(fMatch);
                System.out.println("Made finals" + matchModel.getMatch(53).getWinner().getName() + " vs " + matchModel.getMatch(54).getWinner().getName());
            }
        }
    }
}
