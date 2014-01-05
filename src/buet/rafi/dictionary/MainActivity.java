package buet.rafi.dictionary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	private String[] mDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private ActionBarDrawerToggle mDrawerToggle;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDrawerItemTitles = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer);
        
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, 
        		R.layout.drawer_list_item, mDrawerItemTitles);

        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }	
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
        	return true;
        }

        return super.onOptionsItemSelected(item);
    }

	private class DrawerItemClickListener implements ListView.OnItemClickListener {

	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	selectItem(position);
	    }
	}

	private void selectItem(int position) {
	    mDrawerList.setItemChecked(position, true);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
		ActionBar actionBar = getSupportActionBar();
    	
    	if (position == 0) {
    		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    		
    		Tab e2bTab = actionBar.newTab().setText("E2B");
    		Tab b2eTab = actionBar.newTab().setText("B2E");
    		
    		Fragment e2bFragment = new Dictionary();
    		Fragment b2eFragment = new Dictionary();
    		
    		e2bTab.setTabListener(new MyTabListener(e2bFragment));
    		b2eTab.setTabListener(new MyTabListener(b2eFragment));
    		
    		actionBar.addTab(e2bTab);
    		actionBar.addTab(b2eTab);
    	}
    	else {
    		actionBar.removeAllTabs();
    		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    		
    		FragmentManager fragmentManager = getSupportFragmentManager();
    		fragmentManager.beginTransaction()
	                   .replace(R.id.fragment_container, getFragmentAtPoistion(position))
	                   .commit();
    	}
	}
	
	public Fragment getFragmentAtPoistion(int position) {
		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (position) {
			
		case 1:
			fragment = new BookMarkedWords();
			break;
			
		case 2:
			fragment = new BookMarkedWords();
			break;
			
		case 3:
			fragment = new About();
			break;
		}
		
		fragment.setArguments(args);
		return fragment;
	}
}
