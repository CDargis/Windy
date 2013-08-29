package com.example.windy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String apiKey = "cyevjyeyjj4j46enkv3bkj9c";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void goButtonClick(View view) {
		ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
	 	pb.setVisibility(ProgressBar.VISIBLE);
	 	((TextView)findViewById(R.id.windSpeed)).setVisibility(View.INVISIBLE);
	 	EditText edit = (EditText)findViewById(R.id.input);
	 	this.makeRequest(edit.getText().toString().replaceAll(" ", "%20"));
	}
	
	public void makeRequest(String city) {
		String request = "http://api.worldweatheronline.com/free/v1/weather.ashx?key=" + apiKey
				+ "&q=" + city + "&format=json";
		new DataFetcherTask(this).execute(request);
	}
	
}
