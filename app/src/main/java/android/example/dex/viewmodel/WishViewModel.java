package android.example.dex.viewmodel;

import android.app.Application;
import android.example.dex.CollectionRepository;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WishViewModel extends AndroidViewModel {

    private final CollectionRepository mRepository;
    private final LiveData<List<Pokemon>> mWishlistPokemons;
    private final LiveData<SumPojo> mWishPrice;

    public WishViewModel(@NonNull @NotNull Application application) {
        super(application);
        mRepository = new CollectionRepository(application);
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

    public LiveData<SumPojo> getWishPrice() {
        return mWishPrice;
    }
}
