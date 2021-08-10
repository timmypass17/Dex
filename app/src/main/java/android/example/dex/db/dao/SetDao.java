package android.example.dex.db.dao;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PokeSet pokeSet);

    @Query("SELECT * FROM pokemon_table WHERE setID = :set ORDER BY card_number ASC")
    LiveData<List<Pokemon>> getPokemonBySet(String set);

    // Sorting methods
    @Query("SELECT * FROM set_table ORDER BY releaseDate ASC")
    LiveData<List<PokeSet>> getOldSet();

    @Query("SELECT * FROM set_table ORDER BY releaseDate DESC")
    LiveData<List<PokeSet>> getNewSet();

    @Query("SELECT * FROM set_table ORDER BY name ASC")
    LiveData<List<PokeSet>> getAlphabetizedSetAsc();

    @Query("SELECT * FROM set_table ORDER BY name DESC")
    LiveData<List<PokeSet>> getAlphabetizedSetDesc();

    @Query("SELECT * FROM pokemon_table WHERE isOwned = 1 AND setID = :id")
    LiveData<List<Pokemon>> getAllPokemonFromSet(String id);
}
