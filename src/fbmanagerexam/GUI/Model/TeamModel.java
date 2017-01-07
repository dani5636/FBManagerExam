/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.Team;
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

        int a = 0, b = 0, c = 0, d = 0;
        Random rand = new Random();

        while (a < MIN_VALUE || b < MIN_VALUE || c < MIN_VALUE || d < MIN_VALUE) {
            a = 0;
            b = 0;
            c = 0;
            d = 0;
            for (Team team : teams) {
                boolean looping = true;
                while (looping == true) {
                    int groupSelect = rand.nextInt(4) + 1;
                    if (groupSelect == 1 && a != MAX_VALUE) {
                        team.setGroup("A");
                        a++;
                        looping = false;
                    } else if (groupSelect == 2 && b != MAX_VALUE) {
                        team.setGroup("B");
                        b++;
                        looping = false;
                    } else if (groupSelect == 3 && c != MAX_VALUE) {
                        team.setGroup("C");
                        c++;
                        looping = false;
                    } else if (groupSelect == 4 && d != MAX_VALUE) {
                        team.setGroup("D");
                        d++;
                        looping = false;
                    }
                }

            }
        }
        System.out.println("A: " + a + " B: " + b + " C: " + c + " D: " + d);

    }

}
