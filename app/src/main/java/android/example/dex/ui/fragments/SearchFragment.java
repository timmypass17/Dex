package android.example.dex.ui.fragments;

import android.example.dex.R;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.CollectionViewHolder;
import android.example.dex.ui.adapters.SearchAdapter;
import android.example.dex.ui.adapters.SearchViewHolder;
import android.example.dex.viewmodel.SearchViewModel;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class SearchFragment extends Fragment {

    private SearchViewModel mSearchViewModel;
    private SearchAdapter adapter;
    private RecyclerView rvPokemons;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Enable action bar menu
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPokemons = view.findViewById(R.id.rvPokemons);
        adapter = new SearchAdapter(new SearchAdapter.WordDiff());
        rvPokemons.setAdapter(adapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSearchViewModel = MainActivity.getmSearchViewModel();

        // Default cards init
        mSearchViewModel.getAllPokemonByName("Pikachu").observe(getViewLifecycleOwner(), pokemons -> {
            Log.d("SearchFragment","Getting more pikachues");
            adapter.submitList(pokemons);
        });

        EditText etSearch = view.findViewById(R.id.etSearch);
        Button btnSearch = view.findViewById(R.id.btnGetPokemons);
        btnSearch.setOnClickListener(v -> {
            String name = etSearch.getText().toString();
            mSearchViewModel.getAllPokemonByName(name).observe(getViewLifecycleOwner(), pokemons ->
                    adapter.submitList(pokemons));
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem miShowAll = menu.findItem(R.id.action_show_all);
        MenuItem miShowOwned = menu.findItem(R.id.action_show_owned);

        miShowAll.setOnMenuItemClickListener(item -> showAllCards());
        miShowOwned.setOnMenuItemClickListener(item -> showOwnedCards());
    }

    // Make all cards show color
    private boolean showAllCards() {
        for (int i = 0; i < rvPokemons.getChildCount(); i++) {
            SearchViewHolder holder = (SearchViewHolder) rvPokemons.findViewHolderForAdapterPosition(i);
            // Remove gray filter
            holder.ivCard.setColorFilter(null);
        }
        return true;
    }

    // Apply gray filter on unowned cards
    private boolean showOwnedCards() {
        // Gray filter
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        for (int i = 0; i < rvPokemons.getChildCount(); i++) {
            Pokemon pokemon = adapter.getCurrentList().get(i);
            SearchViewHolder holder = (SearchViewHolder) rvPokemons.findViewHolderForAdapterPosition(i);
            // If card is not owned
            if (pokemon.isOwned != 1) {
                // Apply gray filter
                holder.ivCard.setColorFilter(new ColorMatrixColorFilter(matrix));
            }
        }
        return true;
    }
}