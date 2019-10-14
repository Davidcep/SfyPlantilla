package es.sfy.sfyall.Model.Data;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

public class Movie extends SugarRecord implements Serializable {

    @SerializedName("poster_path") String img;
    @SerializedName("original_title") String title;
    @SerializedName("release_date") String date;
    @SerializedName("overview") String overview;

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getOverview() {
        return overview;
    }
}
