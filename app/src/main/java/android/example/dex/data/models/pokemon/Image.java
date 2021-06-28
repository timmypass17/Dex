package android.example.dex.data.models.pokemon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Image {

    public Image(){}

    @SerializedName("small")
    public String smallImage;

    @SerializedName("large")
    public String largeImage;

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }
}
