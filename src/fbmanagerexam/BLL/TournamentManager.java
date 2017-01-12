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
public class TournamentManager
{

    TournamentHandler tHandler = new TournamentHandler();

    public void saveTournament(ArrayList<Team> teams, ArrayList<Match> matches, File file)//access the DAL to run the save function in the DAL
      {
        tHandler.saveTournament(teams, matches, file);
      }

    public ArrayList<ArrayList<?>> loadTournament(File file) //access the DAL to run the load function in the DAL
      {
        return tHandler.loadTournament(file);
      }

}
