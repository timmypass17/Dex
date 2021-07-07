package android.example.dex.db.entity.pokemon;

import androidx.room.ColumnInfo;

import org.parceler.Parcel;

@Parcel
public class SetID {

    @ColumnInfo(name = "setID")
    public String id;

    public SetID(){};

    public String getId() {
        return id;
    }
}
