package android.example.dex.db.entity.pokemon;

import android.example.dex.utilities.PokeUtil;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

// Note: Retrofit- Use @SerializedName tag if name is not the same as json key.
//       Room - Use @Embedded, table representing Pokemon object now contains columns in SetID
@Parcel
@Entity(tableName = "pokemon_table")
public class Pokemon {

    public Pokemon(String name){
        this.name = name;
    }
    // Empty constructor required for parcel
    public Pokemon(){}

    @PrimaryKey
    @NonNull
    public String id;

    public String name;

    public String supertype;

    @Embedded
    public PokeImage images;

    @Embedded
    public TCGPlayer tcgplayer;

    @Embedded
    public SetID set;

    public String rarity;

    public String artist;

    @Embedded
    public Legalities legalities;

    public String number;

    public int card_number;

    public int isOwned = 0;

    public int isWish = 0;

    public String getName() {
        return name;
    }

    public String getSupertype() {
        return supertype;
    }

    public String getId() {
        return id;
    }

    public PokeImage getImages() {
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

    public void setCard_number(String number) {
        String cardNumber = PokeUtil.getCardNumber(number);
        // If number is not empty, update number
        if (!cardNumber.equals("")) {
            this.card_number = Integer.parseInt(String.valueOf(cardNumber));
        }
    }
}
