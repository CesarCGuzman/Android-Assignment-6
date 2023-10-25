package edu.uncc.assignment06.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    public int uid;
    public String age_group_name;
    public int age_group_id;
    public String mood_name;
    public String mood_imgUrl;
    public int mood_id;
    public String name;

    /*
    {
            "uid": 1,
            "age_group_name": "25-34 years old",
            "age_group_id": 4,
            "mood_name": "Very Good",
            "mood_imgUrl": "http://www.theappsdr.com/imgs/mood/very_good.png",
            "mood_id": 1,
            "name": "Bob Smith"
        },
     */

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAge_group_name() {
        return age_group_name;
    }

    public void setAge_group_name(String age_group_name) {
        this.age_group_name = age_group_name;
    }

    public int getAge_group_id() {
        return age_group_id;
    }

    public void setAge_group_id(int age_group_id) {
        this.age_group_id = age_group_id;
    }

    public String getMood_name() {
        return mood_name;
    }

    public void setMood_name(String mood_name) {
        this.mood_name = mood_name;
    }

    public String getMood_imgUrl() {
        return mood_imgUrl;
    }

    public void setMood_imgUrl(String mood_imgUrl) {
        this.mood_imgUrl = mood_imgUrl;
    }

    public int getMood_id() {
        return mood_id;
    }

    public void setMood_id(int mood_id) {
        this.mood_id = mood_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public User() {
    }

}
