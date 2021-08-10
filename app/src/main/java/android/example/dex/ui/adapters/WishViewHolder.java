package android.example.dex.ui.adapters;

import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.Prices;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.fragments.CollectionFragment;
import android.example.dex.ui.fragments.WishFragment;
import android.example.dex.utilities.PokeUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import static android.example.dex.utilities.PokeUtil.getHighestPrice;
import static android.example.dex.utilities.PokeUtil.getPriceFormatted;
import static android.example.dex.utilities.PokeUtil.getPriceType;

public class WishViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivCardImage;
    private final TextView tvName;
    private final TextView tvPrice;
    public final Button btnRemoveFromWishlist;

    public WishViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        ivCardImage = itemView.findViewById(R.id.ivCardImage);
        tvName = itemView.findViewById(R.id.tvName);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        btnRemoveFromWishlist = itemView.findViewById(R.id.btnRemoveFromWishlist);
    }

    public static WishViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wish, parent, false);
        return new WishViewHolder(view);
    }

    public void bind(Pokemon pokemon) {
        tvName.setText(pokemon.getName());
        tvPrice.setText(getPriceFormatted(getHighestPrice(pokemon)));
        Glide.with(itemView).load(pokemon.getImages().getSmallImage()).into(ivCardImage);

        // Button onClick to remove card from wishlist
        btnRemoveFromWishlist.setOnClickListener(v -> {
            Snackbar.make(v, "Removing \"" + pokemon.getName() + "\" from wishlist...", Snackbar.LENGTH_SHORT).show();
            MainActivity.getmWishViewModel().removeFromWishlist(pokemon.getId());
        });
    }
}
