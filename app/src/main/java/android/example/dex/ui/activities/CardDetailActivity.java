package android.example.dex.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.Prices;
import android.example.dex.db.entity.pokemon.TCGPlayer;
import android.example.dex.ui.MainActivity;
import android.example.dex.viewmodel.CollectionViewModel;
import android.example.dex.viewmodel.WishViewModel;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

public class CardDetailActivity extends AppCompatActivity {

    CollectionViewModel mCollectionViewModel;
    WishViewModel mWishViewModel;

    View contextView;
    Button btnAddToCollection;
    Button btnAddToWishlist;
    ImageView ivCardImage;
    CardView cvNormalPrice;
    TextView tvNormalPrice;
    CardView cvHoloPrice;
    TextView tvHoloPrice;
    String normalPrice = "No price yet.";
    String holoPrice = "No price yet.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

//        mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        mCollectionViewModel = MainActivity.mCollectionViewModel;
        mWishViewModel = MainActivity.mWishViewModel;

        // Get handle on views
        contextView = findViewById(R.id.context_view);
        btnAddToCollection = findViewById(R.id.btnAddToCollection);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        ivCardImage = findViewById(R.id.ivCardImage);
        cvNormalPrice = findViewById(R.id.cvNormalPrice);
        tvNormalPrice = findViewById(R.id.tvNormalPrice);
        cvHoloPrice = findViewById(R.id.cvHoloPrice);
        tvHoloPrice = findViewById(R.id.tvHoloPrice);

        // Unwrapping pokemon object from parent
        Pokemon pokeCard = Parcels.unwrap(getIntent().getParcelableExtra("pokeCard"));

        // Bind data
        bind(pokeCard);
    }

    public void bind(Pokemon pokemon) {
        getPrice(pokemon);
        Glide.with(this).load(pokemon.getImages().getLargeImage()).into(ivCardImage);
        tvNormalPrice.setText(normalPrice);
        tvHoloPrice.setText(holoPrice);
        btnAddToCollection.setOnClickListener(v -> {
            if (pokemon.isOwned == 1) {
                Snackbar.make(contextView, "Removing \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
                mCollectionViewModel.removeFromCollection(pokemon.getId());
                btnAddToCollection.setText("Add to Collection");
            }
            else {
                Snackbar.make(contextView, "Adding \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
                mCollectionViewModel.addToCollection(pokemon.getId());
                btnAddToCollection.setText("Remove from Collection");
            }
            Toast.makeText(this, pokemon.getSetID().getId(), Toast.LENGTH_SHORT).show();
        });

        if (pokemon.isOwned == 1) {
            btnAddToCollection.setText("Remove from Collection");
        }

        btnAddToWishlist.setOnClickListener(v -> {
            mWishViewModel.addToWishlist(pokemon.getId());
        });

        cvNormalPrice.setOnClickListener(v -> goToPurchaseSite(pokemon));
        cvHoloPrice.setOnClickListener(v -> goToPurchaseSite(pokemon));
    }

    // Navigate to TCGPlayer site
    public void goToPurchaseSite(Pokemon pokemon) {
        String url = pokemon.getTcgplayer().getUrl();
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    // Finds price of card and updates price values
    public void getPrice(Pokemon pokemon) {
        // Recall: Some cards do not even have TCGPlayer for some reason
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
    }
}