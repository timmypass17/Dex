package android.example.dex;

import android.app.Application;
import android.example.dex.db.dao.CollectionDao;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.network.PokeResponse;
import android.example.dex.network.PokeService;
import android.util.Log;

import androidx.lifecycle.LiveData;

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
    private LiveData<List<Pokemon>> mAllPokemonByName; // TODO: Initialize mAllPokemonByName by something
    private LiveData<List<Pokemon>> mWishListPokemons;
    private LiveData<SumPojo> mWishPrice;
    private LiveData<List<Pokemon>>  mAllPokemonBySet;

    public CollectionRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mCollectionDao = db.collectionDao();
        mAllPokemons = mCollectionDao.getOwnedPokemons();
        mTotalPrice = mCollectionDao.getCollectionPrice();
        mWishListPokemons = mCollectionDao.getWishlistPokemons();
        mWishPrice = mCollectionDao.getWishlistPrice();
        // populateCards();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    public LiveData<List<Pokemon>> getAllPokemonByName() {
        return mAllPokemonByName;
    }

    public LiveData<List<Pokemon>> getNewPokemon(String name) {
        return mCollectionDao.getPokemonByName(name);
    }

    public LiveData<List<Pokemon>> getmAllPokemonBySet() {
        return mAllPokemonBySet;
    }

    public LiveData<List<Pokemon>> getPokemonBySet(String set) {
        return mCollectionDao.getPokemonBySet(set);
    }

    public LiveData<List<Pokemon>> getWishListPokemons() {
        return mWishListPokemons;
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    public LiveData<SumPojo> getTotalPrice() {
        return mTotalPrice;
    }

    public LiveData<SumPojo> getWishPrice() {
        return mWishPrice;
    }

    public LiveData<List<Pokemon>> getAllPokemonBySet() {
        return mAllPokemonBySet;
    }

    // 1. You must call this on a non-UI thread or your app will throw an exception.
    // 2. Use the ExecutorService we created in the WordRoomDatabase to perform the DAO methods on a background thread.

    public void addToCollection(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.addToCollection(id);
        });
    }

    public void removeFromCollection(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.removeFromCollection(id);
        });
    }

    public void addToWishlist(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.addToWishlist(id);
        });
    }

    public void removeFromWishlist(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCollectionDao.removeFromWishlist(id);
        });
    }

}
