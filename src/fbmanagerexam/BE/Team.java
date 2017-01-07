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
    private int point, gDiff, id;
    private String group;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        point = 0;
        gDiff = 0;

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

    public void setgDiff(int gDiff) {
        this.gDiff = gDiff;
    }

}
