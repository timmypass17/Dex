package android.example.dex.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.activities.CardDetailActivity;
import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.pokemon.Prices;
import android.example.dex.models.pokemon.TCGPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        ImageView ivCard;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            ivCard = itemView.findViewById(R.id.ivCard);
        }


        public void bind(Pokemon pokemon) {
            // Make image size smaller by using RequestOption(). Makes app run much faster too ^_^
            // TODO: Might change width/height values, currently I just kept inputting values until it looks nice
            Glide.with(context)
                    .load(pokemon.getImage().getSmallImage())
                    .apply(new RequestOptions().override(400, 600))
                    .into(ivCard);

            String normalPrice = "No price yet.";
            String holoPrice = "No price yet.";
            // Recall: Some cards do not even have TCGPlayer for some reason
            // TODO: Future me, clean up code, its a little nasty
            TCGPlayer tcgPlayer = pokemon.getTcgplayer();
            // Null checking json values
            if (tcgPlayer != null) {
                // Check if there are prices
                Prices.Normal normal = pokemon.getTcgplayer().getPrices().getNormal();
                Prices.HoloFoil holoFoil = pokemon.getTcgplayer().getPrices().getHolofoil();
                // If there is a price, get it
                if (normal != null) {
                    normalPrice = "$" + String.valueOf(normal.getMarket());
                }
                if (holoFoil != null) {
                    holoPrice = "$" + String.valueOf(holoFoil.getMarket());
                }
            }

            // 1. Register click listener on card
            String finalNormalPrice = normalPrice;
            String finalHoloPrice = holoPrice;
            ivCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, CardDetailActivity.class);
                    // 3. Pass pokemon object into details activity through parcel
                    i.putExtra("pokeCard", Parcels.wrap(pokemon));
                    i.putExtra("pokeNormalPrice", finalNormalPrice);
                    i.putExtra("pokeHoloPrice", finalHoloPrice);
                    // 4. Begin navigation
                    context.startActivity(i);
                }
            });
        }
    }
}
