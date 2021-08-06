package android.example.dex.viewmodel;

import android.app.Application;
import android.example.dex.CollectionRepository;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.SumPojo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// The ViewModel's role is to provide data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.
// The Repository and the UI are completely separated by the ViewModel.

public class CollectionViewModel extends AndroidViewModel {

    private final CollectionRepository mRepository;
    private final LiveData<List<Pokemon>> mAllPokemons;
    private final LiveData<SumPojo> mCollectionPrice;
    private final LiveData<Double> mHighestCollectionPrice;

    public CollectionViewModel(@NonNull @NotNull Application application) {
        super(application);
        mRepository = new CollectionRepository(application);
        mAllPokemons = mRepository.getAllPokemons(); // Intialized the allPokemons LiveData using the repository
        mCollectionPrice = mRepository.getCollectionPrice();
        mHighestCollectionPrice = mRepository.getmHighestCollectionPrice();
    }

    // Return a cached list of pokemons
    public LiveData<List<Pokemon>> getAllPokemons() {
        return mAllPokemons;
    }

    public LiveData<SumPojo> getCollectionPrice() {
        return mCollectionPrice;
    }

    public LiveData<Double> getmHighestCollectionPrice() {
        return mHighestCollectionPrice;
    }

    // Implementation of addToCollection() is encapsulated from the UI.
    public void addToCollection(String id) {
        mRepository.addToCollection(id);
    }

    public void removeFromCollection(String id) {
        mRepository.removeFromCollection(id);
    }

}
