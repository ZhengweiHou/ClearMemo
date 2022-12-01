package com.vector.clearmemo.ui;

import com.example.firstandroidprojectclearmemo.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		SharedPreferences sharedPreferences = getSharedPreferences(
				"clearmemo_message", MODE_APPEND);

		Boolean isfirstinsert = sharedPreferences.getBoolean("isfirstinsert",
				true);

		if (isfirstinsert) {

			startActivity(new Intent(WelcomeActivity.this,
					GuideActivity.class));
			this.finish();
			return;
		}
		
		setContentView(R.layout.activity_welcome);
		skipActivity(3);
	}
	private void skipActivity(int min) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// Intent intent = new Intent(WelcomeActivity.this,
				// MainActivity.class);
				Intent intent = new Intent(WelcomeActivity.this,
						LoginActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}, 1000 * min);
	}
}
