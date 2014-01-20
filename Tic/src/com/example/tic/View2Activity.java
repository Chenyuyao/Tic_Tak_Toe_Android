package com.example.tic;

import java.util.Observable;
import java.util.Observer;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;

public class View2Activity extends Activity  implements Observer  {
	
	int turn;
	Model model;
	EditText nameField1;
	EditText nameField2;
	RadioGroup radio;
	String name1;
	String name2;
	Button button;
	Button buttont;
	RadioButton r1;
	RadioButton r2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEMO", "View2Activity: onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view2);
		model = MyApplication.model;
		
		nameField1 = (EditText) findViewById(R.id.editText1);
		nameField2 = (EditText) findViewById(R.id.editText2);
		radio = (RadioGroup) findViewById(R.id.radioGroup1);
		button = (Button) findViewById(R.id.activity_2_button);
		buttont= (Button) findViewById(R.id.activity_2_tbutton);
		r1 = (RadioButton)findViewById(R.id.radio1);
	    r2 = (RadioButton)findViewById(R.id.radio0);
		
		nameField1.setText(model.getName(1));
		nameField2.setText(model.getName(2));
		
		if (model.isGaming() == true) {
			r1.setEnabled(false);
			r2.setEnabled(false);
			nameField1.setEnabled(false);
			nameField2.setEnabled(false);
			button.setEnabled(false);
		}
		
		radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
	    {
	        @Override
	        public void onCheckedChanged(RadioGroup group, int checkedId) 
	        {
	        	if (checkedId == getResources().getIdentifier("view3_radio_x", "id", "com.example.tic")) {
					model.xWantToStart();
					turn = 1;
				}
				else {
					model.oWantToStart();
					turn = -1;
				}
	        }
	    });
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				name1 = nameField1.getText().toString();
				name2 = nameField2.getText().toString();
				model.inputName(name1, 1);
				model.inputName(name2, 2);
			}
		});
		
		final Context context = this;
		buttont.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				
				Intent intent = new Intent(context, View3Activity.class);
                startActivity(intent); 
                finish();
                // we're done with this activity ...
			}
		});
		// create model
		
		// add this activity to the model's list of observers
		model.addObserver(this);

		// initialize views
		model.initObservers();
		
	}
	
/*	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    turn = extras.getInt("TURN");
		}
		
		update();

	}*/
	
	// update the display 
	@Override
	public void update(Observable observable, Object data) {
	    Log.d("DEMO", "update View2");
	    nameField1 = (EditText) findViewById(R.id.editText1);
		nameField2 = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view2, menu);
		
		
		// get the menu item to add a listener
		MenuItem item = menu.findItem(R.id.menu_view2_gotoview1);
		
		// get the context (must be final to reference inside anonymous object)
		final View2Activity context = this;
	
		
		nameField1 = (EditText) findViewById(R.id.editText1);
		nameField2 = (EditText) findViewById(R.id.editText2);

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
