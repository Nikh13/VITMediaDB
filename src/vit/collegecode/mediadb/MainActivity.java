package vit.collegecode.mediadb;

import vit.collegecode.mediadb.adapters.DrawerListAdapter;
import vit.collegecode.mediadb.fragments.HomePageFragment;
import vit.collegecode.mediadb.fragments.MovieListFragment;
import vit.collegecode.mediadb.fragments.ProfileFragment;
import vit.collegecode.mediadb.fragments.TVListFragment;
import vit.collegecode.mediadb.helpers.FadingActionBarHelper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	public FadingActionBarHelper mActionBarHelper;

	DrawerLayout drawerLayout;
	ListView drawerList;
	DrawerListAdapter drawerAdapter;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mActionBarHelper = new FadingActionBarHelper(getActionBar(),
				getResources().getDrawable(R.drawable.actionbar_bg));
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.actionbar_bg));
		Log.i("test", "Successful Test");
		getSupportFragmentManager().beginTransaction()
				.add(R.id.content_frame, new HomePageFragment()).commit();

		setupDrawer();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	private void setupDrawer() {
		// TODO Auto-generated method stub

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerAdapter = new DrawerListAdapter(getApplicationContext());
		drawerList.setAdapter(drawerAdapter);
		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				mActionBarHelper = new FadingActionBarHelper(getActionBar(),
						getResources().getDrawable(R.drawable.actionbar_bg));
				switch (position) {
				case 0:
					MainActivity.this.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame, new ProfileFragment())
							.commit();
					break;
				case 1:
					MainActivity.this
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame, new HomePageFragment())
							.commit();
					break;
				case 2:
					MainActivity.this
							.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame,
									new MovieListFragment()).commit();
					break;
				case 3:
					MainActivity.this.getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.content_frame, new TVListFragment())
							.commit();
					break;
				}
				MainActivity.this.getSupportFragmentManager()
						.popBackStackImmediate("testing",
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
				if (position != 0)
					drawerList.setItemChecked(position, true);
				drawerLayout.closeDrawers();
			}
		});

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		drawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {

			@Override
			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
//				super.onDrawerClosed(view);
				// this.setDrawerIndicatorEnabled(false);
				invalidateOptionsMenu();
			}

			@Override
			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
//				super.onDrawerClosed(drawerView);
				// this.setDrawerIndicatorEnabled(true);
				invalidateOptionsMenu();
			}
		};
		
		drawerLayout.setDrawerListener(mDrawerToggle);
	}

	public FadingActionBarHelper getFadingActionBarHelper() {
		// TODO Auto-generated method stub
		return mActionBarHelper;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}
}
