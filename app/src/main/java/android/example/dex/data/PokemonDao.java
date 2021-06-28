package android.example.dex.data;

import android.example.dex.data.models.pokemon.Pokemon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// "..." It means that zero or more String objects (or a single array of them) may be passed as the argument(s) for that method

@Dao
public interface PokemonDao {

    // TODO: when i remove onConflict, app crashes. (onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    void deleteAll();

    @Query("SELECT * FROM pokemon_table WHERE name = :query")
    List<Pokemon> loadPokemons(String query);

    @Query("SELECT * FROM pokemon_table ORDER BY name ASC")
    LiveData<List<Pokemon>> getAlphabetizedPokemons();
}
