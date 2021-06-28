package android.example.dex.data.models.pokemon;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

// Note: Retrofit- Use @SerializedName tag if name is not the same as json key.
//       (I just add it for consistency)

@Parcel
@Entity(tableName = "pokemon_table")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    public int pid;

    public String pokeID;

    public String name;

    public String supertype;

    @Embedded
    public Image images;

    @Embedded
    public TCGPlayer tcgplayer;

    // Table representing Pokemon object now contains columns in SetID
    @Embedded
    public SetID setID;


    public Pokemon(String name){
        this.name = name;
    }

    // Empty constructor required for parcel
    public Pokemon(){}

    public String getName() {
        return name;
    }
    public String getSupertype() {
        return supertype;
    }
    public String getId() {
        return pokeID;
    }
    public Image getImages() {
        return images;
    }
    public TCGPlayer getTcgplayer() {
        return tcgplayer;
    }
    public SetID getSetID() {
        return setID;
    }
}
