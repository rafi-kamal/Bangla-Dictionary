package buet.rafi.dictionary;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BookMarkedWords extends ListFragment {
	
	private DictionaryDB dictionaryDB;
	private WordListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bookmarked, container, false);
		
		DatabaseInitializer initializer = new DatabaseInitializer(getActivity());
        dictionaryDB = new DictionaryDB(initializer);
        
        adapter = new WordListAdapter(getActivity(), dictionaryDB);
		setListAdapter(adapter);
		
		List<Bean> bookmarkedWords = dictionaryDB.getBookmarkedWords();
		adapter.updateEntries(bookmarkedWords);
		
		return view;
	}
}
