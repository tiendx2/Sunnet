package com.example.bai41;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class GraphicLevel2Activity extends Activity {

	public static float a, b, c;
	public static int screenWidth, screenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Lay Tham so tu InputForm
		Intent callerIntent = getIntent();
		// Lay bundle duoc gui tu InputForm
		Bundle packageFromCaller = callerIntent.getBundleExtra("ThamSoPT");
		// Lay cac tham so
		a = packageFromCaller.getFloat("NA");
		b = packageFromCaller.getFloat("NB");
		c = packageFromCaller.getFloat("NC");

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		View graphicLevel2 = new GraphicLevel2View(this);
		setContentView(graphicLevel2);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
