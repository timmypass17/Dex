package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// "..." It means that zero or more String objects (or a single array of them) may be passed as the argument(s) for that method

@Dao
public interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    void deleteAll();

    // Get ALL pokemons
    @Query("SELECT * FROM pokemon_table")
    LiveData<List<Pokemon>> getAllPokemons();

    // SQLite does not have a separate Boolean storage class.
    // Instead, Boolean values are stored as integers 0 (false) and 1 (true).
    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY name ASC ")
    LiveData<List<Pokemon>> getOwnedPokemons();

    // Get normal price
    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal FROM pokemon_table WHERE isOwned = 1")
    LiveData<SumPojo> getCollectionPrice();

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("SELECT * FROM pokemon_table WHERE name = :name")
    LiveData<List<Pokemon>> getPokemonByName(String name);

    @Query("UPDATE pokemon_table SET isOwned = 1 WHERE id = :id")
    void addToCollection(String id);
}
