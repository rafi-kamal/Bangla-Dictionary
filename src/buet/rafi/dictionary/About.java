package buet.rafi.dictionary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class About extends Activity {
	
	ScrollingTextView scrolling;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.about);
		
		scrolling = (ScrollingTextView)findViewById(R.id.scrolling);
		String scrollingText = "Ridmik Dicionary is a free\n and open source application developed by\n";
		scrollingText += "\nRafi Kamal\n\n";
		scrollingText += "Your support and suggestions\nare very important to make it better!\n";
		scrollingText += "\nThanks to:\n\n";
		scrollingText += "Shamim Hasnath, Saminur Islam,\nJamshed Khan Mukut, Hasan Almir,\n";
		scrollingText += "Anika Tabassum, Faysal Hossain\n";
		scrollingText += "and\nShawly Samira\n\n";
		scrollingText += "for the friendly support!";
		scrolling.setText(scrollingText);
	}
	
	public void commentClicked(View v){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://m.facebook.com/RidmikLab"));
		startActivity(intent);
	}
	public void emailClicked(View v){
		
		try {
			Intent gmailIntent = new Intent();
		    gmailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
		    gmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Ridmik Dictionary");
		    gmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
		    gmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"ridmiklab@gmail.com"});
		    startActivity(gmailIntent);
		} catch(Exception exception) {
			Toast.makeText(this, "Sorry, email isn't supported in this device", Toast.LENGTH_SHORT);
		}
	}
	public void ridmikKeyboardClicked(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=net.hasnath.android.keyboard&hl=en"));
		startActivity(intent);
	}
}
