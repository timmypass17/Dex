package android.example.dex.ui.adapters;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.activities.SetDetailActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class SetViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView ivLogo;
    TextView tvSet;
    TextView tvSeries;
    TextView tvTotal;

    public SetViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cvSet);
        ivLogo = itemView.findViewById(R.id.ivLogo);
        tvSet = itemView.findViewById(R.id.tvSet);
        tvSeries = itemView.findViewById(R.id.tvSeries);
        tvTotal = itemView.findViewById(R.id.tvTotal);
    }

    public void bind(PokeSet pokeSet) {
        Glide.with(ivLogo.getContext()).load(pokeSet.getmImages().getmLogo()).into(ivLogo);
        tvSet.setText(pokeSet.getmName());
        tvSeries.setText(pokeSet.getmSeries());
        LiveData<List<Pokemon>> pokemons = MainActivity.mSetViewModel.getTotalFromSet(pokeSet.getId());
        pokemons.observe((LifecycleOwner) itemView.getContext(), (Observer<List<Pokemon>>) pokemonList -> {
            tvTotal.setText(pokemonList.size() + " / " + pokeSet.getmTotal());
        });

        // Register click listener on card
        cardView.setOnClickListener(v -> {
            // 1. Navigate to a new activity on tap
            Intent i = new Intent(cardView.getContext(), SetDetailActivity.class);
            // 2. Pass pokeSet object into details activity through parcel
            i.putExtra("pokeSet", Parcels.wrap(pokeSet));
            // 3. Begin navigation
            cardView.getContext().startActivity(i);
        });
    }

    static SetViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set,parent, false);
        return new SetViewHolder(view);
    }
}
