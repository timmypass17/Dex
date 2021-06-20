package android.example.dex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.example.dex.R;
import android.example.dex.models.pokemon.Pokemon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

// TODO: Add holofoil price. (Currently only handles normal market price)
public class CardDetailActivity extends AppCompatActivity {

    ImageView ivCardImage;

    CardView cvNormalPrice;
    TextView tvNormalPrice;

    CardView cvHoloPrice;
    TextView tvHoloPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        // Get handle on views
        ivCardImage = findViewById(R.id.ivCardImage);
        cvNormalPrice = findViewById(R.id.cvNormalPrice);
        tvNormalPrice = findViewById(R.id.tvNormalPrice);
        cvHoloPrice = findViewById(R.id.cvHoloPrice);
        tvHoloPrice = findViewById(R.id.tvHoloPrice);

        // Unwrapping pokemon object from parent
        Pokemon pokeCard = Parcels.unwrap(getIntent().getParcelableExtra("pokeCard"));
        String normalPrice = getIntent().getStringExtra("pokeNormalPrice");
        String holoPrice = getIntent().getStringExtra("pokeHoloPrice");

        // Bind data
        cvNormalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = pokeCard.getTcgplayer().getUrl();

                Uri webpage = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, webpage);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this!");
                }
            }
        });

        // TODO: future me, remove lazy duplicate code (im sleepy)
        cvHoloPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = pokeCard.getTcgplayer().getUrl();

                Uri webpage = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, webpage);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this!");
                }
            }
        });

        Glide.with(this).load(pokeCard.getImage().getLargeImage()).into(ivCardImage);
        tvNormalPrice.setText(normalPrice);
        tvHoloPrice.setText(holoPrice);
    }
}