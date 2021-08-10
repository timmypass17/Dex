package android.example.dex.db.entity.set;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Hashtable;

@Parcel
@Entity(tableName = "set_table")
public class PokeSet {

    @PrimaryKey
    @NonNull
    public String id;

    public String name;

    public String series;

    public String total;

    public String releaseDate;

    @Embedded
    public PokeSetImage images;

    // Empty constructor needed by the parceler library
    public PokeSet() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSeries() {
        return series;
    }

    public PokeSetImage getImages() {
        return images;
    }

    public String getTotal() {
        return total;
    }

    public String getReleaseDate() {
        return releaseDate;
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
}
