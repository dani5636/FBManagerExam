/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbmanagerexam.BE;

/**
 *
 * @author Mecaa
 */
public class Team {

    private String name;
    private int point, gDiff, id, gScore, rank, matchPlayed;
    private String group;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        point = 0;
        gDiff = 0;
        rank = -1;
        matchPlayed = 0;

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getgDiff() {
        return gDiff;
    }

    public void changeDiff(int gDiff) {
        this.gDiff += gDiff;
    }

    public void setGDiff(int gDiff) {
        this.gDiff = gDiff;
    }

    public int getgScored() {
        return gScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void addgScore(int gScore) {
        this.gScore += gScore;
    }

}
