package android.example.dex.ui.fragments;

import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.CollectionAdapter;
import android.example.dex.R;
import android.example.dex.ui.adapters.CollectionViewHolder;
import android.example.dex.viewmodel.CollectionViewModel;
import android.example.dex.viewmodel.WishViewModel;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;


public class CollectionFragment extends Fragment {

    public CollectionViewModel mCollectionViewModel;
    private RecyclerView rvCollection;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Enable action bar menu
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCollection = view.findViewById(R.id.rvCollection);
        TextView tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        TextView tvCardCount = view.findViewById(R.id.tvCardCount);

        final CollectionAdapter adapter = new CollectionAdapter(new CollectionAdapter.WordDiff());
        rvCollection.setAdapter(adapter);
        rvCollection.setLayoutManager(new LinearLayoutManager(getContext()));

        mCollectionViewModel = MainActivity.getmCollectionViewModel();

        mCollectionViewModel.getAllPokemons().observe(getViewLifecycleOwner(), pokemons -> {
            adapter.submitList(pokemons);
            tvCardCount.setText(String.valueOf(pokemons.size()));
        });

        // Set price
        mCollectionViewModel.getTotalPrice().observe(getViewLifecycleOwner(), sumPojo -> {
            tvTotalPrice.setText(sumPojo.getNormalAndHoilPrice());
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_collection, menu);

        // Get handle on menu item
        MenuItem miDelete = menu.findItem(R.id.action_delete);

        // Menu item click listener to display delete button
        miDelete.setOnMenuItemClickListener(item -> showDeleteButton());
    }

    // Show delete option
    private boolean showDeleteButton() {
        for (int i = 0; i < rvCollection.getChildCount(); i++) {
            CollectionViewHolder holder = (CollectionViewHolder) rvCollection.findViewHolderForAdapterPosition(i);
            holder.btnDeletePokemon.setVisibility(View.VISIBLE);
        }
        return true;
    }

}