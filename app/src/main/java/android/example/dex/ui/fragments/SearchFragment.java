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
    private SearchAdapter mSearchAdapter;
    private RecyclerView rvPokemons;
    private EditText etSearch;
    private Button btnSearch;

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

        // Get handle on views
        rvPokemons = view.findViewById(R.id.rvPokemons);
        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnGetPokemons);

        // Set Adapter
        mSearchAdapter = new SearchAdapter(new SearchAdapter.WordDiff());
        rvPokemons.setAdapter(mSearchAdapter);
        rvPokemons.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSearchViewModel = MainActivity.getmSearchViewModel();

        // Initialize default search query
        mSearchViewModel.getAllPokemonByName("Pikachu").observe(getViewLifecycleOwner(), pokemons -> {
            mSearchAdapter.submitList(pokemons);
        });

        // Search button to query pokemons
        btnSearch.setOnClickListener(v -> {
            String name = etSearch.getText().toString();
            mSearchViewModel.getAllPokemonByName(name).observe(getViewLifecycleOwner(), pokemons ->
                    mSearchAdapter.submitList(pokemons));
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        // Get handle on menu items
        MenuItem miShowAll = menu.findItem(R.id.action_show_all);
        MenuItem miShowOwned = menu.findItem(R.id.action_show_owned);

        // Set menu item onClick listener
        miShowAll.setOnMenuItemClickListener(item -> showAllCards());
        miShowOwned.setOnMenuItemClickListener(item -> showOwnedCards());
    }

    // Show all cards color
    private boolean showAllCards() {
        for (int i = 0; i < rvPokemons.getChildCount(); i++) {
            SearchViewHolder holder = (SearchViewHolder) rvPokemons.findViewHolderForAdapterPosition(i);
            // Remove gray filter
            holder.ivCard.clearColorFilter();;
        }
        return true;
    }

    // Show cards color on owned cards only
    private boolean showOwnedCards() {
        // Gray filter
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        for (int i = 0; i < rvPokemons.getChildCount(); i++) {
            Pokemon pokemon = mSearchAdapter.getCurrentList().get(i);
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