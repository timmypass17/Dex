package android.example.dex.data;

import android.example.dex.data.models.pokemon.Pokemon;
import android.example.dex.data.models.pokemon.SumPojo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.math.BigDecimal;
import java.util.List;

// "..." It means that zero or more String objects (or a single array of them) may be passed as the argument(s) for that method

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    void deleteAll();

    @Query("SELECT * FROM pokemon_table ORDER BY name ASC")
    LiveData<List<Pokemon>> getAlphabetizedPokemons();

    // Get normal price
    @Query("SELECT SUM(normal_market) as normalTotal, SUM(holofoil_market) as hoilTotal FROM pokemon_table")
    LiveData<SumPojo> getCollectionPrice();

//    @Query("SELECT * FROM pokemon_table WHERE name = :query")
//    List<Pokemon> loadPokemons(String query);

}
