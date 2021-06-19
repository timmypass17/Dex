package android.example.dex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.dex.R;
import android.example.dex.adapters.PokeAdapter;
import android.example.dex.api.PokeResponse;
import android.example.dex.api.PokeService;
import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.set.PokeSet;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.io.IOException;
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

// TODO: Future me, implement interfaces (Java does not allow multiple inheritances)
// https://stackoverflow.com/questions/5836662/extending-from-two-classes

public class SetDetailActivity extends AppCompatActivity{

    private static final String BASE_URL = "https://api.pokemontcg.io/v2/";
    private static final String API_KEY = "19118357-6a69-4cc5-8b9e-02ccf48daf44";

    private List<Pokemon> pokeData;
    private PokeAdapter pokeAdapter;
    private PokeService service;
    private RecyclerView rvPokemons;
    String currentTab = "Pokémon";
    String currentSearch = "";

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

        // List of pokemon data
        pokeData = new ArrayList<>();

        // Get handle on views
        rvPokemons = findViewById(R.id.rvPokemons);
        ivLogo = findViewById(R.id.ivLogo);
        ivSymbol = findViewById(R.id.ivSymbol);
        tvSetName = findViewById(R.id.tvSetName);
        tvSeries = findViewById(R.id.tvSeries);
        tvCardCount = findViewById(R.id.tvCardCount);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);

        // Adapter
        pokeAdapter = new PokeAdapter(this, pokeData);
        rvPokemons.setAdapter(pokeAdapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(this, 3));

        // Interceptor and Retrofit
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("X-Api-Key", API_KEY).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).client(httpClient.build()).build();
        service = retrofit.create(PokeService.class);

        // Unwrapping pokeSet object from parent
        PokeSet pokeSet = Parcels.unwrap(getIntent().getParcelableExtra("pokeSet"));

        // TODO: Fetch data by 50 instead of the whole thing.
        //  Use infinite pagination technique (when user scrolls down, fetch next 50 cards)

        // Get pokemon data by set id
        Call<PokeResponse> call = service.getPokemons(querySet(pokeSet.getId()));
        call.enqueue(new Callback<PokeResponse>() {
            @Override
            public void onResponse(Call<PokeResponse> call, retrofit2.Response<PokeResponse> response) {
                PokeResponse pokemonResponse = response.body();
                if (pokemonResponse != null) {
                    pokeData.clear();
                    pokeData.addAll(pokemonResponse.pokemons);
                    pokeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokeResponse> call, Throwable t) {

            }
        });

        // Binding data
        Glide.with(this).load(pokeSet.getSetImages().getLogo()).into(ivLogo);
        Glide.with(this).load(pokeSet.getSetImages().getSymbol()).into(ivSymbol);
        tvSetName.setText(pokeSet.getSetName());
        tvSeries.setText(pokeSet.getSeries());
        tvCardCount.setText(pokeSet.getTotal());
        tvReleaseDate.setText(pokeSet.getReleaseDate());
    }

    // Helper method
    private String querySet(String setId) {
        return String.format("set.id:%s", setId);
    }

}