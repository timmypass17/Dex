package android.example.dex.ui.adapters;

import android.example.dex.db.entity.set.PokeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class SetAdapter extends ListAdapter<PokeSet, SetViewHolder> {

    public SetAdapter(@NonNull @NotNull DiffUtil.ItemCallback<PokeSet> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public SetViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return SetViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SetViewHolder holder, int position) {
        PokeSet pokeSet = getItem(position);
        holder.bind(pokeSet);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<PokeSet> {

        @Override
        public boolean areItemsTheSame(@NonNull PokeSet oldItem, @NonNull PokeSet newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PokeSet oldItem, @NonNull PokeSet newItem) {
            return oldItem.getmName().equals(newItem.getmName());
        }
    }

}
