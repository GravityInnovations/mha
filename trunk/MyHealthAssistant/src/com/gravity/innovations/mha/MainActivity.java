package com.gravity.innovations.mha;

import com.gravity.innovation.mha.fragments.ChatFragmentTab;
import com.gravity.innovation.mha.fragments.HistoryFragmentTab;
import com.gravity.innovation.mha.fragments.PedoFragmentTab;
import com.gravity.innovation.mha.fragments.HeartrateFragmentTab;

import java.util.Locale;







import org.json.JSONObject;

import com.gravity.innovations.mha.ChatActivity;
import com.gravity.innovations.mha.MainActivity;
import com.gravity.innovations.mha.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener, Common.Callbacks.HttpCallback {
	HistoryFragmentTab historyFragmentTab;// = new HistoryFragmentTab();
	ChatFragmentTab chatFragmentTab;// = new ChatFragmentTab();
	PedoFragmentTab pedoFragmentTab;// = new PedoFragmentTab();
	HeartrateFragmentTab heartrateFragmentTab;// = new HeartrateFragmentTab();
	SearchFragmentTab searchFragmentTab;
	HeartrateFragmentTab mc= new HeartrateFragmentTab();
	int HR=102;
	String s="03445008891";
	String m="patient is critical";
	
	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	String UserId;
	public SharedPreferences sp;
	public SharedPreferences.Editor spe;
	

	@Override
	

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = this.getSharedPreferences("MHASP", MODE_PRIVATE);
		spe = sp.edit();
		try{
		
		historyFragmentTab = new HistoryFragmentTab();
		historyFragmentTab.SetUp(this);
		chatFragmentTab = new ChatFragmentTab();
		//get userid from sp and save in variable userId
		chatFragmentTab.setUp(this, sp.getString("userid", ""));
		pedoFragmentTab = new PedoFragmentTab();
		heartrateFragmentTab = new HeartrateFragmentTab();
		searchFragmentTab = new SearchFragmentTab();
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					
			
					}
				});
		
	
	

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		}catch(Exception e){
			Log.e("Main Activity onCreate"," Error ");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if (id == R.id.action_chat){
			Intent i = new Intent(MainActivity.this , ChatActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			//Locale  =Locale.getDefault();
			switch (position){
			case 0:
				return (Fragment)historyFragmentTab;
			case 1:
				return (Fragment)chatFragmentTab;
			case 2:
				return (Fragment)pedoFragmentTab;
			case 3:
			    return (Fragment)heartrateFragmentTab;
			case 4:
				return (Fragment)searchFragmentTab;
			}
			
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}	
	@Override
	public void httpResult(JSONObject data, int RequestCode, int ResultCode) {
		switch(RequestCode)
		{
		case 1:
			if(ResultCode == 0)
			historyFragmentTab.pushdata(data);
			break;
		case 2:
			chatFragmentTab.httpResult(data, RequestCode, ResultCode);
			break;
		case 3:
			chatFragmentTab.httpResult(data, RequestCode, ResultCode);
			break;
		}
	}

}
