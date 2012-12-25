package buet.rafi.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordList extends SQLiteOpenHelper {

	private Context baseContext;
	public static final String DATABASE_NAME = "dictionary";
	public static final int DATABASE_VERSION = 3;
	
	public WordList(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		baseContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE IF NOT EXISTS `words` " +
				"(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
				" `en_word` TEXT, `bn_word` TEXT)";
		db.execSQL(query);
		query = "INSERT INTO `words` (`en_word`, `bn_word`) VALUES ('cascade', 'জলপ্রপাত')";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String query = "DROP TABLE `words`";
		db.execSQL(query);
		onCreate(db);
	}

}
