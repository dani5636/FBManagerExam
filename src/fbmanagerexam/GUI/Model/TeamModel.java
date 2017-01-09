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
    private ObservableList<Team> teams
            = FXCollections.observableArrayList();

    private ArrayList<Team> groupA = new ArrayList<>();
    private ArrayList<Team> groupB = new ArrayList<>();
    private ArrayList<Team> groupC = new ArrayList<>();
    private ArrayList<Team> groupD = new ArrayList<>();
    private ArrayList<ArrayList<Team>> allGroups = new ArrayList<>();

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

    public ArrayList<ArrayList<Team>> getAllGroups() {
        allGroups.clear();
        allGroups.add(groupA);
        allGroups.add(groupB);
        allGroups.add(groupC);
        allGroups.add(groupD);
        return allGroups;
    }
}
