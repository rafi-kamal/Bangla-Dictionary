package buet.rafi.dictionary;

import java.util.List;

import android.os.AsyncTask;


public class DataLoader extends AsyncTask<String, Void, List<Bean>> {
	
	public interface CallBack {
		/**
		 * What to do after loading the result
		 */
		public void onLoadResult(List<Bean> result);
		
		/*
		 * What to do if the result is empty (no match found)
		 */
		public void onEmptyResult();
	}
	
	private CallBack callBack;
	private DictionaryDB db;
	
	public DataLoader(DictionaryDB db, CallBack callBack) {
		this.db = db;
		this.callBack = callBack;
	}

	@Override
	protected List<Bean> doInBackground(String... params) {
		return db.getWords(params[0]);
	}
	
	@Override
	protected void onPostExecute(List<Bean> result) {
		if (result.size() == 0)
			callBack.onEmptyResult();
		callBack.onLoadResult(result);
	}
}
