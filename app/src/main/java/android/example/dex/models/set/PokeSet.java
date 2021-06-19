package android.example.dex.models.set;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PokeSet {

    // Empty constructor needed by the parceler library
    public PokeSet() {}

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String setName;

    @SerializedName("series")
    private String series;

    @SerializedName("total")
    private String total;

    @SerializedName("releaseDate")
    private String releaseDate;

    @SerializedName("images")
    private PokeSetImage setImages;

    public String getTotal() {
        return total;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

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
