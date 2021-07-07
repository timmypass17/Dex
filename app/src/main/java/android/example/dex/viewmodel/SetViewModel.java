package android.example.dex.viewmodel;

import android.app.Application;
import android.example.dex.SetRepository;
import android.example.dex.db.entity.set.PokeSet;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// ViewModel takes care of holding and processing all the data needed for the UI
public class SetViewModel extends AndroidViewModel {

    private SetRepository mSetRepository;
    private final LiveData<List<PokeSet>> mAllSets;

    public SetViewModel(@NonNull @NotNull Application application) {
        super(application);
        mSetRepository = new SetRepository(application);
        mAllSets = mSetRepository.getAllSets();
    }

    // Returns cached list of sets
    public LiveData<List<PokeSet>> getAllSets() {
        return mAllSets;
    }
}
