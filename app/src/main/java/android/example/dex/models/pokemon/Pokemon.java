package android.example.dex.models.pokemon;

import android.example.dex.models.set.PokeSet;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

// Note: Use @SerializedName tag if name is not the same as json key.
//       (I just add it for consistency)

@Parcel
public class Pokemon {

    public Pokemon(){}

    @SerializedName("name")
    private String name;

    @SerializedName("supertype")
    private String supertype;

    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private Image image;

    @SerializedName("tcgplayer")
    private TCGPlayer tcgplayer;

    public String getName() {
        return name;
    }

    public String getSupertype() {
        return supertype;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public TCGPlayer getTcgplayer() {
        return tcgplayer;
    }
}
