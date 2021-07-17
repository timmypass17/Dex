package android.example.dex.ui.adapters;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.ui.MainActivity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class CollectionAdapter extends ListAdapter<Pokemon, CollectionViewHolder> {

    public CollectionAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Pokemon> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return CollectionViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CollectionViewHolder holder, int position) {
        Pokemon pokemon = getItem(position);
        holder.bind(pokemon);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Pokemon> {

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
