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

    /*Part of the SingleTon Pattern 1/3*/
    private static final TeamModel TEAMMODEL = new TeamModel();

    private int teamId = 0;
    ObservableList<Team> teams
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

    public void setTeamsIntoGroups() {
        int a = 0, b = 0, c = 0, d = 0;
        Random rand = new Random();

        for (Team team : teams) {
            boolean looping = true;
            while (looping == true) {
                int groupSelect = rand.nextInt(100) + 1;
                if (groupSelect <= 25 && a != 4) {
                    team.setGroup("A");
                    System.out.println(team.getGroup() + "");
                    a++;
                    looping = false;
                } else if (groupSelect > 25 && groupSelect <= 50 && b != 4) {
                    team.setGroup("B");
                    System.out.println(team.getGroup() + "");
                    b++;
                    looping = false;
                } else if (groupSelect > 50 && groupSelect <= 75 && c != 4) {
                    team.setGroup("C");
                    System.out.println(team.getGroup() + "");
                    c++;
                    looping = false;
                } else if (groupSelect > 75 && groupSelect <= 100 && d != 4) {
                    team.setGroup("D");
                    System.out.println(team.getGroup() + "");
                    d++;
                    looping = false;
                }
            }
            if (a >= 3 &&  b>= 3 && c >= 3 && d >= 3) {
                
            }
        }

    }

}
