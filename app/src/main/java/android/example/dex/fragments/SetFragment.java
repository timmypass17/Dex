package android.example.dex.fragments;

import android.example.dex.adapters.SetAdapter;
import android.example.dex.api.PokeResponse;
import android.example.dex.api.PokeService;
import android.example.dex.api.PokeSetResponse;
import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.set.PokeSet;
import android.example.dex.models.set.PokeSetImage;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.dex.R;

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


public class SetFragment extends Fragment {

    private static final String TAG = "SetFragment";
    private static final String BASE_URL = "https://api.pokemontcg.io/v2/";
    private static final String API_KEY = "19118357-6a69-4cc5-8b9e-02ccf48daf44";

    private List<PokeSet> pokeSetData;
    private SetAdapter setAdapter;
    private PokeService service;
    private RecyclerView rvSets;

    public SetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokeSetData = new ArrayList<>();

        rvSets = view.findViewById(R.id.rvSet);

        // Adapters
        setAdapter = new SetAdapter(getContext(), pokeSetData);
        rvSets.setAdapter(setAdapter);
        rvSets.setLayoutManager(new LinearLayoutManager(getContext()));

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

        service = retrofit.create(PokeService.class);

        fetchSets();
    }

    // Get set data
    private void fetchSets() {
        Call<PokeSetResponse> call = service.getSets();
        call.enqueue(new Callback<PokeSetResponse>() {
            @Override
            public void onResponse(Call<PokeSetResponse> call, retrofit2.Response<PokeSetResponse> response) {
                PokeSetResponse pokeSetResponse = response.body();
                if (pokeSetResponse != null) {
                    pokeSetData.clear(); // TODO: Could optimize, make it so we dont have to clear it everytime
                    pokeSetData.addAll(pokeSetResponse.pokeSets);
                    setAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokeSetResponse> call, Throwable t) {
                Log.d(TAG, "onFailure ", t);
            }
        });
    }
}