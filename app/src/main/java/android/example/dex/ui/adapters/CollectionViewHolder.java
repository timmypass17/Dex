package android.example.dex.ui.adapters;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.activities.CardDetailActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import static android.example.dex.utilities.PaletteUtil.getBorderColor;
import static android.example.dex.utilities.PaletteUtil.getColorText;
import static android.example.dex.utilities.PaletteUtil.getRarityBorderColor;
import static android.example.dex.utilities.PaletteUtil.getRarityColorText;
import static android.example.dex.utilities.PokeUtil.getHighestPrice;
import static android.example.dex.utilities.PokeUtil.getPriceFormatted;
import static android.example.dex.utilities.PokeUtil.getPriceType;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardPokemon;
    private final ImageView ivCardImage;
    private final TextView tvName;
    private final TextView tvPrice;
    private final Chip chipCardType;
    private final Chip chipRarity;
    private final TextView tvSet;
    public final Button btnDeletePokemon;
    private final TextView tvSetDate;

    public CollectionViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cardPokemon = itemView.findViewById(R.id.cardPokemonItem);
        ivCardImage = itemView.findViewById(R.id.ivCardImage);
        tvName = itemView.findViewById(R.id.tvName);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        chipCardType = itemView.findViewById(R.id.chipCardType);
        chipRarity = itemView.findViewById(R.id.chipRarity);
        tvSet = itemView.findViewById(R.id.tvSet);
        btnDeletePokemon = itemView.findViewById(R.id.btnDeletePokemon);
        tvSetDate = itemView.findViewById(R.id.tvSetDate);
    }

    public static CollectionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(view);
    }

    public void bind(Pokemon pokemon) {
        tvName.setText(pokemon.getName());
        tvSet.setText(pokemon.getSetID().getName());
        tvPrice.setText(getPriceFormatted(getHighestPrice(pokemon)));
        chipCardType.setText(getPriceType(pokemon));
        chipRarity.setText(pokemon.getRarity());
        chipRarity.setTextAppearanceResource(getRarityColorText(pokemon.getRarity()));
        chipRarity.setChipStrokeColorResource(getRarityBorderColor(pokemon.getRarity()));
        tvSetDate.setText("(" + pokemon.getSetID().getReleaseYear() + ")");
        chipCardType.setTextAppearanceResource(getColorText(getPriceType(pokemon)));
        chipCardType.setChipStrokeColorResource(getBorderColor(getPriceType(pokemon)));
        Glide.with(itemView).load(pokemon.getImages().getSmallImage()).into(ivCardImage);
        btnDeletePokemon.setOnClickListener(v -> {
            Snackbar.make(v, "Removing \"" + pokemon.getName() + "\" from collection...", Snackbar.LENGTH_SHORT).show();
            MainActivity.getmCollectionViewModel().removeFromCollection(pokemon.getId());
        });

        // Card onClick to navigate to CardDetail activity
        cardPokemon.setOnClickListener(v -> {
            Intent i = new Intent(cardPokemon.getContext(), CardDetailActivity.class);
            // Pass pokemon object into details activity through parcel
            i.putExtra("pokeCard", Parcels.wrap(pokemon));
            cardPokemon.getContext().startActivity(i);
        });
    }

}
