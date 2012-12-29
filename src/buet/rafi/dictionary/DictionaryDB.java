package buet.rafi.dictionary;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DictionaryDB {
	
	public static final String ID = "_id";
	public static final String ENGLISH = "en_word";
	public static final String BANGLA = "bn_word";
	public static final String STATUS = "status";
	public static final String USER = "user_created";
    public static final String TABLE_NAME = "words";

	public static final String BOOKMARKED = "b";
	public static final String USER_CREATED = "u";
	
	DatabaseInitializer initializer;
	
	public DictionaryDB(DatabaseInitializer initializer) {
		this.initializer = initializer;
	}
	
	
	public void addWord(String englishWord, String banglaWord) {
		SQLiteDatabase db = initializer.getWritableDatabase();
		
		String sql = "INSERT INTO " + TABLE_NAME + " (" + ENGLISH + 
				", " + BANGLA + ", " + USER + ") VALUES ('" + englishWord +
				"', '" + banglaWord + "', '" + USER_CREATED + "') ";
		db.execSQL(sql);
	}
	
	
	public List<Bean> getWords(String englishWord) {
		if(englishWord.equals(""))
			return new ArrayList<Bean>();
		
		String sql = "SELECT * FROM " + TABLE_NAME +
    			" WHERE " + ENGLISH + " LIKE '" + englishWord +
    			"%' ORDER BY " + ENGLISH + " LIMIT 150";
		/*String sql = "SELECT * FROM " + TABLE_NAME +
    			" WHERE " + ENGLISH + " >= '" + englishWord + "' LIMIT 200";*/
		
		SQLiteDatabase db = initializer.getReadableDatabase();
        if(db.isOpen()) {
	        Cursor cursor = db.rawQuery(sql, null);
	        
	        List<Bean> wordList = new ArrayList<Bean>();
	        while(cursor.moveToNext()) {
	        	int id = cursor.getInt(0);
	        	String english = cursor.getString(1);
	        	String bangla = cursor.getString(2);
	        	String status = cursor.getString(3);
				wordList.add(new Bean(id, english, bangla, status));
			}
	        
	        //db.close();
	        return wordList;
        }
        return new ArrayList<Bean>();
	}
	
	public List<Bean> getBookmarkedWords() {
		SQLiteDatabase db = initializer.getReadableDatabase();
		
		String sql = "SELECT * FROM " + TABLE_NAME +
    			" WHERE " + STATUS + " = '" + BOOKMARKED + "'";
        
        Cursor cursor = db.rawQuery(sql, null);
        
        List<Bean> wordList = new ArrayList<Bean>();
        while(cursor.moveToNext()) {
        	int id = cursor.getInt(0);
        	String english = cursor.getString(1);
        	String bangla = cursor.getString(2);
        	String status = cursor.getString(3);
			wordList.add(new Bean(id, english, bangla, status));
		}
        
        db.close();
        return wordList;
	}
	
	public void bookmark(int _id) {
		SQLiteDatabase db = initializer.getWritableDatabase();
		
		String sql = "UPDATE " + TABLE_NAME + " SET " + STATUS + " = '"
				+ BOOKMARKED + "' WHERE " + ID + " = " + _id;
		db.execSQL(sql);
		db.close();
	}
	
	public void deleteBookmark(int _id) {
		SQLiteDatabase db = initializer.getWritableDatabase();
		
		String sql = "UPDATE " + TABLE_NAME + " SET " + STATUS + " = '' " +
				" WHERE " + ID + " = " + _id;
		db.execSQL(sql);
		db.close();
	}
}
