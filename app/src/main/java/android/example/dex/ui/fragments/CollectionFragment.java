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

import java.text.DecimalFormat;


public class CollectionFragment extends Fragment {

    private CollectionViewModel mCollectionViewModel;
    private CollectionAdapter mCollectionAdapter;
    private RecyclerView rvCollection;
    private TextView tvTotalPrice;
    private TextView tvCardCount;

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

        mCollectionViewModel = MainActivity.getmCollectionViewModel();
        rvCollection = view.findViewById(R.id.rvCollection);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvCardCount = view.findViewById(R.id.tvCardCount);

        mCollectionAdapter = new CollectionAdapter(new CollectionAdapter.WordDiff());
        rvCollection.setAdapter(mCollectionAdapter);
        rvCollection.setLayoutManager(new LinearLayoutManager(getContext()));

        mCollectionViewModel.getAllPokemons().observe(getViewLifecycleOwner(), pokemons -> {
            mCollectionAdapter.submitList(pokemons);
            tvCardCount.setText(String.valueOf(pokemons.size()));
        });

        // Set collection worth
        mCollectionViewModel.getmHighestCollectionPrice().observe(getViewLifecycleOwner(), price -> {
            DecimalFormat moneyFormat = new DecimalFormat("$0.00");
            tvTotalPrice.setText(moneyFormat.format(price));
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_collection, menu);

        // Get handle on menu item
        MenuItem miSort = menu.findItem(R.id.Sort);
        MenuItem miSortNew = menu.findItem(R.id.action_sort_new); // Newest
        MenuItem miSortOld = menu.findItem(R.id.action_sort_old); // Oldest
        MenuItem miSortNameAsc = menu.findItem(R.id.action_sort_name_asc);   // A-Z
        MenuItem miSortNameDesc = menu.findItem(R.id.action_sort_name_desc); // Z-A
        MenuItem miDelete = menu.findItem(R.id.action_delete);

        // Change color of icons
        miSort.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortNew.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortOld.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortNameAsc.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortNameDesc.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miDelete.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);

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