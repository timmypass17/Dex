package android.example.dex.models.pokemon;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Image {

    public Image(){}

    @SerializedName("small")
    private String smallImage;

    @SerializedName("large")
    private String largeImage;

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }
}
