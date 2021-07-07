package android.example.dex.db;

import android.content.Context;
import android.example.dex.db.dao.CollectionDao;
import android.example.dex.db.dao.SetDao;
import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.set.PokeSet;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Usually, you only need one instance of a Room database for the whole app.
@Database(entities = {Pokemon.class, PokeSet.class}, version = 1, exportSchema = false)
public abstract class PokemonRoomDatabase extends RoomDatabase {

    public abstract CollectionDao pokemonDao();
    public abstract SetDao setDao();

    // Singleton, to prevent having multiple instances of the database opened at the same time.
    private static volatile PokemonRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // We need to not run the insert on the main thread,
    // so we use the ExecutorService we created in the WordRoomDatabase to perform the insert on a background thread.
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Returns the singleton
    // It'll create the database the first time it's accessed, using Room's database builder to create a RoomDatabase object in
    // the application context from the WordRoomDatabase class and names it "pokemon_database"
    public static PokemonRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PokemonRoomDatabase.class, "pokemon_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CollectionDao dao = INSTANCE.pokemonDao();
                SetDao setDao = INSTANCE.setDao();
                dao.deleteAll();

//                Pokemon pokemon = new Pokemon("Samplemon");
//                dao.insert(pokemon);
//                pokemon = new Pokemon("Samplemon");
//                dao.insert(pokemon);
            });
        }
    };
}
