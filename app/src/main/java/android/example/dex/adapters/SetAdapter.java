package android.example.dex.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.SetDetailActivity;
import android.example.dex.data.models.set.PokeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        ImageView ivLogo;
        TextView tvSetName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cvSet);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvSetName = itemView.findViewById(R.id.tvSetName);
        }

        public void bind(PokeSet pokeSet) {
            // Add symbol image
            Glide.with(context)
                    .load(pokeSet.getSetImages().getLogo())
                    .into(ivLogo);
            // Add set name
            tvSetName.setText(pokeSet.getSetName());

            // 1. Register click listener on card
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
