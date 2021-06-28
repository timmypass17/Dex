package android.example.dex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    private final TextView pokemonItemView;


    public PokemonViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        pokemonItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        pokemonItemView.setText(text);
    }

    static PokemonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PokemonViewHolder(view);
    }
}
