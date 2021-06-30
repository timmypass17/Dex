package android.example.dex.data;

import android.app.Application;
import android.example.dex.data.models.pokemon.Pokemon;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// The ViewModel's role is to provide data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.
// The Repository and the UI are completely separated by the ViewModel.

public class PokemonViewModel extends AndroidViewModel {

    private PokemonRepository mRepository;
    private final LiveData<List<Pokemon>> mAllPokemons;

    public PokemonViewModel(@NonNull @NotNull Application application) {
        super(application);
        mRepository = new PokemonRepository(application);
        mAllPokemons = mRepository.getAllPokemons(); // Intialized the allPokemons LiveData using the repository
    }

    // Return a cached list of pokemons
    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insert(Pokemon pokemon) {
        mRepository.insert(pokemon);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
