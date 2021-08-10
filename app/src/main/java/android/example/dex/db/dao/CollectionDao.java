package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// SQLite does not have a separate Boolean storage class.
// Instead, Boolean values are stored as integers 0 (false) and 1 (true).

@Dao
public interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon pokemon);

    @Query("SELECT * FROM pokemon_table")
    LiveData<List<Pokemon>> getAllPokemons();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY name ASC ")
    LiveData<List<Pokemon>> getOwnedPokemons();

    @Query("SELECT SUM(highestPrice) as highest FROM pokemon_table WHERE isOwned = 1")
    LiveData<Double> getCollectionPrice();

    @Query("SELECT * FROM pokemon_table WHERE name LIKE :name ORDER BY setReleaseDate")
    LiveData<List<Pokemon>> getPokemonByName(String name);

    @Query("UPDATE pokemon_table SET isOwned = 1 WHERE id = :id")
    void addToCollection(String id);

    @Query("UPDATE pokemon_table SET isOwned = 0 WHERE id = :id")
    void removeFromCollection(String id);

    // Sorting methods
    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY setReleaseDate DESC")
    LiveData<List<Pokemon>> getNewerPokemons();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY setReleaseDate ASC")
    LiveData<List<Pokemon>> getOlderPokemons();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY name ASC")
    LiveData<List<Pokemon>> getAlphabetizedPokemonAsc();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY name DESC")
    LiveData<List<Pokemon>> getAlphabetizedPokemonDesc();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY highestPrice DESC")
    LiveData<List<Pokemon>> getExpensivePokemons();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 ORDER BY highestPrice ASC")
    LiveData<List<Pokemon>> getCheapestPokemons();

}
