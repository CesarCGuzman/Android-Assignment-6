package edu.uncc.assignment06.models;

import java.util.ArrayList;

public class AgeGroupResponse {
    public String status;
    public ArrayList<AgeGroup> moods;

    public AgeGroupResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<AgeGroup> getAge_groups() {
        return moods;
    }

    public void setAge_groups(ArrayList<AgeGroup> age_groups) {
        this.moods = age_groups;
    }
}
