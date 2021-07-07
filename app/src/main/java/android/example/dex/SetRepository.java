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

public class SetRepository {

    private SetDao mSetDao;
    private LiveData<List<PokeSet>> mAllSets;

    public SetRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mSetDao = db.setDao();
        mAllSets = mSetDao.getAlphabetizedSets();
        populateSet();
    }

    public void populateSet() {
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

        Call<PokeSetResponse> call = service.getSets();
        call.enqueue(new Callback<PokeSetResponse>() {
            @Override
            public void onResponse(Call<PokeSetResponse> call, retrofit2.Response<PokeSetResponse> response) {
                PokeSetResponse pokeSetResponse = response.body();
                if (pokeSetResponse != null) {
                    List<PokeSet> pokeSetData = pokeSetResponse.getPokeSets();
                    for (PokeSet pokeSet : pokeSetData) {
                        insert(pokeSet);
                    }
                }
            }

            @Override
            public void onFailure(Call<PokeSetResponse> call, Throwable t) {
                Log.d("SetRepository", "onFailure ", t);
            }
        });
    }

    public LiveData<List<PokeSet>> getAllSets() {
        return mAllSets;
    }

    public void insert(PokeSet pokeSet) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mSetDao.insert(pokeSet);
        });
    }
}
