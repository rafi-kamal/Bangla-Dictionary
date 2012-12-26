package buet.rafi.dictionary;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dictionary extends ListActivity {
	private EditText input;
	private Button clear;
	private DictionaryDB dictionaryDB;
	private SQLiteDatabase db;
	
	public static final String ENGLISH = "en_word";
	public static final String BANGLA = "bn_word";
	public static final String STATUS = "status";
    public static final String TABLE_NAME = "words";
	private WordListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dictionaryDB = new DictionaryDB(getBaseContext());
        dictionaryDB.initializeDataBase();
        db = dictionaryDB.getReadableDatabase();
        input = (EditText) findViewById(R.id.input);
        clear = (Button) findViewById(R.id.clear);
        
        adapter = new WordListAdapter(getBaseContext());
		setListAdapter(adapter);
        
        input.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				loadData(input.getText().toString());
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			public void afterTextChanged(Editable s) {
				
			}
		});
        
        clear.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				input.setText("");
			}
		});
        loadData("");
    }
    
    private void loadData(String word) {
		DataLoader loader = new DataLoader(db, adapter);
		loader.execute(word);
    }
}