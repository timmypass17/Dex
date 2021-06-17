package android.example.dex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.example.dex.fragments.SearchFragment;
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

                if (itemID == R.id.action_home) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    // Todo
                } else if (itemID == R.id.action_compose) {
                    Toast.makeText(MainActivity.this, "Compose", Toast.LENGTH_SHORT).show();
                    // Todo
                } else if (itemID == R.id.action_search) {
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    fragment = new SearchFragment();
                } else {
                    Toast.makeText(MainActivity.this, "Unknown Fragment", Toast.LENGTH_SHORT).show();
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