package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WishDao {

    @Query("SELECT * FROM pokemon_table WHERE isWish = 1")
    LiveData<List<Pokemon>> getWishlistPokemons();

    @Query("UPDATE pokemon_table SET isWish = 1 WHERE id = :id")
    void addToWishlist(String id);

    @Query("UPDATE pokemon_table SET isWish = 0 WHERE id = :id")
    void removeFromWishlist(String id);

    @Query("SELECT SUM(highestPrice) as highest FROM pokemon_table WHERE isWish = 1")
    LiveData<Double> getWishlistPrice();
}
