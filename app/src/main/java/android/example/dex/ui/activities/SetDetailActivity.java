package android.example.dex.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.dex.R;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.PokeAdapter;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.network.PokeResponse;
import android.example.dex.network.PokeService;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.adapters.SearchAdapter;
import android.example.dex.viewmodel.SearchViewModel;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetDetailActivity extends AppCompatActivity{

    SearchViewModel mSearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_detail);
        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvPokemons = findViewById(R.id.rvPokemons);
        final SearchAdapter adapter = new SearchAdapter(new SearchAdapter.WordDiff());
        rvPokemons.setAdapter(adapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(this, 3));

        ImageView ivLogo = findViewById(R.id.ivLogo);
        ImageView ivSymbol = findViewById(R.id.ivSymbol);
        TextView tvSetName = findViewById(R.id.tvSet);
        TextView tvSeries = findViewById(R.id.tvSeries);
        TextView tvCardCount = findViewById(R.id.tvCardCount);
        TextView tvReleaseDate = findViewById(R.id.tvReleaseDate);

        // Unwrapping pokeSet object from parent
        PokeSet pokeSet = Parcels.unwrap(getIntent().getParcelableExtra("pokeSet"));

        // Binding data
        Glide.with(this).load(pokeSet.getmImages().getmLogo()).into(ivLogo);
        Glide.with(this).load(pokeSet.getmImages().getmSymbol()).into(ivSymbol);
        tvSetName.setText(pokeSet.getmName());
        tvSeries.setText(pokeSet.getmSeries());
        tvReleaseDate.setText(pokeSet.getReleaseDate());

        mSearchViewModel = MainActivity.mSearchViewModel;
        String set = pokeSet.getId();
        mSearchViewModel.updateAllPokemonBySet(set);
        mSearchViewModel.getAllPokemonBySet().observe(this, pokemons -> {
            adapter.submitList(pokemons);
        });
        LiveData<List<Pokemon>> pokemons = MainActivity.mSetViewModel.getTotalFromSet(pokeSet.getId());
        pokemons.observe(this, (Observer<List<Pokemon>>) pokemonList -> {
            tvCardCount.setText(pokemonList.size() + " / " + pokeSet.getmTotal());
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}