package buet.rafi.dictionary;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dictionary extends Activity {
	private EditText input;
	private Button clear;
	private TextView output;
	private DictionaryDB dictionaryDB;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dictionaryDB = new DictionaryDB(getBaseContext());
        input = (EditText) findViewById(R.id.input);
        clear = (Button) findViewById(R.id.clear);
        output = (TextView) findViewById(R.id.output);
        
        input.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                	translatedWord(input.getText().toString());
                	return true;
				}
				return false;
			}
		});
        output.setTypeface(Typeface.createFromAsset(getAssets(),"SolaimanLipi.ttf"));
        clear.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				input.setText("");
			}
		});
    }
    
    void translatedWord(String englishWord) {
    	String sql = "SELECT bn_word FROM " + DictionaryDB.TABLE_NAME +
    			" WHERE en_word = '" + englishWord + "'";
        
        SQLiteDatabase db = dictionaryDB.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
    	
        if(cursor.moveToFirst()) {
        	output.setText(cursor.getString(0));
        }
        else
        	output.setText("Sorry, word not found");
    }
}