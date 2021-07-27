package android.example.dex.db.entity.pokemon;

import androidx.room.ColumnInfo;

import org.parceler.Parcel;

@Parcel
public class SetID {

    @ColumnInfo(name = "setID")
    public String id;

    @ColumnInfo(name = "setName")
    public String name;

    @ColumnInfo(name = "setSeries")
    public String series;

    @ColumnInfo(name = "setTotal")
    public int total;

    @ColumnInfo(name = "setReleaseDate")
    public String releaseDate;

    public SetID(){};

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSeries() {
        return series;
    }

    public int getTotal() {
        return total;
    }
}
