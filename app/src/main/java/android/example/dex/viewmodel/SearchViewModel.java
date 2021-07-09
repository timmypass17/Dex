package android.example.dex.viewmodel;

import android.app.Application;
import android.example.dex.CollectionRepository;
import android.example.dex.db.PokemonRoomDatabase;
import android.example.dex.db.entity.pokemon.Pokemon;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final CollectionRepository mRepository;
    private final LiveData<List<Pokemon>> mAllPokemonsByName;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
        mRepository = new CollectionRepository(application);
        mAllPokemonsByName = mRepository.getAllPokemonByName();
    }

    public LiveData<List<Pokemon>> getAllPokemonByName() {
        return mAllPokemonsByName;
    }

    public void updatePokemonBySet(String setID) {
        mRepository.updatePokemonBySet(setID);
    }
}
