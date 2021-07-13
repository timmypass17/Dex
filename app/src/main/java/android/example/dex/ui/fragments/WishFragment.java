package android.example.dex.ui.fragments;

import android.example.dex.ui.MainActivity;
import android.example.dex.ui.adapters.WishAdapter;
import android.example.dex.ui.adapters.WishViewHolder;
import android.example.dex.viewmodel.CollectionViewModel;
import android.example.dex.viewmodel.WishViewModel;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.dex.R;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;


public class WishFragment extends Fragment {

    public WishViewModel mWishViewModel;

    public WishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvWishlistPrice = view.findViewById(R.id.tvWishlistPrice);
        TextView tvCardCount = view.findViewById(R.id.tvWishlistCount);

        RecyclerView rvWishlist = view.findViewById(R.id.rvWishlist);
        final WishAdapter adapter = new WishAdapter(new WishAdapter.WordDiff());
        rvWishlist.setAdapter(adapter);
        rvWishlist.setLayoutManager(new LinearLayoutManager(getContext()));

//        mWishViewModel = new ViewModelProvider(this).get(WishViewModel.class);
        mWishViewModel = MainActivity.mWishViewModel;

        mWishViewModel.getWishlistPokemons().observe(getViewLifecycleOwner(), pokemons -> {
            adapter.submitList(pokemons);
            tvCardCount.setText(String.valueOf(pokemons.size()));
        });

        mWishViewModel.getWishPrice().observe(getViewLifecycleOwner(), sumPojo -> {
            tvWishlistPrice.setText(sumPojo.getNormalAndHoilPrice());
        });
    }
}