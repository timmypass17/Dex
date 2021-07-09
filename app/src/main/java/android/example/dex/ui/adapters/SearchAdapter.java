package android.example.dex.ui.adapters;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class SearchAdapter extends ListAdapter<Pokemon, SearchViewHolder> {

    public SearchAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Pokemon> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return SearchViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchViewHolder holder, int position) {
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
