package android.example.dex.data.models.set;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
@Entity
public class PokeSet {

    @PrimaryKey
    private String id;

    @SerializedName("name")
    private String setName;

    private String series;

    private String total;

    private String releaseDate;

    @Embedded
    @SerializedName("images")
    private PokeSetImage setImages;

    // Empty constructor needed by the parceler library
    public PokeSet() {}

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
