package android.example.dex.db.entity.pokemon;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

// Note: Retrofit- Use @SerializedName tag if name is not the same as json key.
//       (I just add it for consistency)

@Parcel
@Entity(tableName = "pokemon_table")
public class Pokemon {

    @PrimaryKey
    @NonNull
    public String id;

    public String name;

    public String supertype;

    @Embedded
    public Image images;

    @Embedded
    public TCGPlayer tcgplayer;

    // Table representing Pokemon object now contains columns in SetID
    @Embedded
    public SetID set;

    public String rarity;

    public String artist;

    @Embedded
    public Legalities legalities;

    public String number;

    public int card_number;

    public void setCard_number(String number) {
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            // Append numbers only
            if (Character.isDigit(c)) {
                n.append(c);
            }
        }
        // If number is not empty, update number
        if (!String.valueOf(n).equals("")) {
            this.card_number = Integer.parseInt(String.valueOf(n));
        }
    }

    public int isOwned = 0;

    public int isWish = 0;

    public void setIsWish(int isWish) {
        this.isWish = isWish;
    }

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
        return id;
    }

    public Image getImages() {
        return images;
    }

    public TCGPlayer getTcgplayer() {
        return tcgplayer;
    }

    public SetID getSetID() {
        return set;
    }

    public String getRarity() {
        return rarity;
    }

    public String getArtist() {
        return artist;
    }

    public Legalities getLegalities() {
        return legalities;
    }

    public String getNumber() {
        return number;
    }

    public int getCard_number() {
        return card_number;
    }

}
