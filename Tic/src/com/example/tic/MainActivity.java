package com.example.tic;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;


public class MainActivity extends Activity {
	Model model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Tic", "onCreate");

		// load the base UI (has places for the views)
		setContentView(R.layout.mainactivity);

		// create the views and add them to the main activity
/*		View1 view1 = new View1(this, model);
		ViewGroup v1 = (ViewGroup) findViewById(R.id.mainactivity_1);
		v1.addView(view1);

		View2 view2 = new View2(this, model);
		ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
		v2.addView(view2);
				
		View3 view3 = new View3(this, model);
		ViewGroup v3 = (ViewGroup) findViewById(R.id.mainactivity_3);
		v3.addView(view3);*/
		model = MyApplication.model;

		// initialize views
		model.initObservers();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		Log.d("Tic", "onPostCreate");
		// can only get widgets by id in onPostCreate for activity xml res

		// create the views and add them to the main activity
		View1 view1 = new View1(this, model);
		ViewGroup v1 = (ViewGroup) findViewById(R.id.mainactivity_1);
		v1.addView(view1);

		View2 view2 = new View2(this, model);
		ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
		v2.addView(view2);
		
		View3 view3 = new View3(this, model);
		ViewGroup v3 = (ViewGroup) findViewById(R.id.mainactivity_3);
		v3.addView(view3);

		// initialize views
		model.initObservers();

	}

	// save and restore state (need to do this to support orientation change)
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("Tic", "save state");
		outState.putInt("Turn", model.getTurn());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("Tic", "restore state");
		super.onRestoreInstanceState(savedInstanceState);

	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		// get the menu item to add a listener
		MenuItem item = menu.findItem(R.id.menu_view1_gotoview2);
		
		// get the context (must be final to reference inside anonymous object)
		final Context context = this;
		
		// create the menu item controller to change views
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// create intent to request the view2 activity to be shown
				Intent intent = new Intent(context, View2Activity.class);
				// start the activity
                startActivity(intent); 
                // we're done with this activity ...
                finish();
				return false;
			}
		});
		return true;
	}

}
