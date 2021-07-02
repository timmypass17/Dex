package android.example.dex;

import android.example.dex.data.PokemonViewModel;
import android.example.dex.data.models.pokemon.Pokemon;
import android.example.dex.data.models.pokemon.Prices;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivCardImage;
    private final TextView tvName;
    private final TextView tvPrice;
    public final Button btnDeletePokemon;

    public PokemonViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        ivCardImage = itemView.findViewById(R.id.ivCardImage);
        tvName = itemView.findViewById(R.id.tvName);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        btnDeletePokemon = itemView.findViewById(R.id.btnDeletePokemon);
    }

    public void bind(Pokemon pokemon) {
        tvName.setText(pokemon.getName());
        if (pokemon.getTcgplayer() != null) {
            tvPrice.setText("$" + pokemon.getTcgplayer().getPrices().getPrice());
        } else {
            tvPrice.setText("No price found.");
        }
        Glide.with(itemView)
                .load(pokemon.getImages().getSmallImage())
                .into(ivCardImage);

        btnDeletePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Removing \"" + pokemon.getName() + "\" from collection...", Snackbar.LENGTH_SHORT).show();
                MainActivity.mPokemonViewModel.deletePokemon(pokemon);
            }
        });

    }

    static PokemonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PokemonViewHolder(view);
    }
}
