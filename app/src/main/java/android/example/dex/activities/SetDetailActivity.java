package android.example.dex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.example.dex.R;
import android.example.dex.models.set.PokeSet;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class SetDetailActivity extends AppCompatActivity {

    ImageView ivLogo;
    ImageView ivSymbol;
    TextView tvSetName;
    TextView tvSeries;
    TextView tvCardCount;
    TextView tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_detail);

        // Get handle on views
        ivLogo = findViewById(R.id.ivLogo);
        ivSymbol = findViewById(R.id.ivSymbol);
        tvSetName = findViewById(R.id.tvSetName);
        tvSeries = findViewById(R.id.tvSeries);
        tvCardCount = findViewById(R.id.tvCardCount);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);

        // Unwrapping pokeSet object from parent
        PokeSet pokeSet = Parcels.unwrap(getIntent().getParcelableExtra("pokeSet"));

        // Binding data
        Glide.with(this).load(pokeSet.getSetImages().getLogo()).into(ivLogo);
        Glide.with(this).load(pokeSet.getSetImages().getSymbol()).into(ivSymbol);
        tvSetName.setText(pokeSet.getSetName());
        tvSeries.setText(pokeSet.getSeries());
        tvCardCount.setText(pokeSet.getTotal());
        tvReleaseDate.setText(pokeSet.getReleaseDate());
    }
}