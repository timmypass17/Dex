package android.example.dex.models.pokemon;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("small")
    private String smallImage;

    public String getSmallImage() {
        return smallImage;
    }
}
