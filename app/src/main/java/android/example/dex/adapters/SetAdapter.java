package android.example.dex.adapters;

import android.content.Context;
import android.example.dex.R;
import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.set.PokeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder>{

    Context context;
    List<PokeSet> pokeSetData;

    public SetAdapter(Context context, List<PokeSet> pokeSetData) {
        this.context = context;
        this.pokeSetData = pokeSetData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View setView = LayoutInflater.from(context).inflate(R.layout.item_set, parent, false);
        return new ViewHolder(setView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SetAdapter.ViewHolder holder, int position) {
        PokeSet set = pokeSetData.get(position);
        holder.bind(set);
    }

    @Override
    public int getItemCount() {
        return pokeSetData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivSymbol;
        TextView tvSetName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivSymbol = itemView.findViewById(R.id.ivSymbol);
            tvSetName = itemView.findViewById(R.id.tvSetName);
        }

        public void bind(PokeSet pokeSet) {
            // 1. Add symbol image
            Glide.with(context)
                    .load(pokeSet.getSetImages().getSymbol())
                    .into(ivSymbol);
            // 2. Add set name
            tvSetName.setText(pokeSet.getSetName());
        }
    }
}
