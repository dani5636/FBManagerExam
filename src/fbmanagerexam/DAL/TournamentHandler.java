/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.DAL;

import fbmanagerexam.BE.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Mecaa
 */
public class TournamentHandler {

    public void saveTournament(ArrayList<Team> teams, ArrayList<Match> matches, File file) {
        System.out.println("file is " + file.getName());
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("mistakes");
            }
        }
        String dataTBS = "";
        for (Team team : teams) {
            dataTBS
                    += team.getId() + ","
                    + team.getName() + ","
                    + team.getGroup() + ","
                    + team.getPoint() + ","
                    + team.getgDiff()
                    + String.format("%n");
        }
        
                   dataTBS +="Matches" + String.format("%n");
        for (Match match : matches) {
            dataTBS +=
                    + match.getMatchId() + ","
                    + match.getRound() + ","
                    + match.getHomeTeamName() + ","
                    + match.getAwayTeamName() + ","
                    + match.getHomeScore() + ","
                    + match.getAwayScore() + ","
                    + String.format("%n");

        }

        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(file.getAbsoluteFile()))) {
            bw.write(dataTBS);
        } catch (IOException ex) {
            //
        }
    }
}
