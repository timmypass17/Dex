package android.example.dex.models;

import com.google.gson.annotations.SerializedName;

// Note: Use @SerializedName tag if name is not the same as json key.
//       (I just add it for consistency)
public class Pokemon {
    @SerializedName("name")
    private String name;

    @SerializedName("supertype")
    private String supertype;

    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private Image image;

    @SerializedName("set")
    private Set set;

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

    public Set getSet() {
        return set;
    }

    public TCGPlayer getTcgplayer() {
        return tcgplayer;
    }
}
