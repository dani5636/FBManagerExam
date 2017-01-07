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

    public void generateMatches(ObservableList<Team> teams) {
        List<Team> allTeams = new ArrayList<>();
        allTeams.addAll(teams);
    }
}
