/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.BLL;

import fbmanagerexam.BE.*;
import fbmanagerexam.DAL.TournamentHandler;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Mecaa
 */
public class TournamentManager {

    public void saveTournament(ArrayList<Team> teams, ArrayList<Match> matches, File file) {
        TournamentHandler tHandler = new TournamentHandler();
        tHandler.saveTournament(teams, matches, file);
    }
    
}
