package android.example.dex.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

// TODO: Might make cache to fix small delay of card image
public class CardDetailActivity extends AppCompatActivity {

    CollectionViewModel mCollectionViewModel;
    WishViewModel mWishViewModel;

    View contextView;
    Button btnAddToCollection;
    Button btnAddToWishlist;
    ImageView ivCardImage;
    CardView cardPrice;
    TextView tvTypePrice;
    TextView tvPrice;
    TextView tvSet;
    TextView tvSeries;
    TextView tvNumber;
    TextView tvRarity;
    TextView tvArtist;
    TextView tvLegality;

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
        cardPrice = findViewById(R.id.cardPrice);
        tvTypePrice = findViewById(R.id.tvTypePrice);
        tvPrice = findViewById(R.id.tvPrice);
        tvSet = findViewById(R.id.tvSet);
        tvSeries = findViewById(R.id.tvSeries);
        tvNumber = findViewById(R.id.tvNumber);
        tvRarity = findViewById(R.id.tvRarity);
        tvArtist = findViewById(R.id.tvArtist);
        tvLegality = findViewById(R.id.tvLegality);

        // Unwrapping pokemon object from parent
        Pokemon pokeCard = Parcels.unwrap(getIntent().getParcelableExtra("pokeCard"));

        // Bind data
        bind(pokeCard);
    }

    public void bind(Pokemon pokemon) {
        Glide.with(this).load(pokemon.getImages().getLargeImage()).into(ivCardImage);
        tvPrice.setText(Prices.getPrice(pokemon));
        tvTypePrice.setText(Prices.getPriceType(pokemon));
        tvSet.setText(pokemon.getSetID().getName());
        tvSeries.setText(pokemon.getSetID().getSeries());
        tvNumber.setText(pokemon.getNumber() + " / " + pokemon.getSetID().getTotal());
        tvRarity.setText(pokemon.getRarity());
        tvArtist.setText(pokemon.getArtist());
        tvLegality.setText(pokemon.getLegalities().getUnlimited());

        btnAddToCollection.setOnClickListener(v -> {
            if (pokemon.isOwned == 1) {
                Snackbar.make(contextView, "Removing \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
                mCollectionViewModel.removeFromCollection(pokemon.getId());
            }
            else {
                Snackbar.make(contextView, "Adding \"" + pokemon.getName() + "\" to collection...", Snackbar.LENGTH_SHORT).show();
                mCollectionViewModel.addToCollection(pokemon.getId());
            }
        });


        btnAddToWishlist.setOnClickListener(v -> {
            if (pokemon.isWish == 1) {
                Snackbar.make(contextView, "Removing \"" + pokemon.getName() + "\" to wishlist...", Snackbar.LENGTH_SHORT).show();
                mWishViewModel.removeFromWishlist(pokemon.getId());
            }
            else {
                Snackbar.make(contextView, "Adding \"" + pokemon.getName() + "\" to wishlist...", Snackbar.LENGTH_SHORT).show();
                mWishViewModel.addToWishlist(pokemon.getId());
            }
        });

        cardPrice.setOnClickListener(v -> goToPurchaseSite(pokemon));
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
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}