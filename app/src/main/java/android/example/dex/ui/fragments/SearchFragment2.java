package android.example.dex.ui.fragments;

import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.ui.adapters.SearchAdapter;
import android.example.dex.viewmodel.SearchViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment2 extends Fragment {

    SearchViewModel mSearchViewModel;

    public SearchFragment2() {
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

        RecyclerView rvPokemons = view.findViewById(R.id.rvPokemons);
        final SearchAdapter adapter = new SearchAdapter(new SearchAdapter.WordDiff());
        rvPokemons.setAdapter(adapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 3));

        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        EditText etSearch = view.findViewById(R.id.etSearch);
        Button btnSearch = view.findViewById(R.id.btnGetPokemons);
        btnSearch.setOnClickListener(v -> {
            String name = etSearch.getText().toString();
            mSearchViewModel.getNewPokemon(name);
            // THIS TOOK HOURS TO DO!! I hate livedata :(
            LiveData<List<Pokemon>> pokemons = mSearchViewModel.getAllPokemonByName();
            pokemons.observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
                @Override
                public void onChanged(List<Pokemon> pokemons) {
                    adapter.submitList(pokemons);
                }
            });
        });

    }

}