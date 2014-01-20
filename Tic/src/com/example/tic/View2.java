package com.example.tic;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View2 extends LinearLayout implements Observer {
	
	private Model model;
	private Button newGameButton;
	private TextView textview;

	public View2(Context context, Model m) {
		super(context);
		
	    Log.d("DEMO", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view2, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

		// get a reference to widgets to manipulate on update
		newGameButton = (Button)findViewById(R.id.view2_button);
		textview = (TextView)findViewById(R.id.view2_textview);
		// create a controller to increment counter when clicked
		newGameButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.restart();
			}
		});
		
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("DEMO", "update View2");
	    textview = (TextView)findViewById(R.id.view2_textview);
	    if (model.isGaming() == false) {
			textview.setText( "Please select player" );
		}
		else if (model.isWin() != 0 ) {
			textview.setText( ( (model.getTurn() == 1)?"O":"X" )+ " wons");
		}
		else if (model.illegal == true) {
			textview.setText( "Illegal move");
			model.illegal = false;
		}
		else if (model.getTie() == true) {
			textview.setText( "Game over, no winner");
		}
		else if (model.getThisStep() == 0 ) {
			textview.setText( "Make first move");
		}	
		else if (model.getThisStep() > 0 ) {
			textview.setText( "Move "+ model.getThisStep());
		}
		
	}
}
