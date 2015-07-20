package com.example.billhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	
	private ListViewDemo tempList;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		Fragment fragment;
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch(position) {
		  default:
		  case 0:
			  fragment = PlaceholderFragment.newInstance(0);
			  break;
		  case 1:
			  fragment = PlaceholderFragment.newInstance(1);
			  break;
		  case 2:
			  fragment = PlaceholderFragment.newInstance(2);
			  break;
		  case 3:
			  fragment = PlaceholderFragment.newInstance(3);
			  break;
		}
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();
	}
	


	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = getString(R.string.title_section1);
			break;
		case 1:
			mTitle = getString(R.string.title_section2);
			break;
		case 2:
			mTitle = getString(R.string.title_section3);
			break;
		case 3:
			mTitle = getString(R.string.title_section4);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
		return super.onOptionsItemSelected(item);
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = null;
			rootView = inflater.inflate(R.layout.fragment_main, container, false);
	
			
			switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
			   case 0:
					rootView = inflater.inflate(R.layout.fragment_main, container, false);
					break;
			   case 1:
					rootView = inflater.inflate(R.layout.how_much_tip, container, false);
					break;
			   case 2:
					rootView = inflater.inflate(R.layout.did_i_tip, container, false);
					break;
			   case 3:
					rootView = inflater.inflate(R.layout.split_bill, container, false);
					break;
				   
			}
			return rootView;
		}
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}


	}

	// MY OWN CODE BEGINS HERE
	
	//THIS IS FOR HOW MUCH IS MY TIP
	
	public void howMuchTipCalculation(View view) {
		
	  //setting up some variables for usage, these are the edit texts	
      EditText pre_total = (EditText) findViewById(R.id.how_much_total);
      EditText tip_percent = (EditText) findViewById(R.id.tipEnter);	
      
      //setting up the display variable.
      TextView final_total = (TextView) findViewById(R.id.finalTip);
      
      try {
      
    	//I take the string values from the EditText and convert them to strings here.
        String pre_temp = pre_total.getText().toString();
        String tip_temp = tip_percent.getText().toString();
        
        double pre_dub = Double.parseDouble(pre_temp);
        double tip_dub = Double.parseDouble(tip_temp);
    	
        
        //turning the tip into a decimal
        double convert_to_dec = (tip_dub / 100);
        
        double part_one = pre_dub * convert_to_dec;
        
        double part_two = pre_dub + part_one;
        
        int dollars = (int) (part_two);
        double frac = part_two - dollars;
        int cents = (int)((frac * 100)+0.5);
        
        String answer1 = String.valueOf(dollars);
        String answer2 = String.valueOf(cents);
        
        final_total.setText("You should pay " + answer1 +'.' + answer2);
    	  
      } catch(Exception e) {
    	  AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("There is an error");
			builder.setMessage("Please check if your Total Bill and Tip are filled in");
			builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					System.out.println("bruh");
			}
		});
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.show();
      }
		
		
	}
	
	public void figureOutMyTip(View view) {
		
		  //setting up some variables for usage, these are the edit texts	
	      EditText total_w_tip = (EditText) findViewById(R.id.totalWithTip);
	      EditText total_wo_tip = (EditText) findViewById(R.id.totalWithoutTipVal);	
	      
	      //setting up the display variable.
	      TextView final_total = (TextView) findViewById(R.id.percentOfTip);
	      
	      try {
	    	  
	         //I take the string values from the EditText and convert them to strings here.
	          String wtip_temp = total_w_tip.getText().toString();
	          String wotip_temp = total_wo_tip.getText().toString();
	          
	          double wtip_dub = Double.parseDouble(wtip_temp);
	          double wotip_dub = Double.parseDouble(wotip_temp);
	          
	          double step_one = (wtip_dub / wotip_dub);
	        	  
	          double step_two = step_one - 1.0;
	          
	          double final_dub = step_two * 100;
	          
	          int final_result = (int) Math.round(final_dub);
	      	
	          String answer = String.valueOf(final_result);
	          
	          final_total.setText("You tipped            " + answer);
	    	  
	    	  
	      } catch (Exception e) {
	    	  AlertDialog.Builder builder = new AlertDialog.Builder(this); 
				builder.setTitle("There is an error");
				builder.setMessage("Please check if your Before Tip and After Tip are filled in");
				builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("bruh");
				}
			});
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.show();
	      }
	      
		
	}
	
	public void splitOff(View view) {
		

		  //setting up some variables for usage, these are the edit texts	
	      EditText myPart = (EditText) findViewById(R.id.myPartOfBillEdit);
	      EditText myTax = (EditText) findViewById(R.id.enterTip);	
	      EditText myTip = (EditText) findViewById(R.id.tipInsertion);
	      
	      
	      //setting up the display variable.
	      TextView final_total = (TextView) findViewById(R.id.splitResult);
		
	      
	      try {
	    	  
		         //I take the string values from the EditText and convert them to strings here.
		          String mP_temp = myPart.getText().toString();
		          String mTa_temp = myTax.getText().toString();
		          String mTi_temp = myTip.getText().toString();
		          
		          double mP_dub = Double.parseDouble(mP_temp);
		          double mTa_dub = Double.parseDouble(mTa_temp);
		          double mTi_dub = Double.parseDouble(mTi_temp);
		          
		          double step_one = (mTa_dub / 100 );
		        	  
		          double step_two = (step_one * mP_dub);
		          
		          double step_three = (mTi_dub / 100);
		          
		          double step_four = (step_three * mP_dub);
		          
		          double step_five = mP_dub + step_two + step_four;
		          
		          int dollars = (int) (step_five);
		          double frac = step_five - dollars;
		          int cents = (int)((frac * 100)+0.5);
		          
		          String answer1 = String.valueOf(dollars);
		          String answer2 = String.valueOf(cents);
		      
		          
		          final_total.setText("You should pay $" + answer1 + "." + answer2);
		    	  
		    	  
		      } catch (Exception e) {
		    	  AlertDialog.Builder builder = new AlertDialog.Builder(this); 
					builder.setTitle("There is an error");
					builder.setMessage("Please check if everything is filled in before you click Split!");
					builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							System.out.println("bruh");
					}
				});
					builder.setIcon(android.R.drawable.ic_dialog_alert);
					builder.show();
		      }
	      
	}
	
	// this method helps the program go to the right screen.
	public void buttonInteraction(View view) {
		switch (view.getId()) {
		case R.id.doITipButton:
			mTitle = getString(R.string.title_section2);
			onNavigationDrawerItemSelected(1);
			break;
		case R.id.didITipButton:
			mTitle = getString(R.string.title_section3);
		    onNavigationDrawerItemSelected(2);
			break;
		case R.id.splitButton:
			mTitle = getString(R.string.title_section4);
		    onNavigationDrawerItemSelected(3);
			break;
		}
	}
	
	
	

	   
}
