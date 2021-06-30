package android.example.dex.data.models.pokemon;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
