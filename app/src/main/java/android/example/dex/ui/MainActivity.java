package android.example.dex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.example.dex.ui.fragments.CollectionFragment;
import android.example.dex.R;
import android.example.dex.ui.fragments.SearchFragment2;
import android.example.dex.ui.fragments.SetFragment;
import android.example.dex.ui.fragments.SearchFragment;
import android.example.dex.viewmodel.CollectionViewModel;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    public static CollectionViewModel mCollectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment fragment = new SearchFragment(); // Todo: change default here
                int itemID = item.getItemId();
                String title;
                // Switch to screen to whatever was selected in bottom nav
                if (itemID == R.id.action_collection) {
                    fragment = new CollectionFragment();
                    title = "Collection";
                } else if (itemID == R.id.action_set) {
                    fragment = new SetFragment();
                    title = "Set";
                } else if (itemID == R.id.action_search) {
                    fragment = new SearchFragment();
                    title = "Search";
                } else if (itemID == R.id.action_test) {
                    fragment = new SearchFragment2();
                    title = "Test";
                }
                else {
                    fragment = new SearchFragment();
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
        bottomNavigationView.setSelectedItemId(R.id.action_search);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}