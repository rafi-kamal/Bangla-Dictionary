package buet.rafi.dictionary;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Dictionary extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WordList wordList = new WordList(getBaseContext());
        SQLiteDatabase db = wordList.getWritableDatabase();
        db.close();
        SQLiteDatabase database = wordList.getReadableDatabase();
        Toast.makeText(getBaseContext(), "Database Generated", Toast.LENGTH_SHORT).show();
        String sql = "SELECT * FROM `words` WHERE `en_word` = 'nine'";
        Cursor cursor = database.rawQuery(sql, null);
        Toast.makeText(getBaseContext(), "Query completed", Toast.LENGTH_SHORT).show();
        if(cursor.moveToFirst()) {
        	Log.d("", cursor.getString(0));
        	Log.d("", cursor.getString(1));
        	Log.d("", cursor.getString(2));
        	Toast.makeText(getBaseContext(), "রাফি", Toast.LENGTH_SHORT).show();
        	TextView textView = (TextView) findViewById(R.id.textView1);
        	textView.setTypeface(Typeface.createFromAsset(getAssets(),"SolaimanLipi.ttf"));
        	textView.setText(cursor.getString(2));
        }
    }
}