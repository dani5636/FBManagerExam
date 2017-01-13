package fbmanagerexam.BE;

public class Team {

    //sets variables
    private String name;
    private int point, gDiff, id, gScore, rank, matchPlayed;
    private String group;

    //constructor
    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        point = 0;
        gDiff = 0;
        rank = -1;
        matchPlayed = 0;

    }

    //return the current rank
    public int getRank() {
        return rank;
    }

    //sets the current rank
    public void setRank(int rank) {
        this.rank = rank;
    }

    //return theamount of matches played
    public int getMatchPlayed() {
        return matchPlayed;
    }

    //sets the amount of matches played
    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    //adds to the amount of matches played
    public void addMatchPlayed() {
        this.matchPlayed++;
    }

    //substacts from the amount of matches played
    public void subMatchPlayed() {
        this.matchPlayed--;
    }

    //returns the id of the Team
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
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

    public void subMatchPlayer() {
        this.matchPlayed--;
    }

}
