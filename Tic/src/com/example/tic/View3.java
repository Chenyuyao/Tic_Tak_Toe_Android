package com.example.tic;

import java.util.Observable;
import java.util.Observer;


import android.content.Context;
import android.util.*;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout;

public class View3 extends LinearLayout implements Observer {
	
	private Model model;
	private RadioGroup radio;
	private RadioButton radioButtonX;
	private RadioButton radioButtonO;

	public View3(Context context, Model m) {
		super(context);
		
	    Log.d("DEMO", "View3 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view3, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

		// get a reference to widgets to manipulate on update
		radio = (RadioGroup)findViewById(R.id.view3_radioGroup1);
		// create a controller to increment counter when clicked
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("DEMO", "update View3");
	    radio = (RadioGroup)findViewById(R.id.view3_radioGroup1);
	    radioButtonX = (RadioButton)findViewById(R.id.view3_radio_x);
	    radioButtonO = (RadioButton)findViewById(R.id.view3_radio_o);
	    if (model.getName(1) != "") {
	    	radioButtonX.setText("X  ->  " + model.getName(1));
	    } else {
	    	radioButtonX.setText("X");
	    }
	    if (model.getName(2) != "") {
	    	radioButtonO.setText("O  ->  " + model.getName(2));
	    } else {
	    	radioButtonO.setText("O");
	    }
	    
	    if (model.isGaming() == false && model.getTurn() == 0) {
	    	radio.clearCheck();
	    }
	    
	    else if (model.isWin() != 0 || model.getTie() == true) {
	    	radio.clearCheck();
	    }
	    
		else {
			int turn = model.getTurn();
			if (turn == 1) {
				radio.check(R.id.view3_radio_x);
			} else {
				radio.check(R.id.view3_radio_o);
			}			
		}
	    radioButtonX.setEnabled(false);
		radioButtonO.setEnabled(false);
	}
}
