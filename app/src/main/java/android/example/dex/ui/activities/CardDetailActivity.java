package android.example.dex.ui.activities;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.Prices;
import android.example.dex.ui.MainActivity;
import android.example.dex.db.viewmodel.CollectionViewModel;
import android.example.dex.db.viewmodel.WishViewModel;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.Objects;

import static android.example.dex.utilities.PokeUtil.getHighestPrice;
import static android.example.dex.utilities.PokeUtil.getPriceFormatted;
import static android.example.dex.utilities.PokeUtil.getPriceType;

public class CardDetailActivity extends AppCompatActivity {

    private static final String TAG = "CardDetailActivity";
    private CollectionViewModel mCollectionViewModel;
    private WishViewModel mWishViewModel;
    private Button btnAddToCollection;
    private Button btnAddToWishlist;
    private ImageView ivCardImage;
    private CardView cardPrice;
    private TextView tvTypePrice;
    private TextView tvPrice;
    private ImageView ivSymbol;
    private TextView tvSet;
    private TextView tvSeries;
    private TextView tvNumber;
    private TextView tvRarity;
    private TextView tvArtist;
    private TextView tvLegality;
    private View contextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        // Back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        mCollectionViewModel = MainActivity.getmCollectionViewModel();
        mWishViewModel = MainActivity.getmWishViewModel();

        // Get handle on views
        contextView = findViewById(R.id.context_view);
        btnAddToCollection = findViewById(R.id.btnAddToCollection);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        ivCardImage = findViewById(R.id.ivCardImage);
        cardPrice = findViewById(R.id.cardPrice);
        tvTypePrice = findViewById(R.id.tvTypePrice);
        tvPrice = findViewById(R.id.tvPrice);
        ivSymbol = findViewById(R.id.ivSymbol);
        tvSet = findViewById(R.id.tvSet);
        tvSeries = findViewById(R.id.tvSeries);
        tvNumber = findViewById(R.id.tvNumber);
        tvRarity = findViewById(R.id.tvRarity);
        tvArtist = findViewById(R.id.tvArtist);
        tvLegality = findViewById(R.id.tvLegality);

        // Unwrapping pokemon object
        Pokemon pokeCard = Parcels.unwrap(getIntent().getParcelableExtra("pokeCard"));
        // Bind data
        bind(pokeCard);
    }

    private void bind(Pokemon pokemon) {
        Glide.with(this)
                .load(pokemon.getImages().getLargeImage())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivCardImage);
        tvPrice.setText(getPriceFormatted(getHighestPrice(pokemon)));
        Glide.with(this).load(pokemon.getSetID().getImages().getSymbol()).into(ivSymbol);
        tvTypePrice.setText(getPriceType(pokemon));
        tvSet.setText(pokemon.getSetID().getName());
        tvSeries.setText(pokemon.getSetID().getSeries());
        tvNumber.setText(pokemon.getCard_number() + " / " + pokemon.getSetID().getTotal());
        tvRarity.setText(pokemon.getRarity());
        tvArtist.setText(pokemon.getArtist());
        tvLegality.setText(pokemon.getLegalities().getUnlimited());
        btnAddToCollection.setText(getOwnedStatus(pokemon));

        // Button to add card to collection
        btnAddToCollection.setOnClickListener(v -> updateCollection(pokemon));
        // Button to add card to wishlist
        btnAddToWishlist.setOnClickListener(v -> updateWishlist(pokemon));
        // Button to navigate to purchase website
        cardPrice.setOnClickListener(v -> goToPurchaseSite(pokemon));
    }

    // Navigate to TCGPlayer site
    private void goToPurchaseSite(Pokemon pokemon) {
        String url = pokemon.getTcgplayer().getUrl();
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    // Add or Remove card from collection
    private void updateCollection(Pokemon pokemon) {
        // If card is owned
        if (pokemon.isOwned == 1) {
            Snackbar.make(contextView, "Removing \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
            // Remove from collection
            mCollectionViewModel.removeFromCollection(pokemon.getId());
            // Update to show "Not Owned"
            btnAddToCollection.setText("Not Owned");
        }
        // If card is not owned
        else {
            Snackbar.make(contextView, "Adding \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
            // Add to collection
            mCollectionViewModel.addToCollection(pokemon.getId());
            // Update to show "Owned"
            btnAddToCollection.setText("Owned");
        }
    }

    // Add or Remove card from wishlist
    private void updateWishlist(Pokemon pokemon) {
        if (pokemon.isWish == 1) {
            Snackbar.make(contextView, "Removing \"" + pokemon.getName() + "\" to wishlist...", Snackbar.LENGTH_SHORT).show();
            mWishViewModel.removeFromWishlist(pokemon.getId());
        }
        else {
            Snackbar.make(contextView, "Adding \"" + pokemon.getName() + "\" to wishlist...", Snackbar.LENGTH_SHORT).show();
            mWishViewModel.addToWishlist(pokemon.getId());
        }
    }

    // Helper method to return string of ownership
    private String getOwnedStatus(Pokemon pokemon) {
        if (pokemon.isOwned == 1) {
            return "Owned";
        }
        return "Not Owned";
    }

    // Back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}