package android.example.dex.ui.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.activities.SetDetailActivity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class SetViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardView;
    private final ImageView ivLogo;
    private final TextView tvSet;
    private final TextView tvSeries;
    private final TextView tvTotal;
    private final ProgressBar pgbTotal;
    private final View vert_bar;

    public SetViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cvSet);
        ivLogo = itemView.findViewById(R.id.ivLogo);
        tvSet = itemView.findViewById(R.id.tvSet);
        tvSeries = itemView.findViewById(R.id.tvSeries);
        tvTotal = itemView.findViewById(R.id.tvTotal);
        pgbTotal = itemView.findViewById(R.id.pgbTotal);
        vert_bar = itemView.findViewById(R.id.vert_bar);
    }

    public void bind(PokeSet pokeSet) {

        Glide.with(ivLogo.getContext())
                .asBitmap()
                .load(pokeSet.getmImages().getmLogo())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource != null) {
                            Palette.from(resource).generate(palette -> {
                                // Get the "vibrant" color swatch based on the bitmap
                                Palette.Swatch light_vibrant = palette.getLightVibrantSwatch();
                                Palette.Swatch dominant = palette.getDominantSwatch();
                                // Change vert_bar color
                                if (light_vibrant != null) {
                                    vert_bar.setBackgroundColor(light_vibrant.getRgb());
                                    pgbTotal.setProgressTintList(ColorStateList.valueOf(light_vibrant.getRgb()));
                                } else if (dominant != null){
                                    // Darker colors did not have light swatches
                                    vert_bar.setBackgroundColor(dominant.getRgb());
                                    pgbTotal.setProgressTintList(ColorStateList.valueOf(dominant.getRgb()));
                                } else {
                                    vert_bar.setBackgroundColor(ivLogo.getContext().getResources().getColor(R.color.default_bar));
                                }
                            });
                        }
                        return false;
                    }
                })
                .into(ivLogo);

        tvSet.setText(pokeSet.getmName());
        tvSeries.setText(pokeSet.getmSeries());
        LiveData<List<Pokemon>> pokemons = MainActivity.getmSetViewModel().getAllPokemonFromSet(pokeSet.getId());
        pokemons.observe((LifecycleOwner) itemView.getContext(), pokemonList -> {
            int card_owned = pokemonList.size();
            int set_total = Integer.parseInt(pokeSet.getmTotal());
            int percentage_owned = (int) (((double)card_owned / set_total) * 100);
            if (percentage_owned == 100) {
                tvTotal.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
            } else {
                tvTotal.setTextColor(itemView.getContext().getResources().getColor(R.color.default_text));
            }
            tvTotal.setText(+ card_owned + " / " + set_total + "  (" + percentage_owned + "%)");
            pgbTotal.setProgress(pokemonList.size());
            pgbTotal.setMax(Integer.parseInt(pokeSet.getmTotal()));
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
