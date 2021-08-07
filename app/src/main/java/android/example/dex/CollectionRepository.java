package android.example.dex;

import android.app.Application;
import android.example.dex.db.dao.CollectionDao;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;

import androidx.lifecycle.LiveData;

import java.util.List;

// Repositories are meant to mediate between different data sources.
// The Repository implements the logic for deciding whether to fetch data from a network
// or use results cached in a local database
public class CollectionRepository {

    private final CollectionDao mCollectionDao;
    private final LiveData<List<Pokemon>> mAllPokemons;
    private final LiveData<SumPojo> mCollectionPrice;
    private final LiveData<List<Pokemon>> mWishListPokemons;
    private final LiveData<SumPojo> mWishPrice;
    private LiveData<List<Pokemon>> mAllPokemonBySet;
    private final LiveData<Double> mHighestCollectionPrice;

    public CollectionRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mCollectionDao = db.collectionDao();
        mAllPokemons = mCollectionDao.getOwnedPokemons();
        mCollectionPrice = mCollectionDao.getCollectionPrice();
        mWishListPokemons = mCollectionDao.getWishlistPokemons();
        mWishPrice = mCollectionDao.getWishlistPrice();
        mHighestCollectionPrice = mCollectionDao.getHighestCollectionPrice();
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Pokemon>> getAllPokemonByName(String name) {
        return mCollectionDao.getPokemonByName("%" + name + "%");
    }

    public LiveData<List<Pokemon>> getmAllPokemonBySet(String setId) {
        return mCollectionDao.getPokemonBySet(setId);
    }

    public LiveData<Double> getmHighestCollectionPrice() {
        return mHighestCollectionPrice;
    }

    public LiveData<List<Pokemon>> getWishListPokemons() {
        return mWishListPokemons;
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    public LiveData<SumPojo> getCollectionPrice() {
        return mCollectionPrice;
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

    public LiveData<List<Pokemon>> getNewerPokemons() {
        return mCollectionDao.getNewerPokemons();
    }

    public LiveData<List<Pokemon>> getOlderPokemons() {
        return mCollectionDao.getOlderPokemons();
    }

    public LiveData<List<Pokemon>> getAlphabetizedPokemonAsc() {
        return mCollectionDao.getAlphabetizedPokemonAsc();
    }

    public LiveData<List<Pokemon>> getAlphabetizedPokemonDesc() {
        return mCollectionDao.getAlphabetizedPokemonDesc();
    }

    public LiveData<List<Pokemon>> getExpensivePokemons(){
        return mCollectionDao.getExpensivePokemons();
    }

    public LiveData<List<Pokemon>> getCheapestPokemons(){
        return mCollectionDao.getCheapestPokemons();
    }
}
