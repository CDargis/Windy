package com.example.windy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DataFetcherTask extends AsyncTask<String, Void, JSONObject> {

	Activity activity;
	
	DataFetcherTask(Activity a) {
		this.activity = a;
	}
	
	@Override
	protected JSONObject doInBackground(String... arg0) {
		return Json.getJson(arg0[0]);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		ProgressBar pb = (ProgressBar)activity.findViewById(R.id.progressBar);
	 	pb.setVisibility(ProgressBar.INVISIBLE);
	 	try {
	 		JSONArray array = result.getJSONObject("data").getJSONArray("weather");
			((TextView)activity.findViewById(R.id.windSpeed)).setText(array.getJSONObject(0).getString("windspeedMiles") + "\nmph");
			((TextView)activity.findViewById(R.id.windSpeed)).setVisibility(View.VISIBLE);
			int windDirectionDegree = array.getJSONObject(0).getInt("winddirDegree");
			this.rotateImage(windDirectionDegree);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("json", result.toString());
	}
	
	private void rotateImage(int deg) {
		ImageView view = (ImageView)activity.findViewById(R.id.img_arrow);
		Matrix matrix = new Matrix();
		view.setScaleType(ScaleType.MATRIX);
		matrix.postRotate(deg, view.getDrawable().getBounds().width() / 2, 
				view.getDrawable().getBounds().height() / 2);
		view.setImageMatrix(matrix);
	}
}
