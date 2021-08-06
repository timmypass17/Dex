package android.example.dex.ui.adapters;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.Prices;
import android.example.dex.db.entity.pokemon.TCGPlayer;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.activities.CardDetailActivity;
import android.example.dex.ui.fragments.CollectionFragment;
import android.example.dex.viewmodel.CollectionViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;
import org.w3c.dom.Text;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardPokemon;
    private final ImageView ivCardImage;
    private final TextView tvName;
    private final TextView tvPrice;
    private final Chip chipCardType;
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
        tvPrice.setText(Prices.getPrice(pokemon));
        chipCardType.setText(Prices.getPriceType(pokemon));
        tvSetDate.setText("(" + pokemon.getSetID().getReleaseYear() + ")");
        chipCardType.setTextAppearanceResource(getColorText(Prices.getPriceType(pokemon)));
        chipCardType.setChipStrokeColorResource(getBorderColor(Prices.getPriceType(pokemon)));
        Glide.with(itemView).load(pokemon.getImages().getSmallImage()).into(ivCardImage);
        btnDeletePokemon.setOnClickListener(v -> {
            Snackbar.make(v, "Removing \"" + pokemon.getName() + "\" from collection...", Snackbar.LENGTH_SHORT).show();
            MainActivity.getmCollectionViewModel().removeFromCollection(pokemon.getId());
        });

        // 1. Register click listener on card
        cardPokemon.setOnClickListener(v -> {
            // 2. Navigate to a new activity on tap
            Intent i = new Intent(cardPokemon.getContext(), CardDetailActivity.class);
            // 3. Pass pokemon object into details activity through parcel
            i.putExtra("pokeCard", Parcels.wrap(pokemon));
            // 4. Begin navigation
            cardPokemon.getContext().startActivity(i);
        });
    }

    private int getColorText(String cardType) {
        if (cardType.equals("1st Edition Holofoil")) {
            return R.style.ChipLegendary;
        } else if (cardType.equals("Reverse Holofoil")) {
            return R.style.ChipEpic;
        } else if (cardType.equals("Holofoil")) {
            return R.style.ChipRare;
        } else if (cardType.equals("Normal")) {
            return R.style.ChipCommon;
        } else {
            return R.color.black;
        }
    }

    private int getBorderColor(String cardType) {
        if (cardType.equals("1st Edition Holofoil")) {
            return R.color.legendary_orange;
        } else if (cardType.equals("Reverse Holofoil")) {
            return R.color.epic_purple;
        } else if (cardType.equals("Holofoil")) {
            return R.color.rare_blue;
        } else if (cardType.equals("Normal")) {
            return R.color.common_gray;
        } else {
            return R.color.black;
        }
    }
}
