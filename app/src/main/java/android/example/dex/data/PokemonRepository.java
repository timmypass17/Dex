package android.example.dex.data;

import android.app.Application;
import android.example.dex.data.models.pokemon.Pokemon;
import android.example.dex.data.models.pokemon.SumPojo;

import androidx.lifecycle.LiveData;

import java.util.List;

// Repositories are meant to mediate between different data sources.
// The Repository implements the logic for deciding whether to fetch data from a network
// or use results cached in a local database
public class PokemonRepository {

    private PokemonDao mPokemonDao;
    private LiveData<List<Pokemon>> mAllPokemons;
    private LiveData<SumPojo> mTotalPrice;

    PokemonRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mPokemonDao = db.pokemonDao();
        mAllPokemons = mPokemonDao.getAlphabetizedPokemons();
        mTotalPrice = mPokemonDao.getCollectionPrice();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    public LiveData<SumPojo> getTotalPrice() {
        return mTotalPrice;
    }

    // TODO: I changed these methods to public, idk why
    //  Original: https://github.com/googlecodelabs/android-room-with-a-view/blob/master/app/src/main/java/com/example/android/roomwordssample/WordRepository.java

    // 1. You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    // 2. We need to not run the insert on the main thread, so we use the ExecutorService we created
    // in the WordRoomDatabase to perform the insert on a background thread.
    public void insert(Pokemon pokemon) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPokemonDao.insert(pokemon);
        });
    }
    public void deletePokemon(Pokemon pokemon) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPokemonDao.deletePokemon(pokemon);
        });
    }
    public void deleteAll() {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPokemonDao.deleteAll();
        });
    }

}
