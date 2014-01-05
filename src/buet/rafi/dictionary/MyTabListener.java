package buet.rafi.dictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;

public class MyTabListener implements TabListener {

	public Fragment fragment;

	public MyTabListener(Fragment fragment) {
		this.fragment = fragment;
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}
}