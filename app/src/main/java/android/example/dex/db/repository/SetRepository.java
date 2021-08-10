package android.example.dex.db.repository;

import android.app.Application;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.dao.SetDao;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;

import androidx.lifecycle.LiveData;

import java.util.List;

// Repository modules handle data operations.
// They provide a clean API so that the rest of the app can retrieve this data easily.
// They know where to get the data from and what API calls to make when data is updated.
// You can consider repositories to be mediators between different data sources, such as persistent models, web services, and caches.
public class SetRepository {

    private SetDao mSetDao;
    private LiveData<List<PokeSet>> mAllSets;

    public SetRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mSetDao = db.setDao();
        mAllSets = mSetDao.getOldSet(); // Set default order
    }

    public LiveData<List<PokeSet>> getAllSets() {
        return mAllSets;
    }

    public LiveData<List<Pokemon>> getmAllPokemonBySet(String setId) {
        return mSetDao.getPokemonBySet(setId);
    }

    // Get sets in sorted order
    public LiveData<List<PokeSet>> getOldSet() {
        return mSetDao.getOldSet();
    }

    public LiveData<List<PokeSet>> getNewSet() {
        return mSetDao.getNewSet();
    }

    public LiveData<List<PokeSet>> getAlphabetizedSetAsc() {
        return mSetDao.getAlphabetizedSetAsc();
    }
    public LiveData<List<PokeSet>> getAlphabetizedSetDesc() {
        return mSetDao.getAlphabetizedSetDesc();
    }

    public LiveData<List<Pokemon>> getAllPokemonFromSet(String id) {
        return mSetDao.getAllPokemonFromSet(id);
    }
}
