package buet.rafi.dictionary;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;


public class DataLoader extends AsyncTask<String, Void, List<Bean>> {
	
	private SQLiteDatabase db;
	private WordListAdapter adapter;
	
	public DataLoader(SQLiteDatabase db, WordListAdapter adapter) {
		this.db = db;
		this.adapter = adapter;
	}

	@Override
	protected List<Bean> doInBackground(String... params) {
		String sql = "SELECT " + Dictionary.ENGLISH + ", " + Dictionary.BANGLA + " FROM " + Dictionary.TABLE_NAME +
    			" WHERE " + Dictionary.ENGLISH + " >= '" + params[0] + "' LIMIT 10";
        
        Cursor cursor = db.rawQuery(sql, null);
        
        List<Bean> wordList = new ArrayList<Bean>();
        while(cursor.moveToNext()) {
			wordList.add(new Bean(cursor.getString(0), cursor.getString(1)));
		}
        
        return wordList;
	}
	
	@Override
	protected void onPostExecute(List<Bean> result) {
		adapter.updateEntries(result);
	}
}
