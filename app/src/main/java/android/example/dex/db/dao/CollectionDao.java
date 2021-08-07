package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.PokemonUpdate;
import android.example.dex.db.entity.pokemon.Prices;
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

    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal," +
            "SUM(reverseHolofoil_market) as reverseHoilTotal, SUM(firstEditionHolofoil_market) as firstEditionTotal" +
            " FROM pokemon_table WHERE isOwned = 1")
    LiveData<SumPojo> getCollectionPrice();

    @Query("SELECT SUM(highestPrice) as highest FROM pokemon_table WHERE isOwned = 1")
    LiveData<Double> getHighestCollectionPrice();

    @Query("SELECT * FROM pokemon_table WHERE name LIKE :name ORDER BY setReleaseDate")
    LiveData<List<Pokemon>> getPokemonByName(String name);

    @Query("SELECT * FROM pokemon_table WHERE setID = :set ORDER BY card_number ASC")
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

    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal," +
            "SUM(reverseHolofoil_market) as reverseHoilTotal, SUM(firstEditionHolofoil_market) as firstEditionTotal" +
            " FROM pokemon_table WHERE isWish = 1")
    LiveData<SumPojo> getWishlistPrice();

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
