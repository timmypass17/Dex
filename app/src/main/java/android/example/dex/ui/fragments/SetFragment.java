package android.example.dex.ui.fragments;

import android.example.dex.db.entity.set.PokeSet;
import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.SetAdapter;
import android.example.dex.viewmodel.SetViewModel;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.example.dex.R;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetFragment extends Fragment {

    private SetViewModel mSetViewModel;
    private RecyclerView rvSet;
    private SetAdapter mSetAdapter;

    public SetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Enable action bar menu
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSet = view.findViewById(R.id.rvSet);
        mSetAdapter = new SetAdapter(new SetAdapter.WordDiff());
        rvSet.setAdapter(mSetAdapter);
        rvSet.setLayoutManager(new LinearLayoutManager(getContext()));

        mSetViewModel = MainActivity.getmSetViewModel();
        mSetViewModel.getAllSets().observe(getViewLifecycleOwner(), pokeSets -> {
            // Update the cached copy of the words in the adapter.
            mSetAdapter.submitList(pokeSets);
            mSetAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_set, menu);

        // Get handle on menu items
        MenuItem miSortNew = menu.findItem(R.id.action_sort_new); // Newest
        MenuItem miSortOld = menu.findItem(R.id.action_sort_old); // Oldest
        MenuItem miSortNameAsc = menu.findItem(R.id.action_sort_name_asc);   // A-Z
        MenuItem miSortNameDesc = menu.findItem(R.id.action_sort_name_desc); // Z-A

        // Change color of icons
        miSortNew.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortOld.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortNameAsc.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        miSortNameDesc.getIcon().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);

        // Set menu click listener to sort recycler view items
        miSortNew.setOnMenuItemClickListener(item -> sortSetItem(item));
        miSortOld.setOnMenuItemClickListener(item -> sortSetItem(item));
        miSortNameAsc.setOnMenuItemClickListener(item -> sortSetItem(item));
        miSortNameDesc.setOnMenuItemClickListener(item -> sortSetItem(item));

    }

    // Updates recycler view data by name or date
    private boolean sortSetItem(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_sort_new) {
            mSetViewModel.getNewSet().observe(getViewLifecycleOwner(), pokeSets -> {
                mSetAdapter.submitList(pokeSets);
                mSetAdapter.notifyDataSetChanged();
            });
        } else if (menuItem.getItemId() == R.id.action_sort_old) {
            mSetViewModel.getOldSet().observe(getViewLifecycleOwner(), pokeSets -> {
                mSetAdapter.submitList(pokeSets);
                mSetAdapter.notifyDataSetChanged();
            });
        } else if (menuItem.getItemId() == R.id.action_sort_name_asc) {
            mSetViewModel.getAlphabetizedSetAsc().observe(getViewLifecycleOwner(), pokeSets -> {
                mSetAdapter.submitList(pokeSets);
                mSetAdapter.notifyDataSetChanged();
            });
        } else if (menuItem.getItemId() == R.id.action_sort_name_desc) {
            mSetViewModel.getAlphabetizedSetDesc().observe(getViewLifecycleOwner(), pokeSets -> {
                mSetAdapter.submitList(pokeSets);
                mSetAdapter.notifyDataSetChanged();
            });
        }
        return true;
    }
}