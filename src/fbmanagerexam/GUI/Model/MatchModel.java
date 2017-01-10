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
    private TeamModel teamModel = TeamModel.getTeamModel();

    /*Part of the SingleTon Pattern 2/3*/
    private MatchModel() {
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
            /* Jeppe could you fix this?
            
            if (group.size() == 4) {
                for (Team hTeam : group) {
                    roundId++;
                    Random rand = new Random();
                    Team hTeam2 = null;
                    while (hTeam2 == null) {

                        hTeam2 = group.get(rand.nextInt(group.size()));
                        if (hTeam.getId() != hTeam2.getId()) {
                            //sure

                            if (!matches.isEmpty()) {
                            }
                            int checker = 0;
                            for (Match match : matches) {
                                if (hTeam2.getId() == match.getHomeTeam().getId()) {
                                    checker++;
                                }
                            }
                            if (checker >= 2) {
                                System.out.println("Succes");
                            } else {

                                hTeam2 = null;
                            }
                        }
                    }

                    for (Team aTeam : group) {
                        if (hTeam.getId() != aTeam.getId()
                                && hTeam2.getId() != aTeam.getId()
                                && hTeam.getId() != hTeam2.getId()) {
                            matchId++;
                            matches.add(new Match(hTeam, aTeam, matchId, roundId));
                        }
                        for (Team aTeam2 : group) {
                            if (hTeam.getId() != aTeam.getId()
                                    && hTeam2.getId() != aTeam.getId()
                                    && aTeam.getId() != aTeam2.getId()) {

                                matchId++;
                                matches.add(new Match(hTeam2, aTeam2, matchId, roundId));
                                roundId++;
                            } else {

                            }
                        }

                    }

                }*/
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
        gMatches.clear();
        String groupLetter = group.charAt(group.length() - 1) + "";
        for (Match match : matches) {
            if (match.getHomeTeam().getGroup().matches(groupLetter)) {
                gMatches.add(match);
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
        TournamentManager tManager = new TournamentManager();
        ArrayList<Team> teamsTBS = new ArrayList<>();
        teamsTBS.addAll(teamModel.getTeams());
        ArrayList<Match> matchesTBS = new ArrayList<>();
        matchesTBS.addAll(matches);
        tManager.saveTournament(teamsTBS, matchesTBS, file);
    }
}
