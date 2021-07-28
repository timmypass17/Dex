package android.example.dex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static FragmentManager fragmentManager;
    private static CollectionViewModel mCollectionViewModel;
    private static WishViewModel mWishViewModel;
    private static SearchViewModel mSearchViewModel;
    private static SetViewModel mSetViewModel;

    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set hamburger icon
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        // Find our drawer view
        drawerLayout = findViewById(R.id.drawer_layout);
        // Find our navigation view
        nvDrawer = findViewById(R.id.navigation_view);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        mCollectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        mWishViewModel = new ViewModelProvider(this).get(WishViewModel.class);
        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSetViewModel = new ViewModelProvider(this).get(SetViewModel.class);

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        int itemID = menuItem.getItemId();
        String title = "";
        if (itemID == R.id.nav_collection_fragment) {
            fragment = new CollectionFragment();
            title = "Collection";
        } else if (itemID == R.id.nav_set_fragment) {
            fragment = new SetFragment();
            title = "Set";
        } else if (itemID == R.id.nav_search_fragment) {
            fragment = new SearchFragment();
            title = "Search";
        } else if (itemID == R.id.nav_wish_fragment) {
            fragment = new WishFragment();
            title = "Wish";

        } else if (itemID == R.id.nav_github) {
            Uri webpage = Uri.parse("https://github.com/timmypass17");
            Intent i = new Intent(Intent.ACTION_VIEW, webpage);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
            return;
        }
        else {
            fragment = new CollectionFragment();
            Toast.makeText(MainActivity.this, "Unknown Fragment", Toast.LENGTH_SHORT).show();
        }

        // Insert the fragment by replacing any existing fragment
        // Update action bar title, change fragment
        setActionBarTitle(title);
        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();

    }
}