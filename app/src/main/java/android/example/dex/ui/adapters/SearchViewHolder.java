package android.example.dex.ui.adapters;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.ui.activities.CardDetailActivity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardPokemon;
    public final ImageView ivCard;

    public SearchViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cardPokemon = itemView.findViewById(R.id.cardPokemon);
        ivCard = itemView.findViewById(R.id.ivCard);
    }

    public static SearchViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new SearchViewHolder(view);
    }

    public void bind(Pokemon pokemon) {
        // Make image size smaller by using RequestOption(). Makes app run much faster too ^_^
        // TODO: Might change width/height values, currently I just kept inputting values until it looks nice
        Glide.with(itemView)
                .load(pokemon.getImages().getSmallImage())
                .apply(new RequestOptions().override(400, 600))
                .into(ivCard);

        // Cards not owned are grayed out
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ivCard.setColorFilter(new ColorMatrixColorFilter(matrix));

        // Card owned becomes "normalized"
        if (pokemon.isOwned == 1) {
            ivCard.setColorFilter(null);
        }

        // Card Onclick listener
        cardPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Navigate to a new activity on tap
                Intent i = new Intent(itemView.getContext(), CardDetailActivity.class);
                // 2. Pass pokemon object into details activity through parcel
                i.putExtra("pokeCard", Parcels.wrap(pokemon));
                // 3. Begin navigation
                itemView.getContext().startActivity(i);
            }
        });
    }
}
