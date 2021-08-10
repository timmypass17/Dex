package android.example.dex.db.entity.set;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Dictionary;
import java.util.Hashtable;

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

    public String getReleaseDateFormatted(String date) {
        if (date.isEmpty()) {
            return "Date not found";
        }
        Hashtable<String, String> months = new Hashtable<>();
        months.put("01", "Jan");
        months.put("02", "Feb");
        months.put("03", "Mar");
        months.put("04", "Apr");
        months.put("05", "May");
        months.put("06", "Jun");
        months.put("07", "Jul");
        months.put("08", "Aug");
        months.put("09", "Sep");
        months.put("10", "Oct");
        months.put("11", "Nov");
        months.put("12", "Dec");
        // ex. String date = "2020/04/14
        String[] date_parts = date.split("/");
        String year = date_parts[0];
        String month = months.get(date_parts[1]);
        String day = date_parts[2];
        return month + " " + day + ", " +year;
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
