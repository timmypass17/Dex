package android.example.dex.models.set;

import com.google.gson.annotations.SerializedName;

public class PokeSet {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String setName;

    @SerializedName("series")
    private String series;

    @SerializedName("images")
    private PokeSetImage setImages;

    public String getId() {
        return id;
    }

    public String getSetName() {
        return setName;
    }

    public String getSeries() {
        return series;
    }

    public PokeSetImage getSetImages() {
        return setImages;
    }
}
