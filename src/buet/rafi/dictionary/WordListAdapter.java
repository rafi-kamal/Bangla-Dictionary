package buet.rafi.dictionary;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WordListAdapter extends BaseAdapter {

	private List<Bean> wordList;
	private Context context;
	private LayoutInflater mLayoutInflater;
	
	public WordListAdapter(Context context) {
		this.context = context;
		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		wordList = new ArrayList<Bean>();
	}

	public int getCount() {
		return wordList.size();
	}

	public Object getItem(int position) {
		return wordList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = mLayoutInflater.inflate(R.layout.word, null);
		}
		TextView english = (TextView) view.findViewById(R.id.english);
		TextView bangla = (TextView) view.findViewById(R.id.bangla);
		bangla.setTypeface(Typeface.createFromAsset(
				context.getAssets(), "SolaimanLipi.ttf"));
		english.setText(wordList.get(position).english);
		bangla.setText(wordList.get(position).bangla);
		
		return view;
	}
	
	public void updateEntries(List<Bean> wordList) {
		this.wordList = wordList;
		notifyDataSetChanged();
	}
}
