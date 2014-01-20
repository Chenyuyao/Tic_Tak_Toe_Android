package com.example.tic;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class View1 extends LinearLayout implements Observer {
	
	private Model model;

	public View1(Context context, Model m) {
		super(context);
		
		View.inflate(context, R.layout.view1, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);


		class indexActionListener implements OnClickListener{
	   		public final int index;
	    		indexActionListener(int i){
	        		super();
	        		this.index = i;
	   		}
			public void onClick(View v) {
				model.nextButton(index);
			}
		}
		
		
	
		for (int i =0; i<9; i++) {	
			String buttonID = "button" + i ;
		    int resID = getResources().getIdentifier(buttonID, "id", "com.example.tic");
			Button b = ((Button) findViewById(resID));
			b.setOnClickListener(new indexActionListener(i) {});
		}

		
		// create a controller for the button
		
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
		String s = "";
		if (model.isGaming() == false ||  model.getTie() == true) {
			for (int i = 0; i < 9; i++) {
				String buttonID = "button" + i ;
			    int resID = getResources().getIdentifier(buttonID, "id", "com.example.tic");
				Button b = ((Button) findViewById(resID));
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				b.setText(s);
				b.setEnabled(false);
			}
		}
		else if (model.isWin() != 0) {
			for (int i = 0; i < 9; i++) {
				boolean isW = model.isWinSpot(i);
				String buttonID = "button" + i ;
			    int resID = getResources().getIdentifier(buttonID, "id", "com.example.tic");
				Button b = ((Button) findViewById(resID));
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				b.setText(s);
				b.setEnabled(false);
				if (isW) b.setTextColor(-65536);
			}
			
			
		}
		else {
			for (int i = 0; i < 9; i++) {
				String buttonID = "button" + i ;
			    int resID = getResources().getIdentifier(buttonID, "id", "com.example.tic");
				Button b = ((Button) findViewById(resID));
				int f = model.fields[i];				
				if (f == 1) s = "X";
				if (f == -1) s = "O";
				if (f == 0) s = "";
				b.setText(s);
				b.setEnabled(true);
			}

		}
	}
}
