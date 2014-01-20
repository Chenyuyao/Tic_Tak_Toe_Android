package com.example.tic;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int turn, thisStep;
	public boolean gaming,illegal,tie,tourView;
	public int[] fields = new int[9];
	public int w1,w2,w3;
	public int owin, xwin;	
	private String name1;
	private String name2;
	private String tournamentString;
	
	Model() {
		Log.d("DEMO", "Model: create model");
		for (int i = 0; i < 9; i++) {
			fields[i] = 0;
		}
		turn = 0;
		w1 = -1;w2 = -1;w3 = -1;
		owin = 0;
		xwin = 0;
		name1 = "";
		name2 = "";
		tournamentString = "";
	}

	public void start() {
		gaming = true;
		thisStep = 0;
		tie = false;
		w1 = -1;w2 = -1;w3 = -1;
		setChanged();
		notifyObservers();
	}

	public void restart() {
		for (int i = 0; i < 9; i++) {
			fields[i] = 0;
		}
		if (turn == -1 && tie == false) xwin++;
		if (turn == 1 && tie == false) owin++;

		constructString();
		
		turn = 0;
		gaming = false;
		thisStep = 0;
		tie = false;
		w1 = -1;w2 = -1;w3 = -1;
		setChanged();
		notifyObservers();
	}	

	private void constructString(){
		if (turn == -1 && tie == false) {
			if (name1 != ""){
				tournamentString += "Player X "+ name1+" won. Total won " + xwin + "\n";
			}
			else {
				tournamentString += "Player X win. Total won " + xwin +"\n";
			}
		}
		
		else if (turn == 1 && tie == false) {
			if (name2 != ""){
				tournamentString += "Player O "+ name2+" won. Total won " + owin +"\n";
			}
			else {
				tournamentString += "Player O win. Total won " + owin +"\n";
			}
		}
		
		else if (tie == true) {
			tournamentString += "Tie\n";
		}
	}
	
	public String getTString(){
		return tournamentString;
	}
	
	public void clearString(){
		tournamentString = "";
		setChanged();
		notifyObservers();
	}
	
	public void nextButton(int i) {
		if (fields[i] == 0) {
			fields[i] = turn;
			turn = turn * -1;
			thisStep++;
		}
		else illegal = true;

		if (isWin() == 0 && thisStep == 9) {
			tie = true;
		}
		setChanged();
		notifyObservers();
	}

	public int isWin() {
		if (fields[0] == fields[1] && fields[1] == fields[2] && fields[0] != 0) {
			w1 = 0;w2 = 1;w3 = 2;
			return fields[0];
		}
		if (fields[3] == fields[4] && fields[4] == fields[5] && fields[3] != 0) {
			w1 = 3;w2 = 4;w3 = 5;
			return fields[3];
		}
		if (fields[6] == fields[7] && fields[7] == fields[8] && fields[6] != 0) {
			w1 = 6;w2 = 7;w3 = 8;
			return fields[6];
		}
		if (fields[0] == fields[3] && fields[3] == fields[6] && fields[0] != 0) {
			w1 = 0;w2 = 3;w3 = 6;
			return fields[0];
		}
		if (fields[1] == fields[4] && fields[4] == fields[7] && fields[1] != 0) {
			w1 = 1;w2 = 4;w3 = 7;
			return fields[1];
		}
		if (fields[2] == fields[5] && fields[5] == fields[8] && fields[2] != 0) {
			w1 = 2;w2 = 5;w3 = 8;
			return fields[2];
		}
		if (fields[0] == fields[4] && fields[4] == fields[8] && fields[0] != 0) {
			w1 = 0;w2 = 4;w3 = 8;
			return fields[0];
		}
		if (fields[2] == fields[4] && fields[4] == fields[6] && fields[2] != 0) {
			w1 = 2;w2 = 4;w3 = 6;
			return fields[2];
		}
		else return 0;
	}

	public int getTurn() {
		return turn;
	}

	public int getThisStep() {
		return thisStep;
	}

	public void xWantToStart() {
		turn = 1;
		Log.d("DEMO", "Model: X start");
		start();
	}

	public void oWantToStart() {
		turn = -1;
		Log.d("DEMO", "Model: O start");
		start();
	}

	public void displayTourView(boolean b) {
		tourView = b;
		setChanged();
		notifyObservers();
	}
	
	public void inputName(String name, int i) {
		if (i == 1) {
			name1 = name;
			Log.d("DEMO", "Model: set Name1 " +name );
		}
		else {
			name2 = name;
			Log.d("DEMO", "Model: set Name2 " +name );
		}
	}

	public String getName(int i) {
		if (i == 1) {
			Log.d("DEMO", "Model: set Name " +name1 );
			return name1;
		}
		else {
			Log.d("DEMO", "Model: set Name " +name2 );
			return name2;
		}
	}

	public boolean isGaming() {
		return gaming;
	}
	
	public boolean getTie(){
		return tie;
	}
	
	public boolean isWinSpot(int i ){
		if (i == w1 || i == w2 || i == w3) {
			return true;
		}
		else return false;
	}
	// Observer methods
	
	// a helper to make it easier to initialize all observers
	public void initObservers() {
		setChanged();
		notifyObservers();
	}
	
	
	@Override
	public void addObserver(Observer observer) {
		Log.d("DEMO", "Model: Observer added");
		super.addObserver(observer);
	}
	
	@Override
	public synchronized void deleteObserver(Observer observer) {
		Log.d("DEMO", "Model: Observer deleted");
		super.deleteObserver(observer);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void notifyObservers() {
		Log.d("DEMO", "Model: Observers notified");
		super.notifyObservers();
	}

}