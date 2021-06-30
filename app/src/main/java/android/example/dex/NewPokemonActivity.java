package android.example.dex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewPokemonActivity extends AppCompatActivity {

    public static final String EXTRA_OPERATION = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_INSERT = "1";
    public static final String EXTRA_DELETE_ALL = "2";

    private EditText mEditWordView;
    private Button btnSave;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pokemon);

        mEditWordView = findViewById(R.id.edit_word);
        btnSave = findViewById(R.id.button_save);
        btnDelete = findViewById(R.id.button_delete);

        btnSave.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                replyIntent.putExtra(EXTRA_OPERATION, EXTRA_INSERT);
                replyIntent.putExtra(EXTRA_DATA, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        btnDelete.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_OPERATION, EXTRA_DELETE_ALL);
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }
}