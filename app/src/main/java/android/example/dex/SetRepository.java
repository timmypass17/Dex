package android.example.dex;

import android.app.Application;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.dao.SetDao;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.network.PokeService;
import android.example.dex.network.PokeSetResponse;
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
        mAllSets = mSetDao.getAlphabetizedSets();
    }

    public LiveData<List<PokeSet>> getAllSets() {
        return mAllSets;
    }

    public LiveData<List<Pokemon>> getTotalFromSet(String id) {
        return mSetDao.getTotalFromSet(id);
    }
}
