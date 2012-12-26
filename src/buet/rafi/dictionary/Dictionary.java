package buet.rafi.dictionary;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Dictionary extends ListActivity {
	private EditText input;
	private Button clear;
	
	private DictionaryDB dictionaryDB;
	private WordListAdapter adapter;
	
	public static final String FONT = "SolaimanLipi.ttf";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DatabaseInitializer initializer = new DatabaseInitializer(getBaseContext());
        initializer.initializeDataBase();
        dictionaryDB = new DictionaryDB(initializer);
        
        input = (EditText) findViewById(R.id.input);
        clear = (Button) findViewById(R.id.clear);
        
        adapter = new WordListAdapter(getBaseContext(), dictionaryDB);
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
		DataLoader loader = new DataLoader(dictionaryDB, adapter);
		loader.execute(word);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == R.id.bookmarked_words) {
    		Intent intent = new Intent(this, BookMarkedWords.class);
    		startActivity(intent);
    	}
    	else if(item.getItemId() == R.id.about) {
    		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    		
    		TextView textView = new TextView(this);
    		textView.setTypeface(Typeface.createFromAsset(getAssets(), Dictionary.FONT));
    		textView.setPadding(10, 10, 10, 10);
    		textView.setText("Bangla Dictionary\nDeveloped by:\nRafi Kamal\nBUET CSE '10");
    		
    		dialog.setView(textView);
    		dialog.setNeutralButton("Ok", null);
    		dialog.show();
    	}
    	return super.onOptionsItemSelected(item);	
    }
}