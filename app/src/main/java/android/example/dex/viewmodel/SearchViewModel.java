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
    private LiveData<List<Pokemon>> mAllPokemonsByName;
    private  LiveData<List<Pokemon>> mAllPokemonBySet;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
        mCollectionRepository = new CollectionRepository(application);
        mAllPokemonsByName = mCollectionRepository.getAllPokemonByName();
        mAllPokemonBySet = mCollectionRepository.getmAllPokemonBySet();
    }

    public LiveData<List<Pokemon>> getAllPokemonByName() {
        return mAllPokemonsByName;
    }

    public void updateAllPokemonByName(String name) {
        mAllPokemonsByName = mCollectionRepository.getNewPokemon(name);
    }

    public LiveData<List<Pokemon>> getAllPokemonBySet() {
        return mAllPokemonBySet;
    }

    public void updateAllPokemonBySet(String set) {
        mAllPokemonBySet = mCollectionRepository.getPokemonBySet(set);
    }
}
