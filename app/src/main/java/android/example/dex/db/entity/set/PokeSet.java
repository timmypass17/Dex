package android.example.dex.db.entity.set;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
@Entity(tableName = "set_table")
public class PokeSet {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public String mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("series")
    public String mSeries;

    @SerializedName("total")
    public String mTotal;

    @SerializedName("releaseDate")
    public String mReleaseDate;

    @Embedded
    @SerializedName("images")
    public PokeSetImage mImages;

    // Empty constructor needed by the parceler library
    public PokeSet() {}

    public String getmTotal() {
        return mTotal;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmSeries() {
        return mSeries;
    }

    public PokeSetImage getmImages() {
        return mImages;
    }
}
