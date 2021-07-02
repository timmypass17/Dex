package android.example.dex.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.CardDetailActivity;
import android.example.dex.data.models.pokemon.Pokemon;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder>{

    Context context;
    List<Pokemon> pokeData;

    public PokeAdapter(Context context, List<Pokemon> pokeData) {
        this.context = context;
        this.pokeData = pokeData;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View pokeView = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(pokeView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull @NotNull PokeAdapter.ViewHolder holder, int position) {
        // 1. Get the pokemon at the passed in position
        Pokemon pokemon = pokeData.get(position);
        // 2. Bind the movie data into the VH
        holder.bind(pokemon);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return pokeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardPokemon;
        ImageView ivCard;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            cardPokemon = itemView.findViewById(R.id.cardPokemon);
            ivCard = itemView.findViewById(R.id.ivCard);
        }


        public void bind(Pokemon pokemon) {
            // Make image size smaller by using RequestOption(). Makes app run much faster too ^_^
            // TODO: Might change width/height values, currently I just kept inputting values until it looks nice
            Glide.with(context)
                    .load(pokemon.getImages().getSmallImage())
                    .apply(new RequestOptions().override(400, 600))
                    .into(ivCard);

            // 1. Register click listener on card
            cardPokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, CardDetailActivity.class);
                    // 3. Pass pokemon object into details activity through parcel
                    i.putExtra("pokeCard", Parcels.wrap(pokemon));
                    // 4. Begin navigation
                    context.startActivity(i);
                }
            });
        }


    }
}
