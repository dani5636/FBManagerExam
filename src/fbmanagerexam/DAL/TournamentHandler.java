/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.DAL;

import fbmanagerexam.BE.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mecaa
 */
public class TournamentHandler {

    private static String CHECK_IF_VIABLE = "FBManagerSaveFile";
    private static String CHANGE_TO_MATCHES = "Matches";

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
        String dataTBS = CHECK_IF_VIABLE
                + String.format("%n");
        for (Team team : teams) {
            //Team ID, Team Name, Team Group, Team points, Team Goal Difference
            dataTBS
                    += team.getId() + ","
                    + team.getName() + ","
                    + team.getGroup() + ","
                    + team.getPoint() + ","
                    + team.getgDiff() + ","
                    + team.getgScored()
                    + String.format("%n");
        }

        dataTBS += CHANGE_TO_MATCHES + String.format("%n");
        for (Match match : matches) {
            //Match ID, Match Round, HomeTeam, AwayTeam, HomeScore, AwayScore, unplayed
            dataTBS += +match.getMatchId() + ","
                    + match.getRound() + ","
                    + match.getHomeTeam().getId() + ","
                    + match.getAwayTeam().getId() + ","
                    + match.getHomeScore() + ","
                    + match.getAwayScore() + ",";
            if (match.isUnplayed()) {
                dataTBS += "0" + ",";
            } else {
                dataTBS += "1" + ",";
            }
            dataTBS += String.format("%n");

        }

        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(file.getAbsoluteFile()))) {
            bw.write(dataTBS);
        } catch (IOException ex) {
            //
        }
    }

    public ArrayList<ArrayList<?>> loadTournament(File file) {
        ArrayList<ArrayList<?>> allData = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<Match> matches = new ArrayList<>();

        try (BufferedReader tFile
                = new BufferedReader(new FileReader(file))) {
            {

                String dataLine = tFile.readLine();
                if (dataLine.matches(CHECK_IF_VIABLE)) {
                    dataLine = tFile.readLine();
                    while (!dataLine.matches(CHANGE_TO_MATCHES)) {
                        //Team ID, Team Name, Team Group, Team points, Team Goal Difference, Team Goal Scored
                        String[] newTeam = dataLine.split(",");
                        Team team = new Team(Integer.parseInt(newTeam[0]), newTeam[1]);
                        team.setGroup(newTeam[2]);
                        team.setPoint(Integer.parseInt(newTeam[3]));
                        team.setGDiff(Integer.parseInt(newTeam[4]));
                        team.setgScore(Integer.parseInt(newTeam[5]));
                        teams.add(team);

                        dataLine = tFile.readLine();

                        //addToTeams
                    }

                    dataLine = tFile.readLine();
                    while (dataLine != null) {
                        System.out.println(dataLine);
                        String newMatch[] = dataLine.split(",");
                        Match match = null;

                        for (Team team : teams) {
                            if (team.getId() == Integer.parseInt(newMatch[2])) {
                                for (Team aTeam : teams) {
                                    if (aTeam.getId() == Integer.parseInt(newMatch[3])) {
                                        match = new Match(team, aTeam, Integer.parseInt(newMatch[0]), Integer.parseInt(newMatch[1]));

                                    }
                                }

                            }
                        }
                        if (Integer.parseInt(newMatch[6]) == 1) {
                            match.setWinner(Integer.parseInt(newMatch[4]), Integer.parseInt(newMatch[5]));
                        }
                        matches.add(match);
                        //Match ID, Match Round, HomeTeamID, AwayTeamID, HomeScore, AwayScore, unplayed

                        dataLine = tFile.readLine();
                        //AddToMatches
                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TournamentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        allData.add(teams);

        allData.add(matches);
        return allData;
    }
}
