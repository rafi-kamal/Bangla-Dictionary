package buet.rafi.dictionary;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Dictionary extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WordList wordList = new WordList(getBaseContext());
        SQLiteDatabase database = wordList.getWritableDatabase();
    }
}