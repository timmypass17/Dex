package android.example.dex.ui.fragments;

import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.SetAdapter;
import android.example.dex.viewmodel.SetViewModel;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.dex.R;

import org.jetbrains.annotations.NotNull;

public class SetFragment extends Fragment {

    public SetViewModel mSetViewModel;

    public SetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvSet = view.findViewById(R.id.rvSet);
        final SetAdapter adapter = new SetAdapter(new SetAdapter.WordDiff());
        rvSet.setAdapter(adapter);
        rvSet.setLayoutManager(new LinearLayoutManager(getContext()));

        mSetViewModel = MainActivity.mSetViewModel;

        mSetViewModel.getAllSets().observe(getViewLifecycleOwner(), pokeSets -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(pokeSets);
        });


    }
}