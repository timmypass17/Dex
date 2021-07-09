package android.example.dex.ui.fragments;

import android.example.dex.R;
import android.example.dex.ui.adapters.SearchAdapter;
import android.example.dex.viewmodel.SearchViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

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

        mSearchViewModel.getAllPokemonByName().observe(getViewLifecycleOwner(), pokemons -> {
            adapter.submitList(pokemons);
        });
    }
}