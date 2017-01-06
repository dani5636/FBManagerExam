/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public class TeamModel {
    private static final TeamModel TEAMMODEL = new TeamModel();
    
    private int teamId = 0;
    ObservableList<Team> teams
            = FXCollections.observableArrayList();
    
    private TeamModel(){}
    
    public static TeamModel getTeamModel(){
        return TEAMMODEL;
    }
    
    public ObservableList<Team> getTeams(){
        return teams;
    }
    /*split the result of adding teams into an array of teams,*/
    public void addTeams(String result){
        String[] names = result.split(",");
        for (String name : names) {
            teamId++;
            name = name.trim();
            teams.add(new Team(teamId, name));
        }
    }
    public void removeTeam(int index){
        teams.remove(index);
    }
    
}
