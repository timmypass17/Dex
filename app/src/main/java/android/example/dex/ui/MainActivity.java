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

    public static final String TAG = "MainActivity";
    public static FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    public static CollectionViewModel mCollectionViewModel;
    public static WishViewModel mWishViewModel;
    public static SearchViewModel mSearchViewModel;
    public static SetViewModel mSetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        mWishViewModel = new ViewModelProvider(this).get(WishViewModel.class);
        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSetViewModel = new ViewModelProvider(this).get(SetViewModel.class);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment fragment;
                int itemID = item.getItemId();
                String title;
                // Switch to screen to whatever was selected in bottom nav
                if (itemID == R.id.action_collection) {
                    fragment = new CollectionFragment();
                    title = "Collection";
//                } else if (itemID == R.id.action_wish) {
//                    // TODO: Wish List
//                    fragment = new WishFragment();
//                    title = "Wish";
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
                // Update action bar title
                setActionBarTitle(title);
                // Change fragment
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_collection);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}