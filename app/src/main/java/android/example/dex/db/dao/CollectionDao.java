package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.PokemonUpdate;
import android.example.dex.db.entity.pokemon.SumPojo;
import android.example.dex.db.entity.set.PokeSet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon pokemon);

    @Query("SELECT * FROM pokemon_table")
    LiveData<List<Pokemon>> getAllPokemons();

    // SQLite does not have a separate Boolean storage class.
    // Instead, Boolean values are stored as integers 0 (false) and 1 (true).
    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY name ASC ")
    LiveData<List<Pokemon>> getOwnedPokemons();

    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal FROM pokemon_table WHERE isOwned = 1")
    LiveData<SumPojo> getCollectionPrice();

    @Query("SELECT * FROM pokemon_table WHERE name = :name")
    LiveData<List<Pokemon>> getPokemonByName(String name);

    @Query("SELECT * FROM pokemon_table WHERE setID = :set")
    LiveData<List<Pokemon>> getPokemonBySet(String set);

    @Query("UPDATE pokemon_table SET isOwned = 1 WHERE id = :id")
    void addToCollection(String id);

    @Query("UPDATE pokemon_table SET isOwned = 0 WHERE id = :id")
    void removeFromCollection(String id);

    @Query("SELECT * FROM pokemon_table WHERE isWish = 1")
    LiveData<List<Pokemon>> getWishlistPokemons();

    @Query("UPDATE pokemon_table SET isWish = 1 WHERE id = :id")
    void addToWishlist(String id);

    @Query("UPDATE pokemon_table SET isWish = 0 WHERE id = :id")
    void removeFromWishlist(String id);

    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal FROM pokemon_table WHERE isWish = 1")
    LiveData<SumPojo> getWishlistPrice();


}
