package android.example.dex.adapters;

import android.content.Context;
import android.example.dex.R;
import android.example.dex.models.Pokemon;
import android.example.dex.models.Prices;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

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
        TextView tvName;
        TextView tvPrice;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            ivCard = itemView.findViewById(R.id.ivCard);
//            tvName = itemView.findViewById(R.id.tvName);
//            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

        public void bind(Pokemon pokemon) {
//            tvName.setText(pokemon.getName());
//            tvPrice.setText(String.valueOf(pokemon.getTcgplayer().getPrices().getNormal().getMarket()));
            Glide.with(context)
                    .load(pokemon.getImage().getSmallImage())
                    .into(ivCard);

            // Check if there are prices
            Prices.Normal priceNormal = pokemon.getTcgplayer().getPrices().getNormal(); // TODO: null pointer when querying "base1"
            String price = "No price yet.";
            if (priceNormal != null) {
                price = "$" + String.valueOf(priceNormal.getMarket());
            }
            final String marketPrice = price;

            ivCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: fix null pointer exceptions on prices (some prices arent available)
                    Toast.makeText(context, marketPrice , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
