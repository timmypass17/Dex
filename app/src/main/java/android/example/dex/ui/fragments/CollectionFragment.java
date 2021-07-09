package android.example.dex.ui.fragments;

import android.example.dex.ui.adapters.CollectionAdapter;
import android.example.dex.R;
import android.example.dex.viewmodel.CollectionViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


public class CollectionFragment extends Fragment {

    CollectionViewModel mPokemonViewModel;

    public CollectionFragment() {
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

        RecyclerView rvCollection = view.findViewById(R.id.rvCollection);
        TextView tvTotalPrice = view.findViewById(R.id.tvTotalPrice);

        final CollectionAdapter adapter = new CollectionAdapter(new CollectionAdapter.WordDiff());
        rvCollection.setAdapter(adapter);
        rvCollection.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mPokemonViewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(CollectionViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPokemonViewModel.getAllPokemons().observe((LifecycleOwner) getContext(), pokemons -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(pokemons);
        });

        // Set price
        mPokemonViewModel.getTotalPrice().observe((LifecycleOwner) getContext(), sumPojo -> {
            tvTotalPrice.setText(sumPojo.getNormalAndHoilPrice());
        });
        // Equivalent to...
        // SumPojo sumPojo = mPokemonViewModel.getTotalPrice();
        // tvTotalPrice.setText(String.valueOf(sumPojo.getNormalAndHoilPrice()));

    }

}