package buet.rafi.dictionary;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dictionary extends ListActivity {
	private EditText input;
	
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
    		Intent intent = new Intent(this, About.class);
    		startActivity(intent);
    		/*
    		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    		
    		TextView textView = new TextView(this);
    		textView.setTypeface(Typeface.createFromAsset(getAssets(), Dictionary.FONT));
    		textView.setPadding(10, 10, 10, 10);
    		textView.setText("Bangla Dictionary\nDeveloped by:\nRafi Kamal\nBUET CSE '10");
    		
    		dialog.setView(textView);
    		dialog.setNeutralButton("Ok", null);
    		dialog.show();
    		*/
    	}
    	/*
    	else if(item.getItemId() == R.id.add_new) {
    		showInputDialog();
    	}
    	*/
    	return super.onOptionsItemSelected(item);	
    }
    /*
	public void showInputDialog() {
		LayoutInflater factory = LayoutInflater.from(this);

		final View addNew = factory.inflate(R.layout.add_new, null);

		final EditText english = (EditText) addNew.findViewById(R.id.english_input);
		final EditText bangla = (EditText) addNew.findViewById(R.id.Bangla_input);
		
		bangla.setTypeface(Typeface.createFromAsset(getAssets(), Dictionary.FONT));

		final AlertDialog.Builder newWordInputDialog = new AlertDialog.Builder(this);
		newWordInputDialog
			.setTitle("Add a new word")
			.setView(addNew)
			.setPositiveButton("Save",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							String englishWord = english.getText().toString();
							String banglaWord = bangla.getText().toString();
							dictionaryDB.addWord(englishWord, banglaWord);
							
							Toast.makeText(getBaseContext(), "Word Added to the Dictionary",
									Toast.LENGTH_SHORT).show();
						}
					})
			.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							
						}
					});
		newWordInputDialog.show();
	}
	*/
}