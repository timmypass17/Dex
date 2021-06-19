package android.example.dex.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.activities.SetDetailActivity;
import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.set.PokeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

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

        CardView cardView;
        ImageView ivSymbol;
        TextView tvSetName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cvSet);
            ivSymbol = itemView.findViewById(R.id.ivSymbol);
            tvSetName = itemView.findViewById(R.id.tvSetName);
        }

        public void bind(PokeSet pokeSet) {
            // Add symbol image
            Glide.with(context)
                    .load(pokeSet.getSetImages().getSymbol())
                    .into(ivSymbol);
            // Add set name
            tvSetName.setText(pokeSet.getSetName());

            // 1. Register click listener on card
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Series: " + pokeSet.getSeries(), Toast.LENGTH_SHORT).show();
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, SetDetailActivity.class);
                    // 3. Pass pokeSet object into details activity through parcel
                    i.putExtra("pokeSet", Parcels.wrap(pokeSet));
                    // 4. Begin navigation
                    context.startActivity(i);
                }
            });


        }
    }
}
