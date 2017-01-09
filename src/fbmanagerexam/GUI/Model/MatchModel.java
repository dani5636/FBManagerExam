/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.*;
import java.util.ArrayList;
import java.util.List;
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

    public void generateMatches(ArrayList<ArrayList<Team>> allGroups) {
        for (ArrayList<Team> group : allGroups) {
            if (group.size() == 3) {
                for (Team team : group) {
                    for (int i = 0; i < 3; i++) {
                        if (team.getId() != group.get(i).getId()) {
                            matchId++;
                            roundId++;
                            matches.add(new Match(team, group.get(i), matchId, roundId));
                        }
                    }
                }
                roundId = 0;
            }
            if (group.size() == 4) {
                for (Team team1 : group) {
                    for (Team team2 : group) {
                        for (int i = 0; i < 4; i++) {
                            Team aTeam = group.get(i);
                            if (team1.getId() != aTeam.getId()
                                    || team2.getId() != aTeam.getId()) {
                                matches.add(new Match(team1, aTeam, matchId, roundId));
                                for (int j = 0; j < 4; j++) {
                                    Team aTeam2 = group.get(i);
                                }
                            }
                        }
                    }
                }/*
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
                matches.add(new Match(group.get(3), group.get(1), matchId, roundId));*/
                roundId = 0;
            }
        }
    }

}
