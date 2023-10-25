package edu.uncc.assignment06.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Mood implements Serializable {
    public String id;
    public String name;
    public String imgUrl;

    /*
    {
            "id": 1,
            "name": "Very Good",
            "imgUrl": "http://www.theappsdr.com/imgs/mood/very_good.png"
        },
     */

    public Mood() {
    }

    public Mood(String id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
