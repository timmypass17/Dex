package android.example.dex.db.viewmodel;

import android.app.Application;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.repository.WishRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WishViewModel extends AndroidViewModel {

    private final WishRepository mRepository;
    private final LiveData<List<Pokemon>> mWishlistPokemons;
    private final LiveData<Double> mWishPrice;

    public WishViewModel(@NonNull @NotNull Application application) {
        super(application);
        mRepository = new WishRepository(application);
        mWishlistPokemons = mRepository.getWishListPokemons();
        mWishPrice = mRepository.getWishPrice();
    }

    public LiveData<List<Pokemon>> getWishlistPokemons() {
        return mWishlistPokemons;
    }

    public void addToWishlist(String id) {
        mRepository.addToWishlist(id);
    }

    public void removeFromWishlist(String id) {
        mRepository.removeFromWishlist(id);
    }

    public LiveData<Double> getWishPrice() {
        return mWishPrice;
    }
}
