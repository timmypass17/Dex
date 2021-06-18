package android.example.dex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.example.dex.fragments.SearchFragment;
import android.example.dex.fragments.SetFragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment fragment = new SearchFragment(); // Todo: change default here
                int itemID = item.getItemId();

                // Switch to screen to whatever was selected in bottom nav
                if (itemID == R.id.action_home) {
                    // TODO: Create home fragment (whatever that is)
                } else if (itemID == R.id.action_set) {
                    fragment = new SetFragment();
                } else if (itemID == R.id.action_search) {
                    fragment = new SearchFragment();
                } else {
                    fragment = new SearchFragment();
                }
                // Change fragment
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_search);
    }
}