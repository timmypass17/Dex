package android.example.dex.ui.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.SearchAdapter;
import android.example.dex.viewmodel.SearchViewModel;
import android.example.dex.viewmodel.SetViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.List;
import java.util.Objects;

public class SetDetailActivity extends AppCompatActivity{

    private SearchViewModel mSearchViewModel;
    private SearchAdapter searchAdapter;
    private RecyclerView rvPokemons;
    private ImageView ivLogo;
    private ImageView ivSymbol;
    private TextView tvSetName;
    private TextView tvSeries;
    private TextView tvCardCount;
    private TextView tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_detail);
        mSearchViewModel = MainActivity.getmSearchViewModel();
        // Back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Adapter
        rvPokemons = findViewById(R.id.rvPokemons);
        searchAdapter = new SearchAdapter(new SearchAdapter.WordDiff());
        rvPokemons.setAdapter(searchAdapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(this, 3));

        // Get handle on views
        ivLogo = findViewById(R.id.ivLogo);
        ivSymbol = findViewById(R.id.ivSymbol);
        tvSetName = findViewById(R.id.tvSet);
        tvSeries = findViewById(R.id.tvSeries);
        tvCardCount = findViewById(R.id.tvCardCount);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);

        // Unwrapping pokeSet object from parent
        PokeSet pokeSet = Parcels.unwrap(getIntent().getParcelableExtra("pokeSet"));

        // Binding data
        bind(pokeSet);
    }

    private void bind(PokeSet pokeSet) {
        Glide.with(this).load(pokeSet.getmImages().getmLogo()).into(ivLogo);
        Glide.with(this).load(pokeSet.getmImages().getmSymbol()).into(ivSymbol);
        tvSetName.setText(pokeSet.getmName());
        tvSeries.setText(pokeSet.getmSeries());
        tvReleaseDate.setText(pokeSet.getReleaseDateFormatted(pokeSet.getReleaseDate()));

        // Add Observer to update recyclerview of cards (Recall: updates whenever dataset changes)
        mSearchViewModel.getAllPokemonBySet(pokeSet.getId()).observe(this, pokemons -> {
            searchAdapter.submitList(pokemons);
            tvCardCount.setText(getTotalOwned(pokemons) + " / " + pokeSet.getmTotal());
        });
    }

    private int getTotalOwned(List<Pokemon> pokemonList) {
        int ownedCount = 0;
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.isOwned == 1) {
                ownedCount += 1;
            }
        }
        return ownedCount;
    }

    // Back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Just finish activity to go back to prev activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}