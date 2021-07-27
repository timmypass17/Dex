package android.example.dex.viewmodel;

import android.app.Application;
import android.example.dex.CollectionRepository;
import android.example.dex.db.entity.pokemon.Pokemon;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final CollectionRepository mCollectionRepository;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
        mCollectionRepository = new CollectionRepository(application);
    }

    public LiveData<List<Pokemon>> getAllPokemonByName(String name) {
        return mCollectionRepository.getAllPokemonByName(name);
    }

    public LiveData<List<Pokemon>> getAllPokemonBySet(String setId) {
        return mCollectionRepository.getmAllPokemonBySet(setId);
    }
}
