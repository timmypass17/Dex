package android.example.dex.db.entity.pokemon;

import androidx.room.ColumnInfo;

import org.parceler.Parcel;

@Parcel
public class PokemonSetImage {

    public PokemonSetImage() {}

    @ColumnInfo(name = "setSymbolImage")
    public String symbol;

    public String getSymbol() {
        return symbol;
    }
}
