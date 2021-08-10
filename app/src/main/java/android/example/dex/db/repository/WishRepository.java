package android.example.dex.db.repository;

import android.app.Application;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.dao.WishDao;
import android.example.dex.db.entity.pokemon.Pokemon;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WishRepository {

    private final WishDao mWishDao;
    private final LiveData<List<Pokemon>> mWishlistPokemons;
    private final LiveData<Double> mWishPrice;

    public WishRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mWishDao = db.wishDao();
        mWishlistPokemons = mWishDao.getWishlistPokemons();
        mWishPrice = mWishDao.getWishlistPrice();
    }

    public LiveData<List<Pokemon>> getWishListPokemons() {
        return mWishlistPokemons;
    }

    public LiveData<Double> getWishPrice() {
        return mWishPrice;
    }

    public void addToWishlist(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWishDao.addToWishlist(id);
        });
    }

    public void removeFromWishlist(String id) {
        PokemonRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWishDao.removeFromWishlist(id);
        });
    }
}
