/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public class TeamModel {

    private static int MIN_VALUE = 3, MAX_VALUE = 4;
    /*Part of the SingleTon Pattern 1/3*/
    private static final TeamModel TEAMMODEL = new TeamModel();

    private int teamId = 0;
    private static ObservableList<Team> teams
            = FXCollections.observableArrayList();

    private ObservableList<Team> groupA
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupB
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupC
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupD
            = FXCollections.observableArrayList();
    private ArrayList<ObservableList<Team>> allGroups = new ArrayList<>();

    /*Part of the SingleTon Pattern 2/3*/
    private TeamModel() {
    }

    /*Part of the SingleTon Pattern 3/3*/
    public static TeamModel getTeamModel() {
        return TEAMMODEL;
    }

    public ObservableList<Team> getTeams() {
        return teams;
    }

    /*split the result of adding teams into an array of teams,*/
    public void addTeams(String result) {
        String[] names = result.split(",");
        for (String name : names) {
            teamId++;
            name = name.trim();
            teams.add(new Team(teamId, name));
        }
    }

    public void removeTeam(int index) {
        teams.remove(index);
    }

    /* Inputs all teams into one group each, and it continues until */
    public void setTeamsIntoGroups() {

        Random rand = new Random();

        while (groupA.size() < MIN_VALUE || groupB.size() < MIN_VALUE
                || groupC.size() < MIN_VALUE || groupD.size() < MIN_VALUE) {
            groupA.clear();
            groupB.clear();
            groupC.clear();
            groupD.clear();
            for (Team team : teams) {
                boolean looping = true;
                while (looping == true) {
                    int groupSelect = rand.nextInt(4) + 1;
                    if (groupSelect == 1 && groupA.size() != MAX_VALUE) {
                        team.setGroup("A");
                        groupA.add(team);
                        looping = false;
                    } else if (groupSelect == 2 && groupB.size() != MAX_VALUE) {
                        team.setGroup("B");
                        groupB.add(team);
                        looping = false;
                    } else if (groupSelect == 3 && groupC.size() != MAX_VALUE) {
                        team.setGroup("C");
                        groupC.add(team);
                        looping = false;
                    } else if (groupSelect == 4 && groupD.size() != MAX_VALUE) {
                        team.setGroup("D");
                        groupD.add(team);
                        looping = false;
                    }
                }

            }
        }
        System.out.println("A: " + groupA.size() + " B: " + groupB.size()
                + " C: " + groupC.size() + " D: " + groupD.size());

    }

    public ArrayList<ObservableList<Team>> getAllGroups() {
        allGroups.clear();
        allGroups.add(groupA);
        allGroups.add(groupB);
        allGroups.add(groupC);
        allGroups.add(groupD);
        return allGroups;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.clear();
        for (Team team : teams) {
            this.teams.add(team);
        }
        loadIntoGroups(this.teams);
    }

    public void loadIntoGroups(ObservableList<Team> teams) {
        groupA.clear();
        groupB.clear();
        groupC.clear();
        groupD.clear();
        for (Team team : teams) {
            if (team.getGroup().matches("A")) {
                groupA.add(team);
            }

            if (team.getGroup().matches("B")) {
                groupB.add(team);
            }
            if (team.getGroup().matches("C")) {
                groupC.add(team);
            }
            if (team.getGroup().matches("D")) {
                groupD.add(team);
            }
        }
        getAllGroups();
    }

    public void updateGroupRanking() {
        for (ObservableList<Team> group : allGroups) {
            ArrayList<Team> rankedTeams = new ArrayList<>();
            for (Team team : group) {
                if (!rankedTeams.isEmpty()) {
                    boolean isRanked = false;
                    for (int i = 0; i < rankedTeams.size(); i++) {
                        if (team.getId() == winningTeam(team, rankedTeams.get(i)).get(0).getId()) {
                            rankedTeams.add(i, team);
                            isRanked = true;
                            break;
                        }
                    }
                    if (isRanked == false) {
                        rankedTeams.add(team);
                    }

                } else {
                    rankedTeams.add(0, team);
                }
            }
            for (int i = 0; i < rankedTeams.size(); i++) {
                rankedTeams.get(i).setRank(i + 1);
            }
        }
    }

    public ArrayList<Team> winningTeam(Team aTeam, Team bTeam) {
        ArrayList<Team> teams = new ArrayList<>();
        Team winningTeam = null;
        Team losingTeam = null;
        //who had the most point if none go to next criteria
        if (aTeam.getPoint() > bTeam.getPoint()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getPoint() < bTeam.getPoint()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } //who had the highest goal difference if none go to next criteria
        else if (aTeam.getgDiff() > bTeam.getgDiff()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getgDiff() < bTeam.getgDiff()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } //who scored the most goals if none go to next criteria
        else if (aTeam.getgScored() > bTeam.getgScored()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getgScored() < bTeam.getgScored()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } // who won the most against eachother
        else if (MatchModel.getMatchModel().mutualMatchResult(aTeam, bTeam) != null) {
            if (aTeam.getId() == MatchModel.getMatchModel().mutualMatchResult(aTeam, bTeam).getId()) {
                winningTeam = aTeam;
                losingTeam = bTeam;
            } else {
                winningTeam = bTeam;
                losingTeam = aTeam;
            }
        } //since no conclusion have been given, make it random.
        else {
            Random rand = new Random();
            if (0 == rand.nextInt(2)) {
                winningTeam = aTeam;
                losingTeam = bTeam;
            } else {
                winningTeam = bTeam;
                losingTeam = aTeam;
            }
        }
        teams.add(0, winningTeam);
        teams.add(1, losingTeam);
        return teams;
    }

}
