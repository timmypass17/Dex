package android.example.dex;

import android.example.dex.data.models.pokemon.Pokemon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    private final TextView pokemonItemView;
    private final ImageView ivCardImage;

    public PokemonViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        pokemonItemView = itemView.findViewById(R.id.tvName);
        ivCardImage = itemView.findViewById(R.id.ivCardImage);
    }

    public void bind(Pokemon pokemon) {
        pokemonItemView.setText(pokemon.getName());
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
