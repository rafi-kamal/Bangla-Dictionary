package buet.rafi.dictionary;

import java.util.List;

import android.os.Bundle;

public class BookMarkedWords extends ActionBarListActivity {
	
	private DictionaryDB dictionaryDB;
	private WordListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmarked);
		
		DatabaseInitializer initializer = new DatabaseInitializer(getBaseContext());
        dictionaryDB = new DictionaryDB(initializer);
        
        adapter = new WordListAdapter(this, dictionaryDB);
		setListAdapter(adapter);
		
		List<Bean> bookmarkedWords = dictionaryDB.getBookmarkedWords();
		adapter.updateEntries(bookmarkedWords);
	}
}
