package android.example.dex;

import android.example.dex.api.PokeResponse;
import android.example.dex.api.PokeService;
import android.example.dex.adapters.PokeAdapter;
import android.example.dex.data.models.pokemon.Pokemon;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

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

// TODO:
// 1. App crashes (null pointer exception) when querying set "base1", works fine for newer sets

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    private static final String BASE_URL = "https://api.pokemontcg.io/v2/";
    private static final String API_KEY = "19118357-6a69-4cc5-8b9e-02ccf48daf44";

    private List<Pokemon> pokeData;
    private PokeAdapter pokeAdapter;
    private PokeService service;

    private RecyclerView rvPokemons;
    private Button btnGetPokemons;
    private EditText etSearch;
    private TabLayout tabCard;
    String currentTab;
    String cardName;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokeData = new ArrayList<>();

        rvPokemons = view.findViewById(R.id.rvPokemons);
        btnGetPokemons = view.findViewById(R.id.btnGetPokemons);
        etSearch = view.findViewById(R.id.etSearch);
        tabCard = view.findViewById(R.id.tabCards);

        // 1. Create the adapter
        pokeAdapter = new PokeAdapter(getContext(), pokeData);

        // 2. Set the adapter on the recycler view
        rvPokemons.setAdapter(pokeAdapter);

        // 3. Set a Layout Manager on the recycler view
        rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // We can make sure to always inject the API key for each request by defining a RequestInterceptor. class.
        // In this way, we can avoid needing to have it be defined for each API call.
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("X-Api-Key", API_KEY).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build();

        service = retrofit.create(PokeService.class);

        // default fetch
        cardName = "Pikachu";
        currentTab = "Pokémon";
        fetchPokemons(queryBySuperType(cardName, currentTab));

        // Search Button onClick listener
        btnGetPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update search value
                cardName = etSearch.getText().toString().toLowerCase();
                // Get cards
                getCards(cardName);
            }
        });

        // Tab Layout onClick listener
        tabCard.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Update tab
                currentTab = (String) tab.getText();
                getCards(cardName);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    // Get pokemon cards
    private void fetchPokemons(String query) {
        // Network request (cant expect operation to be immediate)
        Call<PokeResponse> call = service.getPokemons(query);
        call.enqueue(new Callback<PokeResponse>() {
            @Override
            public void onResponse(Call<PokeResponse> call, retrofit2.Response<PokeResponse> response) {
                PokeResponse pokemonResponse = response.body();  // Our list of pokemon data
                if (pokemonResponse != null) {
                    pokeData.clear();
                    pokeData.addAll(pokemonResponse.pokemons);
                    pokeAdapter.notifyDataSetChanged();
                }
                Log.d(TAG, "Pokemon Size: " + pokeData.size());
                Log.d(TAG, "onSuccess: " + response);
                Log.d(TAG, "Pokemon Response: " + pokemonResponse);
                Log.d(TAG, "Pokemon Data: " + pokeData);
            }

            @Override
            public void onFailure(Call<PokeResponse> call, Throwable t) {
                Log.d(TAG, "onFailure ", t);
            }
        });
    }

    // Get pokemon card by supertype
    private void getCards(String cardName) {
        switch (currentTab) {
            case "Pokémon":
                fetchPokemons(queryBySuperType(cardName, "Pokémon"));
                break;
            case "Trainer":
                fetchPokemons(queryBySuperType(cardName, "Trainer"));
                break;
            case "Item":
                fetchPokemons(queryBySuperType(cardName, "Energy"));
                break;
        }
    }

    // Helper method
    private String querySet(String setId) {
        return String.format("set.id:%s", setId);
    }

    private String queryBySuperType(String name, String superType) {
        return String.format("name:%s* supertype:%s", name, superType);
    }

}