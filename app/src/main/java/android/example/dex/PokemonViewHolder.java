package android.example.dex;

import android.example.dex.data.PokemonViewModel;
import android.example.dex.data.models.pokemon.Pokemon;
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
        tvPrice.setText("$" + String.valueOf(pokemon.getTcgplayer().getPrices().getPrice()));
        Glide.with(itemView)
                .load(pokemon.getImages().getSmallImage())
                .into(ivCardImage);
    }

    static PokemonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PokemonViewHolder(view);
    }
}
