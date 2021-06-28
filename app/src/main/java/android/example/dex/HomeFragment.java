package android.example.dex;

import android.app.Activity;
import android.content.Intent;
import android.example.dex.adapters.PokeAdapter;
import android.example.dex.api.PokeResponse;
import android.example.dex.api.PokeService;
import android.example.dex.data.PokemonViewModel;
import android.example.dex.data.models.pokemon.Pokemon;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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


public class HomeFragment extends Fragment {

    private PokemonViewModel mPokemonViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final PokemonListAdapter adapter = new PokemonListAdapter(new PokemonListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mPokemonViewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(PokemonViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPokemonViewModel.getAllPokemons().observe((LifecycleOwner) getContext(), pokemons -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(pokemons);
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewPokemonActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //There are no request codes
                        Intent data = result.getData();
                        // your operations...
                        Pokemon pokemon = new Pokemon(data.getStringExtra(NewPokemonActivity.EXTRA_REPLY));
                        Log.d("HomeFragment", "Data received: " + data.getStringExtra(NewPokemonActivity.EXTRA_REPLY));
                        mPokemonViewModel.insert(pokemon);

                    }
                }
            });
}