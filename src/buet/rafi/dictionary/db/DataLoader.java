package buet.rafi.dictionary.db;

import java.util.List;

import android.os.AsyncTask;
import buet.rafi.dictionary.adapter.WordListAdapter;
import buet.rafi.dictionary.model.Word;


public class DataLoader extends AsyncTask<String, Void, List<Word>> {
	
	private DictionaryDB dictionaryDB;
	private WordListAdapter adapter;
	
	public DataLoader(DictionaryDB dictionaryDB, WordListAdapter adapter) {
		this.dictionaryDB = dictionaryDB;
		this.adapter = adapter;
	}

	@Override
	protected List<Word> doInBackground(String... params) {
		return dictionaryDB.getWords(params[0]);
	}
	
	@Override
	protected void onPostExecute(List<Word> result) {
		adapter.updateEntries(result);
	}
}
