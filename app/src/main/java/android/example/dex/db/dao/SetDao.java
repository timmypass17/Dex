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

    @Query("SELECT * FROM set_table ORDER BY mReleaseDate ASC")
    LiveData<List<PokeSet>> getAlphabetizedSets();

    @Query("DELETE FROM set_table")
    void deleteAll();
}
