package com.example.tic;

import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class View3Activity extends Activity  implements Observer  {
	
	Model model;
	Button button;
	TextView textview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEMO", "View3Activity: onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view3);
		model = MyApplication.model;
		

		button = (Button) findViewById(R.id.activity_view3_button);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				model.clearString();
			}
		});
		// create model
		
		// add this activity to the model's list of observers
		model.addObserver(this);

		// initialize views
		model.initObservers();
		
	}
	

	
	// update the display 
	@Override
	public void update(Observable observable, Object data) {
	    Log.d("DEMO", "update View2");
		textview = (TextView) findViewById(R.id.activity_view3_textview);
		textview.setText(model.getTString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view3, menu);
		
		
		// get the menu item to add a listener
		MenuItem item = menu.findItem(R.id.menu_view3_gotoview1);
		
		// get the context (must be final to reference inside anonymous object)
		final View3Activity context = this;


		// create the menu item controller to change views
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {				
				//Intent intent = context.getIntent();
				//intent.putExtra("TURN", turn);
				//context.setResult(RESULT_OK, intent)
				
				Intent intent = new Intent(context, MainActivity.class);
				// start the activity
                startActivity(intent);
				
				finish();
				
				return false;
			}
		});
		return true;

	}
	


}
