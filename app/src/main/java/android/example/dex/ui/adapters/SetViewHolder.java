package android.example.dex.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.activities.SetDetailActivity;
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

public class SetViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView ivLogo;
    TextView tvSetName;

    public SetViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cvSet);
        ivLogo = itemView.findViewById(R.id.ivLogo);
        tvSetName = itemView.findViewById(R.id.tvSetName);
    }

    public void bind(PokeSet pokeSet) {
        // Add symbol image
        Glide.with(ivLogo.getContext())
                .load(pokeSet.getmImages().getmLogo())
                .into(ivLogo);
        // Add set name
        tvSetName.setText(pokeSet.getmName());

        // 1. Register click listener on card
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. Navigate to a new activity on tap
                Intent i = new Intent(cardView.getContext(), SetDetailActivity.class);
                // 3. Pass pokeSet object into details activity through parcel
                i.putExtra("pokeSet", Parcels.wrap(pokeSet));
                // 4. Begin navigation
                cardView.getContext().startActivity(i);
            }
        });
    }

    static SetViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set,parent, false);
        return new SetViewHolder(view);
    }
}
