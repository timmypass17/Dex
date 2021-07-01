package android.example.dex;

import android.example.dex.data.PokemonViewModel;
import android.example.dex.data.models.pokemon.Pokemon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class PokemonListAdapter extends ListAdapter<Pokemon, PokemonViewHolder> {

    protected PokemonListAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Pokemon> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return PokemonViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        holder.bind(pokemon);
        holder.btnDeletePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mPokemonViewModel.deletePokemon(pokemon);
            }
        });
    }

    static class WordDiff extends DiffUtil.ItemCallback<Pokemon> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Pokemon oldItem, @NonNull @NotNull Pokemon newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Pokemon oldItem, @NonNull @NotNull Pokemon newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
