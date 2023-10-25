package edu.uncc.assignment06.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class AgeGroup implements Serializable {
    public String id;
    public String name;
    public int start_age;
    public int end_age;

    /*
        {
            "id": 1,
            "name": "Under 12 years old",
            "start_age": 0,
            "end_age": 11
        }
     */

    public AgeGroup() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart_age() {
        return start_age;
    }

    public void setStart_age(int start_age) {
        this.start_age = start_age;
    }

    public int getEnd_age() {
        return end_age;
    }

    public void setEnd_age(int end_age) {
        this.end_age = end_age;
    }

    @Override
    public String toString() {
        return name;
    }
}
