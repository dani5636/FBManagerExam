package fbmanagerexam.GUI.Model;

import fbmanagerexam.BE.Match;
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
    private static ObservableList<Team> teams
            = FXCollections.observableArrayList();
    private ObservableList<Team> finalTeams
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupA
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupB
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupC
            = FXCollections.observableArrayList();
    private ObservableList<Team> groupD
            = FXCollections.observableArrayList();
    private ArrayList<ObservableList<Team>> allGroups = new ArrayList<>();

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

    /* 
     * splits the a String into an array and trims all the spaces away.
     *  It ups the teamId with one. Then it generates a new team 
     * that is added to the team array for each String in the array.
     */
    public void addTeams(String result) {
        String[] names = result.split(",");
        for (String name : names) {
            teamId++;
            name = name.trim();
            teams.add(new Team(teamId, name));
        }
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

    public ArrayList<ObservableList<Team>> getAllGroups() {
        allGroups.clear();
        allGroups.add(groupA);
        allGroups.add(groupB);
        allGroups.add(groupC);
        allGroups.add(groupD);
        return allGroups;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.clear();
        for (Team team : teams) {
            this.teams.add(team);
        }
        loadIntoGroups();
    }

    public void loadIntoGroups() {
        groupA.clear();
        groupB.clear();
        groupC.clear();
        groupD.clear();
        for (Team team : teams) {
            if (team.getGroup().matches("A")) {
                groupA.add(team);
            }

            if (team.getGroup().matches("B")) {
                groupB.add(team);
            }
            if (team.getGroup().matches("C")) {
                groupC.add(team);
            }
            if (team.getGroup().matches("D")) {
                groupD.add(team);
            }
        }
        getAllGroups();
    }

    public void updateGroupRanking() {
        for (ObservableList<Team> group : allGroups) {
            ArrayList<Team> rankedTeams = new ArrayList<>();
            for (Team team : group) {
                if (!rankedTeams.isEmpty()) {
                    boolean isRanked = false;
                    for (int i = 0; i < rankedTeams.size(); i++) {
                        if (team.getId() == winningTeam(team, rankedTeams.get(i)).get(0).getId()) {
                            rankedTeams.add(i, team);
                            isRanked = true;
                            break;
                        }
                    }
                    if (isRanked == false) {
                        rankedTeams.add(team);
                    }

                } else {
                    rankedTeams.add(0, team);
                }
            }
            for (int i = 0; i < rankedTeams.size(); i++) {
                group.get(i).setRank(i + 1);
                System.out.println(group.get(i).getRank() + " " + group.get(i).getName());

            }
        }
    }

    public void updateFinalPlaceMent() {
        finalTeams.clear();
        ArrayList<Team> notInFinals = new ArrayList<>();

        int rank = 0;
        MatchModel matchModel = MatchModel.getMatchModel();
        Match fMatch = matchModel.getMatch(55);
        Team s1Loser = matchModel.getMatch(53).getLoser();
        Team s2Loser = matchModel.getMatch(54).getLoser();
        Team q1Loser = matchModel.getMatch(49).getLoser();
        Team q2Loser = matchModel.getMatch(50).getLoser();
        Team q3Loser = matchModel.getMatch(51).getLoser();
        Team q4Loser = matchModel.getMatch(52).getLoser();
        finalTeams.add(fMatch.getWinner());
        finalTeams.add(fMatch.getLoser());
        finalTeams.add(winningTeam(s1Loser, s2Loser).get(0));
        finalTeams.add(winningTeam(s1Loser, s2Loser).get(1));
        notInFinals.add(q1Loser);
        notInFinals.add(q2Loser);
        notInFinals.add(q3Loser);
        notInFinals.add(q4Loser);
//sort the quarterfinalists
        ArrayList<Team> rankedTeams = new ArrayList<>();
        for (Team team : notInFinals) {
            if (!rankedTeams.isEmpty()) {
                boolean isRanked = false;
                for (int i = 0; i < rankedTeams.size(); i++) {
                    if (team.getId() == winningTeam(team, rankedTeams.get(i)).get(0).getId()) {
                        rankedTeams.add(i, team);
                        isRanked = true;
                        break;
                    }
                }
                if (isRanked == false) {
                    rankedTeams.add(team);
                }

            } else {
                rankedTeams.add(0, team);
            }

        }
        finalTeams.addAll(notInFinals);
        notInFinals.clear();
        rankedTeams.clear();
        //sorts the rest of the teams that didnt reach the quarterfinals
        for (Team team : teams) {
            boolean isRanked = false;
            for (Team team1 : finalTeams) {
                if (team.getId() == team1.getId()) {
                    isRanked = true;
                }
            }
            if (isRanked == false) {
                notInFinals.add(team);
            }

        }
        for (Team team : notInFinals) {
            if (!rankedTeams.isEmpty()) {
                boolean isRanked = false;
                for (int i = 0; i < rankedTeams.size(); i++) {
                    if (team.getId() == winningTeam(team, rankedTeams.get(i)).get(0).getId()) {
                        rankedTeams.add(i, team);
                        isRanked = true;
                        break;
                    }
                }
                if (isRanked == false) {
                    rankedTeams.add(team);
                }

            } else {
                rankedTeams.add(0, team);
            }

        }
        finalTeams.addAll(rankedTeams);
        for (int i = 0; i < finalTeams.size(); i++) {
            finalTeams.get(i).setRank(i + 1);
            System.out.println(finalTeams.get(i).getName() + " " + finalTeams.get(i).getRank());
        }

    }

    public ArrayList<Team> winningTeam(Team aTeam, Team bTeam) {
        ArrayList<Team> pair = new ArrayList<>();
        Team winningTeam = null;
        Team losingTeam = null;
        //who had the most point if none go to next criteria
        if (aTeam.getPoint() > bTeam.getPoint()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getPoint() < bTeam.getPoint()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } //who had the highest goal difference if none go to next criteria
        else if (aTeam.getgDiff() > bTeam.getgDiff()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getgDiff() < bTeam.getgDiff()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } //who scored the most goals if none go to next criteria
        else if (aTeam.getgScored() > bTeam.getgScored()) {
            winningTeam = aTeam;
            losingTeam = bTeam;
        } else if (aTeam.getgScored() < bTeam.getgScored()) {
            winningTeam = bTeam;
            losingTeam = aTeam;
        } // who won the most against eachother
        else if (MatchModel.getMatchModel().mutualMatchResult(aTeam, bTeam) != null) {
            if (aTeam.getId() == MatchModel.getMatchModel().mutualMatchResult(aTeam, bTeam).getId()) {
                winningTeam = aTeam;
                losingTeam = bTeam;
            } else {
                winningTeam = bTeam;
                losingTeam = aTeam;
            }
        } //since no conclusion have been given, make it random.
        else {
            Random rand = new Random();
            if (0 == rand.nextInt(2)) {
                winningTeam = aTeam;
                losingTeam = bTeam;
            } else {
                winningTeam = bTeam;
                losingTeam = aTeam;
            }
        }
        pair.add(0, winningTeam);
        pair.add(1, losingTeam);
        return pair;
    }

    //removes a team from all matches
    public void removeATeam(int teamId) {
        MatchModel.getMatchModel().deleteMatches(teamId);
        for (Team team : teams) {
            if (team.getId() == teamId) {
                teams.remove(team);
                break;
            }
        }
    }

}
