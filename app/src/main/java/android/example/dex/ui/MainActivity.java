package android.example.dex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.example.dex.CollectionRepository;
import android.example.dex.ui.fragments.CollectionFragment;
import android.example.dex.R;
import android.example.dex.ui.fragments.SearchFragment;
import android.example.dex.ui.fragments.SetFragment;
import android.example.dex.ui.fragments.WishFragment;
import android.example.dex.viewmodel.CollectionViewModel;
import android.example.dex.viewmodel.SearchViewModel;
import android.example.dex.viewmodel.SetViewModel;
import android.example.dex.viewmodel.WishViewModel;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static FragmentManager fragmentManager;
    private static CollectionViewModel mCollectionViewModel;
    private static WishViewModel mWishViewModel;
    private static SearchViewModel mSearchViewModel;
    private static SetViewModel mSetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        mWishViewModel = new ViewModelProvider(this).get(WishViewModel.class);
        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSetViewModel = new ViewModelProvider(this).get(SetViewModel.class);

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment fragment;
                int itemID = item.getItemId();
                String title;
                if (itemID == R.id.action_collection) {
                    fragment = new CollectionFragment();
                    title = "Collection";
                } else if (itemID == R.id.action_set) {
                    fragment = new SetFragment();
                    title = "Set";
                } else if (itemID == R.id.action_search) {
                    fragment = new SearchFragment();
                    title = "Search";
                } else {
                    fragment = new CollectionFragment();
                    Toast.makeText(MainActivity.this, "Unknown Fragment", Toast.LENGTH_SHORT).show();
                    title = "Error";
                }
                // Update action bar title, change fragment
                setActionBarTitle(title);
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_collection);
    }

    public static CollectionViewModel getmCollectionViewModel() {
        return mCollectionViewModel;
    }

    public static WishViewModel getmWishViewModel() {
        return mWishViewModel;
    }

    public static SearchViewModel getmSearchViewModel() {
        return mSearchViewModel;
    }

    public static SetViewModel getmSetViewModel() {
        return mSetViewModel;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}