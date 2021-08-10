package android.example.dex.ui.adapters;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class CardAdapter extends ListAdapter<Pokemon, CardViewHolder> {

    public CardAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Pokemon> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return CardViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        holder.bind(pokemon);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Pokemon> {

        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            // TODO: Might use id instead
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
