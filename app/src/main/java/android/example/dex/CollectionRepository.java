package android.example.dex;

import android.app.Application;
import android.example.dex.db.dao.CollectionDao;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.network.PokeResponse;
import android.example.dex.network.PokeService;
import android.example.dex.network.PokeSetResponse;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Repositories are meant to mediate between different data sources.
// The Repository implements the logic for deciding whether to fetch data from a network
// or use results cached in a local database
public class CollectionRepository {

    private final CollectionDao mCollectionDao;
    private final LiveData<List<Pokemon>> mAllPokemons;
    private final LiveData<SumPojo> mTotalPrice;
    private LiveData<List<Pokemon>> mAllPokemonByName;

    public CollectionRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mCollectionDao = db.collectionDao();
        mAllPokemons = mCollectionDao.getOwnedPokemons();
        mTotalPrice = mCollectionDao.getCollectionPrice();
        // TODO: Initialize mAllPokemonByName by something
        populateCards();
    }

    public LiveData<List<Pokemon>> getAllPokemonByName() {
        return mAllPokemonByName;
    }

    public LiveData<List<Pokemon>> getNewPokemon(String name) {
        return mCollectionDao.getPokemonByName(name);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    public LiveData<SumPojo> getTotalPrice() {
        return mTotalPrice;
    }

    // 1. You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    // 2. We need to not run the insert on the main thread, so we use the ExecutorService we created
    // in the WordRoomDatabase to perform the insert on a background thread.
    public void insert(Pokemon pokemon) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.insert(pokemon);
        });
    }
    public void deletePokemon(Pokemon pokemon) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.deletePokemon(pokemon);
        });
    }
    public void deleteAll() {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.deleteAll();
        });
    }

    public void addToCollection(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.addToCollection(id);
        });
    }

    // Fetch ALL cards and insert into database
    public void populateCards() {
        String BASE_URL = "https://api.pokemontcg.io/v2/";
        String API_KEY = "19118357-6a69-4cc5-8b9e-02ccf48daf44";

        // Interceptor
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("X-Api-Key", API_KEY).build();
                return chain.proceed(request);
            }
        });

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build();

        PokeService service = retrofit.create(PokeService.class);

        Call<PokeResponse> call = service.getAllPokemons();
        call.enqueue(new Callback<PokeResponse>() {
            @Override
            public void onResponse(Call<PokeResponse> call, retrofit2.Response<PokeResponse> response) {
                PokeResponse pokeResponse = response.body();
                if (pokeResponse != null) {
                    Log.d("CollectionRepository", "onSuccess: Getting data");
                    List<Pokemon> pokeData = pokeResponse.getPokemons();
                    for (Pokemon pokemon : pokeData) {
                        insert(pokemon);
                    }
                }
            }

            @Override
            public void onFailure(Call<PokeResponse> call, Throwable t) {
                Log.d("CollectionRepository", "onFailure: Fail to get data", t);
            }
        });
    }

}
