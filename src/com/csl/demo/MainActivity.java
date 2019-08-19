package com.csl.demo;

import com.csl.demo.R;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.startButtonsActivity(R.id.A, MainActivity.this, ActOne.class);
		this.startButtonsActivity(R.id.B, MainActivity.this, ActTwo.class);
		this.startButtonsActivity(R.id.C, MainActivity.this, ActThree.class);
		this.startButtonsActivity(R.id.D, MainActivity.this, ActFour.class);
		this.startButtonsActivity(R.id.E, MainActivity.this, ActFive.class);
	}
}
