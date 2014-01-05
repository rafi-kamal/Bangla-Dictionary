package buet.rafi.dictionary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dictionary extends ListFragment {
	private EditText input;
	private TextView empty;
	
	private DictionaryDB dictionaryDB;
	private WordListAdapter adapter;
	
	public static final String FONT = "SolaimanLipi.ttf";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary, container, false); 
        
        DatabaseInitializer initializer = new DatabaseInitializer(getActivity());
        initializer.initializeDataBase();
        dictionaryDB = new DictionaryDB(initializer);
        
        input = (EditText) view.findViewById(R.id.input);
        empty = (TextView) view.findViewById(android.R.id.empty);
        
        adapter = new WordListAdapter(getActivity(), dictionaryDB);
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
        
        return view;
    }
    
    private void loadData(String word) {
		DataLoader loader = new DataLoader(dictionaryDB, adapter);
		loader.execute(word);
		if(word.equals(""))
			empty.setText("");
		else
			empty.setText("No match found");
    }
    
    @Override
	public void onResume() {
    	super.onResume();
    	loadData(input.getText().toString());
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	inflater.inflate(R.menu.menu, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == R.id.bookmarked_words) {
    		Intent intent = new Intent(getActivity(), BookMarkedWords.class);
    		startActivity(intent);
    	}
    	else if(item.getItemId() == R.id.about) {
    		Intent intent = new Intent(getActivity(), About.class);
    		startActivity(intent);
    	}
    	
    	else if(item.getItemId() == R.id.add_new) {
    		showInputDialog();
    	}
    	
    	return super.onOptionsItemSelected(item);	
    }
    
	public void showInputDialog() {
		LayoutInflater factory = LayoutInflater.from(getActivity());

		final View addNew = factory.inflate(R.layout.add_new, null);

		final EditText english = (EditText) addNew.findViewById(R.id.english_input);
		final EditText bangla = (EditText) addNew.findViewById(R.id.Bangla_input);
		
		bangla.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), Dictionary.FONT));

		final AlertDialog.Builder newWordInputDialog = new AlertDialog.Builder(getActivity());
		newWordInputDialog
			.setTitle("Add a new word")
			.setView(addNew)
			.setPositiveButton("Save",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							String englishWord = english.getText().toString();
							String banglaWord = bangla.getText().toString();
							if((englishWord.equals("") || banglaWord.equals("")))
								Toast.makeText(getActivity(), "Field can't be blank",
										Toast.LENGTH_SHORT).show();
							else {
								dictionaryDB.addWord(englishWord, banglaWord);
								
								Toast.makeText(getActivity(), "Word Added to the Dictionary",
										Toast.LENGTH_SHORT).show();
							}
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
}